/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cmctgy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cmctgy.dao.CommonCategoryDao;
import com.thinkgem.jeesite.modules.cmctgy.entity.CommonCategory;

/**
 * 公共分类树Service
 * @author xiang
 * @version 2015-11-23
 */
@Service
@Transactional(readOnly = true)
public class CommonCategoryService extends TreeService<CommonCategoryDao, CommonCategory> {
	public static final String CACHE_CATEGORY_LIST = "commonCategoryList";
	public CommonCategory get(String id) {
		return super.get(id);
	}
	
	public List<CommonCategory> findList(CommonCategory commonCategory) {
		if (StringUtils.isNotBlank(commonCategory.getParentIds())){
			commonCategory.setParentIds(","+commonCategory.getParentIds()+",");
		}
		return super.findList(commonCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(CommonCategory commonCategory) {
		super.save(commonCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(CommonCategory commonCategory) {
		super.delete(commonCategory);
	}
}