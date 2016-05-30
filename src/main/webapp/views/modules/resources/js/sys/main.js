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
			$("#userNameLabel").html(resultBean.data.userName);
			//buildTreeMenu(resultBean.data.user.menuList);
			buildTreeMenu(resultBean.data);
		},
		complete: function (XMLHttpRequest, textStatus) {
			 //alert(alert("textStatus》》"+textStatus));
        },
		error : function() {
			alert("请求异常！");
		}
	});
	
	
	 /*$.ajax({
         async: false,
         url: getContextPath()+"/pc/sys/user/getUser",  // 跨域URL
         type: 'POST',
         dataType: 'jsonp',
         jsonp: 'callback',
         data: {},
         timeout: 5000,
         beforeSend: function () {
             //jsonp 方式此方法不被触发。原因可能是dataType如果指定为jsonp的话，就已经不是ajax事件了
         },
         success: function (resultBean, status, xhRequest) {
        	 topLoaded();    
     		if(!handleAjaxRequest(resultBean, status,xhRequest))return;
     		$("#userNameLabel").html(resultBean.data.name);
     		buildTreeMenu(resultBean.data.user.menuList);
         },
         complete: function (XMLHttpRequest, textStatus) {
         },
         error: function (xhr) {
             alert("请求出错(请检查相关度网络状况.)" + xhr);
         }
     }); */
});

function goLogout() {
    topLoading();
    $.getJSON(getContextPath() + "/api/authx/logout", {}, function (resultBean, status, xhRequest) {
        topLoaded();
        if (!handleAjaxRequest(resultBean, status, xhRequest)) return;
        top.document.location.href = getContextPath() + "/front/sys/index.html";
    });
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
            		demoIframe.attr("src", getContextPath() + "/front/sys/home.html?vNum=" + randomNum);
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