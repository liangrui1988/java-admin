

function getWebContext(){
	return "/demo/";
}


	

function handleAjaxRequest(resultBean, status,XMLHttpRequest)
{
	if(!resultBean.success)
	{
		if(resultBean.messageCode=="01")
		{
			alert(resultBean.message);
			window.top.location.href=getContextPath()+"/front/";
			return false;
		}else if(resultBean.messageCode=="02")
		{
			alert(resultBean.message);
			return false;
		}else if(resultBean.messageCode=="03")
		{
			alert(resultBean.message);
			return false;
		}
		alert(resultBean.message);
	}
	var ajaxRequestChecking = XMLHttpRequest.getResponseHeader("AJAX_REQUEST_CHECKING");
	if(ajaxRequestChecking!=null)
	{
		if(ajaxRequestChecking=="01")
		{
			alert('您未登录或会话已过期');
			window.top.location.href=getContentPath()+"/front/";
			return false;
		}else if(ajaxRequestChecking=="02"){
			alert('您没有此模块的访问权限');
			return false;
		}else if(ajaxRequestChecking=="03"){
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
