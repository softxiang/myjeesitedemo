/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pPlatform;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pPlatformService;

/**
 * p2p平台Controller
 * @author xiang
 * @version 2015-12-18
 */
@Controller
@RequestMapping(value = "${adminPath}/p2p/cp2pPlatform")
public class Cp2pPlatformController extends BaseController {

	@Autowired
	private Cp2pPlatformService cp2pPlatformService;
	
	@ModelAttribute
	public Cp2pPlatform get(@RequestParam(required=false) String id) {
		Cp2pPlatform entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cp2pPlatformService.get(id);
		}
		if (entity == null){
			entity = new Cp2pPlatform();
		}
		return entity;
	}
	
	@RequiresPermissions("p2p:cp2pPlatform:view")
	@RequestMapping(value = {"list", ""})
	public String list(Cp2pPlatform cp2pPlatform, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Cp2pPlatform> page = cp2pPlatformService.findPage(new Page<Cp2pPlatform>(request, response), cp2pPlatform); 
		model.addAttribute("page", page);
		return "modules/p2p/cp2pPlatformList";
	}

	@RequiresPermissions("p2p:cp2pPlatform:view")
	@RequestMapping(value = "form")
	public String form(Cp2pPlatform cp2pPlatform, Model model) {
		model.addAttribute("cp2pPlatform", cp2pPlatform);
		return "modules/p2p/cp2pPlatformForm";
	}

	@RequiresPermissions("p2p:cp2pPlatform:edit")
	@RequestMapping(value = "save")
	public String save(Cp2pPlatform cp2pPlatform, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cp2pPlatform)){
			return form(cp2pPlatform, model);
		}
		cp2pPlatformService.save(cp2pPlatform);
		addMessage(redirectAttributes, "保存p2p平台成功");
		return "redirect:"+Global.getAdminPath()+"/p2p/cp2pPlatform/?repage";
	}
	
	@RequiresPermissions("p2p:cp2pPlatform:edit")
	@RequestMapping(value = "delete")
	public String delete(Cp2pPlatform cp2pPlatform, RedirectAttributes redirectAttributes) {
		cp2pPlatformService.delete(cp2pPlatform);
		addMessage(redirectAttributes, "删除p2p平台成功");
		return "redirect:"+Global.getAdminPath()+"/p2p/cp2pPlatform/?repage";
	}

}