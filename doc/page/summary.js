var initGroupStatitic = {
	chmonth : 0,
	groupid : 0,

	initStat : function(mehtod, teamId) {
		var dataParm = '';
		if (mehtod != "" && mehtod != null && mehtod != 'undefined') {
			dataParm += "mehtod=" + mehtod;
		}

		if (teamId != "" && teamId != 'undefined' && teamId != null) {

			if (dataParm == "") {
				dataParm += "teamId=" + teamId;
			} else {
				dataParm += "&teamId=" + teamId;
			}
		}

		// var ob = {
		// mehtod: (typeof mehtod) == 'undefined' ? '' : method,
		// teamId: teamId,
		// type:''
		// };

		// alert(dataParm);
		$.ajax({
			url : ctx+"/lead/sale/summary/getStatInfo",
			type : "GET",
			// contentType: "application/json; charset=utf-8",
			dataType : "json",
			// data :{productVoJson:JSON.stringify(objVo)},
			data : dataParm,
			// data:initGroupStatitic.ajaxdata,
			success : initGroupStatitic.ajaxpost
		});

		$("#_zd_tc").text();
	},
	ajaxdata : function() {
		return {
			mehtod : '',
			teamId : '',
			type : ''
		}
	},
	check : function() {
		// ajaxdata //
		return true;
	},
	setMethod : function(method) {

		ajaxdata.method = method;
	},
	init : function() {
		$("#_thisMethod").click(this.methodClick);
		$("#_prveMethod").click(this.methodClick2);
	},
	methodClick : function() {
		$("#_feeDay").val('');
		var temeId = $("#_teme_selection").val();
		// this.fire('staitisc');
		initGroupStatitic.initStat('b', temeId);
		//initTable('b', temeId);
		initAjaxData();

	},
	methodClick2 : function() {
		$("#_feeDay").val('');
		var temeId = $("#_teme_selection").val();
		initGroupStatitic.initStat('s', temeId);
		//initTable('s', temeId);
		initAjaxData();
	},
	ajaxpost : function(returnData, status, XMLHttpRequest) {
		// alert(returnData);
		if (status == "success") {
			var rolelist = returnData;
			// alert(rolelist);
			if (rolelist != null && rolelist != "") {
				// alert("rolelist.nationwide>"+rolelist.nationwide);
				$("#_qg_tc").text(formatCurrency(rolelist.nationwide));
				$("#_zg_tc").text(formatCurrency(rolelist.maxPersonalRecord));
				$("#_gr_tc").text(formatCurrency(rolelist.personalAVG));
				$("#_zd_tc").text(formatCurrency(rolelist.minPersonalRecord));
			} else {
				$("#_qg_tc").text(0);
				$("#_zg_tc").text(0);
				$("#_gr_tc").text(0);
				$("#_zd_tc").text(0);
			}

		}
	}
};


function showClickDate(date) {
	// date 回调
	var ds = date.cal.getDateStr();
	var temeId = $("#_teme_selection").val();
	initGroupStatitic.initStat(ds, temeId);
	initTable(ds, temeId);
	initAjaxData();
}

function page(n, s) {
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}


function initOffce() {
	$.ajax({
		url : ctx+"/lead/sale/summary/getTScaleTeam",
		type : "GET",
		// contentType: "application/json; charset=utf-8",
		dataType : "json",
		// data :{productVoJson:JSON.stringify(objVo)},
		data : '',
		success : function(returnData, status, XMLHttpRequest) {
			if (status == "success") {
				var rolelist = returnData;

				if (rolelist != null && rolelist != "" && rolelist.length > 0) {
					for (var i = 0; i < rolelist.length; i++) {
						var offce = rolelist[i];
						$("#_teme_selection").append(
								"<option value='" + offce.id + "'>"
										+ offce.name + "</option>"); // 为Select追加一个Option(下拉项)

						// var html='<div class="item "
						// data-value="'+offce.id+'">'+offce.name+'</div>';
						// $("#_teme_selection_div").append(html);
					}
				}

			}
		}
	});

	$("#_zd_tc").text();
}

