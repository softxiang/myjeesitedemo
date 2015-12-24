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
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pPlatform;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pSeries;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pPlatformService;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pSeriesService;

/**
 * p2p产品系列Controller
 * 
 * @author xiang
 * @version 2015-12-18
 */
@Controller
@RequestMapping(value = "${adminPath}/p2p/cp2pSeries")
public class Cp2pSeriesController extends BaseController {

	@Autowired
	private Cp2pSeriesService cp2pSeriesService;
	@Autowired
	private Cp2pPlatformService cp2pPlatformService;

	@ModelAttribute
	public Cp2pSeries get(@RequestParam(required = false) String id) {
		Cp2pSeries entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = cp2pSeriesService.get(id);
		}
		if (entity == null) {
			entity = new Cp2pSeries();
		}
		return entity;
	}

	@RequiresPermissions("p2p:cp2pSeries:view")
	@RequestMapping(value = { "list", "" })
	public String list(Cp2pSeries cp2pSeries, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Cp2pSeries> page = cp2pSeriesService.findPage(new Page<Cp2pSeries>(request, response), cp2pSeries);
		model.addAttribute("cp2pPlatformList", cp2pPlatformService.findList(new Cp2pPlatform()));
		model.addAttribute("page", page);
		return "modules/p2p/cp2pSeriesList";
	}

	@RequiresPermissions("p2p:cp2pSeries:view")
	@RequestMapping(value = "form")
	public String form(Cp2pSeries cp2pSeries, Model model) {
		model.addAttribute("cp2pSeries", cp2pSeries);
		model.addAttribute("cp2pPlatformList", cp2pPlatformService.findList(new Cp2pPlatform()));
		return "modules/p2p/cp2pSeriesForm";
	}

	@RequiresPermissions("p2p:cp2pSeries:edit")
	@RequestMapping(value = "save")
	public String save(Cp2pSeries cp2pSeries, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cp2pSeries)) {
			return form(cp2pSeries, model);
		}
		cp2pSeriesService.save(cp2pSeries);
		addMessage(redirectAttributes, "保存p2p产品系列成功");
		return "redirect:" + Global.getAdminPath() + "/p2p/cp2pSeries/?repage";
	}

	@RequiresPermissions("p2p:cp2pSeries:edit")
	@RequestMapping(value = "delete")
	public String delete(Cp2pSeries cp2pSeries, RedirectAttributes redirectAttributes) {
		cp2pSeriesService.delete(cp2pSeries);
		addMessage(redirectAttributes, "删除p2p产品系列成功");
		return "redirect:" + Global.getAdminPath() + "/p2p/cp2pSeries/?repage";
	}

	/*@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : text.trim());
			}

			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
			// @Override
			// public String getAsText() {
			// Object value = getValue();
			// return value != null ? DateUtils.formatDateTime((Date)value) :
			// "";
			// }
		});
	}*/

}