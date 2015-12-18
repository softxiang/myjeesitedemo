/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pPlatform;
import com.thinkgem.jeesite.modules.p2p.dao.Cp2pPlatformDao;

/**
 * p2p平台Service
 * @author xiang
 * @version 2015-12-18
 */
@Service
@Transactional(readOnly = true)
public class Cp2pPlatformService extends CrudService<Cp2pPlatformDao, Cp2pPlatform> {

	public Cp2pPlatform get(String id) {
		return super.get(id);
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
	}
	
	@Transactional(readOnly = false)
	public void delete(Cp2pPlatform cp2pPlatform) {
		super.delete(cp2pPlatform);
	}
	
}