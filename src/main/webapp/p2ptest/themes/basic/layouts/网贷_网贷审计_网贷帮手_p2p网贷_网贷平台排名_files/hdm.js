// JavaScript Document
function tabChange(obj,id)
{
 var arrayli = obj.parentNode.getElementsByTagName("li"); //获取li数组
 var arrayul = document.getElementById(id).getElementsByTagName("ul"); //获取ul数组
 for(i=0;i<arrayul.length;i++)
 {
  if(obj==arrayli[i])
  {
   arrayli[i].className = "cli";
   arrayul[i].className = "";
  }
  else
  {
   arrayli[i].className = "";
   arrayul[i].className = "hidden";
  }
 }
}

function taba(obj)
{
 var arrayli = obj.parentNode.getElementsByTagName("li"); //获取li数组
 for(i=0;i<2;i++)
 {
  if(obj==arrayli[i])
  {
   arrayli[i].className = "cli";
  }
  else
  {
   arrayli[i].className = "";
  }
 }
}

function inponk(nid){
	var pid=document.getElementById(nid);
	if (pid.value=="请输入关键字"){
		pid.value="";
		}
}

function inpoku(nid){
	var paid=document.getElementById(nid);
	if (isNaN(paid.value)){
		alert('只能输入数字');
		paid.value='';
		}
}

function inpofp(nid){
	var paid=document.getElementById(nid);
	if (isNaN(paid.value)){
		alert('只能输入数字');
		paid.value='';
		}
}
function qkbd(){
	document.xbcx.reset();
	}


function tjsjd(kkid,url){
document.getElementById(kkid).src=url;
}


$(document).ready(function(){
$("#back-to-top").hide();
$(function () {$(window).scroll(function(){if ($(window).scrollTop()>100){$("#back-to-top").fadeIn(1500);}else{$("#back-to-top").fadeOut(1500);}});
$("#back-to-top").click(function(){$('body,html').animate({scrollTop:0},1000);return false;});});});


function resizeimg(ImgD,iwidth,iheight) {
     var image=new Image();
     image.src=ImgD.src;
     if(image.width>0 && image.height>0){
        if(image.width/image.height>= iwidth/iheight){
           if(image.width>iwidth){
               ImgD.width=iwidth;
               ImgD.height=(image.height*iwidth)/image.width;
           }else{
                  ImgD.width=image.width;
                  ImgD.height=image.height;
                }
               ImgD.alt=image.width+"×"+image.height;
        }
        else{
                if(image.height>iheight){
                       ImgD.height=iheight;
                       ImgD.width=(image.width*iheight)/image.height;
                }else{
                        ImgD.width=image.width;
                        ImgD.height=image.height;
                     }
                ImgD.alt=image.width+"×"+image.height;
            }
　　　　　ImgD.style.cursor= "pointer"; //改变鼠标指针
    }
}

function checkForm(){
	if (document.formx.title.value==""){
		
			alert("请输入标题！");
			document.formx.title.focus();
			return false;
		}
	if (document.formx.je.value=="" || document.formx.je.value=="0"){
		
			alert("请正确输入金额！");
			document.formx.je.focus();
			return false;
		}
	if (document.formx.fs.value==""){
		
			alert("请输入还款方式！");
			document.formx.fs.focus();
			return false;
		}
	if (document.formx.jd.value=="" || document.formx.jd.value=="0"){
		
			alert("请正确输入进度！");
			document.formx.jd.focus();
			return false;
		}
	if (document.formx.ll.value=="" || document.formx.ll.value=="0"){
		
			alert("请正确输入利率！");
			document.formx.ll.focus();
			return false;
		}
	if (document.formx.y.value=="0" && document.formx.t.value=="0"){
		
			alert("请正确输入期限！");
			return false;
		}	
	}

function checka(){
	if (document.formw.title.value==""){
		
			alert("请输入标题！");
			document.formw.title.focus();
			return false;
		}
	}

function checkc(){
	if (document.formw.title.value==""){
		
			alert("请输入标题！");
			document.formw.title.focus();
			return false;
		}
	if (document.formw.je.value=="" || document.formw.je.value=="0"){
		
			alert("请正确输入欠款总额！");
			document.formw.je.focus();
			return false;
		}
	if (document.formw.sf.value==""){
		
			alert("请输入身份证！");
			document.formw.sf.focus();
			return false;
		}
	if (document.formw.qb.value==""){
		
			alert("请输入逾期天数！");
			document.formw.qb.focus();
			return false;
		}
	if (document.formw.yb.value==""){
		
			alert("请输入逾期笔数！");
			document.formw.yb.focus();
			return false;
		}
	if (document.formw.dh.value==""){
		
			alert("请输入电话！");
			document.formw.dh.focus();
			return false;
		}
	if (document.formw.dz.value==""){
		
			alert("请输入地址！");
			document.formw.dz.focus();
			return false;
		}	
	}