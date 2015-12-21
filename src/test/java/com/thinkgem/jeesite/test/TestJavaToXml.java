package com.thinkgem.jeesite.test;

import java.lang.reflect.Method;

import com.thinkgem.jeesite.modules.p2p.entity.Cp2pSeries;

public class TestJavaToXml {

	public static void main(String[] args) {
		// Cp2pSeries cp2pSeries = new Cp2pSeries();
		// cp2pSeries.setId("xxxxxx");
		// cp2pSeries.setSnumexp("aaaa");

		Class clazz;
		try {
			clazz = Class.forName("com.thinkgem.jeesite.modules.p2p.entity.Cp2pSeries");
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if(method.getName().startsWith("set")){
					method.getParameterTypes();
				}
			}
			//Method m = clazz.getDeclaredMethod("parser");
			//m.invoke(clazz.newInstance(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}