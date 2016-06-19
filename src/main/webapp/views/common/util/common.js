
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
//    alert(curWwwPath);
//    alert(pathName);
//    alert(pos);
//    alert(localhostPaht);
//    alert(projectName);
    
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
function pageFn(_url,_param,reqMothd)
{
	sendPageRequst(_url,_param,reqMothd);
}

//点击
function clickNumFn(i,url,reqMothd){
	  var param=getParam();
	   param.pageIndex=i;
	   sendPageRequst(url,param,reqMothd);
}

function pagecClickEnv(url,P,reqMothd)
{
	var param=getParam();
	//当前页
	var currentPageVar=$("#_currentPage").html();
		 
	if(P=="N")
	{
	  param.pageIndex=parseInt(currentPageVar,10)+1;	
	 	
	}else if(P=="U")
	{
		 param.pageIndex=parseInt(currentPageVar,10)-1;	
	}else if(P=="L")
	{
		 var lastPageVar= $("#_sizePage").html();
		 param.pageIndex=parseInt(lastPageVar,10);
	}else if(P=="T")
	{
		 param.pageIndex=1;
	}else if(P=="J")
	{
		var jumpPageVar=$("#jumpPageText").val();
		  if(checkStr(jumpPageVar))
		  {
		   alert("跳转页请输入有效数字！");
		   return;
		  }
		   var param=getParam();
		   param.pageIndex=jumpPageVar;
		
	}
	sendPageRequst(url,param,reqMothd);
	
}

/**
* 发送请求 
* _url=
* obj=查询参数
* getOrPost=请求类型
* **/
function sendPageRequst(_url,obj,requestMeothd)
{
	
	if(requestMeothd==undefined||requestMeothd==null||requestMeothd=="")
	{
		requestMeothd="get";
	}
	var _sizePage=$("#_sizePage").text();
	var pageindex=obj.pageIndex;
	if((typeof pageindex)=='undefined'){
		obj.pageIndex==1
	}else
	if(parseInt(pageindex)>parseInt(_sizePage)){
		obj.pageIndex=_sizePage;
	}
	
	$.ajax({
		url : _url,
		data : obj,
		type : requestMeothd,
		cache : false,
		dataType : 'json',
		success : function(returnDatas,status,xhRequest) {
			//topLoaded();    
			if(!handleAjaxRequest(returnDatas, status,xhRequest))return;
			
			//----处理分页数据
				  var jsonData=JSON.stringify(returnDatas);
		    	   eval('callbak_page('+jsonData+')');
		    	   returnDatas=returnDatas.data;
					//分页信息
					var pageinfoHTML = "共 <sapn id='_countPage'>" + returnDatas.count
							+ "</sapn>条&nbsp;第<font color='#FF0000' id='_currentPage'>"
							+ returnDatas.currentPage + "</font>/<sapn id='_sizePage'>"
							+ returnDatas.pages + "</sapn>页";
					//页码显示
					var paginationNum="<div>"+pageinfoHTML+"</div><span class=\"next\" rel=\"prev\"	onclick=\"pagecClickEnv('"+_url+"','T','"+requestMeothd+"')\">首页 </span>";
					paginationNum+="<span class=\"next\" rel=\"prev\"	onclick=\"pagecClickEnv('"+_url+"','U','"+requestMeothd+"')\">&lt; </span>";
					//alert(returnDatas.showNum);
					for(var i=returnDatas.startNo;i<(returnDatas.startNo+returnDatas.showNum);i++)
					{
						if(returnDatas.pages<i){
							break;
						}
						if(returnDatas.currentPage==i)
						{
							paginationNum+="<a class='pageSelected' onclick=clickNumFn('"+i+"','"+_url+"','"+requestMeothd+"')>"+i+" </a>";
						}else{
							paginationNum+="<a onclick=clickNumFn('"+i+"','"+_url+"','"+requestMeothd+"')>"+i+" </a>";
						}
						
					}
					paginationNum+="<span class=\"next\" rel=\"prev\" onclick=\"pagecClickEnv('"+_url+"','N','"+requestMeothd+"')\">&gt; </span>";
					paginationNum+="<span class=\"next\" rel=\"prev\"	onclick=\"pagecClickEnv('"+_url+"','L','"+requestMeothd+"')\">尾页 </span>";
					paginationNum+="&nbsp;&nbsp;&nbsp;&nbsp;"
					+"跳到第&nbsp;&nbsp;<input type=\"text\" size=\"4\" id=\"jumpPageText\"> 页&nbsp;  <a onclick=pagecClickEnv('"+_url+"','J','"+requestMeothd+"')>确定 </a>";
	
					//alert(paginationNum);
					$("#_pagination").html(paginationNum);
					addPageClass();
			//----处理分页数据
			
			
		},
		complete: function (XMLHttpRequest, textStatus) {
			 //alert(alert("textStatus》》"+textStatus));
        },
		error : function() {
			alert("请求异常！");
		}
	});
	//ajax end
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

/**
 * 序列话表单为对象
 */
$.fn.serializeObject = function()
{
  var o = {};
  var a = this.serializeArray();
  $.each(a, function() {
    if (o[this.name] !== undefined) {
      if (!o[this.name].push) {
        o[this.name] = [o[this.name]];
      }
      o[this.name].push(this.value || '');
    } else {
      o[this.name] = this.value || '';
    }
  });
  return o;
};



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

/**
 * 字典 根据类型请求
 */
function initDictByType(type,selectId,type_value){
	
	$("#"+selectId).empty();
	
	$.ajax({
		url : getContextPath()+"/sys/dict/getByType",
		type : "GET",
//		contentType: "application/json; charset=utf-8",
		dataType : "json",
		//data :{productVoJson:JSON.stringify(objVo)},
		data :{"type":type},
		success : function(returnData,status,XMLHttpRequest)
		{	
			if(handleAjaxRequest(returnData,status,XMLHttpRequest)){
				var data=returnData.data;
				if(data!=null&&data!=""&&(typeof data)!='undefined'&&data.length>0)
				{
					for(var i=0;i<data.length;i++)
					{
						var dict=data[i];
						
						var html="";
						html+="<option value='"+dict.value+"' ";
						//(typeof type_value)!="undefined"&&type_value!=null&&type_value!=""&&
						if(type_value==dict.value)
						{
							html+=" selected='selected' ";
						}
						
						html+=">";
						html+= dict.name+"</option>";
						
						$("#"+selectId).append(html);
						
					}
				}
				return returnData.data;
			}
		},
		error : function() {
			alert("字典请求异常！");
		}
	});
}
	
