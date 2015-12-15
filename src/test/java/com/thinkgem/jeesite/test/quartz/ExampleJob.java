package com.thinkgem.jeesite.test.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ExampleJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 这里输入任务处理的内容
		System.out.println("ExampleJob executing start... "+new Date());
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		System.out.println("value is " + dataMap.getString("key") +";	key:"+context.getJobDetail().getKey().toString());
		System.out.println("ExampleJob executing end...");
	}

}