/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * p2p平台信息Entity
 * @author xiang
 * @version 2015-12-12
 */
public class Cp2pSeries extends DataEntity<Cp2pSeries> {
	
	private static final long serialVersionUID = 1L;
	private Cp2pPlatform cp2pPlatform;		// 平台 父类
	private String name;		// 类型名称
	private String listuri;		// 列表地址
	private String pageattr;		// 分页参数
	private Integer pagemax;		// 最大获取页数
	
	public Cp2pSeries() {
		super();
	}

	public Cp2pSeries(String id){
		super(id);
	}

	public Cp2pSeries(Cp2pPlatform cp2pPlatform){
		this.cp2pPlatform = cp2pPlatform;
	}

	public Cp2pPlatform getCp2pPlatform() {
		return cp2pPlatform;
	}

	public void setCp2pPlatform(Cp2pPlatform cp2pPlatform) {
		this.cp2pPlatform = cp2pPlatform;
	}
	
	@Length(min=0, max=100, message="类型名称长度必须介于 0 和 100 之间")
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
	
}