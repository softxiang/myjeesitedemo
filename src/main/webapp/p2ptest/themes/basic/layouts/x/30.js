document.writeln("<ul id=\"abgne_fade_pic30\" style=\"margin-bottom:5px;width:610px;height:70px;overflow:hidden;  float:right;position:relative; z-index:5;\">");
document.writeln("</ul>");
function addHits30(c,id){if(c==1){try{jQuery.getScript('http://www.3fzf.com/plus/ajaxs.asp?action=HitsGuangGao&id='+id,function(){});}catch(e){}}}
function checkDate30(date_arr){
 var date=new Date();
 date_arr=date_arr.split("-");
var year=parseInt(date_arr[0]);
var month=parseInt(date_arr[1])-1;
var day=0;
if (date_arr[2].indexOf(" ")!=-1)
day=parseInt(date_arr[2].split(" ")[0]);
else
day=parseInt(date_arr[2]);
var date1=new Date(year,month,day);
if(date.valueOf()>date1.valueOf())
 return false;
else
 return true
}
$(function(){
var sw = 0;
function myShow(i){
$("#abgne_fade_pic30 li").eq(i).stop(true,true).fadeIn(600).siblings("li").fadeOut(600);
}
$("#abgne_fade_pic").hover(function(){
if(myTime){
clearInterval(myTime);
}
},function(){
myTime = setInterval(function(){
myShow(sw)
 sw++;
 if(sw==$("#abgne_fade_pic30  li").length){sw=0;}
} , 3500);
});
var myTime = setInterval(function(){
 myShow(sw)
 sw++;
  if(sw==$("#abgne_fade_pic30  li").length){sw=0;}
	} , 3500);
});
