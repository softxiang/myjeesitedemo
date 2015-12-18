package com.thinkgem.jeesite.modules.p2p.p2pparser.springtask;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sun.tools.classfile.Annotation.element_value;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pProducts;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pSeries;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pProductsService;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pSeriesService;

@Service
@Lazy(false)
public class List5262ParserSpring {
	public final Logger log = Logger.getLogger(getClass());
	private final String cp2pSeriesId = "0bb8a96c2ac6404b9f086a8c5a6f85ef";
	@Autowired
	private Cp2pSeriesService cp2pSeriesService;
	@Autowired
	private Cp2pProductsService cp2pProductsService;

	@Scheduled(cron = "0 10 17 * * ?")
	public void work() {
		log.info(getClass().getName() + "......start......");
		try {
			Cp2pSeries cp2pSeries = cp2pSeriesService.get(new Cp2pSeries(cp2pSeriesId));
			// {cssExp}#regexp#
			String listCSSExp = "";
			Pattern p = Pattern.compile("\\{(.*?)\\}#(.*?)#");
			Matcher m = p.matcher(null != cp2pSeries.getListrootexp() ? cp2pSeries.getListrootexp().toString() : "");
			if (m.find()) {
				listCSSExp = m.group(1);
			}
			// 详细页URI前缀
			String detailURIPrefix = cp2pSeries.getDetailprefix();
			// {cssExp}#regexp#
			String idCSSExp = "";
			String idRegExp = "";
			Pattern idp = Pattern.compile("\\{(.*?)\\}#(.*?)#");
			Matcher idm = idp.matcher(null != cp2pSeries.getDetailidexp() ? cp2pSeries.getDetailidexp().toString() : "");
			if (m.find()) {
				idCSSExp = idm.group(1);
				idRegExp = idm.group(2);
			}
			if (StringUtils.isBlank(listCSSExp) || StringUtils.isBlank(detailURIPrefix) || StringUtils.isBlank(idCSSExp) || StringUtils.isBlank(idRegExp)) {
				throw new InvalidParameterException("参数错误！");
			}
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
				Elements listtr = doc.select(listCSSExp);
				for (Element tr : listtr) {
					Element idtd = tr.select(idCSSExp).get(0);
					
					if (!StringUtils.isBlank(product.getDetailuri())) {
						Detail5262Parser detail5262Parser = new Detail5262Parser(cp2pProductsService, product);
						Thread thread = new Thread(detail5262Parser);
						thread.start();
					}
				}
			}
		} catch (IOException e) {
			log.warn(getClass().getName() + "出错，原因：" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		log.info(getClass().getName() + "......end......");
	}
}

class Detail5262Parser implements Runnable {
	public final Logger log = Logger.getLogger(getClass());
	private Cp2pProducts cp2pProducts;
	private Cp2pProductsService cp2pProductsService;

	public Detail5262Parser(Cp2pProductsService cp2pProductsService, Cp2pProducts cp2pProducts) {
		this.cp2pProducts = cp2pProducts;
		this.cp2pProductsService = cp2pProductsService;
	}

	@Override
	public void run() {
		log.info(getClass().getName() + "......start......");
		try {
			Document doc = Jsoup.connect(cp2pProducts.getDetailuri()).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36").get();
			// 合同编号
			Elements htspans = doc.select(".detail-info .title span");
			if (1 == htspans.size()) {
				Element htspan = htspans.get(0);
				String htbh = null == htspan.text() ? "" : htspan.text().trim();
				htbh = htbh.substring(htbh.indexOf(":") + 1, htbh.length()).trim();
				cp2pProducts.setSnum(htbh);
			} else {
				log.warn(getClass().getName() + " 未获取到合同编号！" + cp2pProducts.getDetailuri());
			}
			cp2pProductsService.save(cp2pProducts);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info(getClass().getName() + "......end......");
	}
}
