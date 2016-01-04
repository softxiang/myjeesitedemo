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
	<!-- 
	<script src="${ctxStatic}/jquery-validation/1.9.1/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-validation/1.9.1/additional-methods.min.js" type="text/javascript"></script>
	 -->
	<script src="${ctxStaticTheme}/login.js" type="text/javascript"></script>
</c:if>
</head>
<body>
	<div class="web">
		<div class="ty" style="background-color:#FFF;">
			<div class="main clearfix">
				<form method="post">
					<div class="main-left">
						<h2>用户登录</h2>
						<div class="input-div clearfix">
							<div class="input-text">
								<input type="text" value="" class="input required:true" id="username" name="username"> <span>用户名/手机号</span>
							</div>
							<span class="point">用户名不能为空</span>
						</div>
						<div class="input-div clearfix">
							<div class="input-text">
								<input type="password" value="" class="input" id="password" name="password"> <span>密码</span>
							</div>
							<span class="point"></span>
						</div>
						<div class="input-div clearfix">
							<div class="login_check">
								<span><i class="cur"></i>自动登录(7天)</span> <a href="http://passport.myfunc.com/user/getpwd?refer=aHR0cDovL3d3dy53ZHpqLmNvbS8=">找回密码？</a>
							</div>
						</div>
						<div class="input-div clearfix">
							<input type="button" value="登 录" id="login_submit" class="submit">
						</div>
					</div>
					<input type="hidden" name="csrf_test_name" value="ff51725ce3d648ff38674643263b3c8d"> <input type="hidden" name="login_submit" value="1">
				</form>
				<div class="main-right">
					<div class="main-qq">
						<a href="http://passport.myfunc.com/user/qqconnect?op=init&amp;referer=index.php&amp;statfrom=login_simple&amp;refer=aHR0cDovL3d3dy53ZHpqLmNvbS8="></a> <span>QQ快捷登录</span>
					</div>
					<div class="main-gosign">
						<span>没有账号？</span> <a href="register">立即注册 →</a>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				$(".input-text .input").blur(function() {
					myfunc.user.checkblur1(this, 1);
					return false;
				});
				myfunc.user.checkfocus();
				$(".input-div .submit").click(function() {
					if (myfunc.user.checkblur1($('#username')) && myfunc.user.checkblur1($('#password'), 1)) {
						$('#login_submit').attr('disabled', 'disabled');
						$('#login_submit').val('提交中..');
						myfunc.user.check_submit("csrf_test_name", "ff51725ce3d648ff38674643263b3c8d");
					}
					return false;
				});
			</script>
			<script type="text/javascript">
				document.onkeydown = keyDownSearch;

				function keyDownSearch(e) {
					// 兼容FF和IE和Opera    
					var theEvent = e || window.event;
					var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
					if (code == 13) {
						$('#login_submit').click();//具体处理函数    
						return false;
					}
					return true;
				}
			</script>
		</div>
	</div>
</body>
</html>