$.ajaxSetup ({cache: false });

function getLoginUser()
{
	return loginUser;
}
var loginUser;
//初始化加载
$(function(){

	//topLoading();
//	$.post(getContextPath()+"/sys/menu/listAll",{"t":12645313},function(resultBean, status, xhRequest)
//	{	  
//		alert("xx");
//		//alert(resultBean);
//		//topLoaded();    
//		//if(!handleAjaxRequest(resultBean, status,xhRequest))return;
//		//loginUser=resultBean.data;
//		$("#userNameLabel").html("adminhh");
//		//buildTreeMenu(resultBean.data.user.menuList);
//		buildTreeMenu(resultBean.data);
//
//	},'json');
	
	
	
	/**
	 * 获取菜单列表
	 */
	$.ajax({
		url : getContextPath()+"/sys/menu/getlistByCurentUser",
		data : {"t":12645313},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(resultBean,status,xhRequest) {
			topLoaded();    
			if(!handleAjaxRequest(resultBean, status,xhRequest))return;
			//loginUser=resultBean.data;
			//buildTreeMenu(resultBean.data.user.menuList);
			buildTreeMenu(resultBean.data);
			var userObjxxx=getUserComment();
//			console.log(userObjxxx)
			$("#userNameLabel").text(userObjxxx.userName);
		},
		complete: function (XMLHttpRequest, textStatus) {
			 //alert(alert("textStatus》》"+textStatus));
        },
		error : function() {
			alert("请求异常！");
		}
	});
	
	
	
	
function goLogout() {
	var result=confirm("确定要退出系统吗？");
	if(result){
	    topLoading();
	    $.post(getContextPath() + "/logout", {}, function (resultBean, status, xhRequest) {
	        topLoaded();
	        //alert(JSON.stringify(resultBean));
	       // alert(resultBean.message);
	        if(resultBean.success)
	    	{
	        	if(resultBean.messageCode=="011"){
	        		 top.document.location.href = getContextPath() + "/views/login.html";
	        	}
	    	}
	    });
		
	}else{
		
	}
	
	
//	$.confrim({
//			title: '确定',
//			content: '确定要退出系统吗？',
//			confirm: function(){
//			           alert('the user clicked confirm');
//			},
//			cancel: function(){
//			   alert('the user clicked cancel')
//			}
//		});
	
	

}

//构建菜单树
function buildTreeMenu(menuList) {
    var t = $("#nav-tree");
    t = $.fn.zTree.init(t, setting, menuList);
    demoIframe = $("#iframeid");
}

var zTree;
var demoIframe;

var setting = {
    view: {
        showLine: false,
        showIcon: false,
        dblClickExpand: false,
        showLine: false,
        selectedMulti: false
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: ""
        }
    },
    callback: {
        beforeClick: function (treeId, treeNode) {
            zTree = $.fn.zTree.getZTreeObj(treeId);
            if (treeNode.isParent) {
                zTree.expandNode(treeNode);
                return false;
            } else {
            	var randomNum = Math.floor(Math.random() * (182014 + 1));
            	if(typeof(treeNode.href) == 'undefined' || !treeNode.href){
            		demoIframe.attr("src", getContextPath() + "/views/home.html?vNum=" + randomNum);
            		return;
            	}
            	if(treeNode.href.lastIndexOf(".html")>0)
            	{
            		demoIframe.attr("src", getContextPath() + treeNode.href+"?vNum=" + randomNum);
            	}else
        		{
            		demoIframe.attr("src", getContextPath() + treeNode.href + ".html?vNum=" + randomNum);
        		}
                return true;
            }
        }
    }
};