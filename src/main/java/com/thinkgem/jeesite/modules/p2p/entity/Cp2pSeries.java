/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * p2p产品系列Entity
 * @author xiang
 * @version 2015-12-18
 */
public class Cp2pSeries extends DataEntity<Cp2pSeries> {
	
	private static final long serialVersionUID = 1L;
	private Cp2pPlatform cp2pPlatform;		// 平台
	private String name;		// 名称
	private String listuri;		// 列表地址
	private String pageattr;		// 分页参数
	private Integer pagemax;		// 最大页数
	private String listdatatype;		// 返回结果类型
	private String listrootexp;		// 列表获取表达式
	private String detailidexp;		// 明细id表达式
	private String listscheduleexp;		//列表进度表达式
	private String detailprefix;		// 详细页前缀
	private String snumexp;		// 项目编号表达式
	private String nameexp;		// 名称表达式
	private String rateexp;		// 利率表达式
	private String platformrateexp;		// 平台利率表达式
	private String totalmoneyexp;		// 总金额表达式
	private String termexp;		// 期限表达式
	private String scheduleexp;		// 进度表达式
	private String starttimeexp;		// 开始日期表达式
	private String repaymodeexp;		// 还款方法表达式
	private String repaydateexp;		// 还款日期表达式
	private String istransferexp;		// 是否转让标表达式
	private String allowtransfer;		// 允许转让表达式
	private String transfernoticeexp;		// 转让说明表达式
	private String ismortgageexp;		// 是否抵押表达式
	private String isguaranteeexp;		// 是否担保表达式
	
	public Cp2pSeries() {
		super();
	}

	public Cp2pSeries(String id){
		super(id);
	}

	@NotNull(message="平台不能为空")
	public Cp2pPlatform getCp2pPlatform() {
		return cp2pPlatform;
	}

	public void setCp2pPlatform(Cp2pPlatform cp2pPlatform) {
		this.cp2pPlatform = cp2pPlatform;
	}
	
