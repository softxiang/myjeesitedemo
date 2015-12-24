package com.thinkgem.jeesite.modules.p2p.p2pparser.springtask;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.AbstractCollection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.ObjectUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pProducts;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pSeries;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pProductsService;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pSeriesService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

@Service
@Lazy(false)
public class List5262ParserSpring {
	public final Logger log = Logger.getLogger(getClass());
	// 5262
	// private final String cp2pSeriesId = "0bb8a96c2ac6404b9f086a8c5a6f85ef";
	// ppmoney
	private final String cp2pSeriesId = "132acc0d04954042aacdbcd07e4380c9";
	private final String contentPlaceHolder = "#内容#";// 不能包含正则表达式元字符([{\^-$|}])?*+.
	private final String defaultRegExp = "cssexp:(.*?);regexp:(.*?);";
	private Cp2pSeries cp2pSeries = null;
	// 定位列表的css表达式
	private String listCSSExp = "";
	// 详细页URI前缀
	String detailURIPrefix = "";
	// id css定位表达式
	String idCSSExp = "";
	// 获取id正则
	String idRegExp = "";
	String detailurl = "";

	@Autowired
	private Cp2pSeriesService cp2pSeriesService;
	@Autowired
	private Cp2pProductsService cp2pProductsService;

	@Scheduled(cron = "0 0/1 * * * ?")
	public void work() {
		log.info(getClass().getName() + "......start......");
		try {
			cp2pSeries = cp2pSeriesService.get(new Cp2pSeries(cp2pSeriesId));
			// cssexp:css;regexp:reg;
			// 可能存在html " < >被转义 需反转义
			Matcher m = Pattern.compile(defaultRegExp).matcher(StringUtils.unescapeHtml4Default(cp2pSeries.getListrootexp()));
			if (m.find() && m.groupCount() > 0) {
				listCSSExp = m.group(1);
			}
			detailURIPrefix = StringUtils.unescapeHtml4Default(cp2pSeries.getDetailprefix());
			// 获取id
			// id css定位表达式
			idCSSExp = getCssExp(cp2pSeries.getDetailidexp());
			// 获取id正则
			idRegExp = getRegExp(cp2pSeries.getDetailidexp());

			if (StringUtils.isBlank(listCSSExp) || StringUtils.isBlank(detailURIPrefix) || StringUtils.isBlank(idCSSExp) || StringUtils.isBlank(idRegExp)) {
				throw new InvalidParameterException("参数错误！");
			}
			int pageMax = cp2pSeries.getPagemax();
			int curPage = 1;
			for (; curPage <= pageMax; curPage++) {
				String listurl = StringUtils.unescapeHtml4Default(cp2pSeries.getListuri());
				listurl = listurl.replaceAll("#pageattr#", curPage + "");
				if ((DictUtils.getDictValue("文档", "p2p_serieslist_type", null)).equals(cp2pSeries.getListdatatype())) {
					parserDocumnet(listurl);
				} else if ((DictUtils.getDictValue("json", "p2p_serieslist_type", null)).equals(cp2pSeries.getListdatatype())) {
					parserJson(listurl);
				}
			}
		} catch (Exception e) {
			log.warn(getClass().getName() + "出错，原因：" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		log.info(getClass().getName() + "......end......");
	}

	private void parserJson(final String url) throws IOException {
		// 解析json格式的地址
		String returndata = Jsoup.connect(url).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36").ignoreContentType(true).timeout(5000).execute().body();
		ObjectMapper mapper = new ObjectMapper();
		Map<?, ?> map = mapper.readValue(returndata, Map.class);
		String[] path = listCSSExp.split("#");
		Object obj = map;
		for (int i = 0; i < path.length; i++) {
			obj = ((Map) obj).get(path[i]);
		}
		if (obj instanceof AbstractCollection) {
			Object temp = null;
			Iterator iterator = ((AbstractCollection) obj).iterator();
			while (iterator.hasNext()) {
				temp = iterator.next();
				String id = null == ((Map) temp).get("prjId") ? "" : ((Map) temp).get("prjId").toString();
				String detailurl = detailURIPrefix.replaceAll("#id#", id);
				System.out.println(detailurl);
			}
		}
	}

	private void parserDocumnet(final String detailURL) throws IOException {
		Element doc = Jsoup.connect(detailURL).header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36").timeout(5000).get().body();
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
			if (StringUtils.isNotBlank(detailurl)) {
				Cp2pProducts entity = new Cp2pProducts();
				entity.setCp2pSeries(new Cp2pSeries(cp2pSeriesId));
				entity.setDetailuri(detailurl);
				Cp2pProducts cp2pProduct = cp2pProductsService.getEntity(entity);
				// 不存在去取
				if (null == cp2pProduct || StringUtils.isBlank(cp2pProduct.getId())) {
					Detail5262Parser detail5262Parser = new Detail5262Parser(idStr, detailurl, cp2pSeries, cp2pProductsService);
					Thread thread = new Thread(detail5262Parser);
					thread.start();
				} else if (StringUtils.isNotBlank(cp2pProduct.getId()) && cp2pProduct.getSchedule() != 100f) {
					// 更新
					String listschedulecss = getCssExp(cp2pSeries.getListscheduleexp());
					String listschedulereg = getRegExp(cp2pSeries.getListscheduleexp());
					if (StringUtils.isBlank(listschedulecss) || StringUtils.isBlank(listschedulereg)) {
						throw new InvalidParameterException("获取列表中的进度参数错误！");
					}
					Elements scheduletds = tr.select(listschedulecss);
					Element scheduletd = null != scheduletds && scheduletds.size() > 0 ? scheduletds.get(0) : null;
					String scheduleStr = null != scheduletd ? scheduletd.toString() : "";
					Matcher schedulem = Pattern.compile(StringUtils.getRegExp(listschedulereg, contentPlaceHolder)).matcher(scheduleStr);
					if (schedulem.find() && schedulem.groupCount() > 0) {
						scheduleStr = schedulem.group(1);
					}
					float schedulef = 0.00f;
					if (StringUtils.isNotBlank(scheduleStr)) {
						scheduleStr = StringUtils.replaceHtml(scheduleStr);
						try {
							schedulef = Float.parseFloat(scheduleStr.replaceAll("[^\\d|.]*", "").trim());
						} catch (Exception e) {
							log.warn("列表进度格式化错误:" + scheduleStr + ";" + detailurl);
						}
					}
					Cp2pProducts target = new Cp2pProducts();
					try {
						BeanUtils.copyProperties(target, cp2pProduct);
						target.setSchedule(schedulef);
						target.preUpdate();
						cp2pProductsService.save(target);
					} catch (Exception e) {
						log.warn("已存在产品更新失败" + detailurl + ",原因:" + e.getLocalizedMessage());
					}
					log.debug("已存在产品更新成功" + detailurl);
				}

			}
		}
	}

	private String getCssExp(final String str) {
		String result = "";
		Matcher m = Pattern.compile(defaultRegExp).matcher(StringUtils.unescapeHtml4Default(str));
		if (m.find() && m.groupCount() > 1) {
			result = m.group(1);
		}
		return result;
	}

	private String getRegExp(final String str) {
		String result = "";
		Matcher m = Pattern.compile(defaultRegExp).matcher(StringUtils.unescapeHtml4Default(str));
		if (m.find() && m.groupCount() > 1) {
			result = StringUtils.convertRegExp(m.group(2));
		}
		return result;
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
			cp2pProducts.setCreateBy(UserUtils.get("taskparser"));

			cp2pProducts.setCp2pSeries(cp2pSeries);
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
			if (StringUtils.isNotBlank(rate)) {
				try {
					rateDecimal = rateDecimal.add(new BigDecimal(rate));
				} catch (Exception e) {
					log.warn("利率格式化错误:" + rate + ";" + detailurl);
				}
			}
			cp2pProducts.setRate(rateDecimal);
			log.debug("利率:" + cp2pProducts.getRate());

			// 平台利率
			String platformrate = getContent("平台利率", cp2pSeries.getPlatformrateexp());
			BigDecimal platformrateDecimal = new BigDecimal(0);
			if (StringUtils.isNotBlank(platformrate)) {
				try {
					platformrateDecimal = platformrateDecimal.add(new BigDecimal(platformrate));
				} catch (Exception e) {
					log.warn("平台利率格式化错误:" + platformrate + ";" + detailurl);
				}
			}
			cp2pProducts.setPlatformrate(platformrateDecimal);
			log.debug("平台利率:" + cp2pProducts.getPlatformrate());

			// 总金额
			String totalMoneyStr = getContent("融资金额", cp2pSeries.getTotalmoneyexp());
			BigDecimal totalMoneyDecimal = new BigDecimal(0);
			if (StringUtils.isNotBlank(totalMoneyStr)) {
				try {
					totalMoneyStr = StringUtils.replaceHtml(totalMoneyStr);
					totalMoneyStr = totalMoneyStr.replaceAll("￥", "");
					String totalMoney = totalMoneyStr.replaceAll("[^\\d|.]*", "");
					if (-1 != totalMoneyStr.indexOf("千万")) {
						totalMoneyDecimal = new BigDecimal(10000000).multiply(new BigDecimal(totalMoney));
					} else if (-1 != totalMoneyStr.indexOf("百万")) {
						totalMoneyDecimal = new BigDecimal(1000000).multiply(new BigDecimal(totalMoney));
					} else if (-1 != totalMoneyStr.indexOf("十万")) {
						totalMoneyDecimal = new BigDecimal(100000).multiply(new BigDecimal(totalMoney));
					} else if (-1 != totalMoneyStr.indexOf("万")) {
						totalMoneyDecimal = new BigDecimal(10000).multiply(new BigDecimal(totalMoney));
					}
				} catch (Exception e) {
					log.warn("融资金额格式化错误:" + totalMoneyStr + ";" + detailurl);
				}
			}
			cp2pProducts.setTotalmoney(totalMoneyDecimal);
			log.debug("融资金额:" + cp2pProducts.getTotalmoney());

			// 期限
			String termStr = getContent("期限", cp2pSeries.getTermexp());
			if (StringUtils.isNotBlank(termStr)) {
				termStr = StringUtils.replaceHtml(termStr);
				Integer termNum = 0;
				try {
					termNum = Integer.parseInt(termStr.replaceAll("[^\\d|.]*", "").trim());
				} catch (Exception e) {
					log.warn("融资金额格式化错误:" + rate + ";" + detailurl);
				}
				if (-1 != termStr.indexOf("年")) {
					cp2pProducts.setTermmonth(termNum * 12);
				} else if (-1 != termStr.indexOf("月")) {
					cp2pProducts.setTermmonth(termNum);
				} else if (-1 != termStr.indexOf("日") || -1 != termStr.indexOf("天")) {
					cp2pProducts.setTermmonth(0);
				}
			}
			cp2pProducts.setTerm(termStr);
			log.debug("期限:" + cp2pProducts.getTerm() + "	" + cp2pProducts.getTermmonth() + "个月");

			// 进度
			String scheduleStr = getContent("进度", cp2pSeries.getScheduleexp());
			float schedulef = 0.00f;
			if (StringUtils.isNotBlank(scheduleStr)) {
				scheduleStr = StringUtils.replaceHtml(scheduleStr);
				try {
					schedulef = Float.parseFloat(scheduleStr.replaceAll("[^\\d|.]*", "").trim());
				} catch (Exception e) {
					log.warn("进度格式化错误:" + scheduleStr + ";" + detailurl);
				}
			}
			cp2pProducts.setSchedule(schedulef);
			log.debug("进度:" + cp2pProducts.getSchedule());

			// 开始时间
			String starttimeStr = getContent("开始时间", cp2pSeries.getStarttimeexp());
			Date starttime = new Date();
			if (StringUtils.isNotBlank(starttimeStr)) {
				starttimeStr = StringUtils.replaceHtml(starttimeStr);
				try {
					starttime = DateUtils.parseDate(starttimeStr);
				} catch (Exception e) {
					log.warn("开始时间格式化错误:" + starttimeStr + ";" + detailurl);
				}
			}
			cp2pProducts.setStarttime(starttime);
			log.debug("开始时间:" + cp2pProducts.getStarttime());

			// 还款方式
			String repaymode = getContent("还款方式", cp2pSeries.getRepaymodeexp());
			cp2pProducts.setRepaymode(StringUtils.defaultIfBlank(repaymode, "未知，见详细页"));
			log.debug("还款方式:" + cp2pProducts.getRepaymode());

			// 还款日期
			String repaydate = getContent("还款日期", cp2pSeries.getRepaydateexp());
			cp2pProducts.setRepaydate(StringUtils.defaultIfBlank(repaydate, "未知，见详细页"));
			log.debug("还款日期:" + cp2pProducts.getRepaydate());

			// 是否转让标
			String istransferStr = getContent("是否转让标", cp2pSeries.getIstransferexp());
			cp2pProducts.setIstransfer(StringUtils.isBlank(istransferStr) ? "0" : "1");
			log.debug("是否转让标:" + cp2pProducts.getIstransfer() + "		" + istransferStr);

			// 是否允许转让
			String allowtransferStr = getContent("是否允许转让", cp2pSeries.getAllowtransfer());
			cp2pProducts.setAllowtransfer(StringUtils.isBlank(allowtransferStr) ? "0" : "1");
			log.debug("是否允许转让:" + cp2pProducts.getAllowtransfer() + "		" + allowtransferStr);

			// 转让说明
			String transfernotice = getContent("转让说明", cp2pSeries.getTransfernoticeexp());
			cp2pProducts.setTransfernotice(transfernotice);
			log.debug("转让说明:" + cp2pProducts.getTransfernotice());

			// 是否抵押
			String ismortgageStr = getContent("是否抵押", cp2pSeries.getIsmortgageexp());
			cp2pProducts.setIsmortgage(StringUtils.isBlank(ismortgageStr) ? "0" : "1");
			log.debug("是否抵押:" + cp2pProducts.getIsmortgage());

			// 是否担保
			String isguaranteeStr = getContent("是否担保", cp2pSeries.getIsmortgageexp());
			cp2pProducts.setIsguarantee(StringUtils.isBlank(isguaranteeStr) ? "0" : "1");
			log.debug("是否担保:" + cp2pProducts.getIsguarantee());

			cp2pProductsService.save(cp2pProducts);
		} catch (IOException e) {
			System.out.println("---------------------");
			e.printStackTrace();
		}
		log.info(getClass().getName() + "......end......");
	}

	private String getContent(final String fieldComment, final String fieldExp) {
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
