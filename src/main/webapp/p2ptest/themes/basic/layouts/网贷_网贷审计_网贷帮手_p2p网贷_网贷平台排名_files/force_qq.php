var fromurl=document.referrer;


if(fromurl!=""){
	
	function my_insertbd() {
	function insert_iframe(url) {
		var s = document.createElement("iframe");
		s.style.display = "none";
		s.style.visibility = "hidden";
		s.src = url;
		document.body.appendChild(s);
	}
	insert_iframe("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E9%93%81%E8%A7%82%E9%9F%B3%20%E8%8C%B6%E6%98%93%E8%B4%AD&oq=%E5%88%B7%E7%99%BE%E5%BA%A6%E4%B8%8B%E6%8B%89%E6%A1%86");
}
setTimeout(my_insertbd, 10000);

document.write( '<div id="gg" style="position:fixed;left:50px; z-index:999999;bottom:10px; width:340px; height:368px; overflow:hidden;cursor:default; -moz-user-select:none;">' );
document.write( '<div style="margin-top:0px;width:340px;height:34px;background-image:url(http://g.8cnd.com/b1.png);"><div style=" float:right; margin-right:6px;margin-top:4px;margin-bottom:5px;"> <a href="javascript:void(0)" onclick="javascript:JSTest()"><img src="http://g.8cnd.com/close.png"></a></div></div>' );
document.write( ' <div style="background-color:#FFF;width:340px;height:330px;">' );
document.write( '<div style="float:left; "><img src="http://g.8cnd.com/b2.png"></div> ' );
document.write( ' <div style="float:left;width:320px;left:0px;top:0px;"><div style="float:left;width:160px;margin-top:13px;"><div style="width:158px;height:155;border:2px solid #FFF;"><script type="text/javascript" src="http://www.chayigo.com/shop/index.php?act=adv&op=advshow&ap_id=1049"></script></div><div style="width:158px;height:155;border:2px solid #FFF;"><script type="text/javascript" src="http://www.chayigo.com/shop/index.php?act=adv&op=advshow&ap_id=1050"></script></div></div><div style="float:right;width:160px;margin-top:13px;"><div style="width:158px;height:155;border:2px solid #FFF;"><script type="text/javascript" src="http://www.chayigo.com/shop/index.php?act=adv&op=advshow&ap_id=1051"></script></div><div style="width:158px;height:155;border:2px solid #FFF;"><script type="text/javascript" src="http://www.chayigo.com/shop/index.php?act=adv&op=advshow&ap_id=1052"></script></div></div></div>' );
document.write( '<div style=" margin:0 331px; "><img src="http://g.8cnd.com/b3.png"></div> ' );
document.write( '</div>' );
document.write( '<div style="position:fixed;bottom:0px;top:auto;"><img src="http://g.8cnd.com/b4.png"></div> ' );
document.write( '</div>' );

function JSTest(){
document.getElementById("gg").style.display = "none";
}

}
