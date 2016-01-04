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
	<div class="head">
		<div class="ty" id="top" style="position:relative; z-index:98;  background-color:#FFF;">
			<div class="logo">
				<a href="#"><img src="${ctxStaticTheme}/images/index_06.jpg" alt="网贷平台_中国首家权威P2P网贷理财行业门户" /></a>
			</div>
			<ul id="abgne_fade_pic30" style="margin-bottom:5px;width:610px;height:70px;overflow:hidden;  float:right;position:relative; z-index:5;">
			</ul>
		</div>
		<div class="menu topmenu topmenu_fixed">
			<div style="width:8px; float:left;">
				<img src="${ctxStaticTheme}/images/index_12.jpg" alt="" />
			</div>
			<div style="width:970px; float:left;">
				<ul>
					<li><a href="${ctxStaticTheme}/images/网贷_网贷审计_网贷帮手_p2p网贷_网贷平台排名.htm">首 页</a></li>
					<li style="width:2px;"><img src="${ctxStaticTheme}/images/index_15.jpg" alt="" /></li>
					<li><a href="http://www.wangdaibangshou.com/ptpl.asp?lid=15">评 论</a>
						<ul>
							<li><a href="http://www.wangdaibangshou.com/ptpl.asp?lid=14">平台调查</a></li>
							<li><a href="http://www.wangdaibangshou.com/ptpl.asp?lid=15">平台评论</a></li>
							<li><a href="http://www.wangdaibangshou.com/dc.asp">平台申请调查</a></li>
						</ul></li>
					<li style="width:2px;"><img src="${ctxStaticTheme}/images/index_15.jpg" alt="" /></li>
					<li><a href="http://bbs.wangdaibangshou.com/forum.php?mod=forumdisplay&amp;fid=157" target="_blank" style="position:relative;">梦评选</a>
						<div style="width:25px; position:absolute; top:-7px; display:inline; left:945px;">
							<img src="${ctxStaticTheme}/images/h.gif" width="25" />
						</div></li>
				</ul>
			</div>
			<div style="width:8px; float:right;">
				<img src="${ctxStaticTheme}/images/index_18.jpg" alt="" />
			</div>
		</div>
		<script type="text/javascript">
			$(function() {
				$(document).scroll(function() {
					if ($(this).scrollTop() > 129) {
						$('.topmenu').addClass('topmenu_fixed');
					} else {
						$('.topmenu').removeClass('topmenu_fixed');
					}
				});

			})
		</script>
	</div>
	<sitemesh:body />
	<!-- /container -->
	<div class="footer">
		<div class="footer-left">
			<ul>
				<li><a href="#">关于我们</a></li>
				<li><a href="#">诚聘英才</a></li>
				<li><a href="#">网站地图</a></li>
				<li><a href="#">使用条款</a></li>
				<li><a href="#">联系方式</a></li>
				<li><a href="#">广告服务</a></li>
				<li><a href="#">意见或建议</a></li>
			</ul>
			<span>版权所有：网贷 | 粤ICP备xxx号</span>
		</div>
		<div class="footer-right">
			<div class="ftop">
				Tel<span> | </span>Tel
			</div>
			<div class="fcen">
				服务时间 9:00 — 18:00 <span>工作日</span>
			</div>
			<div>邮箱 email</div>
		</div>
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
</body>
</html>