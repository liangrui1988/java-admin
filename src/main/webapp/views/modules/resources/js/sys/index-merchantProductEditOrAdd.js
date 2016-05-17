//----------------------------------------------------------------------------------------------------------------
//-- 商家商品维护 新增保存js  对应页面 index-merchantProductEditOrAdd.html
//--liangrui
//--2014/3
//--------------------------------------------------------------------------------------------------------------
 
    
     
     /////////validation mark//////////////
     var f_name=false;
	 var f_searchProBar=false;
	 var f_normWeight=false;
	 var f_shelfLife=false;
	 var f_price=false;
	 var f_v_baseType=false;
	 var f_actual_price=false;
	 var productTypeId;	//当前商品所属分类的ID
	 var typeIds = [];		//存放商品所属类型，及其父、爷类型：0：第一级，1：第二级，2：第三级

    $(function(){
    	
         getDicts();//加载字典单位
         
    	$('[placeholder]').focusin();
	    validationBlurEvn();//鼠标移开验证
		action = getUrlParam("action");
	   //如果是update,则导入商品数据：
		 $('[placeholder]').focusout();
	  if(action=="update")
	  {
		
		var productId = getUrlParam("id");
		$("#merchantId").val(productId);
		$("#mer_product_nav_but").html("修改");	
		
		loadProductType_Update(productId);//加载分类
		//查询
		//inIdFindProductInfo(productId);
	    //加载update but
		$("#but_save").click(function()
		{
			if(subFormValidation())
			{
			  update_save();
		    };//验证表单
		});
	  }else{
		
		loadProductType();//加载分类
		//加载save but
		$("#but_save").click(function()
	    {
		  if(subFormValidation())
		  {
			but_save();
		  };//验证表单
		});
	  }//else end;
	  //$("#imgSrc0").click(function(){$("#file_0").click();}); //兼容性问题，不使用这种
		
	});

  //从字典表商品单位：
  function getDicts()
  {
      $.ajax({
				url : getContextPath()+"/api/sys/getDicts",
				type : "POST",
				dataType : "json",
				data : {clsCode:'UNIT'},
				success : function(returnData, status)
				{
					if(status="success")
					{
						var html = '<option value="">请选择：</option>';
						$.each(returnData.data, function(value, entry) 
						{
	      				  html += '<option value="'+value+'" >'+entry+'</option>';	
	      		         });
		    			$("#unit").html(html);
					}
				}
		    });
  }
  
  //加载分类
  function loadProductType()
  {
	  $.ajax({
			url : getContextPath()+"/pc/pro/merProductType/searchByMerchantID",
			type : "POST",
			dataType : "json",
			//data : {clsCode:'UNIT'},
			success : function(returnData, status)
			{
				if(status="success")
				{
					var htmls = '<option value="">请选择：</option>';
					var temp_Date=returnData.data.beanList;
					    $.each(temp_Date, function(value, entry) 
						{
						  htmls += '<option value="'+entry.id+'" >'+entry.name+'</option>';	
		      		    });
					
					/*for(var i=0;i<temp_Date.length;i++)
					{
					  htmls += '<option value="'+temp_Date[i].id+'" >'+temp_Date[i].name+'</option>';	
					}*/
	    			$("#type0").html(htmls);
				}
			}
	    });
  }
  
  
  //加载分类
  function loadProductType_Update(productId)
  {
	  $.ajax({
			url : getContextPath()+"/pc/pro/merProductType/searchByMerchantID",
			type : "POST",
			dataType : "json",
			success : function(returnData, status)
			{
				if(status="success")
				{
					var htmls = '<option value="">请选择：</option>';
					var tempDate=returnData.data.beanList;
					for(var i=0;i<tempDate.length;i++)
					{
					  htmls += '<option value="'+tempDate[i].id+'" >'+tempDate[i].name+'</option>';	
					}
	    			$("#type0").html(htmls);
	    			//查询
	    			inIdFindProductInfo(productId);
				}
			}
	    });
   }
  
  //保存
  function but_save(){		 
	    //获取值
	    var  typeId=$("#type0").val();
		var  status=$("input[name='status']:checked").val(); //状态   编号 		
		var  unit=$("#unit").val();           //商品单位code 
		var  name=$("#name").val();	          //商品名称 
		var  price=$("#price").val();         //参考价格 
		var  ikey=$("#ikey").val();           //标签  
		if(ikey=="多标签使用;隔开"){
			ikey = "";
	  	}
	
		var remark=$("#remark").val();//商品简介
		var  photoUrl=$("#imgPath").val();    //缩略图  		
		var normWeight=$("#normWeight").val();//标准重量（克） 
		var actual_price=$("#actual_price").val();// 销售价格
		
		var TProProductPhotos = new Array();  //商品图片
		$(".imgUrl").each(function(){
			 var ProductPhoto=new Object();
			 ProductPhoto.url=$(this).val();
			 TProProductPhotos.push(ProductPhoto);
		 });
		 //""处理
		 if(""==price) price=null;
		 if(""==normWeight)   normWeight=null;
		 if(""==actual_price) actual_price=null;
		var productVo={
			typeId:typeId,
			status:status,
			unit:unit,
			name:name,
			price:price,
			ikey:ikey,
			remark:remark,
			photoUrl:photoUrl,
			normWeight:normWeight,
			actualPrice:actual_price,
			TProProductPhotos:TProProductPhotos,
	};
		
            
		$.ajax({
			url : getContextPath()+"/pc/pro/merproduct/insert",
			type : "POST",
			//contentType: "application/json; charset=utf-8",
			dataType : "json",
			data :{productVoJson:JSON.stringify(productVo)},
			success : function(returnData,status,XMLHttpRequest)
			{	
				if(handleAjaxRequest(returnData,status,XMLHttpRequest))
				{
					window.location.href=getContextPath()+"/front/pro/index-merchantProductList.html";
					//window.history.go(-1);
				}
			}						
		});
		
  } //save end
  
