package com.thinkgem.jeesite.test;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		String txt="{cssExp}#regexp#";
	    Pattern px = Pattern.compile("\\{(.*?)\\}#(.*?)#");
	    Matcher mx = px.matcher(txt);
	    if (mx.find())
	    {
	        String word1=mx.group(1);
	        System.out.println(mx.group(0)+"("+word1.toString()+")"+mx.group(2));
	    }
		String str = "（%[GD1108010110000]（（）*[GD1108010111100]*+-";
        Matcher m = Pattern.compile("\\[(.*?)\\]").matcher(str);
        while (m.find()) {
            System.out.println(m.group(1));
        }
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
