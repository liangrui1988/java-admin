 var  demoIframe = $("#testIframe");
 var clickNode="";   //获取最后点击的菜单树节点
 var treeData="";
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
            var zTree = $.fn.zTree.getZTreeObj(treeId);
            clickNode=treeNode;
            findMenu();
            if (treeNode.isParent) {
                zTree.expandNode(treeNode);
            }
            return true;
        }
    }
};
 
$(function(){
	topLoading();
	loadMenuTree();
	$("#save_button").on("click",save);
	$("#requestFrom").validate({
		   rules: {
				name:{required:true},
				status:"required",
				orderNo:{digits:true}
		   },
		   submitHandler: function(form) {
				var params=getParams();
				if($("#id").val()!=""){
					if(clickNode.isParent){
						var childrens = clickNode.children;
						if(childrens){
							if(childrens.length>0&&params.parentId){
								show_dialog(true, "亲！不能把有子菜单的一级菜单移到其他一级菜单", "error");
								$("#save_button").on("click",save);
								return;
							}
							if(params.status<=0){
								for(var i = 0;i < childrens.length; i++){
									if(childrens[i].status==1){
										show_dialog(true, "请先删除该菜单的子菜单", "error");
										$("#save_button").on("click",save);
										return;
									}
								}
							}
						}
					}
					
					params["id"]=$("#id").val();
					$.ajax({
				        url:getContextPath()+"/pc/sys/menu/update", 
				        type: "POST",
				        dataType:"json",
				        data: params, 
				        success: function(resultBean, status, xhRequest){
				        	if(!handleAjaxRequest(resultBean, status,xhRequest))return;
				        	if(resultBean.success==false){
				        		show_dialog(true, "修改失败", "error");
							}else{
								show_dialog(true, "修改成功", "success");
								if($("#oldMenuImg").val()!=$("#imgPath").val()){
									var oldImagePath = $("#oldMenuImg").val()?"&path="+$("#oldMenuImg").val():"";
									deleteOldImg(oldImagePath);
								}
								loadMenuTree();
								clean_data();
								clickNode="";
							}
				        	$("#save_button").on("click",save);
				        }
					});
				}/*else{
					$.ajax({
				        url:getContextPath()+"/pc/sys/menu/insert", 
				        type: "POST",
				        dataType:"json",
				        data: params, 
				        success: function(resultBean, status, xhRequest){
				        	if(resultBean.success==false){
								show_dialog(true, "保存失败", "error");
							}else{
								show_dialog(true, "保存成功", "success");
								loadMenuTree();
								clean_data();
								clickNode="";
							}
				        	$("#save_button").on("click",save);
				        }
					});
				}*/
		   },
		   invalidHandler: function(form, validator) { 
			   $("#save_button").on("click",save);
		   }
	});
});

function _Dialog(options) {
    $(window.parent.showPrompt(options));
}

function show_dialog(_countdown,_msg,_msgicon){
	options = {
            _type: 'MsgDialog',
            _countdown:_countdown,
            _msg: _msg, 
            _msgicon: _msgicon,
            _closeDialog:true
        };
	_Dialog(options);
}

//加载树
function loadMenuTree(){
	$("#parentId").empty();
	$.ajax({
        url:getContextPath()+"/pc/sys/menu/loadTreeMenus", 
        type: "POST",
        dataType:"json",
        data: "", 
        success: function(data, status, xhRequest){
        	topLoaded();
       	 	var t = $("#min-ztree");
       	 	treeData=data.data;
            t = $.fn.zTree.init(t, setting, data.data);
            var menuList = data.data;
            $("#parentId").append("<option value=''>菜单选择</option>");
            for(var i = 0;i < menuList.length;i++){
            	if(!menuList[i].parentId){
            		$("#parentId").append("<option value='"+menuList[i].id+"'>"+menuList[i].name+"</option>");
            	}
            }
        }
    });
}

