/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pParserconfig;
import com.thinkgem.jeesite.modules.p2p.dao.Cp2pParserconfigDao;

/**
 * 采集器配置Service
 * @author xiang
 * @version 2015-12-12
 */
@Service
@Transactional(readOnly = true)
public class Cp2pParserconfigService extends CrudService<Cp2pParserconfigDao, Cp2pParserconfig> {

	public Cp2pParserconfig get(String id) {
		return super.get(id);
	}
	
	public List<Cp2pParserconfig> findList(Cp2pParserconfig cp2pParserconfig) {
		return super.findList(cp2pParserconfig);
	}
	
	public Page<Cp2pParserconfig> findPage(Page<Cp2pParserconfig> page, Cp2pParserconfig cp2pParserconfig) {
		return super.findPage(page, cp2pParserconfig);
	}
	
	@Transactional(readOnly = false)
	public void save(Cp2pParserconfig cp2pParserconfig) {
		super.save(cp2pParserconfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(Cp2pParserconfig cp2pParserconfig) {
		super.delete(cp2pParserconfig);
	}
	
}