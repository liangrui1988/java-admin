/**
 * Pagination分页 table 需要table-data-url='uri'
 * 
 * @param table
 *            id或calss
 * @param flag
 *            刷新表数据
 */

function getPaginationTable(table, flag) {
	alert("xx");
	$(table).bootstrapTable('removeAll');
	var uri = $(table).attr('table-data-url');
	if (flag) {// null undefinded "" NaN typeof
		$(table).bootstrapTable('refresh', queryParams);
		return;
	}
	alert(uri);

	$(table).bootstrapTable({
		method : 'get', // 这里要设置为get，不知道为什么 设置post获取不了
		url : getContextPath()+ uri,
		cache : false,
		striped : true,
		silent : true,
		pagination : true,
		pageList : [ 5, 10, 20, 50, 100, 200 ],
		// contentType: "application/x-www-form-urlencoded",
		pageSize : 10,
		// pageNumber : 1,
		queryParamsType : 'limit',
		// search : true,
		sidePagination : 'server',// 设置为服务器端分页
		queryParams : queryParams
	// ,// 参数
	// showColumns : true,
	// showRefresh : true
	});
};