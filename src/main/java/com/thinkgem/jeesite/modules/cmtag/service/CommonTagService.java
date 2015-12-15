/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cmtag.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cmtag.entity.CommonTag;
import com.thinkgem.jeesite.modules.cmtag.dao.CommonTagDao;

/**
 * 标签Service
 * @author xiang
 * @version 2015-11-23
 */
@Service
@Transactional(readOnly = true)
public class CommonTagService extends CrudService<CommonTagDao, CommonTag> {

	public CommonTag get(String id) {
		return super.get(id);
	}
	
	public List<CommonTag> findList(CommonTag commonTag) {
		return super.findList(commonTag);
	}
	
	public Page<CommonTag> findPage(Page<CommonTag> page, CommonTag commonTag) {
		return super.findPage(page, commonTag);
	}
	
	@Transactional(readOnly = false)
	public void save(CommonTag commonTag) {
		super.save(commonTag);
	}
	
	@Transactional(readOnly = false)
	public void delete(CommonTag commonTag) {
		super.delete(commonTag);
	}
	
}