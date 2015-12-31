<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/webfront/themes/basic/layouts/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:title default="欢迎光临" /> - Powered By JeeSite</title>
<%@include file="/WEB-INF/views/modules/webfront/themes/basic/layouts/head.jsp"%>
<!-- Baidu tongji analytics -->
<script>
	var _hmt = _hmt || [];
	(function() {
		var hm = document.createElement("script");
		hm.src = "//hm.baidu.com/hm.js?2d87af56de4fe357ec1737bfb420ef6c";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm, s);
	})();
</script>
<sitemesh:head />
</head>
<body>
	<div class="top_h">
		<center>
			<table width="1000" border="0" cellspacing="0" cellpadding="0" align="center">
				<tbody>
					<tr>
						<td width="127" style="float:left;">
							<table width="118" height="38" style="width:118px;">
								<tbody>
									<tr>
										<td width="32" style="width:auto; float:left; font-size:12px;"><img src="${ctxStaticTheme}/images/wb.gif" /></td>
										<td width="80" style="width:auto; margin-left:3px; float:left; font-size:12px;"><a href="#" target="_blank">官方微博</a></td>
									</tr>
								</tbody>
							</table>
						</td>
						<td width="767" align="right" style="width:750px; float:right;">
							<table width="812" style="width:auto;">
								<tbody>
									<tr>
										<td width="275" align="right" style="width:auto; margin-left:1px; margin-right:3px; float:left;">&nbsp;</td>
										<td width="152" align="left" style="width:auto; margin-left:1px; margin-right:3px; float:left;">a：<a target="_blank" href="#" style="font-weight:bold; color:#ff6600;">a</a>
										</td>
										<td width="152" style="width:auto; margin-left:3px; margin-right:3px; float:left;">b：<a target="_blank" href="#" style="font-weight:bold; color:#ff6600;">b</a>
										</td>
										<td width="213" style="width:auto; margin-left:3px; margin-right:3px; float:left;">|广告合作</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
		</center>
	</div>
	<sitemesh:body />
	<!-- /container -->
	<div>
		<footer>
			<div class="foot">
				<a href="http://www.wangdaibangshou.com/about.asp?id=1">关于我们</a> | <a href="http://www.wangdaibangshou.com/about.asp?id=2">加入我们</a> | <a href="http://www.wangdaibangshou.com/about.asp?id=3">服务中心</a>
				| <a href="http://www.wangdaibangshou.com/message.asp">意见建议</a> | <a href="http://www.wangdaibangshou.com/#">网站地图</a> <br /> CopyRight (C) 2012-2015 网贷帮手 版权所有 辽ICP备13002521号 <br />E-mail:wangdaibangshou@163.com
				Code:201005 TEL:010-82345799 Mobail:15040052628 <br />
			</div>
			<!-- 代码 开始 -->
			<div>
				<div id="moquu_wxin" class="moquu_wxin">
					<a href="javascript:void(0)">1
						<div class="moquu_wxinh"></div>
					</a>
				</div>
				<div id="moquu_wshare" class="moquu_wshare">
					<a href="javascript:void(0)">2
						<div class="moquu_wshareh"></div>
					</a>
				</div>
				<div id="moquu_wmaps">
					<a href="javascript:void(0)" class="moquu_wmaps">3</a>
				</div>
				<a id="moquu_top" href="javascript:void(0)" style="display: none;"></a>
			</div>
			<script type="text/javascript">
				function backtop() {
					h = $(window).height();
					t = $(document).scrollTop();
					t > h ? $("#moquu_top").show() : $("#moquu_top").hide();
				}
				$(document).ready(function() {
					backtop();
					$("#moquu_top").click(function() {
						$(document).scrollTop(0)
					})
				})
				$(window).scroll(function() {
					backtop()
				})
			</script>
		</footer>
	</div>
</body>
</html>