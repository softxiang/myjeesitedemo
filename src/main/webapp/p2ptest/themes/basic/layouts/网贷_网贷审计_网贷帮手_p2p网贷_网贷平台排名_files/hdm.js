// JavaScript Document
function tabChange(obj,id)
{
 var arrayli = obj.parentNode.getElementsByTagName("li"); //��ȡli����
 var arrayul = document.getElementById(id).getElementsByTagName("ul"); //��ȡul����
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
 var arrayli = obj.parentNode.getElementsByTagName("li"); //��ȡli����
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
	if (pid.value=="������ؼ���"){
		pid.value="";
		}
}

function inpoku(nid){
	var paid=document.getElementById(nid);
	if (isNaN(paid.value)){
		alert('ֻ����������');
		paid.value='';
		}
}

function inpofp(nid){
	var paid=document.getElementById(nid);
	if (isNaN(paid.value)){
		alert('ֻ����������');
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
               ImgD.alt=image.width+"��"+image.height;
        }
        else{
                if(image.height>iheight){
                       ImgD.height=iheight;
                       ImgD.width=(image.width*iheight)/image.height;
                }else{
                        ImgD.width=image.width;
                        ImgD.height=image.height;
                     }
                ImgD.alt=image.width+"��"+image.height;
            }
����������ImgD.style.cursor= "pointer"; //�ı����ָ��
    }
}

function checkForm(){
	if (document.formx.title.value==""){
		
			alert("��������⣡");
			document.formx.title.focus();
			return false;
		}
	if (document.formx.je.value=="" || document.formx.je.value=="0"){
		
			alert("����ȷ�����");
			document.formx.je.focus();
			return false;
		}
	if (document.formx.fs.value==""){
		
			alert("�����뻹�ʽ��");
			document.formx.fs.focus();
			return false;
		}
	if (document.formx.jd.value=="" || document.formx.jd.value=="0"){
		
			alert("����ȷ������ȣ�");
			document.formx.jd.focus();
			return false;
		}
	if (document.formx.ll.value=="" || document.formx.ll.value=="0"){
		
			alert("����ȷ�������ʣ�");
			document.formx.ll.focus();
			return false;
		}
	if (document.formx.y.value=="0" && document.formx.t.value=="0"){
		
			alert("����ȷ�������ޣ�");
			return false;
		}	
	}

function checka(){
	if (document.formw.title.value==""){
		
			alert("��������⣡");
			document.formw.title.focus();
			return false;
		}
	}

function checkc(){
	if (document.formw.title.value==""){
		
			alert("��������⣡");
			document.formw.title.focus();
			return false;
		}
	if (document.formw.je.value=="" || document.formw.je.value=="0"){
		
			alert("����ȷ����Ƿ���ܶ");
			document.formw.je.focus();
			return false;
		}
	if (document.formw.sf.value==""){
		
			alert("���������֤��");
			document.formw.sf.focus();
			return false;
		}
	if (document.formw.qb.value==""){
		
			alert("����������������");
			document.formw.qb.focus();
			return false;
		}
	if (document.formw.yb.value==""){
		
			alert("���������ڱ�����");
			document.formw.yb.focus();
			return false;
		}
	if (document.formw.dh.value==""){
		
			alert("������绰��");
			document.formw.dh.focus();
			return false;
		}
	if (document.formw.dz.value==""){
		
			alert("�������ַ��");
			document.formw.dz.focus();
			return false;
		}	
	}