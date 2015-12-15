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
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pProducts;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pProductsService;

/**
 * p2p产品Controller
 * @author xiang
 * @version 2015-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/p2p/cp2pProducts")
public class Cp2pProductsController extends BaseController {

	@Autowired
	private Cp2pProductsService cp2pProductsService;
	
	@ModelAttribute
	public Cp2pProducts get(@RequestParam(required=false) String id) {
		Cp2pProducts entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = cp2pProductsService.get(id);
		}
		if (entity == null){
			entity = new Cp2pProducts();
		}
		return entity;
	}
	
	@RequiresPermissions("p2p:cp2pProducts:view")
	@RequestMapping(value = {"list", ""})
	public String list(Cp2pProducts cp2pProducts, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Cp2pProducts> page = cp2pProductsService.findPage(new Page<Cp2pProducts>(request, response), cp2pProducts); 
		model.addAttribute("page", page);
		return "modules/p2p/cp2pProductsList";
	}

	@RequiresPermissions("p2p:cp2pProducts:view")
	@RequestMapping(value = "form")
	public String form(Cp2pProducts cp2pProducts, Model model) {
		model.addAttribute("cp2pProducts", cp2pProducts);
		return "modules/p2p/cp2pProductsForm";
	}

	@RequiresPermissions("p2p:cp2pProducts:edit")
	@RequestMapping(value = "save")
	public String save(Cp2pProducts cp2pProducts, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cp2pProducts)){
			return form(cp2pProducts, model);
		}
		cp2pProductsService.save(cp2pProducts);
		addMessage(redirectAttributes, "保存p2p产品成功");
		return "redirect:"+Global.getAdminPath()+"/p2p/cp2pProducts/?repage";
	}
	
	@RequiresPermissions("p2p:cp2pProducts:edit")
	@RequestMapping(value = "delete")
	public String delete(Cp2pProducts cp2pProducts, RedirectAttributes redirectAttributes) {
		cp2pProductsService.delete(cp2pProducts);
		addMessage(redirectAttributes, "删除p2p产品成功");
		return "redirect:"+Global.getAdminPath()+"/p2p/cp2pProducts/?repage";
	}

}