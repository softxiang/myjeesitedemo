package com.thinkgem.jeesite.modules.p2p.p2pquartz.parser;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pProducts;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pSeries;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pProductsService;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pSeriesService;

public class List5262Parser {
	public final Logger log = Logger.getLogger(getClass());
	@Autowired
	private Cp2pSeriesService cp2pSeriesService;
	@Autowired
	private Cp2pProductsService cp2pProductsService;
	private final String cp2pSeriesId = "f7ce85821fe24f008e12f90f0912c746";

	public void work() {
		log.info(getClass().getName() + "......start......");
		try {
			Cp2pSeries cp2pSeries = cp2pSeriesService.get(new Cp2pSeries(cp2pSeriesId));
			int pageMax = 1;// cp2pSeries.getPagemax();
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
					Cp2pProducts product = new Cp2pProducts();
					int tdindex = 0;
					Elements listtd = tr.select("td");
					for (Element td : listtd) {
						tdindex++;
						if (1 == tdindex) {
							// 标名称
							String detailurl = td.getElementsByTag("a").get(0).absUrl("href");
							String name = td.text();
							if (!StringUtils.isBlank(detailurl)) {
								Detail5262Parser detail5262Parser = new Detail5262Parser(detailurl);
								Thread thread = new Thread(detail5262Parser);
								thread.start();
							}
							product.setDetailuri(detailurl);
							product.setName(name);
						} else if (2 == tdindex) {
							// 年华利率
							String tempstr = td.text();
							tempstr = null == tempstr ? "" : tempstr.trim().replace("%", "");
							float yearpercent = Float.parseFloat(tempstr);
							product.setRate(new BigDecimal(yearpercent));
							product.setPlatformrate(new BigDecimal(0));
						} else if (3 == tdindex) {
							// 投资期限
							String tempstr = td.text();
							tempstr = null == tempstr ? "" : tempstr.trim();
							Pattern p = Pattern.compile("[0-9\\.]+");
							Matcher m = p.matcher(tempstr);
							int needtimeint = 0;
							while (m.find()) {
								needtimeint = Integer.parseInt(m.group().trim());
							}
							
							product.setTerm(tempstr);
							if (-1 != tempstr.indexOf("日") || -1 != tempstr.indexOf("天")) {
								product.setTermday(needtimeint);
								//needtimeint/365*product.get
							} else if (-1 != tempstr.indexOf("月")) {
								product.setTermmonth(needtimeint);
							} else if ("".equals(tempstr) && -1 != tempstr.indexOf("年")) {
								product.setTermyear(needtimeint);
							}
							if ((tempstr.indexOf("日") != -1 || tempstr.indexOf("天") != -1) && tempstr.indexOf("月") != -1 && tempstr.indexOf("年") != -1) {
								product.setTerm("日期获取错误");
							}
						} else if (4 == tdindex) {
							// 募集金额
							String tempstr = td.text();
							tempstr = null == tempstr ? "" : tempstr.trim();
							int jine = 0; // 数字金额
							Pattern p = Pattern.compile("[0-9\\.]+");
							Matcher m = p.matcher(tempstr);
							while (m.find()) {
								jine = Integer.parseInt(m.group().trim());
							}
							if (tempstr.indexOf("万") != -1) {
								jine = jine*10000;
							}
							product.setTotalmoney(new BigDecimal(jine));
						} else if (5 == tdindex) {
							// 进度

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

class Detail5262Parser implements Runnable {
	public final Logger log = Logger.getLogger(getClass());
	private String detailUrl;

	public Detail5262Parser(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	@Override
	public void run() {
		log.info(getClass().getName() + "......start......");
		try {
			Document doc = Jsoup.connect(detailUrl).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36").get();
			Elements listtr = doc.select("div.data-list tbody tr");
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info(getClass().getName() + "......end......");
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
}
