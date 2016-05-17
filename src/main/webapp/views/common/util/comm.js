function getImgDomain()
{
   return "http://imgupload.youboy.com/";	
}

/**获取根目录**/
function getRootPath(){
	
	 //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    
    alert(curWwwPath);
    alert(pathName);
    alert(pos);
    alert(localhostPaht);
    alert(projectName);
    
    return(localhostPaht+projectName);
}

function getRootPath2(){
	
	 //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
   var curWwwPath=window.document.location.href;
   //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
   var pathName=window.document.location.pathname;
   var pos=curWwwPath.indexOf(pathName);
   //获取主机地址，如： http://localhost:8083
   var localhostPaht=curWwwPath.substring(0,pos);
 
   return(localhostPaht);
}

function resolveResultBeanIsOk(data,status)
{
  if(status=="success")
  {
    if(data.isOk==true||data.isOk=='true'||data.message=="SUCCESS")
	{
	  yb.alert("操作成功!");
	  return true;
	
	}else
	{
	  yb.alert("操作失败："+data.message);
	  return false;
	}
  
  }else
  {
   // alert(JSON.stringify(data));
     yb.alert("操作失败："+data.message);
	 return false;
  }
  
}



/**显示 信息**/
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return "";
}
	
	