/*
 * 菜单显示
 * @author ruilaing
 * @date 2017/03/28
 * 
 */
$(function(){
	initMenu();
})

/**
 * 获取菜单列表
 */
function initMenu(){
	$.ajax({
		url : getContextPath()+"/sys/menu/getlistByCurentUser",
		data : {"t":12645313},
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(resultBean,status,xhRequest) {
			if(!handleAjaxRequest(resultBean, status,xhRequest)){return};
			//loginUser=resultBean.data;
			//buildTreeMenu(resultBean.data.user.menuList);
			buildTreeMenuV2(resultBean.data);
            //console.log("resultBean.data>>>"+resultBean.data);
			var userObjxxx=getUserComment();
			$("#userNameLabel").text(userObjxxx.userName);
		},
		complete: function (XMLHttpRequest, textStatus) {
			 //alert(alert("textStatus》》"+textStatus));
        },
		error : function() {
			alert("请求异常！");
		}
	});
 }

/**
 * 菜单显示
 */
function buildTreeMenuV2(menuList) {
//	console.log("resultBean.data>>>"+JSON.stringify(menuList));
	//在ul里添加菜单 
	var sideMenu=$("#side-menu");
	var menu_html_1="";
	for (var i = 0; i < menuList.length; i++) {
		menu_html_1+='<li>';
		menu_html_1+='<a href="#">';
          //menu_html_1+='<i class="fa fa-edit"></i>'; //小图标
		menu_html_1+='<span	class="nav-label">';		
		menu_html_1+=menuList[i].name;			
		menu_html_1+='</span> <span class="fa arrow"></span></a>';
		//二级处理
		var menu_2=menuList[i].menus;
		if(menu_2!=null&&menu_2!=""){
			menu_html_1+='<ul class="nav nav-second-level">'; // collapse
			for (var j = 0; j < menu_2.length; j++) {//二级 for 开始
				var menu_3=menu_2[j].menus;
				if(menu_3!=null&&menu_3!=""){//有三级 &&typeof(menu_3) != 'undefined'
					menu_html_1+='<li><a href="#">';
					menu_html_1+=menu_2[j].name;
					menu_html_1+='<span class="fa arrow"></span></a>';
					menu_html_1+='<ul class="nav nav-third-level">';
					//三级处理
					for (var k = 0; k < menu_3.length; k++) {
						var menu_4=menu_3[k].menus;
						if(menu_4!=null&&menu_4!=""){//有四级
							menu_html_1+='<li><a href="#">';
							menu_html_1+=menu_3[k].name;
							menu_html_1+='<span class="fa arrow"></span></a>';
							menu_html_1+='<ul class="nav nav-four-level" style="padding-left:15px;">';
							for (var p = 0; p < menu_4.length; p++) {
								menu_html_1+='<li><a class="J_menuItem" href="';
								var href_4=getHrefHtml(menu_4[p].href);
								menu_html_1+=href_4;
								menu_html_1+='">';
								menu_html_1+=menu_4[p].name;
								menu_html_1+='</a>';
								menu_html_1+='</li>';
							}
							menu_html_1+='</ul></li>';//四级结束
						}else{
							var href_3=getHrefHtml(menu_3[k].href);
							menu_html_1+='<li><a class="J_menuItem" href="';
							menu_html_1+=href_3;
							menu_html_1+='">';
							menu_html_1+=menu_3[k].name;
							menu_html_1+='</a>';
							menu_html_1+='</li>';
						}
					}
					menu_html_1+='</ul></li>';//三级结束
				}else{
					var href=getHrefHtml(menu_2[j].href);						
					menu_html_1+='<li><a class="J_menuItem" href="';
					menu_html_1+=href;
					menu_html_1+='">';
					menu_html_1+=menu_2[j].name;
					menu_html_1+='</a></li>';
				}
			}//二级 for 结束
			menu_html_1+='</ul>';
		}//二级 if end
		menu_html_1+='</li>';//一级结束
	}//一级for end
	sideMenu.append(menu_html_1);
    	  // MetsiMenu
	    $('#side-menu').metisMenu();
	    //菜单点击
	     J_iframe
		 $(".J_menuItem").on('click',function(){
		        var url = $(this).attr('href');
		        $("#J_iframe").attr('src',url);
		        return false;
		});
}
	
/**
 * 拼接html
 */
	function getHrefHtml(html){
 		var randomNum = Math.floor(Math.random() * (182014 + 1));
		var href="/views/login.html";
    	if(typeof(html) == 'undefined' || !html){
    		href=getContextPath() + "/views/login.html?vNum=" + randomNum;
    	}
    	if(html.lastIndexOf(".html")>0)
    	{
//     		href=getContextPath() + html+"?vNum=" + randomNum;
    		href=getContextPath() + html;

    	}else
		{
    		href=getContextPath() + html+".html";
		}
    	return href;
	}
	