/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cmctgy.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 公共分类树Entity
 * @author xiang
 * @version 2015-11-23
 */
public class CommonCategory extends TreeEntity<CommonCategory> {
	
	private static final long serialVersionUID = 1L;
	private CommonCategory parent;		// 父级
	private String parentIds;		// 所有父级
	private String objcode;		// 编码
	private String name;		// 名称
	private Integer objlevel;		// 级别
	private Integer sort;		// 排序
	
	public CommonCategory() {
		super();
	}

	public CommonCategory(String id){
		super(id);
	}

	@JsonBackReference
	public CommonCategory getParent() {
		return parent;
	}

	public void setParent(CommonCategory parent) {
		this.parent = parent;
	}
	
	@Length(min=1, max=2000, message="所有父级长度必须介于 1 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=0, max=20, message="编码长度必须介于 0 和 20 之间")
	public String getObjcode() {
		return objcode;
	}

	public void setObjcode(String objcode) {
		this.objcode = objcode;
	}
	
	@Length(min=0, max=50, message="名称长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message="级别不能为空")
	public Integer getObjlevel() {
		return objlevel;
	}

	public void setObjlevel(Integer objlevel) {
		this.objlevel = objlevel;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}