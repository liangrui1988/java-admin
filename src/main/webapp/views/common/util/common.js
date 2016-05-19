
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
	  alert("操作成功!");
	  return true;
	
	}else
	{
	    alert("操作失败："+data.message);
	  return false;
	}
  
  }else
  {
   // alert(JSON.stringify(data));
     alert("操作失败："+data.message);
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



/**分页js start***/

//init
function pageFn(_url,_param)
{
//	if(moethd==""||(moethd!="get"&&moethd!="post")){
//		moethd="get";
//	}
    sendGetReq(_url,_param);
		
}

//点击
function clickNumFn(i,url){
	  var param=getParam();
	   param.mPageIndex=i;
	   sendGetReq(url,param);
	
}

function pagecClickEnv(url,P)
{
	var param=getParam();
	//当前页
	var currentPageVar=$("#_currentPage").html();
		 
	if(P=="N")
	{
	  param.mPageIndex=parseInt(currentPageVar,10)+1;	
	 	
	}else if(P=="U")
	{
		 param.mPageIndex=parseInt(currentPageVar,10)-1;	
	}else if(P=="L")
	{
		 var lastPageVar= $("#_sizePage").html();
		 param.mPageIndex=parseInt(lastPageVar,10);
	}else if(P=="T")
	{
		 param.mPageIndex=1;
	}else if(P=="J")
	{
		var jumpPageVar=$("#jumpPageText").val();
		  if(checkStr(jumpPageVar))
		  {
		   alert("跳转页请输入有效数字！");
		   return;
		  }
		   var param=getParam();
		   param.mPageIndex=jumpPageVar;
		
	}
	sendGetReq(url,param);
	
}

/**
* 发送请求
* **/
function sendGetReq(_url,obj)
{
 				  
	  $.get(_url,obj,function(returnDatas,status)
	  {
	       if(status=="success")
		   { 
		       var jsonData=JSON.stringify(returnDatas);

	    	   eval('callbak_page('+jsonData+')');
	    	   
	    	   returnDatas=returnDatas.data;
				//分页信息
				var pageinfoHTML = "共 <sapn id='_countPage'>" + returnDatas.count
						+ "</sapn>条&nbsp;第<font color='#FF0000' id='_currentPage'>"
						+ returnDatas.currentPage + "</font>/<sapn id='_sizePage'>"
						+ returnDatas.pages + "</sapn>页";
						
			
				
				//$("#_pageInfos").html(pageinfoHTML);
				
				//页码显示
				var paginationNum="<div>"+pageinfoHTML+"</div><span class=\"next\" rel=\"prev\"	onclick=\"pagecClickEnv('"+_url+"','T')\">首页 </span>";
				paginationNum+="<span class=\"next\" rel=\"prev\"	onclick=\"pagecClickEnv('"+_url+"','U')\">&lt; </span>";
				//alert(returnDatas.showNum);
				for(var i=returnDatas.startNo;i<(returnDatas.startNo+returnDatas.showNum);i++)
				{
					if(returnDatas.pages<i){
						break;
					}
					if(returnDatas.currentPage==i)
					{
						paginationNum+="<a class='pageSelected' onclick=clickNumFn('"+i+"','"+_url+"')>"+i+" </a>";
					}else{
						paginationNum+="<a onclick=clickNumFn('"+i+"','"+_url+"')>"+i+" </a>";
					}
					
				}
				
				paginationNum+="<span class=\"next\" rel=\"prev\" onclick=\"pagecClickEnv('"+_url+"','N')\">&gt; </span>";
				paginationNum+="<span class=\"next\" rel=\"prev\"	onclick=\"pagecClickEnv('"+_url+"','L')\">尾页 </span>";
				paginationNum+="&nbsp;&nbsp;&nbsp;&nbsp;"
				+"跳到第&nbsp;&nbsp;<input type=\"text\" size=\"4\" id=\"jumpPageText\"> 页&nbsp;  <a onclick=pagecClickEnv('"+_url+"','J')>确定 </a>";

				//alert(paginationNum);
				$("#_pagination").html(paginationNum);
			
				addPageClass();
		    }else
			{
			  alert("系统异常!");
			}
		
	  });
}

/**验证数字 true 有 false 没有**/ 
function checkStr(varStr) { 
var included = "1234567890"; 
	//var i; 
	var c; 
	for( var i = 0; i < varStr.length; i ++ )   
	{   
		c = varStr.charAt(i); 
		if (included.indexOf(c) ==-1)
		{ //在"included"中找不到"c"   
		   return true; 
	    } 
	} 
	return false; 
} 

/** *分页鼠标经过效果** */
function addPageClass(){
	$(".pagination a,.pagination span,.btnSure").hover(function() {
		$(this).addClass("current");
	}, function() {
		$(this).removeClass("current");
	});
}


/**分页js end***/


	
	