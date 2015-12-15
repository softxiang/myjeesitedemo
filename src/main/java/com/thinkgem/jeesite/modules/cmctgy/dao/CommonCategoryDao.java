/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cmctgy.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cmctgy.entity.CommonCategory;

/**
 * 公共分类树DAO接口
 * @author xiang
 * @version 2015-11-23
 */
@MyBatisDao
public interface CommonCategoryDao extends TreeDao<CommonCategory> {
	
}