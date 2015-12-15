package com.thinkgem.jeesite.test.htmlparser;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		parserWDZJ();
	}

	public static void parser5262() {
		System.out.println("JsoupParser parser5262 start....");
		final String url = "http://www.5262.com/product/list.html?sortby=&direction=&page=2";
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			// 数据tr列表
			Elements listtr = doc.select("div.data-list tbody tr");
			for (Element tr : listtr) {
				// System.out.println("++++++++++++++++++++++++++");
				int tdindex = 0;
				String detalurl = "";
				String name = "";
				float yearpercent = 0.00f;
				int needtimeint = 0;
				String needtimeunit = "";
				Elements listtd = tr.select("td");
				for (Element td : listtd) {
					// System.out.println("++++++++");
					tdindex++;
					System.out.println(td.html());
					if (1 == tdindex) {
						// 标名称
						detalurl = td.getElementsByTag("a").get(0).absUrl("href");
						name = td.text();
					} else if (2 == tdindex) {
						String tempstr = td.text();
						tempstr = null == tempstr ? "" : tempstr.trim().replace("%", "");
						yearpercent = Float.parseFloat(tempstr);
					} else if (3 == tdindex) {
						String tempstr = td.text();
						tempstr = null == tempstr ? "" : tempstr.trim();
						Pattern p = Pattern.compile("[0-9\\.]+");
						Matcher m = p.matcher(tempstr);
						while (m.find()) {
							needtimeint = Integer.parseInt(m.group().trim());
						}
						needtimeunit = ((-1 != tempstr.indexOf("日") || -1 != tempstr.indexOf("天")) ? "天" : "");

					} else if (4 == tdindex) {

					}
					// System.out.println("--------");
				}
				System.out.println("name:" + name + "	url:" + detalurl + "	year:" + yearpercent);
				break;
				// System.out.println("--------------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("JsoupParser parser5262 end....");
	}

	public static void parserWDZJ() {
		System.out.println("JsoupParser parserWDZJ start....");
		final String url = "http://shuju.wdzj.com/platdata-1.html";
		Document doc;
		try {
			doc = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36").get();
			// 数据tr列表
			Elements listtr = doc.select("#tb_content tbody tr");
			for (Element tr : listtr) {
				// System.out.println("++++++++++++++++++++++++++");
				int tdindex = 0;
				Elements listtd = tr.select("td");
				for (Element td : listtd) {
					// System.out.println("++++++++");
					tdindex++;
					System.out.println(td.html());
					// System.out.println("--------");
				}
				break;
				// System.out.println("--------------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("JsoupParser parserWDZJ end....");
	}
}
