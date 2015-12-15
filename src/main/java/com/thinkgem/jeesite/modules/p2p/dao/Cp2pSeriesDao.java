/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pSeries;

/**
 * p2p平台信息DAO接口
 * @author xiang
 * @version 2015-12-12
 */
@MyBatisDao
public interface Cp2pSeriesDao extends CrudDao<Cp2pSeries> {
	
}