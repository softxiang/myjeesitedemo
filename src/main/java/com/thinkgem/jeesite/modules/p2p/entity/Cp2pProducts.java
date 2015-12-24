/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * p2p产品Entity
 * 
 * @author xiang
 * @version 2015-12-18
 */
public class Cp2pProducts extends DataEntity<Cp2pProducts> {

	private static final long serialVersionUID = 1L;
	private Cp2pSeries cp2pSeries; // 产品系列
	private String sid; // 原平台id
	private String snum; // 原平台编号
	private String name; // 名称
	private String detailuri; // 明细url
	private BigDecimal rate = new BigDecimal(0); // 利率
	private BigDecimal platformrate = new BigDecimal(0); // 平台利率
	private BigDecimal totalrate = new BigDecimal(0); // 总利率
	private Integer wanrate; // 项目万元收益
	private BigDecimal totalmoney = new BigDecimal(0); // 总金额
	private String term; // 期限
	private Integer termday = new Integer(0); // 日
	private Integer termmonth = new Integer(0); // 月
	private Integer termyear = new Integer(0); // 年
	private float schedule; // 进度
	private Date starttime; // 开始日期
	private String interesttime; // 计息时间
	private String repaymode; // 还款方式
	private String repaydate; // 还款日期
	private String istransfer; // 是否转让标
	private String allowtransfer; // 是否允许转让
	private String transfernotice; // 装让说明
	private String ismortgage; // 是否抵押
	private String isguarantee; // 是否担保

	public Cp2pProducts() {
		super();
	}

	public Cp2pProducts(String id) {
		super(id);
	}

	@Length(min = 1, max = 64, message = "产品系列长度必须介于 1 和 64 之间")
	public Cp2pSeries getCp2pSeries() {
		return cp2pSeries;
	}

	public void setCp2pSeries(Cp2pSeries cp2pSeries) {
		this.cp2pSeries = cp2pSeries;
	}

	@Length(min = 0, max = 50, message = "原平台id长度必须介于 0 和 50 之间")
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	@Length(min = 0, max = 50, message = "原平台编号长度必须介于 0 和 50 之间")
	public String getSnum() {
		return snum;
	}

	public void setSnum(String snum) {
		this.snum = snum;
	}

	@Length(min = 0, max = 200, message = "名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min = 0, max = 500, message = "明细url长度必须介于 0 和 500 之间")
	public String getDetailuri() {
		return detailuri;
	}

	public void setDetailuri(String detailuri) {
		this.detailuri = detailuri;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getPlatformrate() {
		return platformrate;
	}

	public void setPlatformrate(BigDecimal platformrate) {
		this.platformrate = platformrate;
	}

	public BigDecimal getTotalrate() {
		totalrate = rate.add(platformrate);
		return totalrate;
	}

	public void setTotalrate() {
		getTotalrate();
	}

	public Integer getWanrate() {
		BigDecimal wanratetemp = new BigDecimal(0);
		if (termday > 0) {
			wanratetemp = totalrate.divide(new BigDecimal(100), 4, BigDecimal.ROUND_UP).multiply(new BigDecimal(10000)).divide(new BigDecimal(365), 4, BigDecimal.ROUND_UP).multiply(new BigDecimal(termday));
		} else if (termmonth > 0) {
			wanratetemp = wanratetemp.add(totalrate.divide(new BigDecimal(100), 4, BigDecimal.ROUND_UP).multiply(new BigDecimal(10000)).divide(new BigDecimal(12), 4, BigDecimal.ROUND_UP).multiply(new BigDecimal(termmonth)));
		} else if (termyear > 0) {
			wanratetemp = wanratetemp.add(totalrate.divide(new BigDecimal(100), 4, BigDecimal.ROUND_UP).multiply(new BigDecimal(10000)).multiply(new BigDecimal(termyear)));
		}
		wanrate = wanratetemp.intValue();
		return wanrate;
	}

	public void setWanrate() {
		getWanrate();
	}

	public BigDecimal getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(BigDecimal totalmoney) {
		this.totalmoney = totalmoney;
	}

	@Length(min = 0, max = 50, message = "期限长度必须介于 0 和 50 之间")
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public Integer getTermday() {
		return termday;
	}

	public void setTermday(Integer termday) {
		this.termday = termday;
	}

	public Integer getTermmonth() {
		return termmonth;
	}

	public void setTermmonth(Integer termmonth) {
		this.termmonth = termmonth;
	}

	public Integer getTermyear() {
		return termyear;
	}

	public void setTermyear(Integer termyear) {
		this.termyear = termyear;
	}

	public float getSchedule() {
		return schedule;
	}

	public void setSchedule(float schedule) {
		this.schedule = schedule;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	@Length(min = 0, max = 50, message = "计息时间长度必须介于 0 和 50 之间")
	public String getInteresttime() {
		return interesttime;
	}

	public void setInteresttime(String interesttime) {
		this.interesttime = interesttime;
	}

	@Length(min = 0, max = 50, message = "还款方式长度必须介于 0 和 50 之间")
	public String getRepaymode() {
		return repaymode;
	}

	public void setRepaymode(String repaymode) {
		this.repaymode = repaymode;
	}

	@Length(min = 0, max = 50, message = "还款日期长度必须介于 0 和 50 之间")
	public String getRepaydate() {
		return repaydate;
	}

	public void setRepaydate(String repaydate) {
		this.repaydate = repaydate;
	}

	@Length(min = 0, max = 1, message = "是否转让标长度必须介于 0 和 1 之间")
	public String getIstransfer() {
		return istransfer;
	}

	public void setIstransfer(String istransfer) {
		this.istransfer = istransfer;
	}

	@Length(min = 0, max = 1, message = "是否允许转让长度必须介于 0 和 1 之间")
	public String getAllowtransfer() {
		return allowtransfer;
	}

	public void setAllowtransfer(String allowtransfer) {
		this.allowtransfer = allowtransfer;
	}

	@Length(min = 0, max = 200, message = "装让说明长度必须介于 0 和 200 之间")
	public String getTransfernotice() {
		return transfernotice;
	}

	public void setTransfernotice(String transfernotice) {
		this.transfernotice = transfernotice;
	}

	@Length(min = 0, max = 1, message = "是否抵押长度必须介于 0 和 1 之间")
	public String getIsmortgage() {
		return ismortgage;
	}

	public void setIsmortgage(String ismortgage) {
		this.ismortgage = ismortgage;
	}

	@Length(min = 0, max = 1, message = "是否担保长度必须介于 0 和 1 之间")
	public String getIsguarantee() {
		return isguarantee;
	}

	public void setIsguarantee(String isguarantee) {
		this.isguarantee = isguarantee;
	}
}