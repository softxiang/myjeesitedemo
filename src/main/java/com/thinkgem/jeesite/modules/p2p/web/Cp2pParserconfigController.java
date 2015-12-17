/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.p2p.web;

import java.util.List;

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
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pParserconfig;
import com.thinkgem.jeesite.modules.p2p.entity.Cp2pSeries;
import com.thinkgem.jeesite.modules.p2p.p2pparser.quartz.QuartzServices;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pParserconfigService;
import com.thinkgem.jeesite.modules.p2p.service.Cp2pSeriesService;

/**
 * 采集器配置Controller
 * 
 * @author xiang
 * @version 2015-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/p2p/cp2pParserconfig")
public class Cp2pParserconfigController extends BaseController {

	@Autowired
	private Cp2pParserconfigService cp2pParserconfigService;
	@Autowired
	private Cp2pSeriesService cp2pSeriesService;
	@Autowired
	private QuartzServices quartzServices;

	@ModelAttribute
	public Cp2pParserconfig get(@RequestParam(required = false) String id) {
		Cp2pParserconfig entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = cp2pParserconfigService.get(id);
		}
		if (entity == null) {
			entity = new Cp2pParserconfig();
		}
		return entity;
	}

	@RequiresPermissions("p2p:cp2pParserconfig:view")
	@RequestMapping(value = { "list", "" })
	public String list(Cp2pParserconfig cp2pParserconfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Cp2pSeries> seriesList = cp2pSeriesService.findList(new Cp2pSeries());
		model.addAttribute("seriesList", seriesList);

		Page<Cp2pParserconfig> page = cp2pParserconfigService.findPage(new Page<Cp2pParserconfig>(request, response), cp2pParserconfig);
		model.addAttribute("page", page);
		return "modules/p2p/cp2pParserconfigList";
	}

	@RequiresPermissions("p2p:cp2pParserconfig:view")
	@RequestMapping(value = "form")
	public String form(Cp2pParserconfig cp2pParserconfig, Model model) {
		List<Cp2pSeries> seriesList = cp2pSeriesService.findList(new Cp2pSeries());
		model.addAttribute("seriesList", seriesList);
		model.addAttribute("cp2pParserconfig", cp2pParserconfig);
		return "modules/p2p/cp2pParserconfigForm";
	}

	@RequiresPermissions("p2p:cp2pParserconfig:edit")
	@RequestMapping(value = "save")
	public String save(Cp2pParserconfig cp2pParserconfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, cp2pParserconfig)) {
			return form(cp2pParserconfig, model);
		}
		cp2pParserconfigService.save(cp2pParserconfig);
		addMessage(redirectAttributes, "保存采集器配置成功");
		return "redirect:" + Global.getAdminPath() + "/p2p/cp2pParserconfig/?repage";
	}

	@RequiresPermissions("p2p:cp2pParserconfig:edit")
	@RequestMapping(value = "delete")
	public String delete(Cp2pParserconfig cp2pParserconfig, RedirectAttributes redirectAttributes) {
		cp2pParserconfigService.delete(cp2pParserconfig);
		addMessage(redirectAttributes, "删除采集器配置成功");
		return "redirect:" + Global.getAdminPath() + "/p2p/cp2pParserconfig/?repage";
	}

	@RequiresPermissions("p2p:cp2pParserconfig:edit")
	@RequestMapping(value = "addQuartz")
	public String addQuartz(Cp2pParserconfig cp2pParserconfig, RedirectAttributes redirectAttributes) {
		cp2pParserconfig = cp2pParserconfigService.get(cp2pParserconfig);
		if (StringUtils.isBlank(cp2pParserconfig.getCp2pSeries().getId())
				||StringUtils.isBlank(cp2pParserconfig.getClassname())
				||StringUtils.isBlank(cp2pParserconfig.getCronex())) {
			addMessage(redirectAttributes, "加入Quartz失败，参数错误！");
		}else{
			if(quartzServices.addJob(cp2pParserconfig)){
				addMessage(redirectAttributes, "加入Quartz成功");
			}else{
				addMessage(redirectAttributes, "加入Quartz失败，请联系管理员！");
			}
		}
		return "redirect:" + Global.getAdminPath() + "/p2p/cp2pParserconfig/?repage";
	}
}