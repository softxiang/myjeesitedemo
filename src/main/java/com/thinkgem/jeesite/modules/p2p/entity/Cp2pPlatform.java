/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * p2p平台Entity
 * @author xiang
 * @version 2015-12-18
 */
public class Cp2pPlatform extends DataEntity<Cp2pPlatform> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String platformuri;		// 平台网址
	private String companyname;		// 公司名称
	private String corporation;		// 法人
	private String address;		// 地址
	private String province;		// 省
	private String city;		// 市
	
	public Cp2pPlatform() {
		super();
	}

	public Cp2pPlatform(String id){
		super(id);
	}

	@Length(min=1, max=200, message="名称长度必须介于 1 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=200, message="平台网址长度必须介于 0 和 200 之间")
	public String getPlatformuri() {
		return platformuri;
	}

	public void setPlatformuri(String platformuri) {
		this.platformuri = platformuri;
	}
	
	@Length(min=0, max=200, message="公司名称长度必须介于 0 和 200 之间")
	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	
	@Length(min=0, max=200, message="法人长度必须介于 0 和 200 之间")
	public String getCorporation() {
		return corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	
	@Length(min=0, max=1000, message="地址长度必须介于 0 和 1000 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=100, message="省长度必须介于 0 和 100 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=100, message="市长度必须介于 0 和 100 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}