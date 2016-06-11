function getContextPath()
{
	
	if(document.location.href.indexOf("x-eyess.com/")!=-1)
	{
		return "";
	}
	return "";
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






function topLoading()
{
	if($("#topLoadingDiv").html()==undefined)
	{
		$("body").append("<div id='topLoadingDiv' class='top_loading'><img border='0' src='"+getContextPath()+"/views/modules/resources/images/loading.gif'/></div>");
	}
	$("#topLoadingDiv").attr("class","top_loading");
	$("#topLoadingDiv").css("left",(document.documentElement.clientWidth-32)/2+"px");
	$("#topLoadingDiv").css("top",(document.documentElement.clientHeight-32)/2+"px");
}

function topLoaded()
{
	if($("#topLoadingDiv").html()!=undefined)
	{
		$("#topLoadingDiv").attr("class","top_loaded");
	}
}

function appendTrToTBody(rowNo,tBody,data,cls)
{
	var html="<tr class='"+(rowNo%2==0?'row_0':'row_1')+"'>";
	var s=data.length;
	if(cls==undefined || cls==null) cls=new Array(s);
	for(var i=0;i<s;i++)
	{
		if(cls[i]==undefined || cls[i]==null || cls[i]=="")
		{
			html+="<td>";
		}else{
			html+="<td class='"+cls[i]+"'>";
		}
		html+=data[i];
		html+="</td>";
	}
	html+="</tr>";
	$("#"+tBody).html($("#"+tBody).html()+html);
}

function initSelectOptions()
{
	$("select").each(function()
	{
		var isWithBlank=false;
		var reqUrl=null,select=null,opt=null;
		if($(this).attr("isWithBlank"))
		{
			isWithBlank=$(this).attr("isWithBlank").toLowerCase()=="true"?true:false;
		}

		if($(this).attr("data") && this.options.length==0)
		{
			select=this;
			
			reqUrl=$(this).attr("data");
			if(reqUrl.indexOf(getContextPath()+"/")==-1)
			{
				reqUrl=getContextPath()+reqUrl;
			}
			var s= reqUrl.indexOf("?")==-1 ? "?_=" : "&_=";
			
			reqUrl=reqUrl+s+new Date().getTime();
			
			blankLabel=$(this).attr("blankLabel");
			if(blankLabel==undefined)
			{
				blankLabel="";
			}
			topLoading();
			$.ajax({type:"GET",dataType:"json",url:reqUrl,async:false,success:function(resultBean, status, xhRequest)
			{
				topLoaded();
				if(!handleAjaxRequest(resultBean, status,xhRequest)){return;}
				
				for(var i=select.options.length;i>=0;i--) select.options.remove(i);	
				
				if(isWithBlank)
				{
					opt=document.createElement('option');
					select.options.add(opt);
					opt.value="";
					opt.innerHTML=blankLabel; 
				}
				var dataMap=resultBean.data;
				for(var key in dataMap)
				{
					opt=document.createElement('option');
					select.options.add(opt);
					opt.value=key;
					opt.innerHTML=dataMap[key]; 	
				}
				
			}});	
		}
	});
}

function selectOption(id,value)
{
  var obj=document.getElementById(id).options;
  for(var i=0;i<obj.length;i++)
  {
    if(obj[i].value==value)
    {
      obj[i].selected=true;
      break;
    }
  }
}

function getLength(value)
{
	var len=0;
	if(value==undefined || value=="")
	{
		return len;
	}
	for(var k=0;k<value.length;k++)
	{
		v=value.charCodeAt(k);
	    if(v<0||v>255){len+=2;}else{len++;}
	}
	return len;
}

/**地区 下拉数据的 动态添加
 * @author tanjin
 * @param optType
 * @param selectClazz
 * @param url
 */
function initRegionSelectOptions(selectClazz,url)
{
	//api/sys/getRegions
	//api/sys/getRegions?parentId=?	
	var select=null;//,opt=null;
	select = $("#"+selectClazz);	
	$.getJSON(url,{},function(resultBean, status, xhRequest)
			{
				//if(!handleAjaxRequest(resultBean, status,xhRequest)){return;}
				var dataMap=resultBean.data;				
				//$("#"+selectClazz).empty();
				$(select).html("");
				$("<option selected='true' value=''>请选择</option>").appendTo(select);
				$.each(dataMap, function (i, n) {					
					$("<option value='" + i + "'>" + n + "</option>").appendTo(select);
		          /*  if (eval("n." + options.valuefiled) == options.selectedindex) {
		                $("<option selected='true' value='" + eval("n." + options.valuefiled) + "'>" + eval("n." + options.textfield) + "</option>").appendTo(select);
		            } else {
		                $("<option value='" + eval("n." + options.valuefiled) + "'>" + eval("n." + options.textfield) + "</option>").appendTo(select);
		            }*/
					//console.log(n+"===="+i);
		        });	
				
			});	
}

function initXytItem()
{
	initSelectOptions();
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

function _Dialog(title, url,callback,width) {
	 options = {
			 _type:'Dialog',
             _dialogW: 530,
             _url:url,
             _title:title,
             _callbackfn: callback,
             _jquery: $ // 测试Jq 的实例传出
         };
	 if(width)
		 options._dialogW = width;
    $(window.parent.showPrompt(options));
}

/**
 * 错误提示框，在iframe中使用
 * @param msg
 * @param callback
 * @param icontype: "success"/"error"
 */
function _message(msg, callback, icontype){	
	var _icontype=icontype|| "success";
	options = {
            _type: 'MsgDialog',
            _countdown:true,
            _msg: msg, 
            _msgicon: _icontype,
            _closeDialog:true,
            _callbackfn: callback
        };

	$(window.parent.showPrompt(options));
}

/**
 * 错误提示框，在弹出框中使用
 * @param msg
 * @param icontype: "success"/"error"
 */
function _showMessage(msg, icontype){
	var _icontype=icontype|| "success";
	options = {
            _type: 'MsgDialog',
            _countdown:true,
            _msgicon: _icontype,
            _msg:msg,
            _closeDialog:true,
            _callbackfn: function () {
            	$("#iframeid").contents().find("#but-search").click();
            }
        };
        showPrompt(options);
}

/**
 * 错误提示框，在弹出框中使用
 * @param msg
 * @param icontype: "success"/"error"
 * @param closeDialog: true/false 是否关闭弹出框
 */
function _alert(msg, icontype, closeDialog){
	var _icontype=icontype|| "success";
	options = {
            _type: 'MsgDialog',
            _countdown:true,
            _msgicon: _icontype,
            _msg:msg,
            _closeDialog:closeDialog
        };
   showPrompt(options);
}

function _confirmDialog(msg,title,callback){
	options = {
            _type: 'ConfirmDialog',
            _dialogW:450,
            _title:title,
            _msg: msg,
            _closeDialog: true,
            _callbackfn:  callback
        };
	$(window.parent.showPrompt(options));
 
}

function getRealPath(){
	return "/fs";	
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

function change(){
	 $("input").css({"color":"black"});
}

function _showCount(element){
	var maxlength = element.attr("maxlength");
		
	var counter = element.val() ? element.val().length : 0;
	
	if (counter > maxlength)
		//如果元素区字符数大于最大字符数，按照最大字符数截断；
		element.val(element.val().substring(0, maxlength));
	else
		//在记数区文本框内显示剩余的字符数；
		element.next("label").html('(还可以输入<var style="color: #C00">'+(maxlength - counter)+'</var>个字符)');
}

/**
 * 检查上传图片的类型
 * @param fileName
 * @returns {Boolean}
 */
function CheckFile(fileName) {
    var array = new Array('gif', 'jpeg', 'png', 'jpg','jpe','jpg','bmp');  //可以上传的文件类型
    if (fileName == '') {
        _alert("让选择要上传的图片!");
        return false;
    }
    else {
        var fileContentType = fileName.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用：）
        var isExists = false;
        for (var i in array) {
            if (fileContentType.toLowerCase() == array[i].toLowerCase()) {
                isExists = true;
                return true;
            }
        }
        if (isExists == false) {
            _alert("上传图片类型不正确!");
            return false;
        }
        return false;
    }
}
/*
 * 判断是否图片路径
 */
function isImage (str) {
	return str.match(/(^data:image\/.*,)|(\.(jp(e|g|eg)|gif|png|bmp|webp|svg)((\?|#).*)?$)/i);
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

/**
 * 取得公共上传服务地址
 * @returns
 */
function getPublicUploadDomain(test)
{
	if(test){
		return "http://l.x-eyess.com:8080/ocr";
	}else{
		return window.top.getLoginUser().publicUploadDomain;
	}
}

/**
 * 取得商家商品上传服务地址
 * @returns
 */
function getMerchantProductDomain(test)
{
	if(test){
		return "http://l.x-eyess.com:8080/ocr";
	}else{
		return window.top.getLoginUser().merchantProductDomain;
	}
}