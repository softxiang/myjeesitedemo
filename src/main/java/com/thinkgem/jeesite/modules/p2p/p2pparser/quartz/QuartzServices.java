package com.thinkgem.jeesite.modules.p2p.p2pparser.quartz;


import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
//import static org.quartz.JobBuilder.newJob;
//import org.quartz.CronExpression;
//import org.quartz.CronScheduleBuilder;
//import org.quartz.CronTrigger;
//import org.quartz.JobDetail;
//import org.quartz.JobKey;
//import org.quartz.Scheduler;
//import org.quartz.SchedulerException;
//import org.quartz.Trigger;
//import org.quartz.TriggerBuilder;
//import org.quartz.TriggerKey;
//import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.p2p.entity.Cp2pParserconfig;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pSeries;
import com.thinkgem.jeesite.modules.p2p.p2pparser.quartz.impl.ProxyJob;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pSeriesService;

@Service
@Transactional(readOnly = true)
public class QuartzServices {
	public final Logger log = Logger.getLogger(this.getClass());
	// @Autowired
	// private Scheduler quartzScheduler;
	@Autowired
	private Cp2pSeriesService cp2pSeriesService;

	public boolean addJob(final Cp2pParserconfig cp2pParserconfig) {
		boolean result = false;
		// if (!CronExpression.isValidExpression(cp2pParserconfig.getCronex()))
		// {
		// log.error("任务 [" + cp2pParserconfig.getClassname() +
		// "]---------------未添加成功，时间表达式错误！！！");
		// return result;
		// }
		// String jobName = "";
		// String jobGroup = "";
		// if (true) {
		// jobName =
		// cp2pParserconfig.getClassname().substring(cp2pParserconfig.getClassname().lastIndexOf("."));
		// }
		// if (true) {
		// jobGroup = jobName;
		// }
		// try {
		// TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		// CronTrigger trigger = (CronTrigger)
		// quartzScheduler.getTrigger(triggerKey);
		//
		// JobDetail jobDetail = newJob(ProxyJob.class).withIdentity(jobName,
		// jobGroup).build();
		// jobDetail.getJobDataMap().put("cp2pParserconfig", cp2pParserconfig);
		// Cp2pSeries cp2pSeries =
		// cp2pSeriesService.get(cp2pParserconfig.getCp2pSeries());
		// jobDetail.getJobDataMap().put("cp2pParserconfig", cp2pParserconfig);
		// jobDetail.getJobDataMap().put("cp2pSeries", cp2pSeries);
		//
		// CronScheduleBuilder scheduleBuilder =
		// CronScheduleBuilder.cronSchedule(cp2pParserconfig.getCronex());
		// trigger = TriggerBuilder.newTrigger().withIdentity(jobName,
		// jobGroup).withSchedule(scheduleBuilder).build();
		//
		// quartzScheduler.scheduleJob(jobDetail, trigger);
		// result = true;
		// log.error("任务 [" + cp2pParserconfig.getClassname() +
		// "]---------------添加成功");
		// } catch (SchedulerException e) {
		// log.error("任务 [" + cp2pParserconfig.getClassname() +
		// "]---------------未添加成功，消息:" + e.getLocalizedMessage());
		// e.printStackTrace();
		// }
		return result;
	}

	// delete a job
	public boolean deleteJob(Cp2pParserconfig cp2pParserconfig) {
		boolean result = false;
		// String jobName = "";
		// String jobGroup = "";
		// if (true) {
		// jobName =
		// cp2pParserconfig.getClassname().substring(cp2pParserconfig.getClassname().lastIndexOf("."));
		// }
		// if (true) {
		// jobGroup = jobName;
		// }
		// TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
		// JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
		// if (null != triggerKey) {
		// try {
		// quartzScheduler.pauseTrigger(triggerKey);
		// quartzScheduler.unscheduleJob(triggerKey);
		// result = quartzScheduler.deleteJob(jobKey);
		// log.info("任务 [" + cp2pParserconfig.getClassname() +
		// "]---------------删除成功！");
		// } catch (SchedulerException e) {
		// log.error("任务 [" + cp2pParserconfig.getClassname() +
		// "]---------------删除失败！消息：" + e.getLocalizedMessage());
		// e.printStackTrace();
		// }
		// }
		return result;
	}

	public void getAllJob() {
		// try {
		// GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		// Set<JobKey> jobKeys = quartzScheduler.getJobKeys(matcher);
		// for (JobKey jobKey : jobKeys) {
		// System.out.println("name:" + jobKey.getName() + "group:" +
		// jobKey.getGroup());
		// List<? extends Trigger> triggers =
		// quartzScheduler.getTriggersOfJob(jobKey);
		// for (Trigger trigger : triggers) {
		// TriggerKey triggerkey = trigger.getKey();
		// System.out.println("name:" + triggerkey.getName() + "group:" +
		// triggerkey.getGroup());
		// }
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}
}