function initTable(mehtod, teamId) {
	return ;//fix;
	var dataParm = '';
	if (mehtod != "" && mehtod != null && mehtod != 'undefined') {
		dataParm += "&mehtod=" + mehtod;
	}
	if (teamId != "" && teamId != 'undefined' && teamId != null) {
		dataParm += "&teamId=" + teamId;
	}

	//
	$.ajax({
		url : ctx+"/lead/sale/summary/getStatList",
		type : "GET",
		// contentType: "application/json; charset=utf-8",
		dataType : "json",
		// data :{productVoJson:JSON.stringify(objVo)},
		data : 'pageNo=1&pageSize=20' + dataParm,
		success : function(returnData, status, XMLHttpRequest) {
			// console.log(returnData);
			if (status == "success") {

				$("#summary_table tbody tr").remove();
				var rolelist = returnData.list;

				if (rolelist == "" || rolelist == null
						|| (typeof rolelist) == 'undefined'
						|| rolelist.length <= 0) {
					return;
				}
				var roleHtml = '';

				for (var i = 0; i < rolelist.length; i++) {
					var roleObj = rolelist[i];

					roleHtml += ' <tr>';
					roleHtml += ' <td>' + i + '</td>';
					roleHtml += ' <td>' + roleObj.salesman.name + '</td>';
					roleHtml += ' <td>' + roleObj.bonusAmount + '</td>';
					roleHtml += '    <td>' + roleObj.sellBonus + '</td>';
					roleHtml += '   <td>' + roleObj.halfYearBonus + '</td>';
					roleHtml += '    <td>' + roleObj.monthBonus + '</td>';
					roleHtml += '    <td>' + roleObj.salesNumber + '</td>';

					if (i == 0) {
						roleHtml += '   <td ';
						roleHtml += 'rowspan="' + rolelist.length + '"';
						roleHtml += ' onClick="javascript:btnDwonTable()" ><a href="#" >下载</a></td>';
					} else {
						// roleHtml += '<td></td>';
					}

					roleHtml += ' </tr>';

				}
				$("#summary_table tbody").append(roleHtml);
			}
		}
	});
}

// function thisMethod(){
// var temeId=$("#_teme_selection").val();
// initStat('b',temeId);

// }

// 本月



// 选择日期
function selectDateStat() {

	var temeId = $("#_teme_selection").val();
	var feeDay = $("#_feeDay").val();
	// 选择本月和上月
	if (feeDay == "" || "undefined" == feeDay) {
		var val_radio = $('input[name="radio1"]:checked ').val();
		initGroupStatitic.initStat(val_radio, temeId);

	} else {
		initGroupStatitic.initStat(feeDay, temeId);
	}

}


//$("#barMain-f-avg").hide();
//选择人均
$("#rj_hot_deals").click(function(){
	var rj_check = $("#rj_hot_deals_check");
	var disbar=rj_check.attr("disabled");
	
	if(disbar=="disabled"){
		return false;
	}
	
	$("#rj_hot_deals_check").attr('checked','true');
	initAjaxData();
	
	
//	if(rj_check.prop('checked')){
	
//	}
	//$('#barMain-f').toggle();
	//$("#barMain-f-avg").toggle();
	
	
});

//选择提成分类
$("#tc_fl_div").click(function(){
	$("#_tc_fl_checkbox").attr('checked','true');
	initAjaxData();
});

//选择环比
$("#_hb_div").click(function(){
//	$("#rj_hot_deals_check").attr('checked','true');
	initAjaxData();
});



var myBarChart;
// 路径配置
require.config({
	paths : {
		echarts : 'http://echarts.baidu.com/build/dist'
	}
});

var my_big_ec = null;

