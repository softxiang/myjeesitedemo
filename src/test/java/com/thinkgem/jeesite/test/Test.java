package com.thinkgem.jeesite.test;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLClassLoader;

public class Test {

	public static void main(String[] args) {
		BigDecimal wanratetemp = new BigDecimal(0);
		BigDecimal totalrate = new BigDecimal(12.00);
		wanratetemp = totalrate.add(totalrate.divide(new BigDecimal(100)).multiply(new BigDecimal(10000)).divide(new BigDecimal(12)).multiply(new BigDecimal(6)));
		System.out.println(wanratetemp.intValue());
		
		/*Class clazz;
		try {
			System.err.println(Test.class.getClass().getClassLoader());
			clazz = Class.forName("com.thinkgem.jeesite.modules.p2p.p2pquartz.parser.List5262Parser");
			Method m = clazz.getDeclaredMethod("parser");
			m.invoke(clazz.newInstance(), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		
		/***
		 * 
		Test t = new Test();
		t.classLoad();
		
		 */
	}

	public void classLoad() {
		URLClassLoader c = (URLClassLoader) getClass().getClassLoader().getParent().getParent();
		try {
			URL jar = new URL("https://javaweb.org/jars.jar");
			Method m = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			m.setAccessible(true);
			m.invoke(c, new Object[] { jar });
			URL[] url = c.getURLs();
			for (URL u : url) {
				System.out.println(u);
			}
			Class<?> test = Class.forName("com.thinkgem.jeesite.modules.p2p.p2pquartz.parser.List5262Parser");
			Method method = test.getDeclaredMethod("parser");
			Object obj = method.invoke(test.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
