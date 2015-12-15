/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pProducts;
import com.thinkgem.jeesite.modules.p2p.dao.Cp2pProductsDao;

/**
 * p2p产品Service
 * @author xiang
 * @version 2015-12-12
 */
@Service
@Transactional(readOnly = true)
public class Cp2pProductsService extends CrudService<Cp2pProductsDao, Cp2pProducts> {

	public Cp2pProducts get(String id) {
		return super.get(id);
	}
	
	public List<Cp2pProducts> findList(Cp2pProducts cp2pProducts) {
		return super.findList(cp2pProducts);
	}
	
	public Page<Cp2pProducts> findPage(Page<Cp2pProducts> page, Cp2pProducts cp2pProducts) {
		return super.findPage(page, cp2pProducts);
	}
	
	@Transactional(readOnly = false)
	public void save(Cp2pProducts cp2pProducts) {
		super.save(cp2pProducts);
	}
	
	@Transactional(readOnly = false)
	public void delete(Cp2pProducts cp2pProducts) {
		super.delete(cp2pProducts);
	}
	
}