//============================================================================================================
//修改操作js
//==========================================================================================================
   //id查询商品信息
   function inIdFindProductInfo(idVal){
	   var pager=new Object();	
	   var productVo=new Object();
	   productVo.id=idVal;
	   $.ajax({
			url : getContextPath()+"/pc/pro/merproduct/search",
			type : "POST",			
			dataType : "json",
			data : (pager,productVo),
			success : function(returnData,status,XMLHttpRequest) { 
			if(handleAjaxRequest(returnData,status,XMLHttpRequest)==true)
			{					
				var returnDataObj=returnData.data;				
				//填充数据：
				$("#productType").val(returnDataObj.typeId);  // 商品分类   
				$("#unit").val(returnDataObj.unit);           //商品单位code 	
				$("#name").val(returnDataObj.name);	          //商品名称 
				$("#price").val(returnDataObj.price);         //参考价格 
				$("#ikey").val(returnDataObj.ikey);           //标签  
				$("#remark").val(returnDataObj.remark);
				$("#normWeight").val(returnDataObj.normWeight);//标准重量（克） 	
				$("#actual_price").val(returnDataObj.actualPrice);
				$("#mer_pro_id").val(returnDataObj.id);
				$("#type0").val(returnDataObj.typeId);
				
				//缩略图  
				if(returnDataObj.photoUrl!="")
				{
					addOnePic("#mainPicButton", returnDataObj.photoUrl, returnDataObj.fullPhotoUrl);
					origPics[0] = returnDataObj.photoUrl;
				}
				 //状态   编号 	
				$("input[name='status']").each(function(){
					if($(this).val()==returnDataObj.status){
						$(this).attr('checked',true);
					}
				});
			
				//显示图片
				var arryPhoto=returnDataObj.tproProductPhotos;
				for(var i=0;i<arryPhoto.length;i++){
					addOnePic("#morePicButton",arryPhoto[i].url, arryPhoto[i].fullUrl);
				    //imgNo=i+1;
				    origPics[i+1] = arryPhoto[i].url;
				}
				//-------------------------------------------------------------------	
				}
			}
		});
   }
   
   //update_save 修改保存
   function update_save(){
	   //获取值
	   var mer_pro_id=$("#mer_pro_id").val();
	    var  typeId=$("#type0").val();
	    var  id=$("#pro_id").val();
		var  status=$("input[name='status']:checked").val(); //状态   编号 		
		var  unit=$("#unit").val();           //商品单位code 
		var  name=$("#name").val();	          //商品名称 
		var  price=$("#price").val();         //参考价格 
		var  ikey=$("#ikey").val();           //标签  
		if(ikey=="多标签使用;隔开"){
			ikey = "";
	  	}
		var remark=$("#remark").val();//商品简介
		var  photoUrl=$("#imgPath").val();    //缩略图  		
		var normWeight=$("#normWeight").val();//标准重量（克） 
		var actual_price=$("#actual_price").val();// 销售价格
		var TProProductPhotos = new Array();  //商品图片
		
		$(".imgUrl").each(function(){
			 var ProductPhoto=new Object();
			 ProductPhoto.url=$(this).val();
			 TProProductPhotos.push(ProductPhoto);
		 });
	
		 
		 //""处理
		 if(""==price) price=null;
		 if(""==normWeight)   normWeight=null;
		 if(""==actual_price) actual_price=null;
		 
		var productVo={
			id:mer_pro_id,
			typeId:typeId,
			status:status,
			unit:unit,
			name:name,
			price:price,
			ikey:ikey,
			remark:remark,
			photoUrl:photoUrl,
			normWeight:normWeight,
			actualPrice:actual_price,
			TProProductPhotos:TProProductPhotos,
	};
		
		//如果和原图不一样，则把原图删除
		/* if(origPics.length>0 && origPics[0]!=photoUrl)
		 {
			delImg(origPics[0], true);//true 删除图片 
	    }*/
		//是否有需要删除的图片原文件
		// deleteOrigPics(TProProductPhotos);
		
		$.ajax({
			url : getContextPath()+"/pc/pro/merproduct/update",
			type : "POST",
			//contentType: "application/json; charset=utf-8",
			dataType : "json",
			data :{productVoJson:JSON.stringify(productVo)},
			success : function(returnData,status,XMLHttpRequest)
			{	
				if(handleAjaxRequest(returnData,status,XMLHttpRequest))
				{
				 window.location.href=getContextPath()+"/front/pro/index-merchantProductList.html";
				}
			}
		});
   }

	
	//鼠标移开验证
	function validationBlurEvn(){
		var reg=/^[0-9]+(.[0-9]{0,2})?$/;//验证有两位小数的正实数
		//var reg=/[0-9]+\.[0-9]{2}?/; //验证有两位小数的正实数
		 $("#name").blur(function()
				 {
						if($("#name").val()==''||$("#name").val()==null){
							$("#nameDiv").show();
							$("#nameDiv").html("<i>商品名称不能为空</i>");
							f_name=false;
						}else{
							$("#nameDiv").show();
							var objvar=lengthCheck($("#name")[0],200);
							$("#nameDiv").html("<i>"+objvar.showvar+"</i>");
							f_name= objvar.trueOrfalse;
							
						}
				});
				
			$("#normWeight").blur(function()
				 {
					 if($("#normWeight").val()!=""&&$("#normWeight").val()!=null){
						if(reg.test($("#normWeight").val()))
						{
							f_normWeight=true;
							$("#normWeightDiv").hide();
						}else
						{
							f_normWeight=false;
							$("#normWeightDiv").show();
							$("#normWeightDiv").html("<i>必须为整数或带有两位小数的小数</i>");
						}
					 }
					});
				 
				 $("#price").blur(function()
				 {
					 var v=$("#price").val();
					 if(v!=""&&v!=null)
					 {
						 if(!reg.test(v)){
								f_price=false;
								$("#priceDiv").show();
								$("#priceDiv").html("<i>必须为整数或带有两位小数的小数</i>");
							}else{
								f_price=true;
								$("#priceDiv").hide();
							} 
					 }
					});
			
			$("#actual_price").blur(function()
			  {
					 var v=$("#actual_price").val();
					 if(v!=""&&v!=null)
					 {
						 if(!reg.test(v)){
								f_actual_price=false;
								$("#actual_priceDiv").show();
								$("#actual_priceDiv").html("<i>必须为整数或带有两位小数的小数</i>");
							}else{
								f_actual_price=true;
								$("#actual_priceDiv").hide();
							} 
					 }
						
				});
				 
	}////鼠标移开验证 end
	
	//提交表单时js 验证
	function subFormValidation(){
		 //　　isNaN	
		var reg=/^[0-9]+(.[0-9]{0,2})?$/;//验证有三位小数的正实数
			
		 if(!f_name){	
				if($("#name").val()==''||$("#name").val()==null){
					$("#nameDiv").show();
					$("#nameDiv").html("<i>商品名称不能为空</i>");
					return false;
				}else{
					$("#nameDiv").show();
					var objvar=lengthCheck($("#name")[0],200);
					$("#nameDiv").html("<i>"+objvar.showvar+"</i>");
				    if(!objvar.trueOrfalse) return false;
				}
		 }
		 
		var sub_normWeight_val=$("#normWeight").val();
		if(sub_normWeight_val!=""&&!f_normWeight){
			if(reg.test(sub_normWeight_val)){
				$("#normWeightDiv").hide();
			 }else{
				$("#normWeightDiv").show();
				$("#normWeightDiv").html("<i>必须为整数或带有两位小数的小数</i>");return false;
			 }
		  }
		
		var sub_price_val=$("#price").val();
		if(sub_price_val!=""&&!f_price){
			if(!reg.test(sub_price_val)){
				$("#priceDiv").show();
				$("#priceDiv").html("<i>必须为整数或带有两位小数的整数</i>");return false;
			}else{
				$("#priceDiv").hide();
			}
		}

		var actual_price_val=$("#actual_price").val();
		if(actual_price_val!=""&&!f_price){
			if(!reg.test(actual_price_val)){
				$("#actual_priceDiv").show();
				$("#actual_priceDiv").html("<i>必须为整数或带有两位小数的整数</i>");return false;
			}else{
				$("#actual_priceDiv").hide();
			}
			
		}
		
		//商家分类判断
	   // var isSelfType=false;
		//自家分类验证
		if($("#type0").val()=="")
		{
			_message("商品分类不能为空！", function(){}, "error");
			return false;
		}
		
	 return true;
	}//提交表单时js 验证 end;
///////////////////////////////////////////////////////////////////////////	
	//跨域回调方法
	function success(resultBean){
		//兼容IE
	   	var obj = typeof(resultBean)=="string" ? jQuery.parseJSON(resultBean) : resultBean;
	   	if(obj.success){
	  		//显示图片
	  		$("#imgsrc").attr("src", getContextPath() + obj.data.path);
	  		$("#imgPath").val(obj.data.path);
	  		$("#oldImgPath").val(obj.data.path);
	  		$("#bigImagePath").attr("href",$("#imgsrc").attr("src"));
	   	}else{
	   		show_dialog(true, obj.data.path, "error");
	   	}
	}
	
	
	  /* //填充三级分类列表
	   function fillProductTypes(){
			//导入基础商品分类（第一级）：	
	   	fillTypeList(0, null, typeIds[0]);
	   
			//如果是修改，则加载第二第三级分类
			if(action=="update"){
				for(var i=1; i<typeIds.length; i++){
					fillTypeList(i, typeIds[i-1], typeIds[i]);
					//fillTypeList(i, typeIds[i-1]);
				}
			}
	   };*/
	