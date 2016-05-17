
function showimg(id) {
	var obj = document.getElementById(id);
	obj.submit();
	
	//$(id+">img")
	
	
}

function imgUpcallback(resultDate) {
	//alert(JSON.stringify(resultDate));
	var id = resultDate.id;
	
	var img_size="_180x170";
	
	//if(resultDate.imgPath=="")
	//{
	//  $("#imgsrc_" + id + "").attr("src", getImgDomain() + "/audit/images/NULL_PRODUCT.jpg?170*160");
	//}else{
	  $("#img_" + id + "").attr("value", resultDate.imgPath);
	  $("#imgsrc_" + id + "").attr("src", getImgDomain() + resultDate.imgPath+img_size);
	//}

	
};

// ////////////////
function upload1(o) {
	var hideForm = $('#insertPicForm'), $file = $(o).clone();
	hideForm.append($file);
	var options = {
		dataType : "json",
		beforeSubmit : function() {
			alert("正在上传");
		},
		success : function(result) {
			alert('成功上传！');
			var data = jQuery.parseJSON(result.result);
			document.getElementById("photoId").value = data.photoId;
			var photoUrl = document.getElementById("photoUrl");
			photoUrl.src = data.photoUrl;
			$file.remove();
		},
		error : function(result) {
			var data = jQuery.parseJSON(result.result);
			var message = data.message;
			$file.remove();
			alert(message);
		}
	};
	hideForm.ajaxSubmit(options);
	return !1;
}