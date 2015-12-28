if(0==0 || (0==1 && checkDate40('2013-12-26'))){
document.writeln("<div style=\"padding-bottom:2px;padding-top:2px;\" onclick=\"addHits40(1,48)\"><a href=\"http://www.wangdaikanpan.com/\" target=\"_blank\"><img  alt=\"无忧科技 中国最安全的网贷系统\"  border=\"0\"  src=\"http://www.3fzf.com//UploadFiles/2013-12/0/201312022235262443.gif\"></a></div> ");
}
function addHits40(c,id){if(c==1){try{jQuery.getScript('http://www.3fzf.com/plus/ajaxs.asp?action=HitsGuangGao&id='+id,function(){});}catch(e){}}}
function checkDate40(date_arr){
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
