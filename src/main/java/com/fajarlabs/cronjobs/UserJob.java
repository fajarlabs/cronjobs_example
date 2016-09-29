package com.fajarlabs.cronjobs;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class UserJob implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
        /* Terima parameter dari user untuk di eksekusi disabled */
		/*
		SchedulerContext schedulerContext = null;
        try {
            schedulerContext = context.getScheduler().getContext();
        } catch (SchedulerException e1) {
            e1.printStackTrace();
        }*/
		
		String triggerName = context.getTrigger().getJobKey().getName();
		System.out.println("TEST "+triggerName);
		for (Map.Entry<String, DataPacket> entry : CronJob.stack.entrySet()) {
		    String key = (String)entry.getKey();
			DataPacket value = (DataPacket)entry.getValue();
			if(triggerName.equals(value.getKey())) {
				job(key,value);
			}
		}
	}
	
	/* eksekusi disini */
	public void job(String key, DataPacket value) {
		System.out.println("JOB "+key);
	}

}