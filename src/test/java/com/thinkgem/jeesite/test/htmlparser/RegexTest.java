package com.thinkgem.jeesite.test.htmlparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tempstr = "00a111";
		Pattern p = Pattern.compile("[0-9\\.]+");
		Matcher m = p.matcher(tempstr);
		
	}

}
