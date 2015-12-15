/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cmtag.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cmtag.entity.CommonTag;

/**
 * 标签DAO接口
 * @author xiang
 * @version 2015-11-23
 */
@MyBatisDao
public interface CommonTagDao extends CrudDao<CommonTag> {
	
}