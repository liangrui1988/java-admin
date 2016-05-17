$(function() {
	$("#tradeFirst li").live("click", function() {
		$(".hx_baikeName").hide();
		$("#tradeFirst li,#tradeSeconds li").removeClass();
		$(this).addClass("on");
		$("#tradeSeconds").show();
		$("#tradeThird").hide();
		// sortClass('tradeSeconds',pid);
		userSort01();
	});
	$("#tradeSeconds li").live("click", function() {
		$(".hx_baikeName").hide();
		$("#tradeSeconds li,#tradeThird li").removeClass();
		$(this).addClass("on");
		$("#tradeThird").show();
		// sortClass('tradeThird',pid);
		userSort02();
	});

	$("#tradeThird li").live("click", function() {
		tid = $(this).attr("myPid");
		$("#tradeThird li").removeClass();
		$(this).addClass("on");
		$(".btnSave").show();
		userSort03();

	});

	$(".btnSave").click(function() {
		$(".hx_baikeName").show();
	})

})
/* 产品分类 */
function userSort01() {
	var first = $("#tradeFirst .on").attr("myClass");
	var firstId = $("#tradeFirst .on").attr("mypid");
	userSortValue(first);
	userSite(firstId);
}
function userSort02() {
	var first = $("#tradeFirst .on").attr("myClass");
	var seconds = $("#tradeSeconds .on").attr("myClass");
	var firstId = $("#tradeFirst .on").attr("mypid");
	var secondsId = $("#tradeSeconds .on").attr("mypid");
	userSortValue(first, seconds);
	userSite(firstId, secondsId);
}
function userSort03() {
	var first = $("#tradeFirst .on").attr("myClass");
	var seconds = $("#tradeSeconds .on").attr("myClass");
	var third = $("#tradeThird .on").attr("myClass");
	var firstId = $("#tradeFirst .on").attr("mypid");
	var secondsId = $("#tradeSeconds .on").attr("mypid");
	var thirdId = $("#tradeThird .on").attr("mypid");
	userSortValue(first, seconds, third);
	userSite(firstId, secondsId, thirdId);
}

function userSortValue(first, seconds, third, four) {
	classForm(first, seconds, third, four);
	if (first.length == 0) {
		return;
	}
	$("#usreclass").html('');
	$("#usreclass").html(first);
	$("#_name").val(first);

	if (seconds == undefined || seconds.length == 0) {
		return;
	}
	$("#usreclass").html($("#usreclass").html() + " > " + seconds);
	$("#_name").val(seconds);
	if (third == undefined || third.length == 0) {
		return;
	}
	$("#usreclass").html($("#usreclass").html() + " > " + third);
	$("#_name").val(third);
	if (four == undefined || four.length == 0) {
		return;
	}
}
function userSite(first, seconds, third) {
	var site = "";
	if (first.length == 0) {
		return;
	}
	site = site + first + "#";
	$("#_site").val(site);
	$("#_tradeId").val(first);
	$("#_sort").val(1);
	if (seconds == undefined || seconds.length == 0) {
		return;
	}
	site = site + seconds + "#";
	$("#_site").val(site);
	$("#_tradeId").val(seconds);
	$("#_sort").val(2);
	if (third == undefined || third.length == 0) {
		return;
	}
	site = site + third + "#";
	$("#_tradeId").val(third);
	$("#_site").val(site);
	$("#_sort").val(3);
}

function classForm(first, seconds, third, four) {
	$("#formFirst").val(first);
	$("#formSecond").val(seconds);
	$("#formThird").val(third);
	$("#formFour").val(four);

}
function c02(pid) {
	$("#tradeSeconds").empty();
	$.ajax({
		type : "POST",
		url : "/area/getIndustry.do",
		data : {
			key : pid
		},
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, item) {
				$("#tradeSeconds").append(
						"<li mypid=\"" + item.id + "\" myclass=\"" + item.name
								+ "\" onclick=\"c03(" + item.id + ");\"><span>"
								+ item.name + "</span><i>&gt;</i></li>");
			});
		}
	});
}
function c03(pid) {
	$("#tradeThird").empty();
	$.ajax({
		type : "POST",
		url : "/area/getIndustry.do",
		data : {
			key : pid
		},
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, item) {
				$("#tradeThird").append(
						"<li mypid=\"" + item.id + "\" myclass=\"" + item.name
								+ "\"><span>" + item.name
								+ "</span><i>&gt;</i></li>");
			});
		}
	});
}

/**init**/
//initIndustry();
function initIndustry()
{
	$.ajax({
		type : "POST",
		url : "/area/viewIndustry.do",
//		data : {
//			key : pid
//		},
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, item) {
				$("#tradeFirst").append(
						"<li mypid='"+item.id+"' myclass='"+item.name+"'	onclick='"+c02(item.id)+"'><span>"+item.name+"</span> <i>&gt;</i>	</li>"
						
//						"<li mypid=\"" + item.id + "\" myclass=\"" + item.name
//								+ "\"><span>" + item.name
//								+ "</span><i>&gt;</i></li>"
				);
			});
		}
	});
}
