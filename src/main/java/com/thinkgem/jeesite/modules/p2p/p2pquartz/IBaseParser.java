package com.thinkgem.jeesite.modules.p2p.p2pquartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface IBaseParser extends Job{
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException;
}
