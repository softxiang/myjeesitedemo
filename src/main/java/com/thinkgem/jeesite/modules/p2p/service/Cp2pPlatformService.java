/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pPlatform;
import com.thinkgem.jeesite.modules.p2p.dao.Cp2pPlatformDao;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pSeries;
import com.thinkgem.jeesite.modules.p2p.dao.Cp2pSeriesDao;

/**
 * p2p平台信息Service
 * @author xiang
 * @version 2015-12-12
 */
@Service
@Transactional(readOnly = true)
public class Cp2pPlatformService extends CrudService<Cp2pPlatformDao, Cp2pPlatform> {

	@Autowired
	private Cp2pSeriesDao cp2pSeriesDao;
	
	public Cp2pPlatform get(String id) {
		Cp2pPlatform cp2pPlatform = super.get(id);
		cp2pPlatform.setCp2pSeriesList(cp2pSeriesDao.findList(new Cp2pSeries(cp2pPlatform)));
		return cp2pPlatform;
	}
	
	public List<Cp2pPlatform> findList(Cp2pPlatform cp2pPlatform) {
		return super.findList(cp2pPlatform);
	}
	
	public Page<Cp2pPlatform> findPage(Page<Cp2pPlatform> page, Cp2pPlatform cp2pPlatform) {
		return super.findPage(page, cp2pPlatform);
	}
	
	@Transactional(readOnly = false)
	public void save(Cp2pPlatform cp2pPlatform) {
		super.save(cp2pPlatform);
		for (Cp2pSeries cp2pSeries : cp2pPlatform.getCp2pSeriesList()){
			if (cp2pSeries.getId() == null){
				continue;
			}
			if (Cp2pSeries.DEL_FLAG_NORMAL.equals(cp2pSeries.getDelFlag())){
				if (StringUtils.isBlank(cp2pSeries.getId())){
					cp2pSeries.setCp2pPlatform(cp2pPlatform);
					cp2pSeries.preInsert();
					cp2pSeriesDao.insert(cp2pSeries);
				}else{
					cp2pSeries.preUpdate();
					cp2pSeriesDao.update(cp2pSeries);
				}
			}else{
				cp2pSeriesDao.delete(cp2pSeries);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Cp2pPlatform cp2pPlatform) {
		super.delete(cp2pPlatform);
		cp2pSeriesDao.delete(new Cp2pSeries(cp2pPlatform));
	}
	
}