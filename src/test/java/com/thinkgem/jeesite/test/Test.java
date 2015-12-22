package com.thinkgem.jeesite.test;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thinkgem.jeesite.common.utils.StringUtils;

public class Test {

	public static void main(String[] args) {
		String str = "cssexp:xxxx;";
		// String str = "{td a}##";
		Matcher m = Pattern.compile("cssexp:(.*?);regexp:(.*?);").matcher(str);
		if (m.find()) {
			System.out.println(m.group(0) + ":" + m.group(1) + ":" + m.group(2));
			System.out.println(m.groupCount());
		} else {
			System.out.println("没找到");
		}

		// String str1 = "http://www.5262.com/product/detail.html?id=1078\"";
		// Pattern p1 = Pattern.compile("[\\?|&]id=(.*)\"");
		String str1 = "?id=[.#内容#\"";
		// ([{\^-$|}])?*+.
		System.out.println(StringUtils.convertRegExp(str1));
		// aaaa();
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

	public static void aaaa() {
		// 被替换关键字的的数据源
		Map tokens = new HashMap();
		tokens.put("cat", "Garfield");
		tokens.put("beverage", "coffee");
		// 匹配类似velocity规则的字符串
		String template = "${cat} really needs some ${beverage}.";
		// 生成匹配模式的正则表达式
		String patternString = "\\$\\{(" + StringUtils.join(tokens.keySet(), "|") + ")\\}";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(template);
		// 两个方法：appendReplacement, appendTail
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, tokens.get(matcher.group(1)).toString());
		}
		matcher.appendTail(sb);
		// out: Garfield really needs some coffee.
		System.out.println(sb.toString());
		// 对于特殊含义字符"\","$"，使用Matcher.quoteReplacement消除特殊意义
		matcher.reset();
		// out: cat really needs some beverage.
		System.out.println(matcher.replaceAll("$1"));
		// out: $1 really needs some $1.
		System.out.println(matcher.replaceAll(Matcher.quoteReplacement("$1")));
		// 到得邮箱的前缀名。插一句，其实验证邮箱的正则多种多样，根据自己的需求写对应的正则才是王道
		String emailPattern = "^([a-z0-9_\\.\\-\\+]+)@([\\da-z\\.\\-]+)\\.([a-z\\.]{2,6})$";
		pattern = Pattern.compile(emailPattern);
		matcher = pattern.matcher("test@qq.com");
		// 验证是否邮箱
		System.out.println(matcher.find());
		// 得到@符号前的邮箱名 out: test
		System.out.println(matcher.replaceAll("$1"));
		// 获得匹配值
		String temp = "";
		pattern = Pattern.compile("android:(name|value)=\"(.+?)\"");
		matcher = pattern.matcher(temp);
		while (matcher.find()) {
			// out: appid, joy
			System.out.println(matcher.group(2));
		}
	}
}
