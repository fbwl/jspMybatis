var path = $("#span_path").text();

$(document).ready(function() {
	if ($("#span_menu_gubun").text() == 'chart_index') {
		goPage('createJsonProductChart');
	} else {
		goPage('createJsonSurveyAnswerChart', '', $("#span_no").text());
	}
});


function goPage(value1, value2, value3) {
	var method_type = "post";
	var param = {}
	var url = $("#span_path").text() + "/chart_servlet/" + value1 + ".do";

	if (value3 > 0) {
		param = {
			"no": $("#span_no").text()
		}
	}
	$.ajax({
		type: method_type,
		data: param,
		url: url,
		success: function(data) {
			$("#result2").html(data);
		}
	});
}
