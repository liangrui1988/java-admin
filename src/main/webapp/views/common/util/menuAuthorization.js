//setting 配置树菜单 属性
		var settingA = {
			check: {
					enable: true,
					chkStyle: "checkbox",
					chkboxType: { "Y": "ps", "N": "ps" }, //"p" 表示操作会影响父级节点； 	"s" 表示操作会影响子级节点。
					nocheckInherit: true, //当父节点设置 nocheck = true 时，设置子节点是否自动继承 nocheck = true 。
					chkDisabledInherit: true //当父节点设置 chkDisabled = true 时，设置子节点是否自动继承 chkDisabled = true 。
			},
		    async: {
				enable: false, //不用异步加载
				url:getContextPath()+"/sys/menu/list",
				autoParam:["id","name"],
				//autoParam:["id", "name=n", "level=lv"],
				//otherParam:{"otherParam":"zTreeAsyncTest"},
				//dataFilter: filter,
				type: "get"
			},
			view: {
				dblClickExpand: false //双击节点时，是否自动展开父节点的标识 默认值: true
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "parentId",
					rootPId: ""
				}
			}
//			,
//			callback: {
//				beforeClick: beforeClick,//用于捕获单击节点之前的事件回调函数，并且根据返回值确定是否允许单击操作 默认值：null
//				onClick: onClick  //用于捕获节点被点击的事件回调函数
//			}
		};
		

		var zNodes;

		function beforeClick(treeId, treeNode) {
			//var check = (treeNode && !treeNode.isParent);
			//if (!check) alert("只能选择城市...");
			return true;
		}
		
		/**
		 * 点击事件
		 * @param e
		 * @param treeId
		 * @param treeNode
		 */
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			var code="";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) 
			{
				//v += nodes[i].name + ",";
				v = nodes[i].name;
				code=nodes[i].id;
				var nodeall = nodes[i].getParentNode();
				var check = (nodeall && !nodeall.isParent);
				if (!check)
				{
				  v=(nodeall.name+"  ")+v;
				  $("#area_h_2").val(nodeall.id);
				  var nodeall2 = nodeall.getParentNode();
				  if (nodeall2!=null)
			      {
				     v=(nodeall2.name+"  ")+v;
					 $("#area_h_3").val(nodeall2.id);
			      }else
			      {
			    	   $("#area_h_3").val("");
			      }
				}
			}
			//if (v.length > 0 ) v = v.substring(0, v.length);//-1
			var cityObj = $("#citySel");
			cityObj.attr("value", v);
			$("#area_h_1").val(code);
		}

		//选 择  显示div
		function showMenu() 
		{
			var cityObj = $("#citySel");
			var cityOffset = $("#citySel").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
			$("body").bind("mousedown", onBodyDown);
		}
		
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}

		
		/***
		 * 初始加载数据
		 */
		$(document).ready(function(){
			$.ajax({
				url : getContextPath()+"/sys/menu/listAll",
				type : "get",
				dataType : "json",
				data : {
					level : ""
				},
				success : function(returnData, status) {
					if (status = "success") {
						//alert("returnData:"+JSON.stringify(returnData.data));
						if(returnData.success){
							 zNodes = returnData.data;
							 $.fn.zTree.init($("#treeDemo"), settingA, zNodes);
								
						}else{
							alert("请求失败");
						}
					}
				}
			});
		});
		

		
		 //获取所有选中节点的值
	    function getCheckedAll() {
	        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	        var nodes = treeObj.getCheckedNodes(true);
	        var arrayObj = new Array();　//创建一个数组
	        for (var i = 0; i < nodes.length; i++) {
	        	arrayObj.push(nodes[i].id);
	        }
	       // alert(JSON.stringify(arrayObj))
	        return arrayObj;
	    }    
	    
		
		//全选
	    function checkAllNodes() {
	        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	        treeObj.checkAllNodes(true);
	    }

	    //全取消
	    function cancelAllNodes() {
	        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	        treeObj.checkAllNodes(false);
	    }
	    
	    //折叠展开
	    function expandAll(istrue) {
	        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		    treeObj.expandAll(istrue);
	    }
	    
	 
	    
	    
	    //确定
	    function enterSel(){
	    	$("#menuContent").css("display","none");
	    	//getCheckedAll();
	    }