var rsData = {
		yAxisTd:[],
		sellBonuss:[],
		halfYearBonuss:[],
		monthBonuss:[],
		bonusAmounts:[],
        avgs:[],
        yAxisTdPerv:[],
		sellBonussPerv:[],
		halfYearBonussPerv:[],
		monthBonussPerv:[],
		bonusAmountsPerv:[],
        avgsPerv:[]
        
};


//mehtod, teamId
function initAjaxData(){
	//查询表数据
	getPaginationTable(".rl-page-table",true);
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
	}

	if (teamId != "" && teamId != 'undefined' && teamId != null) {

		if (dataParm == "") {
			dataParm += "teamId=" + teamId;
		} else {
			dataParm += "&teamId=" + teamId;
		}
	}


	// ajax 加载数据
	$.ajax({
		url : ctx+"/lead/sale/summary/getTeamSummary",
		type : "GET",
		async : false,
		dataType : "json",
		// data :{productVoJson:JSON.stringify(objVo)},
		data : dataParm,
		success : function(returnData, status, XMLHttpRequest) {
			if (status == "success") {
				//rsData = returnData;
				rsData.yAxisTd = typeof returnData.officeNames== 'undefined' ? [] : returnData.officeNames;
				rsData.sellBonuss = typeof returnData.sellBonuss == 'undefined' ? [] : returnData.sellBonuss;
				rsData.halfYearBonuss =typeof returnData.halfYearBonuss== 'undefined' ? [] : returnData.halfYearBonuss;
				rsData.monthBonuss =typeof returnData.monthBonuss== 'undefined' ? [] : returnData.monthBonuss;
				//总
				rsData.bonusAmounts=typeof returnData.bonusAmounts== 'undefined' ? [] : returnData.bonusAmounts;
				//人均
				rsData.avgs=typeof returnData.avgs== 'undefined' ? [] : returnData.avgs;
				
				//上一个月的数据
				rsData.yAxisTdPerv = typeof returnData.officeNamesPerv== 'undefined' ? [] : returnData.officeNamesPerv;
				rsData.sellBonussPerv = typeof returnData.sellBonussPerv == 'undefined' ? [] : returnData.sellBonussPerv;
				rsData.halfYearBonussPerv =typeof returnData.halfYearBonussPerv== 'undefined' ? [] : returnData.halfYearBonussPerv;
				rsData.monthBonussPerv =typeof returnData.monthBonussPerv== 'undefined' ? [] : returnData.monthBonussPerv;
				//总
				rsData.bonusAmountsPerv=typeof returnData.bonusAmountsPerv== 'undefined' ? [] : returnData.bonusAmountsPerv;
				//人均
				rsData.avgsPerv=typeof returnData.avgsPerv== 'undefined' ? [] : returnData.avgsPerv;
			}
		}
	});
	//$('#barMain-f').empty();
	myBarChart = my_big_ec.init(document.getElementById('barMain-f'), 'macarons');
	
	
	//根据选项来设置 不同
	var _option={};
	
	//有选人均
	var disbar=$("#rj_hot_deals_check").attr("disabled");
	var radio_v= $('input:radio[name="hot-deals_x"]:checked').val();
	//alert("radio_v>> "+radio_v);
	
	var radio_bh= $('input:radio[name="hot-deals_hb_x"]:checked').val();

	 //如果环比按扭选中	
	
	 if($('#hot-deals_hb_x').prop('checked'))
	 {
		
		 console.log(rsData);
		 if(radio_v=='tcfl')
		 {
			_option=get_option_bar_perv();
		}else if(disbar!="disabled"&&radio_v=='rj')
		{
			_option=get_option_rj_bar_perv();
		}else
		{//全部
			
			var _teme_selection_v= $("#_teme_selection").val();
			if(_teme_selection_v=="0"){//如果选择的是全部团队，
				_option=get_option_taemSum_bar_perv();
			}else{//一个团队的情况
				_option=get_option_taemSum_bar_perv_oneT();
			}
			
		}
	 }else
	 {
			if(radio_v=='tcfl')
			{
				_option=get_option_bar();
			}else if(disbar!="disabled"&&radio_v=='rj'){
				_option=get_option_rj_bar();
			}else{
				//全部
				_option=get_option_taemSum_bar();
			}
		 
	 }
	
	
	
	 
	
	myBarChart.setOption(_option,true,false);
	
}

