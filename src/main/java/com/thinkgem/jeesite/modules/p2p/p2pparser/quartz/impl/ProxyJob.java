package com.thinkgem.jeesite.modules.p2p.p2pparser.quartz.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pParserconfig;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pSeries;

public class ProxyJob {//implements Job {
	public final Logger log = Logger.getLogger(getClass());

//	@Override
//	public void execute(JobExecutionContext context) throws JobExecutionException {
//		Cp2pParserconfig cp2pParserconfig = (Cp2pParserconfig) context.getMergedJobDataMap().get("cp2pParserconfig");
//		Object object = null;
//		Class<?> clazz = null;
//		if (StringUtils.isNotBlank(cp2pParserconfig.getClassname())) {
//			try {
//				clazz = Class.forName(cp2pParserconfig.getClassname(),false,getClass().getClassLoader());
//				object = clazz.newInstance();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		if (object == null) {
//			log.error("任务 [" + cp2pParserconfig.getClassname() + "]---------------未启动成功，初始化类失败！！！");
//			return;
//		}
//		clazz = object.getClass();
//		Method method = null;
//		try {
//			method = clazz.getDeclaredMethod("parser",Cp2pSeries.class);
//		} catch (NoSuchMethodException e) {
//			log.error("任务 [" + cp2pParserconfig.getClassname() + "]---------------未启动成功，类中未找到parser方法！！！");
//		} catch (SecurityException e) {
//			e.printStackTrace();
//		}
//		if (method != null) {
//			try {
//				Cp2pSeries cp2pSeries = (Cp2pSeries) context.getMergedJobDataMap().get("cp2pSeries");
//				method.invoke(object,cp2pSeries);
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			} catch (IllegalArgumentException e) {
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				e.printStackTrace();
//			}
//		}
//		log.info("任务 [" + cp2pParserconfig.getClassname() + "]---------------启动成功");
//	}
}
