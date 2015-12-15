/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * p2p产品Entity
 * @author xiang
 * @version 2015-12-12
 */
public class Cp2pProducts extends DataEntity<Cp2pProducts> {
	
	private static final long serialVersionUID = 1L;
	private Cp2pSeries cp2pSeries;		// 系列id
	private String sid;		// 原id
	private String snum;		// 原编号
	private String name;		// 名称
	private String detailuri;		// 详细地址
	private String rate;		// 利率
	private String platformrate;		// 平台利率
	private String totalrate;		// 总利率
	private String wanrate;		// 万元预计收益
	private String totalmoney;		// 总金额
	private String term;		// 周期
	private String termday;		// 周期天
	private String termmonth;		// 周期月
	private String termyear;		// 周期年
	private String schedule;		// 进度
	private String interesttime;		// 计息时间
	private String repaymode;		// 还款方式
	private String repaydate;		// 还款日期
	private String istransfer;		// 是否转让
	private String allowtransfer;		// 允许转让
	private String transfernotice;		// 转让注意事项
	private String ismortgage;		// 是否抵押
	private String isguarantee;		// 是否担保
	
	public Cp2pProducts() {
		super();
	}

	public Cp2pProducts(String id){
		super(id);
	}

	@Length(min=1, max=64, message="系列id长度必须介于 1 和 64 之间")
	public Cp2pSeries getCp2pSeries() {
		return cp2pSeries;
	}

	public void setCp2pSeries(Cp2pSeries cp2pSeries) {
		this.cp2pSeries = cp2pSeries;
	}
	
	@Length(min=0, max=50, message="原id长度必须介于 0 和 50 之间")
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
	@Length(min=0, max=50, message="原编号长度必须介于 0 和 50 之间")
	public String getSnum() {
		return snum;
	}

	public void setSnum(String snum) {
		this.snum = snum;
	}
	
	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=500, message="详细地址长度必须介于 0 和 500 之间")
	public String getDetailuri() {
		return detailuri;
	}

	public void setDetailuri(String detailuri) {
		this.detailuri = detailuri;
	}
	
	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public String getPlatformrate() {
		return platformrate;
	}

	public void setPlatformrate(String platformrate) {
		this.platformrate = platformrate;
	}
	
	public String getTotalrate() {
		return totalrate;
	}

	public void setTotalrate(String totalrate) {
		this.totalrate = totalrate;
	}
	
	public String getWanrate() {
		return wanrate;
	}

	public void setWanrate(String wanrate) {
		this.wanrate = wanrate;
	}
	
	public String getTotalmoney() {
		return totalmoney;
	}

	public void setTotalmoney(String totalmoney) {
		this.totalmoney = totalmoney;
	}
	
	@Length(min=0, max=50, message="周期长度必须介于 0 和 50 之间")
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	public String getTermday() {
		return termday;
	}

	public void setTermday(String termday) {
		this.termday = termday;
	}
	
	public String getTermmonth() {
		return termmonth;
	}

	public void setTermmonth(String termmonth) {
		this.termmonth = termmonth;
	}
	
	public String getTermyear() {
		return termyear;
	}

	public void setTermyear(String termyear) {
		this.termyear = termyear;
	}
	
	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	@Length(min=0, max=50, message="计息时间长度必须介于 0 和 50 之间")
	public String getInteresttime() {
		return interesttime;
	}

	public void setInteresttime(String interesttime) {
		this.interesttime = interesttime;
	}
	
	@Length(min=0, max=50, message="还款方式长度必须介于 0 和 50 之间")
	public String getRepaymode() {
		return repaymode;
	}

	public void setRepaymode(String repaymode) {
		this.repaymode = repaymode;
	}
	
	@Length(min=0, max=50, message="还款日期长度必须介于 0 和 50 之间")
	public String getRepaydate() {
		return repaydate;
	}

	public void setRepaydate(String repaydate) {
		this.repaydate = repaydate;
	}
	
	@Length(min=0, max=1, message="是否转让长度必须介于 0 和 1 之间")
	public String getIstransfer() {
		return istransfer;
	}

	public void setIstransfer(String istransfer) {
		this.istransfer = istransfer;
	}
	
	@Length(min=0, max=1, message="允许转让长度必须介于 0 和 1 之间")
	public String getAllowtransfer() {
		return allowtransfer;
	}

	public void setAllowtransfer(String allowtransfer) {
		this.allowtransfer = allowtransfer;
	}
	
	@Length(min=0, max=200, message="转让注意事项长度必须介于 0 和 200 之间")
	public String getTransfernotice() {
		return transfernotice;
	}

	public void setTransfernotice(String transfernotice) {
		this.transfernotice = transfernotice;
	}
	
	@Length(min=0, max=1, message="是否抵押长度必须介于 0 和 1 之间")
	public String getIsmortgage() {
		return ismortgage;
	}

	public void setIsmortgage(String ismortgage) {
		this.ismortgage = ismortgage;
	}
	
	@Length(min=0, max=1, message="是否担保长度必须介于 0 和 1 之间")
	public String getIsguarantee() {
		return isguarantee;
	}

	public void setIsguarantee(String isguarantee) {
		this.isguarantee = isguarantee;
	}
	
}