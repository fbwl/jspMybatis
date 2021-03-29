$(document).ready(function() {
	$("#btnCreateJson").click(function() {
		goPage('createJson');
	});
	
	$("#btnGoogleChartJson").click(function() {
		goPage('googleChartJson');
	});
	
	$("#btnGoogleChartDb").click(function() {
		goPage('googleChartDb');
	});
	
	$("#btnJfreeChartPng").click(function() {
// 		goPage('jfreeChartPng');
		location.href='${path}/chart_servlet/jfreeChartPng.do';
		return;
	});
	
	$("#btnJfreeChartPdf").click(function() {
		goPage('jfreeChartPdf');
	});
	
	function goPage(value1) {
		var method_type = "post";
		var param = {}
		var url = "${path}/chart_servlet/" + value1 + ".do";
		
		$.ajax({
			type : method_type,
			data : param,
			url : url,
			success : function(data) {
				$("#result").html(data);
			}
		});
	}
})