	@Length(min=0, max=200, message="名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=200, message="列表地址长度必须介于 0 和 200 之间")
	public String getListuri() {
		return listuri;
	}

	public void setListuri(String listuri) {
		this.listuri = listuri;
	}
	
	@Length(min=0, max=50, message="分页参数长度必须介于 0 和 50 之间")
	public String getPageattr() {
		return pageattr;
	}

	public void setPageattr(String pageattr) {
		this.pageattr = pageattr;
	}
	
	public Integer getPagemax() {
		return pagemax;
	}

	public void setPagemax(Integer pagemax) {
		this.pagemax = pagemax;
	}
	
	public String getListdatatype() {
		return listdatatype;
	}

	public void setListdatatype(String listdatatype) {
		this.listdatatype = listdatatype;
	}
	
	@Length(min=0, max=200, message="列表获取表达式长度必须介于 0 和 200 之间")
	public String getListrootexp() {
		return listrootexp;
	}

	public void setListrootexp(String listrootexp) {
		this.listrootexp = listrootexp;
	}

	@Length(min=0, max=500, message="列表进度表达式长度必须介于 0 和 500 之间")
	public String getListscheduleexp() {
		return listscheduleexp;
	}

	public void setListscheduleexp(String listscheduleexp) {
		this.listscheduleexp = listscheduleexp;
	}

	@Length(min=0, max=500, message="明细id表达式长度必须介于 0 和 500 之间")
	public String getDetailidexp() {
		return detailidexp;
	}

	public void setDetailidexp(String detailidexp) {
		this.detailidexp = detailidexp;
	}
	
	@Length(min=0, max=500, message="详细页前缀长度必须介于 0 和 500 之间")
	public String getDetailprefix() {
		return detailprefix;
	}

	public void setDetailprefix(String detailprefix) {
		this.detailprefix = detailprefix;
	}
	
	@Length(min=0, max=500, message="项目编号表达式长度必须介于 0 和 500 之间")
	public String getSnumexp() {
		return snumexp;
	}

	public void setSnumexp(String snumexp) {
		this.snumexp = snumexp;
	}
	
	@Length(min=0, max=500, message="名称表达式长度必须介于 0 和 500 之间")
	public String getNameexp() {
		return nameexp;
	}

	public void setNameexp(String nameexp) {
		this.nameexp = nameexp;
	}
	
	@Length(min=0, max=500, message="利率表达式长度必须介于 0 和 500 之间")
	public String getRateexp() {
		return rateexp;
	}

	public void setRateexp(String rateexp) {
		this.rateexp = rateexp;
	}
	
	@Length(min=0, max=500, message="平台利率表达式长度必须介于 0 和 500 之间")
	public String getPlatformrateexp() {
		return platformrateexp;
	}

	public void setPlatformrateexp(String platformrateexp) {
		this.platformrateexp = platformrateexp;
	}
	
	@Length(min=0, max=500, message="总金额表达式长度必须介于 0 和 500 之间")
	public String getTotalmoneyexp() {
		return totalmoneyexp;
	}

	public void setTotalmoneyexp(String totalmoneyexp) {
		this.totalmoneyexp = totalmoneyexp;
	}
	
	@Length(min=0, max=500, message="期限表达式长度必须介于 0 和 500 之间")
	public String getTermexp() {
		return termexp;
	}

	public void setTermexp(String termexp) {
		this.termexp = termexp;
	}
	
	@Length(min=0, max=500, message="进度表达式长度必须介于 0 和 500 之间")
	public String getScheduleexp() {
		return scheduleexp;
	}

	public void setScheduleexp(String scheduleexp) {
		this.scheduleexp = scheduleexp;
	}
	
	@Length(min=0, max=500, message="开始日期表达式长度必须介于 0 和 500 之间")
	public String getStarttimeexp() {
		return starttimeexp;
	}

	public void setStarttimeexp(String starttimeexp) {
		this.starttimeexp = starttimeexp;
	}
	
	@Length(min=0, max=500, message="还款方法表达式长度必须介于 0 和 500 之间")
	public String getRepaymodeexp() {
		return repaymodeexp;
	}

	public void setRepaymodeexp(String repaymodeexp) {
		this.repaymodeexp = repaymodeexp;
	}
	
	@Length(min=0, max=500, message="还款日期表达式长度必须介于 0 和 500 之间")
	public String getRepaydateexp() {
		return repaydateexp;
	}

	public void setRepaydateexp(String repaydateexp) {
		this.repaydateexp = repaydateexp;
	}
	
	@Length(min=0, max=500, message="是否转让标表达式长度必须介于 0 和 500 之间")
	public String getIstransferexp() {
		return istransferexp;
	}

	public void setIstransferexp(String istransferexp) {
		this.istransferexp = istransferexp;
	}
	
	@Length(min=0, max=500, message="允许转让表达式长度必须介于 0 和 500 之间")
	public String getAllowtransfer() {
		return allowtransfer;
	}

	public void setAllowtransfer(String allowtransfer) {
		this.allowtransfer = allowtransfer;
	}
	
	@Length(min=0, max=500, message="转让说明表达式长度必须介于 0 和 500 之间")
	public String getTransfernoticeexp() {
		return transfernoticeexp;
	}

	public void setTransfernoticeexp(String transfernoticeexp) {
		this.transfernoticeexp = transfernoticeexp;
	}
	
	@Length(min=0, max=500, message="是否抵押表达式长度必须介于 0 和 500 之间")
	public String getIsmortgageexp() {
		return ismortgageexp;
	}

	public void setIsmortgageexp(String ismortgageexp) {
		this.ismortgageexp = ismortgageexp;
	}
	
	@Length(min=0, max=500, message="是否担保表达式长度必须介于 0 和 500 之间")
	public String getIsguaranteeexp() {
		return isguaranteeexp;
	}

	public void setIsguaranteeexp(String isguaranteeexp) {
		this.isguaranteeexp = isguaranteeexp;
	}
	
}