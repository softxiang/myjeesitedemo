/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cmtag.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.cmctgy.entity.CommonCategory;

/**
 * 标签Entity
 * @author xiang
 * @version 2015-11-23
 */
public class CommonTag extends DataEntity<CommonTag> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 标签
	private CommonCategory commonCategory;		// 标签分类
	private Integer sort;		// 排序
	
	public CommonTag() {
		super();
	}

	public CommonTag(String id){
		super(id);
	}

	@Length(min=1, max=50, message="标签长度必须介于 1 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public CommonCategory getCommonCategory() {
		return commonCategory;
	}

	public void setCommonCategory(CommonCategory commonCategory) {
		this.commonCategory = commonCategory;
	}

	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
}