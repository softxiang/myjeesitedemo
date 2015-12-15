/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cmtag.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cmtag.entity.CommonTag;
import com.thinkgem.jeesite.modules.cmtag.service.CommonTagService;

/**
 * 标签Controller
 * @author xiang
 * @version 2015-11-23
 */
@Controller
@RequestMapping(value = "${adminPath}/cmtag/commonTag")
public class CommonTagController extends BaseController {

	@Autowired
	private CommonTagService commonTagService;
	
	@ModelAttribute
	public CommonTag get(@RequestParam(required=false) String id) {
		CommonTag entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = commonTagService.get(id);
		}
		if (entity == null){
			entity = new CommonTag();
		}
		return entity;
	}
	
	@RequiresPermissions("cmtag:commonTag:view")
	@RequestMapping(value = {"list", ""})
	public String list(CommonTag commonTag, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CommonTag> page = commonTagService.findPage(new Page<CommonTag>(request, response), commonTag); 
		model.addAttribute("page", page);
		return "modules/cmtag/commonTagList";
	}

	@RequiresPermissions("cmtag:commonTag:view")
	@RequestMapping(value = "form")
	public String form(CommonTag commonTag, Model model) {
		model.addAttribute("commonTag", commonTag);
		return "modules/cmtag/commonTagForm";
	}

	@RequiresPermissions("cmtag:commonTag:edit")
	@RequestMapping(value = "save")
	public String save(CommonTag commonTag, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, commonTag)){
			return form(commonTag, model);
		}
		commonTagService.save(commonTag);
		addMessage(redirectAttributes, "保存标签成功");
		return "redirect:"+Global.getAdminPath()+"/cmtag/commonTag/?repage";
	}
	
	@RequiresPermissions("cmtag:commonTag:edit")
	@RequestMapping(value = "delete")
	public String delete(CommonTag commonTag, RedirectAttributes redirectAttributes) {
		commonTagService.delete(commonTag);
		addMessage(redirectAttributes, "删除标签成功");
		return "redirect:"+Global.getAdminPath()+"/cmtag/commonTag/?repage";
	}
	
}