//获取菜单树节点信息
function findMenu(){
	$("#id").val(clickNode.id);
	$("#name").val(clickNode.name);
	$("#orderNo").val(clickNode.orderNo);
	$("#status").val(clickNode.status);
	$("#uri").val(clickNode.uri);
	$("#imgsrc").attr("src",clickNode.imgPath?getPublicUploadDomain(true)+clickNode.imgPath:'../../resources/images/logo/pic1.jpg').removeAttr("style");
	$("#imgPath").val(clickNode.imgPath);
	$("#oldMenuImg").val(clickNode.imgPath);
	$("#bigImagePath").attr("href",$("#imgsrc").attr("src"));
	$("#maker").val(clickNode.maker);
	$("#isModual")[0].checked = clickNode.isModual==1?true:false;
	if(clickNode.parentId){
		$("#parentId").val(clickNode.parentId);
	}else{
		$("#parentId").val("");
	}
	$("#name + label").remove();
	$("#status + label").remove();
	$("#orderNo + label").remove();
	countWorld();
	var oldImagePath = $("#oldImgPath").val()?"&path="+$("#oldImgPath").val():"";
	deleteOldImg(oldImagePath);
}

function deleteOldImg(oldImagePath){
	if(oldImagePath){
  		 $.post(getPublicUploadDomain(true) + "/api/delete","cutPhoto=false"+oldImagePath,function(resultBean){
  			 if(resultBean.success){
  				 $("#oldImgPath").val("");
  			 }
  		 },'jsonp');
  	}
}

//添加一级菜单
/*function addMenu(){
	clean_data();
}*/

//添加子菜单
/*function addClidrenMenu(){
	if(clickNode==""){
		show_dialog(true, "请先选择父菜单", "error");
		return;
	}
	if(clickNode.level>=1)
	{
		show_dialog(true, "亲，最多只能添加两级菜单", "error");
		return;
	}
	clean_data();
	$("#parentId").val(clickNode.id);
}*/

function clean_data(){
	$("#id").val("");
	$("#name").val("");
	$("#status").val("");
	$("#orderNo").val("");
	$("#uri").val("");
	$("#parentId").val("");
	$("#imgsrc").attr("src","../../resources/images/logo/pic1.jpg");
	$("#imgPath").val("");
	$("#oldImgPath").val("");
	$("#maker").val("");
	$("#isModual")[0].checked = false;
	countWorld();
}

//删除菜单
/*function deleteMenu(){
	if(clickNode==""){
		show_dialog(true, "请先选择菜单", "error");
		return;
	}
	if(clickNode.isParent){
		var childrens = clickNode.children;
		if(childrens){
			for(var i = 0;i < childrens.length; i++){
				if(childrens[i].status==1){
					show_dialog(true, "请先删除该菜单的子菜单", "error");
					return;
				}
			}
		}
	}
	_confirmDialog("亲，你确定要删除吗？","删除温馨提示",function(e){
		if(e){
			topLoading();
			$.ajax({
		        url:getContextPath()+"/pc/sys/menu/delete", 
		        type: "POST",
		        dataType:"json",
		        data: {"id":clickNode.id}, 
		        success: function(resultBean, status, xhRequest){
		        	topLoaded();
		        	if(resultBean.success==false){
		        		show_dialog(true, "删除失败", "error");
					}else{
						show_dialog(true, "删除成功", "success");
						var oldImagePath = $("#oldMenuImg").val()?"&path="+$("#oldMenuImg").val():"";
						deleteOldImg(oldImagePath);
						loadMenuTree();
						clean_data();
						clickNode="";
					}
		        }
			});
		}
	});
}*/

//提交保存
function save(){
	$("#save_button").unbind('click');
	if(clickNode==""){
		show_dialog(true, "请先选择菜单", "error");
		$("#save_button").on("click",save);
		return;
	}
	$("#requestFrom").submit();
}

//获取表单数据
function getParams(){
	return {
		"name":$("#name").val(),
		"orderNo":$("#orderNo").val(),
		"uri":$("#uri").val(),
		"maker":$("#maker").val(),
		"parentId":$("#parentId").val(),
		"imgPath":$("#imgPath").val(),
		"isModual":$("#isModual")[0].checked?"1":"0",
		"status":$("#status").val()
	};
}

//跨域回调方法
function success(resultBean){
	//兼容IE
   	var obj = typeof(resultBean)=="string" ? jQuery.parseJSON(resultBean) : resultBean;
   	if(obj.success){
  		//显示图片
  		$("#imgsrc").attr("src", getPublicUploadDomain(true) + obj.data.path);
  		$("#imgPath").val(obj.data.path);
  		$("#bigImagePath").attr("href",$("#imgsrc").attr("src"));
  		
  		var oldImagePath = $("#oldImgPath").val()?"&path="+$("#oldImgPath").val():"";
		deleteOldImg(oldImagePath);
		$("#oldImgPath").val(obj.data.path);
   	}else{
   		show_dialog(true, obj.data.path, "error");
   	}
}