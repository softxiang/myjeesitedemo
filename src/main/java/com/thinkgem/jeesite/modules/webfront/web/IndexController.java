/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.webfront.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.session.Session;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.security.shiro.session.SessionDAO;
import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
import com.thinkgem.jeesite.common.utils.CacheUtils;
import com.thinkgem.jeesite.common.utils.Encodes;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.cms.entity.Site;
import com.thinkgem.jeesite.modules.cms.utils.CmsUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.security.FormAuthenticationFilter;
import com.thinkgem.jeesite.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.sys.web.LoginController;
import com.thinkgem.jeesite.modules.webfront.service.IndexService;

/**
 * 网站Controller
 * 
 * @author ThinkGem
 * @version 2013-5-29
 */
@Controller
@RequestMapping(value = "${indexPath}")
public class IndexController extends BaseController {

	@Autowired
	private IndexService indexService;
	@Autowired
	private SessionDAO sessionDAO;
	@Autowired
	private SystemService systemService;

	/**
	 * 网站首页
	 */
	@RequestMapping
	public String index(Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		model.addAttribute("isIndex", true);
		return "modules/webfront/themes/basic/index";
	}

	/**
	 * 网站首页
	 */
	@RequestMapping(value = "index-{siteId}${urlSuffix}")
	public String index(@PathVariable String siteId, Model model) {
		if (siteId.equals("1")) {
			return "redirect:" + Global.getFrontPath();
		}
		Site site = CmsUtils.getSite(siteId);
		// 子站有独立页面，则显示独立页面
		if (StringUtils.isNotBlank(site.getCustomIndexView())) {
			model.addAttribute("site", site);
			model.addAttribute("isIndex", true);
			return "modules/cms/front/themes/" + site.getTheme() + "/frontIndex" + site.getCustomIndexView();
		}
		// 否则显示子站第一个栏目
		List<Category> mainNavList = CmsUtils.getMainNavList(siteId);
		if (mainNavList.size() > 0) {
			String firstCategoryId = CmsUtils.getMainNavList(siteId).get(0).getId();
			return "redirect:" + Global.getFrontPath() + "/list-" + firstCategoryId + Global.getUrlSuffix();
		} else {
			model.addAttribute("site", site);
			return "modules/cms/front/themes/" + site.getTheme() + "/frontListCategory";
		}
	}

	/**
	 * 内容列表
	 */
	@RequestMapping(value = "list-{categoryId}${urlSuffix}")
	public String list(@PathVariable String categoryId, @RequestParam(required = false, defaultValue = "1") Integer pageNo, @RequestParam(required = false, defaultValue = "15") Integer pageSize, Model model) {
		return "modules/cms/front/themes/";
	}

	/**
	 * 到登录界面
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String toLogin(Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		return "modules/webfront/themes/basic/login";
	}

	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		String targetURL = WebUtils.getCleanParam(request, "targetURL");
		String username = WebUtils.getCleanParam(request, "username");
		String password = WebUtils.getCleanParam(request, "password");
		String captcha = WebUtils.getCleanParam(request, ValidateCodeServlet.VALIDATE_CODE);// 验证码
		boolean isMobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
		String message = "";

		Principal principal = UserUtils.getPrincipal();

		// 如果已经登录，则跳转到管理首页
		if (principal != null) {
			return "redirect:" + indexPath;
		}
		try {
			// 校验登录验证码
			if (LoginController.isValidateCodeLogin(username, false, false)) {
				Session session = UserUtils.getSession();
				String code = (String) session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
				if (StringUtils.isBlank(captcha) || !captcha.toUpperCase().equals(code)) {
					throw new Exception("验证码验证错误！");
				}
			}

			// 校验用户名密码
			User user = systemService.getUserByLoginName(username);
			if (user != null) {
				if (Global.NO.equals(user.getLoginFlag())) {
					throw new Exception("该已帐号禁止登录!");
				}
				if(!SystemService.validatePassword(password, user.getPassword())){
					throw new Exception("用户名密码错误！");
				}
			}
			if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")) {
				message = "用户或密码错误, 请重试.";
			}
		} catch (Exception e) {
			message = e.getLocalizedMessage();
			// 非授权异常，登录失败，验证码加1。
			model.addAttribute("isValidateCodeLogin", isValidateCodeLogin(username, true, false));
			if (logger.isDebugEnabled()) {
				logger.debug("login fail, active session size: {}, message: {}, exception: {}", sessionDAO.getActiveSessions(false).size(), message,e);
			}
		}

		model.addAttribute("username", username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, isMobile);
		model.addAttribute("message", message);

		// 验证失败清空验证码
		request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, IdGen.uuid());

		// 如果是手机登录，则返回JSON字符串
		if (isMobile) {
			return renderString(response, model);
		}
		return targetURL;
	}

	/**
	 * 是否是验证码登录
	 * 
	 * @param useruame
	 *            用户名
	 * @param isFail
	 *            计数加1
	 * @param clean
	 *            计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean) {
		Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.get("loginFailMap");
		if (loginFailMap == null) {
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum == null) {
			loginFailNum = 0;
		}
		if (isFail) {
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean) {
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}

	/**
	 * 到注册界面
	 */
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String toRegister(Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		return "modules/webfront/themes/basic/register";
	}

	/**
	 * 到注册界面
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		return "modules/webfront/themes/basic/register";
	}
}