//初始化图表信息
function initBarChart(){
	// ajax 加载数据
	$.ajax({
		url : ctx+"/lead/sale/summary/getTeamSummary",
		type : "GET",
		async : false,
		dataType : "json",
		// data :{productVoJson:JSON.stringify(objVo)},
		data : "mehtod=b&teamId=0",
		success : function(returnData, status, XMLHttpRequest) {
			if (status == "success") {
				//rsData = returnData;
				rsData.yAxisTd = typeof returnData.officeNames== 'undefined' ? [] : returnData.officeNames;
				rsData.sellBonuss = typeof returnData.sellBonuss == 'undefined' ? [] : returnData.sellBonuss;
				rsData.halfYearBonuss =typeof returnData.halfYearBonuss== 'undefined' ? [] : returnData.halfYearBonuss;
				rsData.monthBonuss =typeof returnData.monthBonuss== 'undefined' ? [] : returnData.monthBonuss;
				//总
				rsData.bonusAmounts=typeof returnData.bonusAmounts== 'undefined' ? [] : returnData.bonusAmounts;
				//人均
				rsData.avgs=typeof returnData.avgs== 'undefined' ? [] : returnData.avgs;
				
				//上一个月的数据
				rsData.yAxisTdPerv = typeof returnData.officeNamesPerv== 'undefined' ? [] : returnData.officeNamesPerv;
				rsData.sellBonussPerv = typeof returnData.sellBonussPerv == 'undefined' ? [] : returnData.sellBonussPerv;
				rsData.halfYearBonussPerv =typeof returnData.halfYearBonussPerv== 'undefined' ? [] : returnData.halfYearBonussPerv;
				rsData.monthBonussPerv =typeof returnData.monthBonussPerv== 'undefined' ? [] : returnData.monthBonussPerv;
				//总
				rsData.bonusAmountsPerv=typeof returnData.bonusAmountsPerv== 'undefined' ? [] : returnData.bonusAmountsPerv;
				//人均
				rsData.avgsPerv=typeof returnData.avgsPerv== 'undefined' ? [] : returnData.avgsPerv;
			}
		}
	});
	//$('#barMain-f').empty();
	myBarChart = my_big_ec.init(document.getElementById('barMain-f'), 'macarons');
	var _option=get_option_taemSum_bar();
	myBarChart.setOption(_option,true,false);
	
}

//---------------------------------------------------正常开始 图表option-------------------------------------------------------------------------------------
//获取提成分类的图标
function get_option_bar(){
  return {
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' |
			// 'shadow'
			}
		},
		legend : {
			data : [ '当月售车提奖', '半年提奖', '缴租提车' ]
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'value'
		} ],
		yAxis : [ {
			type : 'category',
			data : rsData.yAxisTd
		} ],
		series : [ {
			name : '当月售车提奖',
			type : 'bar',
			stack : '总量',
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'insideRight'
					}
				}
			},
			// 团队1当月售车提奖，团队1，团队2，
			data : rsData.sellBonuss
		}, {
			name : '半年提奖',
			type : 'bar',
			stack : '总量',
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'insideRight'
					}
				}
			},
			data : rsData.halfYearBonuss
		}, {
			name : '缴租提车',
			type : 'bar',
			stack : '总量',
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'insideRight'
					}
				}
			},
			data : rsData.monthBonuss
		} ]
	};
}



