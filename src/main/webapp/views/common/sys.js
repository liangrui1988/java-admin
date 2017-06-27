function getContextPath()
{
	
	if(document.location.href.indexOf("rui.com/")!=-1)
	{
		return "/zdata";
	}
	return "/zdata";
}
/**
 * 判斷是否是正整數
 * @param {Object} s
 */
function isPos(s)
{
	var p = /^[0-9]*[1-9][0-9]*$/;
	return !(p.exec(s)==null);
}


//是否失败
function resolveResultBeanIsError(data,status)
{
	  if(status!="success")
	  {
		  alert("请求系统异常");
	       return false;
	  }
	
	 if(!data.success)
	 {
		alert(data.message);
	    return false; 	 
	 }
	 
	 if(data.messageCode != "001")
	 {
		alert(data.message);
	    return false; 	 
	 }

    return true;
  
}

//是否成功
function resolveResultBeanIsOk(data,status)
{
  if(status=="success")
  {
    if(data.success)
	{
	  alert("操作成功");
	  return true;
	}else
	{
	  alert("操作失败："+data.message);
	  return false;
	}
  
  }else
  {
   // alert(JSON.stringify(data));
     alert("请求失败："+data.message);
	 return false;
  }
  
}


//系统解析JSON返回状态和结果
function handleAjaxRequest(resultBean, status,XMLHttpRequest)
{
	//console.log(XMLHttpRequest);
	//console.log(resultBean);
	//alert(resultBean.success);
	if(!resultBean.success)
	{
		if(resultBean.messageCode=="010"||resultBean.messageCode=="002"||resultBean.messageCode=="003")
		{
			alert(resultBean.message);
			window.top.location.href=getContextPath()+"/views/login.html";
			return false;
		}
		alert(resultBean.message);
		return false;
	}
	
	var ajaxRequestHeader = XMLHttpRequest.getResponseHeader("AJAX_REQUEST_HEADER");
	//alert("ajaxRequestHeader:"+ajaxRequestHeader);
	if(ajaxRequestHeader!=null)
	{
		if(ajaxRequestHeader=="001")
		{
			alert('您未登录或会话已过期');
			window.top.location.href=getContextPath()+"/views/login.html";
			return false;
		}else if(ajaxRequestHeader=="004"){
			alert('请输入验证码');
			window.top.location.href=getContextPath()+"/views/login.html?isCaptcha=1";
			return false;
		}else if(ajaxRequestHeader=="002"){
			alert('您没有此模块的访问权限');
			return false;
		}else if(ajaxRequestHeader=="003"){
			alert('系统出错');
			return false;
		}
	}
	var ajaxRequestException = XMLHttpRequest.getResponseHeader("AJAX_REQUEST_EXCEPTION");
	if(ajaxRequestException!=null)
	{
		alert(ajaxRequestException);
		return false;
	}
	if (status == "error") 
	{
		alert('系统出错');
		return false;
	}
	return resultBean.success;
}



//系统解析JSON返回状态和结果
function handleAjaxRequestBACK(resultBean, status,XMLHttpRequest)
{
	//console.log(XMLHttpRequest);
	//console.log(resultBean);
	//alert(resultBean.success);
	if(!resultBean.success)
	{
		if(resultBean.messageCode=="010"||resultBean.messageCode=="002"||resultBean.messageCode=="003")
		{
			alert(resultBean.message);
			window.top.location.href=getContextPath()+"/views/login.html";
			return false;
		}
		alert(resultBean.message);
	}
	
	var ajaxRequestHeader = XMLHttpRequest.getResponseHeader("AJAX_REQUEST_HEADER");
	//alert("ajaxRequestHeader:"+ajaxRequestHeader);
	if(ajaxRequestHeader!=null)
	{
		if(ajaxRequestHeader=="001")
		{
			alert('您未登录或会话已过期');
			window.top.location.href=getContextPath()+"/views/login.html";
			return false;
		}else if(ajaxRequestHeader=="004"){
			alert('请输入验证码');
			window.top.location.href=getContextPath()+"/views/login.html?isCaptcha=1";
			return false;
		}else if(ajaxRequestHeader=="002"){
			alert('您没有此模块的访问权限');
			return false;
		}else if(ajaxRequestHeader=="003"){
			alert('系统出错');
			return false;
		}
	}
	var ajaxRequestException = XMLHttpRequest.getResponseHeader("AJAX_REQUEST_EXCEPTION");
	if(ajaxRequestException!=null)
	{
		alert(ajaxRequestException);
		return false;
	}
	if (status == "error") 
	{
		alert('系统出错');
		return false;
	}
	return resultBean.success;
}

/****
 * 
 *  获取用户信息公用方法
 * getUser
 */
function getUserComment(userid){
	
	var objreturn="";
	
	var param="";
	if((typeof userid)=='undefined'||userid==null||userid==""){
		param="";
	}else{
		param="id="+userid;
	}
	
	$.ajax({
		url : getContextPath()+"/sys/user/getUser",
		type : "GET",
		//cache:false, 
	    async:false, 
		dataType : "json",
		data :param,
		success : function(returnData,status,XMLHttpRequest)
		{	
			if(handleAjaxRequest(returnData,status,XMLHttpRequest)){
				objreturn=returnData.data;
			}
		},
		error : function() {
			alert("获取用户信息异常！");
		}
	});
	return objreturn;
}


function goLogout() {
	var result=confirm("确定要退出系统吗？");
	if(result){
//	    topLoading();
	    $.post(getContextPath() + "/logout", {}, function (resultBean, status, xhRequest) {
//	        topLoaded();
	        //alert(JSON.stringify(resultBean));
	       // alert(resultBean.message);
	        if(resultBean.success)
	    	{
	        	if(resultBean.messageCode=="011"){
	        		 top.document.location.href = getContextPath() + "/views/login.html";
	        	}
	    	}
	    });
		
	}
}

/**
 * 获取URL中参数的值
 * 
 * 例子：http://abc.com?action=update&id=987654321789
 * var action = getUrlParam("action"); //返回action的值为"update"
 * 
 * Author: 
 * 
 * Param: name: 要获取的参数名字
 * return：返回参数的值
 */
function getUrlParam(name,_location){
	//console.log(window.location);
	var _location_url =_location || window.location.search;
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = _location_url.substr(1).match(reg); //匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; //返回参数值
}

/**
 * 统计输入字符数
 */
function countWorld(){
	$.each($("textarea"),function(x,y){
		_showCount($(this));
		$(this).on("keyup",function(){
			_showCount($(this));
		});
		$(this).on("change",function(){
			_showCount($(this));
		});
	});
}



/**
 * 兼容IE9以下的placeholder属性显示
 * 注意在加载列表数据之前要先调$('[placeholder]').focusin() 清空placeholder的显示值,否 作参数提交
 * 数据加载完成之后,可调用$('[placeholder]').focusout() 重新显示placeholder;
 */
function placeholder(){
		if(!placeholderSupport()){   // 判断浏览器是否支持 placeholder
			 $('[placeholder]').focusin(function() {
			var input = $(this);
			if (input.val() == input.attr('placeholder')) {
			    input.val('');
			    input.removeClass('placeholder');
			}
		    }).focusout(function() {
			var input = $(this);
			if (input.val() == '' || input.val() == input.attr('placeholder')) {
			    input.addClass('placeholder');
			    input.val(input.attr('placeholder'));
			}
		    }).focusout();
		
		
		}; 
	   }

function placeholderSupport() {return 'placeholder' in document.createElement('input'); } 

