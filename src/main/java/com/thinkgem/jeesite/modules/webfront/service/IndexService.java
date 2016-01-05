/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.webfront.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;

/**
 * 
 * 
 * @author xiang
 * @version 2015-11-23
 */
@Service
@Transactional(readOnly = true)
public class IndexService extends BaseService {
	@Autowired
	private SystemService systemService;

	public User login(String username, String password) {
		User user = null;
		return user;
	}
}