//获取人均的图标
function get_option_rj_bar(){
	  return {
		  title : {
				text : '人均提成 | 团队总提成',
				// subtext: '纯属虚构',
				x : 'left'
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' |
				// 'shadow'
				}
			},
			legend : {
				data : [ '人均提成','当月售车提奖', '半年提奖', '缴租提车' ]
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'value'
			} ],
			yAxis : [ {
				type : 'category',
				axisTick : {
					show : false
				},
				data : rsData.yAxisTd
			} ],
			series : [ {
				name : '当月售车提奖',
				type : 'bar',
				stack : '总量',
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'inside'
						}
					}
				},
				data : rsData.sellBonuss
			}, {
				name : '半年提奖',
				type : 'bar',
				stack : '总量',
				barWidth : 5,
				itemStyle : {
					normal : {
						label : {
							show : true
						}
					}
				},
				data : rsData.halfYearBonuss
			},{
				name : '缴租提车',
				type : 'bar',
				stack : '总量',
				barWidth : 5,
				itemStyle : {
					normal : {
						label : {
							show : true
						}
					}
				},
				data : rsData.monthBonuss
			},
			{
				name : '人均提成',
				type : 'bar',
				stack : '总量',
				barWidth : 5,
				itemStyle : {
					normal : {
						label : {
							show : true,
							 position: 'left'
						}
					}
				},
				//emphasis | normal
//				itemStyle: {normal:{
//	                label : {show: true, position: 'left',formatter:function(params,ticket,callback){ return Math.abs(params.data);}}
//	            }},
				data : rsData.avgs //[-10000,-2000,-200]//
			} ],
//			tooltip:{
//				formatter:function(params,ticket,callback){console.log(typeof params.data);return Math.abs(params.data);}
//			}
			
		};
	}




//获取团队总提成图标
function get_option_taemSum_bar(){
	  return {
		  title : {
				text : '团队总提成',
				// subtext: '纯属虚构',
				x : 'left'
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' |
				// 'shadow'
				}
			},
			legend : {
				data : [ '团队总提成']
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'value'
			} ],
			yAxis : [ {
				type : 'category',
				axisTick : {
					show : false
				},
				data : rsData.yAxisTd
			} ],
			series : [ {
				name : '当月售车提奖',
				type : 'bar',
				stack : '总量',
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'inside'
						}
					}
				},
				data : rsData.bonusAmounts
			}
			],
			
			
		};
	}




//---------------------------------------------------正常节束 图表option-------------------------------------------------------------------------------------


// 做成一个函数
function initDrawBarData(mehtod, teamId) {
	//默认显示
	var _option=get_option_taemSum_bar();
	if (myBarChart) {
		myBarChart.setOption(_option, false);
		//myBarChart.refresh();
	}

}

function drawBar_f(ec) {
	myBarChart = ec.init(document.getElementById('barMain-f'), 'macarons');
	initDrawBarData('', '');
}




/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 * 
 * @param num
 *            数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
function formatCurrency(num) {

	if (typeof (num) == 'undefined') {
		return 0;
	}

	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.50000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10)
		cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
		num = num.substring(0, num.length - (4 * i + 3)) + ','
				+ num.substring(num.length - (4 * i + 3));
	return (((sign) ? '' : '-') + num + '.' + cents);
}


