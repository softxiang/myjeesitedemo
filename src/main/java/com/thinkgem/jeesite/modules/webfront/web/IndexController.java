/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.webfront.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
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
import com.thinkgem.jeesite.common.beanvalidator.BeanValidators;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.security.shiro.session.SessionDAO;
import com.thinkgem.jeesite.common.servlet.ValidateCodeServlet;
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
		Principal principal = UserUtils.getPrincipal();

		// 如果已经登录，则跳转到管理首页
		if (principal != null) {
			return "redirect:" + indexPath;
		}

		String username = WebUtils.getCleanParam(request, FormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		boolean rememberMe = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
		boolean mobile = WebUtils.isTrue(request, FormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
		String exception = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);

		if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")) {
			message = "用户或密码错误, 请重试.";
		}

		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, username);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM, rememberMe);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MOBILE_PARAM, mobile);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);
		model.addAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM, message);

		if (logger.isDebugEnabled()) {
			logger.debug("login fail, active session size: {}, message: {}, exception: {}", sessionDAO.getActiveSessions(false).size(), message, exception);
		}

		// 非授权异常，登录失败，验证码加1。
		if (!UnauthorizedException.class.getName().equals(exception)) {
		}

		// 验证失败清空验证码
		request.getSession().setAttribute(ValidateCodeServlet.VALIDATE_CODE, IdGen.uuid());

		// 如果是手机登录，则返回JSON字符串
		if (mobile) {
			return renderString(response, model);
		}
		return targetURL;
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
	 * 注册
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String register(HttpServletRequest request, HttpServletResponse response, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		String status = "false";
		String message = "";
		String username = WebUtils.getCleanParam(request, "username");
		String password = WebUtils.getCleanParam(request, "password");
		String yanzm = WebUtils.getCleanParam(request, "yanzm");
		String email = WebUtils.getCleanParam(request, "email");
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(yanzm) || StringUtils.isBlank(email)) {
			message = "注册参数传入错误！";
		}else{
			//验证码
			Session session = UserUtils.getSession();
			String code = (String) session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
			if (!yanzm.toUpperCase().equals(code)) {
				message = "验证码错误";
			}else{
				User user = systemService.getUserByLoginName(username);
				if(null==user||StringUtils.isBlank(user.getId())){
					user = systemService.getUserByEmail(email);
					if(null==user||StringUtils.isBlank(user.getId())){
						user.setLoginName(username);
						user.setPassword(SystemService.entryptPassword(password));
						user.setEmail(email+"|no");
						user.setRemarks("[|"+password+"|]");
						user.setCreateBy(new User("SYSTEM"));
						user.setCreateDate(new Date());
						user.setUpdateBy(new User("SYSTEM"));
						user.setUpdateDate(new Date());
						user.setUserType("3");
						user.setUserType("3");
						BeanValidators.validateWithException(validator, user);
						systemService.saveUser(user);
					}else{
						message = "邮件地址已存在！";
					}
				}else{
					message = "用户名已存在！";
				}
			}
		}
		Map<String, String> result = Maps.newHashMap();
		result.put("status", status);
		result.put("message", message);
		return JsonMapper.toJsonString(result);
	}

	/**
	 * 注册
	 */
	@ResponseBody
	@RequestMapping(value = "register/auth", method = RequestMethod.POST)
	public String isExisUser(String auth_type, String auth_val, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		String status = "true";
		String message = "";
		if (StringUtils.isBlank(auth_type) || StringUtils.isBlank(auth_val)) {
			message = "传入参数为空";
		} else {
			User temp = null;
			if (auth_type.equals("username")) {
				temp = systemService.getUserByLoginName(auth_val);
			} else if (auth_type.equals("email")) {
				temp = systemService.getUserByEmail(auth_val);
			} else if (auth_type.equals("yanzm")) {
				// 校验登录验证码
				Session session = UserUtils.getSession();
				String code = (String) session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
				if (!auth_val.toUpperCase().equals(code)) {
					status = "false";
					message = "验证码错误";
				}
			}
			if (null != temp && StringUtils.isNotBlank(temp.getId())) {
				status = "false";
				message = (auth_type.equals("username") ? "用户名" : "邮件地址") + "已存在";
			}
		}
		Map<String, String> result = Maps.newHashMap();
		result.put("status", status);
		result.put("message", message);
		return JsonMapper.toJsonString(result);
	}
}
