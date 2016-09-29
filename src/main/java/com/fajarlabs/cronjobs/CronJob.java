package com.fajarlabs.cronjobs;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CronJob {

	/* simulate Stack / database */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, DataPacket> stack = new HashMap();
	public static Scheduler scheduler = null;
	Logger logger = LoggerFactory.getLogger(CronJob.class);


	/*public static void main(String[] args) throws Exception {
		final CronJob c = new CronJob();
		c.buildCronJob("JOB_1","GROUP_1", "TRIGGER_1","0 0/1 * 1/1 * ? *", "Data Job 1");
		c.buildCronJob("JOB_2","GROUP_1", "TRIGGER_2","0 0/1 * 1/1 * ? *", "Data Job 2");
		c.buildCronJob("JOB_3","GROUP_1", "TRIGGER_3","0 0/1 * 1/1 * ? *", "Data Job 3");
	}*/
	
	public void buildCronJob(String jobName,String groupName, String triggerName,
			String cronExpression, String data) throws ParseException,
			SchedulerException {

		/*
		 * pengecekan apakah jobName sudah ada di stack, kalau belum maka
		 * masukkan kedalam antrian
		 */
		int flag = 0;		
		for (Map.Entry<String, DataPacket> entry : stack.entrySet()) {
		    String key = (String)entry.getKey();
		    @SuppressWarnings("unused")
			DataPacket value = (DataPacket)entry.getValue();
			if (key.equals(jobName))
				flag++;
		}

		/* Jika belum ada di antrian maka sisipkan */
		if (flag < 1) {
			JobDetail job = createJobDetail(jobName,groupName);
			Trigger trigger = createTrigger(triggerName, cronExpression,groupName);
			Scheduler scheduler = createScheduler(job, trigger, data);
			stack.put(jobName, new DataPacket(triggerName, data, scheduler));
		}
	}

	/* untuk membuat scheduler */
	public Scheduler createScheduler(JobDetail job, Trigger trigger,
			String data) throws SchedulerException {
		/* create scheduler singleton */
		if (scheduler == null) {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
		}
		/* Passing data via context disabled */
		/*scheduler.getContext().put("externalInstance", "{}");*/
		scheduler.scheduleJob(job, trigger);
		return scheduler;
	}
	
	/* Remove the trigger as well as the job. */
	public void deleteCronJob(String jobName, String groupName) {
		if(scheduler != null) {
			try {
				scheduler.deleteJob(new JobKey(jobName, groupName));
			} catch (SchedulerException e) {
				System.err.println(e.getMessage());
			}
		} 
	}
	
	/* Unschedule the Job (basically removes the trigger and hence the job) */
	public void unscheduleCronJob(String triggerName, String groupName) {
		try {
			scheduler.unscheduleJob(new TriggerKey(triggerName,groupName));
		} catch (SchedulerException e) {
			System.err.println(e.getMessage());
		}
	}

	/* Untuk membuat job detail */
	public JobDetail createJobDetail(String jobName, String groupName) {
		
		JobKey jobKey = new JobKey(jobName, groupName);
    	JobDetail job = JobBuilder.newJob(UserJob.class)
		.withIdentity(jobKey).build();
		return job;
	}

	/* Untuk membuat trigger */
	public Trigger createTrigger(String triggerName, String cronExpression, String groupName)
			throws ParseException {
    	Trigger trigger = TriggerBuilder
		.newTrigger()
		.withIdentity(triggerName, groupName)
		.withSchedule(
			CronScheduleBuilder.cronSchedule(cronExpression))
		.build();
    	
    	return trigger;
	}
}