//---------------------------------------------------环比开始 图表option-------------------------------------------------------------------------------------
//获取提成分类的图标
function get_option_bar_perv(){
return {
		tooltip : {
			trigger : 'axis',
			axisPointer : { // 坐标轴指示器，坐标轴触发有效
				type : 'shadow' // 默认为直线，可选为：'line' |
			// 'shadow'
			}
		},
		legend : {
			data : [ '当月售车提奖','上月售车提奖', '半年提奖','上月半年提奖', '缴租提车', '上月缴租提车', ]
		},
		toolbox : {
			show : true,
			feature : {
//				mark : {
//					show : true
//				},
				dataView : {
					show : true,
					readOnly : false
				},
//				magicType : {
//					show : true,
//					type : [ 'line', 'bar' ]
//				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'value'
		} ],
		yAxis : [ {
			type : 'category',
			data : rsData.yAxisTd
		} ],
		series : [ {
			name : '当月售车提奖',
			type : 'bar',
			stack : '总量',
			itemStyle : {
				normal : {
					label : {
						show : true,
						//position : 'insideRight'
					}
				}
			},
			// 团队1当月售车提奖，团队1，团队2，
			data : rsData.sellBonuss
		}, 
		{
			name : '上月售车提奖',
			type : 'bar',
			stack : '上月',
			itemStyle : {
				normal : {
					label : {
						show : true,
						//position : 'insideRight'
					}
				}
			},
			// 团队1当月售车提奖，团队1，团队2，
			data : rsData.sellBonussPerv
		}, 
		{
			name : '半年提奖',
			type : 'bar',
			stack : '总量',
			itemStyle : {
				normal : {
					label : {
						show : true,
						//position : 'insideRight'
					}
				}
			},
			data : rsData.halfYearBonuss
		},
		{
			name : '上月半年提奖',
			type : 'bar',
			stack : '上月',
			itemStyle : {
				normal : {
					label : {
						show : true,
						position : 'insideRight'
					}
				}
			},
			data : rsData.halfYearBonussPerv
		},
		{
			name : '缴租提车',
			type : 'bar',
			stack : '总量',
			itemStyle : {
				normal : {
					label : {
						show : true,
						//position : 'insideRight'
					}
				}
			},
			data : rsData.monthBonuss
		},
		{
			name : '上月缴租提车',
			type : 'bar',
			stack : '上月',
			itemStyle : {
				normal : {
					label : {
						show : true,
						//position : 'insideRight'
					}
				}
			},
			data : rsData.monthBonussPerv
		} 
		]
	};
}



//获取人均的图标
function get_option_rj_bar_perv(){
	  return {
		  title : {
				text : '人均提成 | 团队总提成',
				// subtext: '纯属虚构',
				x : 'left'
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' |
				// 'shadow'
				}
			},
			legend : {
				data : [ '当月售车提奖','上月售车提奖', '半年提奖','上月半年提奖', '缴租提车', '上月缴租提车' ,'人均提成','上月人均提成']
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'value'
			} ],
			yAxis : [ {
				type : 'category',
				axisTick : {
					show : false
				},
				data : rsData.yAxisTd
			} ],
			series : [ {
				name : '当月售车提奖',
				type : 'bar',
				stack : '总量',
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'inside'
						}
					}
				},
				data : rsData.sellBonuss
			},
			{
				name : '上月售车提奖',
				type : 'bar',
				stack : '上月',
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'inside'
						}
					}
				},
				data : rsData.sellBonussPerv
			},
			{
				name : '半年提奖',
				type : 'bar',
				stack : '总量',
				barWidth : 5,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'inside'
						}
					}
				},
				data : rsData.halfYearBonuss
			},
			{
				name : '上月半年提奖',
				type : 'bar',
				stack : '上月',
				barWidth : 5,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'inside'
						}
					}
				},
				data : rsData.halfYearBonussPerv
			},
			{
				name : '缴租提车',
				type : 'bar',
				stack : '总量',
				barWidth : 5,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'inside'
						}
					}
				},
				data : rsData.monthBonuss
			},
			{
				name : '上月缴租提车',
				type : 'bar',
				stack : '上月',
				barWidth : 5,
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'inside'
						}
					}
				},
				data : rsData.monthBonussPerv
			},
			{
				name : '人均提成',
				type : 'bar',
				stack : '总量',
				barWidth : 5,
				itemStyle : {
					normal : {
						label : {
							show : true,
							 position: 'left'
						}
					}
				},
				//emphasis | normal
//				itemStyle: {normal:{
//	                label : {show: true, position: 'left',formatter:function(params,ticket,callback){ return Math.abs(params.data);}}
//	            }},
				data : rsData.avgs //[-10000,-2000,-200]//
			} ,
			{
				name : '上月人均提成',
				type : 'bar',
				stack : '上月',
				barWidth : 5,
				itemStyle : {
					normal : {
						label : {
							show : true,
							 position: 'left'
						}
					}
				},
				//emphasis | normal
//				itemStyle: {normal:{
//	                label : {show: true, position: 'left',formatter:function(params,ticket,callback){ return Math.abs(params.data);}}
//	            }},
				data : rsData.avgsPerv //[-10000,-2000,-200]//
			}
			
			],
