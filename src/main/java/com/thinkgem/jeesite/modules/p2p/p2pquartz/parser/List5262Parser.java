package com.thinkgem.jeesite.modules.p2p.p2pquartz.parser;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.modules.p2p.entity.Cp2pParserconfig;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pProducts;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pSeries;
import com.thinkgem.jeesite.modules.p2p.p2pquartz.IBaseParser;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pSeriesService;

public class List5262Parser {
	public final Logger log = Logger.getLogger(getClass());
	@Autowired
	private Cp2pSeriesService cp2pSeriesService;
	private final String cp2pSeriesId="f7ce85821fe24f008e12f90f0912c746";
	public void work() {
		log.info(getClass().getName() + "......start......");
		Cp2pParserconfig cp2pParserconfig = (Cp2pParserconfig) context.getMergedJobDataMap().get("cp2pParserconfig");
		Cp2pSeries cp2pSeries = (Cp2pSeries) context.getMergedJobDataMap().get("cp2pSeries");
		try {
			int pageMax = cp2pSeries.getPagemax();
			int curPage = 1;
			for (; curPage <= pageMax; curPage++) {
				StringBuffer uri = new StringBuffer(cp2pSeries.getListuri());
				if (!cp2pSeries.getListuri().endsWith("&")) {
					uri.append("&");
				}
				uri.append(cp2pSeries.getPageattr());
				uri.append("=");
				uri.append(curPage);
				Document doc = Jsoup.connect(uri.toString()).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36").get();
				// 数据tr列表
				Elements listtr = doc.select("div.data-list tbody tr");
				for (Element tr : listtr) {
					Cp2pProducts pro = new Cp2pProducts();
					int tdindex = 0;
					String detailurl = "";
					String name = "";
					float yearpercent = 0.00f;
					int needtimeint = 0;
					String needtimeunit = "";
					Elements listtd = tr.select("td");
					for (Element td : listtd) {
						tdindex++;
						if (1 == tdindex) {
							// 标名称
							detailurl = td.getElementsByTag("a").get(0).absUrl("href");
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
					}
					System.out.println("name:" + name + "	url:" + detailurl + "	year:" + yearpercent);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info(getClass().getName() + "......end......");
	}
}
