package com.thinkgem.jeesite.test.quartz;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.StatefulJob;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class Main {
	public static void main(String[] args) {
		//add();
		deleteJob();
		//getAll();
	}

	@Test
	public static void add(){
		QrtzManager manager = new QrtzManager();
		Date runTime = evenMinuteDate(new Date());
		Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
		JobModel model1 = new JobModel("job1", "group1", ExampleJob.class, trigger);
		JobDataMap dataMap = new JobDataMap();
		dataMap.put("key", "job1");
		manager.deploy(model1, dataMap);
		System.out.println("deploy model1 at:" + new Date());
		
		QrtzManager manager2 = new QrtzManager();
		String expression = "0 */1 * * * ?";
		Trigger trigger2 = null;
		trigger2 = newTrigger().withIdentity("trigger2", "group2").withSchedule(cronSchedule(expression)).startNow().build();
		JobModel model2 = new JobModel("job2", "group2", ExampleJob.class, trigger2);
		JobDataMap dataMap2 = new JobDataMap();
		dataMap.put("key", "job2");
		manager2.deploy(model2, dataMap);
		System.out.println("deploy model2 at:" + new Date());
	}
	public static Date parse(String str) {
		DateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Test
	public static void deleteJob() {
		QrtzManager manager = new QrtzManager();
		JobKey jobkey = new JobKey("job2", "group2");
		TriggerKey triggerkey = new TriggerKey("trigger1", "group1");
		System.out.println(manager.unDeploy(jobkey, triggerkey));
	}
	
	@Test
	public static void getAll() {
		QrtzManager manager = new QrtzManager();
		manager.getAllJob();
	}
}