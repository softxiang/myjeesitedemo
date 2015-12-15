/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cmctgy.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cmctgy.entity.CommonCategory;
import com.thinkgem.jeesite.modules.cmctgy.service.CommonCategoryService;

/**
 * 公共分类树Controller
 * @author xiang
 * @version 2015-11-23
 */
@Controller
@RequestMapping(value = "${adminPath}/cmctgy/commonCategory")
public class CommonCategoryController extends BaseController {

	@Autowired
	private CommonCategoryService commonCategoryService;
	
	@ModelAttribute
	public CommonCategory get(@RequestParam(required=false) String id) {
		CommonCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = commonCategoryService.get(id);
		}
		if (entity == null){
			entity = new CommonCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("cmctgy:commonCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(CommonCategory commonCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CommonCategory> list = commonCategoryService.findList(commonCategory); 
		model.addAttribute("list", list);
		return "modules/cmctgy/commonCategoryList";
	}

	@RequiresPermissions("cmctgy:commonCategory:view")
	@RequestMapping(value = "form")
	public String form(CommonCategory commonCategory, Model model) {
		if (commonCategory.getParent()!=null && StringUtils.isNotBlank(commonCategory.getParent().getId())){
			commonCategory.setParent(commonCategoryService.get(commonCategory.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(commonCategory.getId())){
				CommonCategory commonCategoryChild = new CommonCategory();
				commonCategoryChild.setParent(new CommonCategory(commonCategory.getParent().getId()));
				List<CommonCategory> list = commonCategoryService.findList(commonCategory); 
				if (list.size() > 0){
					commonCategory.setSort(list.get(list.size()-1).getSort());
					if (commonCategory.getSort() != null){
						commonCategory.setSort(commonCategory.getSort() + 30);
					}
				}
			}
		}
		if (commonCategory.getSort() == null){
			commonCategory.setSort(30);
		}
		model.addAttribute("commonCategory", commonCategory);
		return "modules/cmctgy/commonCategoryForm";
	}

	@RequiresPermissions("cmctgy:commonCategory:edit")
	@RequestMapping(value = "save")
	public String save(CommonCategory commonCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, commonCategory)){
			return form(commonCategory, model);
		}
		commonCategoryService.save(commonCategory);
		addMessage(redirectAttributes, "保存分类成功");
		return "redirect:"+Global.getAdminPath()+"/cmctgy/commonCategory/?repage";
	}
	
	@RequiresPermissions("cmctgy:commonCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(CommonCategory commonCategory, RedirectAttributes redirectAttributes) {
		commonCategoryService.delete(commonCategory);
		addMessage(redirectAttributes, "删除分类成功");
		return "redirect:"+Global.getAdminPath()+"/cmctgy/commonCategory/?repage";
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<CommonCategory> list = commonCategoryService.findList(new CommonCategory());
		for (int i=0; i<list.size(); i++){
			CommonCategory e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}