package com.fajarlabs.cronjobs;

import org.quartz.Scheduler;

public class DataPacket {
	private String key;
	private String val;
	private Scheduler sched;
	
	public DataPacket() {}
	
	public DataPacket(String key, String val, Scheduler sched) {
		super();
		this.key = key;
		this.val = val;
		this.sched = sched;
	}
	
	public Scheduler getSched() {
		return sched;
	}

	public void setSched(Scheduler sched) {
		this.sched = sched;
	}

	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	
	
}
