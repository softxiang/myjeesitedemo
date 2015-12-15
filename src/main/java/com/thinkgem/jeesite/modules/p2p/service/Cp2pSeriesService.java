/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pSeries;
import com.thinkgem.jeesite.modules.p2p.dao.Cp2pSeriesDao;

/**
 * p2p产品系列Service
 * @author xiang
 * @version 2015-12-12
 */
@Service
@Transactional(readOnly = true)
public class Cp2pSeriesService extends CrudService<Cp2pSeriesDao, Cp2pSeries> {

	public Cp2pSeries get(String id) {
		return super.get(id);
	}
	
	public List<Cp2pSeries> findList(Cp2pSeries cp2pSeries) {
		return super.findList(cp2pSeries);
	}
	
	public Page<Cp2pSeries> findPage(Page<Cp2pSeries> page, Cp2pSeries cp2pSeries) {
		return super.findPage(page, cp2pSeries);
	}
	
	@Transactional(readOnly = false)
	public void save(Cp2pSeries cp2pSeries) {
		super.save(cp2pSeries);
	}
	
	@Transactional(readOnly = false)
	public void delete(Cp2pSeries cp2pSeries) {
		super.delete(cp2pSeries);
	}
	
}