package com.thinkgem.jeesite.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestHarness {

	public static void main(String[] args) {
		while (true) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String str = null;
				System.out.println("Enter your regex: ");
				str = br.readLine();
				Pattern pattern = Pattern.compile(str);
				System.out.println("Enter input string to search:");
				str = br.readLine();
				Matcher matcher = pattern.matcher(str);
				boolean found = false;
				while (matcher.find()) {
					System.out.println(String.format("I found the text \"%s\" starting at index %d " + "and ending at index %d.%n", matcher.group(), matcher.start(), matcher.end()));
					found = true;
				}
				if (!found) {
					System.out.println("No match found.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}