/*!
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */

// 添加收藏
function addFavorite(sURL, sTitle){
	if(!sTitle){sTitle = document.title;}
    try{
    	window.external.addFavorite(sURL, sTitle);
    }catch (e){
        try{
        	window.sidebar.addPanel(sTitle, sURL, "");
        }catch (e){
        	alert("加入收藏失败，请使用Ctrl+D进行添加");
        }
    }
}
//设为首页       
function SetHome(url) {
	if (document.all) {
		document.body.style.behavior = 'url(#default#homepage)';
		document.body.setHomePage(url);
	} else {
		alert("您好,您的浏览器不支持自动设置页面为首页功能,请您手动在浏览器里设置该页面为首页!");
	}
}
// 自动滚动：setInterval("autoScroll('.jcarousellite')",3000);
function autoScroll(obj){
	var height = $(obj).find("ul:first li:first").height()+3;
    $(obj).find("ul:first").animate({marginTop:"-" + height + "px"},'slow',function(){
    		$(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
    });
}