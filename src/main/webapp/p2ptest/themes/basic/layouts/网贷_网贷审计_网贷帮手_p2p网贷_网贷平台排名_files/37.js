if(0==0 || (0==1 && checkDate37('2015-12-8'))){
document.writeln("<div style=\"padding-bottom:2px;padding-top:2px;\" onclick=\"addHits37(1,410)\"><a href=\"http://www.tuandai.com/\" target=\"_blank\"><img  alt=\"ÍÅ´ûÍø\"  border=\"0\"  src=\"http://www.3fzf.com//UploadFiles/2015-12/305/2015120816340628218.jpg\"></a></div> ");
}
function addHits37(c,id){if(c==1){try{jQuery.getScript('http://www.3fzf.com/plus/ajaxs.asp?action=HitsGuangGao&id='+id,function(){});}catch(e){}}}
function checkDate37(date_arr){
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
