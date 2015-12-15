package com.thinkgem.jeesite.test.quartz;

import static org.quartz.JobBuilder.newJob;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QrtzManager {
	private static Scheduler scheduler;
	static {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-quartz.xml");
		scheduler = (StdScheduler) context.getBean("quartzScheduler");
	}

	public void standBy() {
		try {
			scheduler.standby();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	// add a job
	public void deploy(JobModel model, JobDataMap dataMap) {
		JobDetail jobDetail = newJob(model.getJobClass()).withIdentity(model.getJobName(), model.getGroup()).setJobData(dataMap).build();
		try {
			scheduler.scheduleJob(jobDetail, model.getTrigger());
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	// delete a job
	public boolean unDeploy(JobKey jobkey, TriggerKey triggerkey) {
		boolean result = false;
		if (null != jobkey) {
			try {
				scheduler.pauseJob(jobkey);
				scheduler.unscheduleJob(triggerkey);
				result = scheduler.deleteJob(jobkey);
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void getAllJob() {
		try {
			GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
			Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
			for (JobKey jobKey : jobKeys) {
				System.out.println("name:"+jobKey.getName()+"group:"+jobKey.getGroup());
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					TriggerKey triggerkey = trigger.getKey();
					System.out.println("name:"+triggerkey.getName()+"group:"+triggerkey.getGroup());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
