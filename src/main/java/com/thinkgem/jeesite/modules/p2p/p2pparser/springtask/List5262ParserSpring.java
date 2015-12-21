package com.thinkgem.jeesite.modules.p2p.p2pparser.springtask;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

	@Scheduled(cron = "0 0/1 * * * ?")
	public void work() {
		log.info(getClass().getName() + "......start......");
		try {
			Cp2pSeries cp2pSeries = cp2pSeriesService.get(new Cp2pSeries(cp2pSeriesId));
			// {cssExp}#regexp#
			String listCSSExp = "";
			Pattern p = Pattern.compile("\\{(.*?)\\}#(.*?)#");
			//Matcher m = p.matcher(null != cp2pSeries.getListrootexp() ? cp2pSeries.getListrootexp().toString() : "");
			//可能存在html " < >被转义 需反转义
			Matcher m = p.matcher(StringEscapeUtils.unescapeHtml4(null != cp2pSeries.getListrootexp() ? cp2pSeries.getListrootexp().toString() : ""));
			if (m.find()) {
				listCSSExp = m.group(1).trim();
			}
			// 详细页URI前缀
			String detailURIPrefix = StringEscapeUtils.unescapeHtml4(null != cp2pSeries.getDetailprefix() ? cp2pSeries.getDetailprefix().toString() : "");
			// {cssExp}#regexp#
			String idCSSExp = "";
			String idRegExp = "";
			Pattern idp = Pattern.compile("\\{(.*?)\\}#(.*?)#");
			String detailidexp = null != cp2pSeries.getDetailidexp() ? cp2pSeries.getDetailidexp().toString(): "";
			Matcher idm = idp.matcher(detailidexp);
			if (idm.find()) {
				idCSSExp = idm.group(1).trim();
				idRegExp = idm.group(2).trim();
			}
			idRegExp = StringEscapeUtils.unescapeHtml4(idRegExp);
			if (StringUtils.isBlank(listCSSExp) || StringUtils.isBlank(detailURIPrefix) || StringUtils.isBlank(idCSSExp) || StringUtils.isBlank(idRegExp)) {
				throw new InvalidParameterException("参数错误！");
			}
			int pageMax = 1;// cp2pSeries.getPagemax();
			int curPage = 1;
			for (; curPage <= pageMax; curPage++) {
				StringBuffer uri = new StringBuffer(StringEscapeUtils.unescapeHtml4(null != cp2pSeries.getListuri() ? cp2pSeries.getListuri().toString() : ""));
				if (!uri.toString().endsWith("&")) {
					uri.append("&");
				}
				uri.append(cp2pSeries.getPageattr());
				uri.append("=");
				uri.append(curPage);
				Document doc = Jsoup.connect(uri.toString()).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36").get();
				// 数据tr列表
				Elements listtr = doc.select(listCSSExp);
				for (Element tr : listtr) {
					Elements idtds = tr.select(idCSSExp);
					Element idtd = null!=idtds&&idtds.size()>0?idtds.get(0):null;
					String idStrTemp = null!=idtd?idtd.toString().trim():"";
					String idStr = "";
					Pattern idptn = Pattern.compile(idRegExp);
					Matcher idmhr = idptn.matcher(idStrTemp);
					if (idmhr.find()) {
						idStr = idmhr.group(2).trim();
					}
					if(StringUtils.isBlank(idStr)){
						throw new InvalidParameterException("获取详细页ID元素错误！");
					}
					String detailurl = detailURIPrefix.replaceAll("\\$\\{id\\}", idStr);
					if (!StringUtils.isBlank(detailurl)) {
						Detail5262Parser detail5262Parser = new Detail5262Parser(idStr,detailurl,cp2pSeries,cp2pProductsService);
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
	private String sid;
	private String detailurl;
	private Cp2pSeries cp2pSeries;
	private Cp2pProductsService cp2pProductsService;

	public Detail5262Parser(String sid,String detailurl,Cp2pSeries cp2pSeries,Cp2pProductsService cp2pProductsService) {
		this.sid = sid;
		this.detailurl = detailurl;
		this.cp2pSeries = cp2pSeries;
		this.cp2pProductsService = cp2pProductsService;
	}

	@Override
	public void run() {
		log.info(getClass().getName() + "......start......");
		try {
			Document doc = Jsoup.connect(detailurl).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36").get();
			Cp2pProducts cp2pProducts = new Cp2pProducts();
			cp2pProducts.setSid(sid);
			cp2pProducts.setDetailuri(detailurl);
			StringBuffer cssExp = new StringBuffer();
			StringBuffer regExp = new StringBuffer();
			Pattern p = Pattern.compile("\\{(.*?)\\}#(.*?)#");
			// 合同编号
			Matcher m = p.matcher(StringEscapeUtils.unescapeHtml4(null != cp2pSeries.getSnumexp() ? cp2pSeries.getSnumexp().toString() : ""));
			if (m.find()) {
				cssExp = cssExp.append(StringUtils.isBlank(m.group(1))?"":m.group(1).trim());
				regExp = regExp.append(StringUtils.isBlank(m.group(2))?"":m.group(2).trim());
			}
			Elements snumEles = doc.select(cssExp.toString());
			String snum = "";
			if (1 == snumEles.size()) {
				Element snumEle = snumEles.get(0);
				String tempStr = null == snumEle ? "" : snumEle.toString().trim();
				Pattern tempp = Pattern.compile(regExp.toString());
				Matcher tempm = tempp.matcher(tempStr);
				if (tempm.find()) {
					snum = tempm.group(1).trim();
				}
			}
			if(StringUtils.isBlank(snum)){
				log.warn("未获取到合同编号：" + detailurl);
			}
			cp2pProducts.setSnum(snum);
			System.err.println(cp2pProducts.getSnum());
			//cp2pProductsService.save(cp2pProducts);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info(getClass().getName() + "......end......");
	}
}
