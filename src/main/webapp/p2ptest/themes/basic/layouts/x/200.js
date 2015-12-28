if(0==0 || (0==1 && checkDate200('2015-12-23'))){
document.writeln("<div style=\"padding-bottom:2px;padding-top:2px;\" onclick=\"addHits200(0,623)\"><a href=\"http://www.hlixin.com/\" target=\"_blank\"><img  alt=\"好利信\"  border=\"0\"  src=\"http://www.3fzf.com//UploadFiles/2015-11/306/2015111811115714737.gif\"></a></div> ");
}
if(0==0 || (0==1 && checkDate200('2015-12-23'))){
document.writeln("<div style=\"padding-bottom:2px;padding-top:2px;\" onclick=\"addHits200(0,631)\"><a href=\"http://ylhp2p888.com/\" target=\"_blank\"><img  alt=\"友力汇\"  border=\"0\"  src=\"http://www.3fzf.com//UploadFiles/2015-12/306/2015121816013734935.gif\"></a></div> ");
}
if(0==0 || (0==1 && checkDate200('2015-12-21'))){
document.writeln("<div style=\"padding-bottom:2px;padding-top:2px;\" onclick=\"addHits200(0,625)\"><a href=\"http://www.edled.net/\" target=\"_blank\"><img  alt=\"北深贷\"  border=\"0\"  src=\"http://www.3fzf.com/UploadFiles/2015-10/305/2015102215010276398.gif\"></a></div> ");
}
if(0==0 || (0==1 && checkDate200('2015-12-21'))){
document.writeln("<div style=\"padding-bottom:2px;padding-top:2px;\" onclick=\"addHits200(0,627)\"><a href=\"http://www.cqzxtz.net/\" target=\"_blank\"><img  alt=\"中协贷\"  border=\"0\"  src=\"http://www.3fzf.com/UploadFiles/2015-11/305/2015111313061832125.gif\"></a></div> ");
}
if(0==0 || (0==1 && checkDate200('2015-12-21'))){
document.writeln("<div style=\"padding-bottom:2px;padding-top:2px;\" onclick=\"addHits200(0,628)\"><a href=\"http://www.sycfd.com/\" target=\"_blank\"><img  alt=\"车房贷\"  border=\"0\"  src=\"http://www.3fzf.com/UploadFiles/2015072911020436600.gif\"></a></div> ");
}
if(0==0 || (0==1 && checkDate200('2015-12-21'))){
document.writeln("<div style=\"padding-bottom:2px;padding-top:2px;\" onclick=\"addHits200(0,629)\"><a href=\"http://www.8ncb.com/\" target=\"_blank\"><img  alt=\"农村宝理财\"  border=\"0\"  src=\"http://www.3fzf.com/UploadFiles/2015-12/306/2015121012381490058.gif\"></a></div> ");
}
if(0==0 || (0==1 && checkDate200('2015-12-21'))){
document.writeln("<div style=\"padding-bottom:2px;padding-top:2px;\" onclick=\"addHits200(0,630)\"><a href=\"http://ymjintz.com/\" target=\"_blank\"><img  alt=\"益满金\"  border=\"0\"  src=\"http://www.3fzf.com/UploadFiles/2015120814063423912.gif\"></a></div> ");
}
if(0==0 || (0==1 && checkDate200('2015-12-23'))){
document.writeln("<div style=\"padding-bottom:2px;padding-top:2px;\" onclick=\"addHits200(0,626)\"><a href=\"http://www.jinshaedai.com/\" target=\"_blank\"><img  alt=\"金沙贷\"  border=\"0\"  src=\"http://www.3fzf.com//UploadFiles/20151209185027413.gif\"></a></div> ");
}
if(0==0 || (0==1 && checkDate200('2015-12-23'))){
document.writeln("<div style=\"padding-bottom:2px;padding-top:2px;\" onclick=\"addHits200(0,632)\"><a href=\"http://www.jfwcf.com/\" target=\"_blank\"><img  alt=\"金饭碗\"  border=\"0\"  src=\"http://www.3fzf.com//UploadFiles/2015-11/306/201511242031584989.gif\"></a></div> ");
}
function addHits200(c,id){if(c==1){try{jQuery.getScript('http://www.3fzf.com/plus/ajaxs.asp?action=HitsGuangGao&id='+id,function(){});}catch(e){}}}
function checkDate200(date_arr){
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