//			tooltip:{
//				formatter:function(params,ticket,callback){console.log(typeof params.data);return Math.abs(params.data);}
//			}
			
		};
	}




//获取团队总提成图标
function get_option_taemSum_bar_perv(){
	//return get_test();
	  return {
		  title : {
				text : '团队总提成',
				// subtext: '纯属虚构',
				x : 'left'
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' |
				// 'shadow'
				}
			},
			legend : {
				data : [ '团队总提成','上月团队总提成'],
				selected:{
                    '团队总提成':true,
                    '上月团队总提成':true
                }
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'value'
			} ],
			yAxis : [ {
				type : 'category',
				axisTick : {
					show : false
				},
				data : rsData.yAxisTdPerv
			} ],
			series : [ {
				name : '团队总提成',
				type : 'bar',
				//stack : '总量',
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'inside'
						}
					}
				},
				data : rsData.bonusAmounts
			},
			{
				name : '上月团队总提成',
				type : 'bar',
				//stack : '上月',
                itemStyle : {
                    normal: {
                        lineStyle: {color:'#000'},
                        label:{
                            lineStyle: {color:'#000'},
                        	show : true,
							position : 'inside',
                           // textStyle:{color:'orange'}
                        }
                    }
                },
				data : rsData.bonusAmountsPerv
			}
			],
			
			
		};
	}



//get_option_taemSum_bar_perv_oneT

//获取团队总提成图标
function get_option_taemSum_bar_perv_oneT(){
	//return get_test();
	  return {
		  title : {
				//text : '团队总提成',
				// subtext: '纯属虚构',
				x : 'left'
			},
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' |
				// 'shadow'
				}
			},
			legend : {
				data : [ '个人总提成','上月个人总提成'],
				selected:{
                  '个人总提成':true,
                  '上月个人总提成':true
              }
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'value'
			} ],
			yAxis : [ {
				type : 'category',
				axisTick : {
					show : false
				},
				data : rsData.yAxisTd
			} ],
			series : [ {
				name : '个人总提成',
				type : 'bar',
				//stack : '总量',
				itemStyle : {
					normal : {
						label : {
							show : true,
							position : 'inside'
						}
					}
				},
				data : rsData.bonusAmounts
			},
			{
				name : '上月个人总提成',
				type : 'bar',
				//stack : '上月',
              itemStyle : {
                  normal: {
                      lineStyle: {color:'#000'},
                      label:{
                          lineStyle: {color:'#000'},
                      	show : true,
							position : 'inside',
                         // textStyle:{color:'orange'}
                      }
                  }
              },
				data : rsData.bonusAmountsPerv
			}
			],
			
			
		};
	}

//---------------------------------------------------环比节束 图表option-------------------------------------------------------------------------------------

//下载
$("#btnDwonTable").click(function(){
alert("xxx");
//	
//	top.$.jBox.confirm("确认要导出月缴费清单数据（用于缴费）吗？","系统提示",function(v,h,f){
//		if(v=="ok"){

			//获取查询条件
			var mehtod = $("#_feeDay").val();
			var teamId = $("#_teme_selection").val();
			// 选择本月和上月
			if (mehtod == "" || "undefined" == mehtod) {
				mehtod = $('input[name="radio1"]:checked ').val();
			} 
			
			var dataParm = 'pageNo=1&pageSize=10000';
			if (mehtod != "" && mehtod != null && mehtod != 'undefined') {
				dataParm += "&mehtod=" + mehtod;
			}

			if (teamId != "" && teamId != 'undefined' && teamId != null) {
					dataParm += "&teamId=" + teamId;
			}

			
			
			$.ajax({
				url : ctx+"/lead/sale/summary/redDown",
				type : "GET",
				dataType : "json",
				// data :{productVoJson:JSON.stringify(objVo)},
				data : dataParm,
				success : function(returnData, status, XMLHttpRequest) {
					alert(status);
				}
			});
			
		//}
//	},{buttonsFocus:1});
//	top.$('.jbox-body .jbox-icon').css('top','55px');
	//$("#searchForm").attr("action",action);
});

