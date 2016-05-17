
		var setting = {
		    async: {
				enable: true,
				url:"/area/getAreas.do",
				autoParam:["id=code"],
				//autoParam:["id", "name=n", "level=lv"],
				//otherParam:{"otherParam":"zTreeAsyncTest"},
				//dataFilter: filter,
				type: "get"
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};

		var zNodes;

		function beforeClick(treeId, treeNode) {
			//var check = (treeNode && !treeNode.isParent);
			//if (!check) alert("只能选择城市...");
			return true;
		}
		
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			var code="";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				//v += nodes[i].name + ",";
				v = nodes[i].name;
				code=nodes[i].id;
			}
			if (v.length > 0 ) v = v.substring(0, v.length);//-1
			var cityObj = $("#citySel");
			cityObj.attr("value", v);
			$("#areaCodeV").val(code);
		}

		//选 择
		function showMenu() {
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

		$(document).ready(function(){
			
			$.ajax({
				url : "/area/getAreas.do",
				type : "POST",
				dataType : "json",
				data : {
					code : "P"
				},
				success : function(returnData, status) {
					if (status = "success") {
						//alert("returnData:"+returnData);
						 zNodes = returnData;
						//zNodes=dataj;
						$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					}
				}
			});
			
			
			//alert(JSON.stringify(zNodes));
			//$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
		//通过等级获取了菜单 , seleId
		function getAreas(level) {
			var dataj;
			$.ajax({
				url : "/area/getAreas.do",
				type : "POST",
				dataType : "json",
				data : {
					code : level
				},
				success : function(returnData, status) {
					if (status = "success") {
						alert("returnData:"+returnData);
						var dataj = returnData;
						zNodes=dataj;
					}
				}
			});
			
			return dataj;
		}
		
		//清空
		function claclArea()
		{
			$("#citySel").val('');
			$("#areaCodeV").val('');
		}