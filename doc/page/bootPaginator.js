function getTab() {
	var url = ctx+"/lead/sale/summary/getStatListTable";
	$('#summary_table').bootstrapTable({
		method : 'get', // 这里要设置为get，不知道为什么 设置post获取不了
		url : url,
		cache : false,		
		striped : true,
		pagination : true,
		pageList : [5, 10, 20, 50, 100, 200],
		// contentType: "application/x-www-form-urlencoded",
		pageSize : 10,
		//pageNumber : 1,
		queryParamsType:'limit',
		//search : true,
		sidePagination : 'server',// 设置为服务器端分页
		queryParams : queryParams//,// 参数
		//showColumns : true,
		
		//showRefresh : true
	});
}
// 设置传入参数
function queryParams(parma) {
	//获取查询条件
	var mehtod = $("#_feeDay").val();
	var teamId = $("#_teme_selection").val();
	// 选择本月和上月
	if (mehtod == "" || "undefined" == mehtod) {
		mehtod = $('input[name="radio1"]:checked ').val();
	} 
	
	var dataParm = '';
	if (mehtod != "" && mehtod != null && mehtod != 'undefined') {
		dataParm += "mehtod=" + mehtod;
		parma = $.extend(parma,{mehtod:mehtod,});

	}

	if (teamId != "" && teamId != 'undefined' && teamId != null) {

		parma = $.extend(parma,{teamId:teamId,});

	}
	//parma.teamId = teamId;
	//return dataParm+parma;
	return parma;
}
$(function() {
	getTab();
	$('#_prveMethod').click(function(){
		$('#downth').text('sdfsdfsdf');
	});
});

function xuhao(r,v,i){
	return i+1;
}
function down(r,v,i){
	if(i == 0){
		return '下载';
	}
	return '';
}