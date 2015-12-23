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

import com.thinkgem.jeesite.common.utils.ObjectUtils;
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
				listCSSExp = m.group(1);
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
				idCSSExp = idm.group(1);
				idRegExp = StringUtils.convertRegExp(idm.group(2));
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
				Document doc = Jsoup.connect(uri.toString()).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36").timeout(5000).get();
				// 数据tr列表
				Elements listtr = doc.select(listCSSExp);
				for (Element tr : listtr) {
					Elements idtds = tr.select(idCSSExp);
					Element idtd = null != idtds && idtds.size() > 0 ? idtds.get(0) : null;
					String idStrTemp = null != idtd ? idtd.toString() : "";
					String idStr = "";
					Matcher idmhr = Pattern.compile(StringUtils.getRegExp(idRegExp, contentPlaceHolder)).matcher(idStrTemp);
					if (idmhr.find() && idmhr.groupCount() > 0) {
						idStr = idmhr.group(1);
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
	private Document doc;

	public Detail5262Parser(String sid, String detailurl, Cp2pSeries cp2pSeries, Cp2pProductsService cp2pProductsService) throws IOException {
		this.sid = sid;
		this.detailurl = detailurl;
		this.cp2pSeries = cp2pSeries;
		this.cp2pProductsService = cp2pProductsService;
	}

	@Override
	public void run() {
		log.info(getClass().getName() + "......start......");
		try {
			doc = Jsoup.connect(detailurl).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36").timeout(5000).get();
			Cp2pProducts cp2pProducts = new Cp2pProducts();
			cp2pProducts.setSid(sid);
			cp2pProducts.setDetailuri(detailurl);
			// 合同编号
			String snum = getContent("合同编号", cp2pSeries.getSnumexp());
			cp2pProducts.setSnum(snum);
			log.debug("产品编号:" + cp2pProducts.getSnum());

			// 名称
			String name = getContent("产品名称", cp2pSeries.getNameexp());
			cp2pProducts.setName(name);
			log.debug("产品名称:" + cp2pProducts.getName());

			// 利率
			String rate = getContent("利率", cp2pSeries.getRateexp());
			BigDecimal rateDecimal = new BigDecimal(0);
			rateDecimal = rateDecimal.add(new BigDecimal(rate));
			cp2pProducts.setRate(rateDecimal);
			log.debug("利率:" + cp2pProducts.getRate());

			// 平台利率
			String platformrate = getContent("平台利率", cp2pSeries.getPlatformrateexp());
			BigDecimal platformrateDecimal = new BigDecimal(0);
			platformrateDecimal = platformrateDecimal.add(new BigDecimal(platformrate));
			cp2pProducts.setRate(rateDecimal);
			log.debug("平台利率:" + cp2pProducts.getRate());
			// cp2pProductsService.save(cp2pProducts);
		} catch (IOException e) {
			System.out.println("---------------------");
			e.printStackTrace();
		}
		log.info(getClass().getName() + "......end......");
	}

	public String getContent(final String fieldComment, final String fieldExp) {
		String temp = StringUtils.defaultIfBlank(fieldExp, "").trim();
		String result = "";
		if (StringUtils.isNotBlank(fieldComment) && StringUtils.isNotBlank(temp)) {
			StringBuffer cssExp = new StringBuffer();
			StringBuffer regExp = new StringBuffer();
			Matcher m = Pattern.compile(defaultRegExp).matcher(StringUtils.unescapeHtml4Default(fieldExp));
			if (m.find() && 2 == m.groupCount()) {
				cssExp = cssExp.append(StringUtils.defaultIfBlank(m.group(1), ""));
				regExp = regExp.append(StringUtils.getRegExp(StringUtils.defaultIfBlank(m.group(2), ""), contentPlaceHolder));
			} else {
				throw new InvalidParameterException(fieldComment + "采集规则配置错误!" + detailurl);
			}
			Elements eles = doc.select(cssExp.toString());
			if (1 == eles.size()) {
				Element ele = eles.get(0);
				String tempStr = ObjectUtils.defaultIfNull(ele, "").toString();
				Matcher tempm = Pattern.compile(regExp.toString()).matcher(tempStr);
				if (tempm.find()) {
					result = StringUtils.defaultIfBlank(tempm.group(1), "").trim();
				}
			}
			if (StringUtils.isBlank(result)) {
				log.debug("未获取到" + fieldComment + "：" + detailurl);
			}
		}
		return result;
	}
}
