<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/webfront/themes/basic/layouts/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta name="decorator" content="index_default_${site.theme}" />
<meta name="description" content="JeeSite ${site.description}" />
<meta name="keywords" content="JeeSite ${site.keywords}" />
<c:if test="${site.theme eq 'basic'}">
	<link href="${ctxStaticTheme}/login.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStaticTheme}/login.js" type="text/javascript"></script>
</c:if>
</head>
<body>
	<div class="web">
		<div class="ty" style="background-color:#FFF;">
			<div class="main clearfix">
				<div class="main-left">
					<h2>立即注册</h2>
					<div class="input-div clearfix">
						<div class="input-text">
							<input type="text" value="" class="input register-ajax" id="username"> <span>用户名</span>
						</div>
						<span class="point">用户名不超过15个字符</span>
					</div>
					<div class="input-div clearfix">
						<div class="input-text">
							<input type="password" value="" class="input" id="password"> <span>密码</span>
						</div>
						<span class="point">密码不少于6位数</span>
					</div>
					<div class="input-div clearfix">
						<div class="input-text">
							<input type="password" value="" class="input" id="password2"> <span>再次输入密码</span>
						</div>
						<span class="point"></span>
					</div>
					<div class="input-div clearfix">
						<div class="input-text">
							<input type="text" value="" class="input register-ajax" id="email"> <span>E-mail</span>
						</div>
						<span class="point"></span>
					</div>
					<div class="input-div clearfix">
						<div class="input-text input-yzm">
							<input type="text" value="" class="input register-ajax" id="yanzm"> <span>验证码</span>
						</div>
						<div class="yanzm">
							<img src="http://passport.wdzj.com/captcha/1451807877.5953.jpg" width="85" height="30" style="border:0;" alt=" ">
						</div>
						<span class="point"></span>
					</div>
					<div class="input-div clearfix">
						<input type="hidden" id="reg_free" value="1"> <input type="button" name="reg_submit" id="reg_submit" value="提 交" class="submit">
					</div>
				</div>
				<div class="main-right">
					<div class="main-qq">
						<a href="http://passport.wdzj.com/user/qqconnect?op=init&amp;referer=index.php&amp;statfrom=login_simple&amp;refer=aHR0cDovL3d3dy53ZHpqLmNvbS8="></a> <span>QQ快捷登录</span>
					</div>
					<div class="main-gosign">
						<span>已有账号？</span> <a href="login">立即登录 →</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>