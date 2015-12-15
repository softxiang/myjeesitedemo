package com.thinkgem.jeesite.test.quartz;

import org.quartz.Job;
import org.quartz.Trigger;

public class JobModel {
	private String jobName;
	private String group;// 对于job，trigger相互绑定的，采用相同的group
	private Class<? extends Job> jobClass;
	private Trigger trigger;

	public JobModel() {
		super();
	}


	public JobModel(String jobName, String group, Class<? extends Job> jobClass, Trigger trigger) {
		super();
		this.jobName = jobName;
		this.group = group;
		this.jobClass = jobClass;
		this.trigger = trigger;
	}


	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Class<? extends Job> getJobClass() {
		return jobClass;
	}

	public void setJobClass(Class<? extends Job> jobClass) {
		this.jobClass = jobClass;
	}

	public Trigger getTrigger() {
		return trigger;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}
	
}