function btnDwonTable(){
	var mehtod = $("#_feeDay").val();
	var teamId = $("#_teme_selection").val();
	// 选择本月和上月
	if (mehtod == "" || "undefined" == mehtod) {
		mehtod = $('input[name="radio1"]:checked ').val();
	} 
	
	var dataParm = 'pageNo=1&pageSize=10000';
	if (mehtod != "" && mehtod != null && mehtod != 'undefined') {
		dataParm += "&mehtod=" + mehtod;
	}

	if (teamId != "" && teamId != 'undefined' && teamId != null) {
			dataParm += "&teamId=" + teamId;
	}

	location.href=ctx+"/lead/sale/summary/redDown?"+dataParm;

	
//	$.ajax({
//		url : ctx+"/lead/sale/summary/redDown",
//		type : "GET",
//		dataType : "json",
//		// data :{productVoJson:JSON.stringify(objVo)},
//		data : dataParm,
//		success : function(returnData, status, XMLHttpRequest) {
//			alert(status);
//		}
//	});
	
}


function xuhao(r, v, i) {

	return i + 1;
}
function down(r, v, i) {
	if (i == 0) {
		return '下载';
	}
	return '';
}

//设置传入参数
function queryParams(parma) {
	// 获取查询条件
	var mehtod = $("#_feeDay").val();
	var teamId = $("#_teme_selection").val();
	// 选择本月和上月
	if (mehtod == "" || "undefined" == mehtod) {
		mehtod = $('input[name="radio1"]:checked ').val();
	}

	var dataParm = '';
	if (mehtod != "" && mehtod != null && mehtod != 'undefined') {
		dataParm += "mehtod=" + mehtod;
		parma = $.extend(parma, {
			mehtod : mehtod,
		});

	}

	if (teamId != "" && teamId != 'undefined' && teamId != null) {

		parma = $.extend(parma, {
			teamId : teamId,
		});

	}
	// parma.teamId = teamId;
	// return dataParm+parma;
	return parma;
}




$(function() {
	
	initGroupStatitic.init();
	//initTable('', '');
	initOffce();
	initGroupStatitic.initStat("b","0");
	

	// 所有团队
	$("#_teme_selection").change(function() {
		
		var feeDay = $("#_feeDay").val();
		var temeId = $("#_teme_selection").val();

		// 如果团队id为0 禁掉人均
		if (temeId == '0') {
			$("#rj_hot_deals_check").prop("disabled", false);
		} else {
			$("#rj_hot_deals_check").attr("checked",false);
			$("#rj_hot_deals_check").prop("disabled", true);
		}
	
		// 选择本月和上月
		if (feeDay == "" || "undefined" == feeDay) {
			var val_radio = $('input[name="radio1"]:checked ').val();
			initGroupStatitic.initStat(val_radio, temeId);
			initAjaxData();

		} else {
			initGroupStatitic.initStat(feeDay, temeId);
			//initTable(feeDay, temeId);
			initDrawBarData(feeDay, temeId);
			initAjaxData();
		}
	});
	
	//初始化表
	getPaginationTable(".rl-page-table",false);
	
	
	
	
});

//使用
require([ 'echarts', 'echarts/chart/bar', 'echarts/chart/line' ], drawEcharts);

function drawEcharts(ec) {
	my_big_ec = ec;
	drawBar_f(ec);
	// initDrawBarData('','');
	// drawBar_s(ec);
	 //drawBar_t(ec);
	initBarChart();//图表
}

