package com.thinkgem.jeesite.modules.p2p.p2pparser.springtask;

import java.io.IOException;
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
	private final String contentPlaceHolder = "#内容#";// 不能包含正则表达式元字符([{\^-$|}])?*+.
	private final String defaultRegExp = "cssexp:(.*?);regexp:(.*?);";
	@Autowired
	private Cp2pSeriesService cp2pSeriesService;
	@Autowired
	private Cp2pProductsService cp2pProductsService;

	@Scheduled(cron = "0 0/1 * * * ?")
	public void work() {
		log.info(getClass().getName() + "......start......");
		try {
			Cp2pSeries cp2pSeries = cp2pSeriesService.get(new Cp2pSeries(cp2pSeriesId));
			// cssexp:css;regexp:reg;
			// 可能存在html " < >被转义 需反转义
			// 定位列表的css表达式
			String listCSSExp = "";
			Matcher m = Pattern.compile(defaultRegExp).matcher(StringUtils.unescapeHtml4Default(cp2pSeries.getListrootexp()));
			if (m.find() && m.groupCount() > 0) {
				listCSSExp = m.group(1).trim();
			}
			// 详细页URI前缀
			String detailURIPrefix = StringUtils.unescapeHtml4Default(cp2pSeries.getDetailprefix());
			// 获取id
			// id css定位表达式
			String idCSSExp = "";
			// 获取id正则
			String idRegExp = "";
			String detailidexp = StringUtils.unescapeHtml4Default(cp2pSeries.getDetailidexp());
			Matcher idm = Pattern.compile(defaultRegExp).matcher(detailidexp);
			if (idm.find() && idm.groupCount() > 1) {
				idCSSExp = idm.group(1).trim();
				idRegExp = StringUtils.convertRegExp(idm.group(2).trim());
			}
			if (StringUtils.isBlank(listCSSExp) || StringUtils.isBlank(detailURIPrefix) || StringUtils.isBlank(idCSSExp) || StringUtils.isBlank(idRegExp)) {
				throw new InvalidParameterException("参数错误！");
			}
			int pageMax = 1;// cp2pSeries.getPagemax();
			int curPage = 1;
			for (; curPage <= pageMax; curPage++) {
				StringBuffer uri = new StringBuffer(StringUtils.unescapeHtml4Default(cp2pSeries.getListuri()));
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
					Element idtd = null != idtds && idtds.size() > 0 ? idtds.get(0) : null;
					String idStrTemp = null != idtd ? idtd.toString().trim() : "";
					String idStr = "";
					Matcher idmhr = Pattern.compile(StringUtils.getRegExp(idRegExp, contentPlaceHolder)).matcher(idStrTemp);
					if (idmhr.find() && idmhr.groupCount() > 0) {
						idStr = idmhr.group(1).trim();
					}
					if (StringUtils.isBlank(idStr)) {
						throw new InvalidParameterException("获取详细页ID元素错误！");
					}
					String detailurl = detailURIPrefix.replaceAll("#id#", idStr);
					if (!StringUtils.isBlank(detailurl)) {
						Detail5262Parser detail5262Parser = new Detail5262Parser(idStr, detailurl, cp2pSeries, cp2pProductsService);
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
	private final String contentPlaceHolder = "#内容#";// 不能包含正则表达式元字符([{\^-$|}])?*+.
	private final String defaultRegExp = "cssexp:(.*?);regexp:(.*?);";
	private String sid;
	private String detailurl;
	private Cp2pSeries cp2pSeries;
	private Cp2pProductsService cp2pProductsService;

	public Detail5262Parser(String sid, String detailurl, Cp2pSeries cp2pSeries, Cp2pProductsService cp2pProductsService) {
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
			Pattern p = Pattern.compile(defaultRegExp);
			// 合同编号
			Matcher m = p.matcher(StringUtils.unescapeHtml4Default(cp2pSeries.getSnumexp()));
			if (m.find() && 2 == m.groupCount()) {
				cssExp = cssExp.append(StringUtils.defaultIfBlank(m.group(1), "").trim());
				regExp = regExp.append(StringUtils.defaultIfBlank(m.group(2), "").trim());
			} else {
				throw new InvalidParameterException("平台采集规则配置错误!" + detailurl);
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
			if (StringUtils.isBlank(snum)) {
				log.warn("未获取到合同编号：" + detailurl);
			}
			cp2pProducts.setSnum(snum);
			System.err.println(cp2pProducts.getSnum());
			// cp2pProductsService.save(cp2pProducts);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info(getClass().getName() + "......end......");
	}
}
