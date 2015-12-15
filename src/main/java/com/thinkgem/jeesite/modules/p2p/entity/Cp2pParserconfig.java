/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 采集器配置Entity
 * @author xiang
 * @version 2015-12-12
 */
public class Cp2pParserconfig extends DataEntity<Cp2pParserconfig> {
	
	private static final long serialVersionUID = 1L;
	private Cp2pSeries cp2pSeries;		// 产品系列
	private String classname;		// 类名
	private String cronex;		// 时间Cron
	private String status;		// 状态
	
	public Cp2pParserconfig() {
		super();
	}

	public Cp2pParserconfig(String id){
		super(id);
	}

	@NotNull(message="产品系列不能为空")
	public Cp2pSeries getCp2pSeries() {
		return cp2pSeries;
	}

	public void setCp2pSeries(Cp2pSeries cp2pSeries) {
		this.cp2pSeries = cp2pSeries;
	}
	
	@Length(min=1, max=200, message="类名长度必须介于 1 和 200 之间")
	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
	
	@Length(min=0, max=200, message="时间Cron长度必须介于 0 和 200 之间")
	public String getCronex() {
		return cronex;
	}

	public void setCronex(String cronex) {
		this.cronex = cronex;
	}
	
	@Length(min=0, max=50, message="状态长度必须介于 0 和 50 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}