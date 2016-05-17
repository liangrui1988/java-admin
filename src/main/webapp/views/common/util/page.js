/**分页***/



//init
function pageFn(_url,_param)
{
	 //init
   sendReq(_url,_param);
	   //首页
//	   $("#_firstPage").click(function(){			 
//			var param=getParam();		  
//			 param.mPageIndex=1;
//			 sendReq(_url,param);
//	    });
	   //尾页
//	   $("#_lastPage").click(function(){
//	   
//	        var param=getParam();
//	        var lastPageVar= $("#_sizePage").html();
//	         param.mPageIndex=parseInt(lastPageVar,10);
//			 sendReq(_url,param);
//	    });
	   //上一页
//	    $("#_pageUp").click(function(){
//		
//	        var param=getParam();
//			 var currentPageVar=$("#_currentPage").html();
//			 param.mPageIndex=parseInt(currentPageVar,10)-1;
//			 sendReq(_url,param);
//	 
//	    });
	    
	   
	   //下一页
//	    $("#_nextPage").click(function(){
//		
//		     var param=getParam();														
//		  	//当前页
//			 var currentPageVar=$("#_currentPage").html();
//			 param.mPageIndex=parseInt(currentPageVar,10)+1;										
//			 sendReq(_url,param);
//	   
//	    });
	    
	   //跳转
//	    $("#_jumpPage").click(function(){
//	         var jumpPageVar=$("#jumpPageText").val();
//	        
//			  if(checkStr(jumpPageVar))
//			  {
//			   alert("跳转页请输入有效数字！");
//			   return;
//			  }
//			  
//			   var param=getParam();
//			   param.mPageIndex=jumpPageVar;
//			   sendReq(_url,param);
//	    });
	    
	   
		
}

//点击
function clickNumFn(i,url){
	  var param=getParam();
	   param.mPageIndex=i;
	   sendReq(url,param);
	
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
	 sendReq(url,param);
	
}

/**
 * 发送请求
 * **/
function sendReq(_url,obj)
{
   				  
	  $.post(_url,obj,function(returnDatas,status)
	  {
	       if(status=="success")
		   { 
		       var jsonData=JSON.stringify(returnDatas);
	    	   eval('callbak_page('+jsonData+')');
				//分页信息
				var pageinfoHTML = "共 <sapn id='_countPage'>" + returnDatas.mRecords
						+ "</sapn>条&nbsp;第<font color='#FF0000' id='_currentPage'>"
						+ returnDatas.mCurrentPage + "</font>/<sapn id='_sizePage'>"
						+ returnDatas.mPages + "</sapn>页";
						
				
				//$("#_pageInfos").html(pageinfoHTML);
				
				//页码显示
				var paginationNum="<div>"+pageinfoHTML+"</div><span class=\"next\" rel=\"prev\"	onclick=\"pagecClickEnv('"+_url+"','T')\">首页 </span>";
				paginationNum+="<span class=\"next\" rel=\"prev\"	onclick=\"pagecClickEnv('"+_url+"','U')\">&lt; </span>";
				//alert(returnDatas.showNum);
				for(var i=returnDatas.startNo;i<(returnDatas.startNo+returnDatas.showNum);i++)
				{
					if(returnDatas.mPages<i){
						break;
					}
					if(returnDatas.mCurrentPage==i)
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




