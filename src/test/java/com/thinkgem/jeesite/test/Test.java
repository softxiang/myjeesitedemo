package com.thinkgem.jeesite.test;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		// String str = "{td a}#(\\?|&)id=([^&?\"]*)#";
//		String str = "{td a}##";
//		Matcher m = Pattern.compile("\\{(.*?)\\}#(.*?)#").matcher(str);
//		if (m.find()) {
//			System.out.println(m.group(0) + ":" + m.group(1) + ":" + m.group(2));
//			System.out.println(m.groupCount());
//		} else {
//			System.out.println("没找到");
//		}

		String str1 = "http://www.5262.com/product/detail.html?id=1078\"";
		Pattern p1 = Pattern.compile("\\?id=(.*)\"");
		Matcher m1 = p1.matcher(str1);
		if (m1.find()) {
			System.out.println(m1.group(0)+ "-" + m1.group(1));// + ":" + m1.group(2));
			System.out.println(m1.groupCount());
		} else {
			System.out.println("没找到");
		}
		
		System.out.println(Pattern.matches("\\?id=(.*?)", "?id=aaaaa"));
		
		/*
		 * Class clazz; try {
		 * System.err.println(Test.class.getClass().getClassLoader()); clazz =
		 * Class.forName(
		 * "com.thinkgem.jeesite.modules.p2p.p2pquartz.parser.List5262Parser");
		 * Method m = clazz.getDeclaredMethod("parser");
		 * m.invoke(clazz.newInstance(), null); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		/***
		 * 
		 Test t = new Test(); t.classLoad();
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
