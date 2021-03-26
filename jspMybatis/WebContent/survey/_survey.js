var path = $("#span_path").text();

$(document).ready(function() {
	if ($("#span_menu_gubun").text() == 'survey_index') {
		suntaek_proc('list', '1', '');
	} else if ($("#span_menu_gubun").text() == 'survey2_index') {
		suntaek_proc('list_2', '1', '');
	}
});

function search() {
	$("#span_search_option").text($("#search_option").val());
	$("#span_search_data").text($("#search_data").val());
	$("#span_search_date_s").text($("#search_date_s").val());
	$("#span_search_date_e").text($("#search_date_e").val());
	suntaek_proc('list', '1', '');
}

function checkboxChk() {
	if ($("input:checkbox[id=search_date_check]").is(":checked") == true) {
		$("#span_search_date_check").text($("#search_date_check").val());
		$("#span_search_date_s").text($("#search_date_s").val());
		$("#span_search_date_e").text($("#search_date_e").val());
	} else {
		$("#span_search_date_check").text('');
		$("#span_search_date_s").text('');
		$("#span_search_date_e").text('');
		$("#search_date_s").text('');
		$("#search_date_e").text('');
	}
}

function suntaek_all() {
	$("#span_search_option").text("");
	$("#span_search_data").text("");
	suntaek_proc('list', '1', '');
}

function suntaek_list(value1) {
	$("#span_list_gubun").text(value1);
	suntaek_proc('list', '1', '');
}

function suntaek_proc(value1, value2, value3) {
	$("#span_proc").text(value1);
	if (value2 != "0") {			// 0 <- 건너뜀
		$("#span_pageNumber").text(value2);
	}
	if (value3 != "0") {
		$("#span_no").text(value3);
	}
	goPage(value1);
}

function check_answer(value1, value2) {
	$("#span_answer" + value1).text(value2);
	if (value2 == '1') {
		$("#mun1" + value1).text('❶')
		$("#mun2" + value1).text('②')
		$("#mun3" + value1).text('③')
		$("#mun4" + value1).text('④')
	} else if (value2 == '2') {
		$("#mun1" + value1).text('①')
		$("#mun2" + value1).text('❷')
		$("#mun3" + value1).text('③')
		$("#mun4" + value1).text('④')
	} else if (value2 == '3') {
		$("#mun1" + value1).text('①')
		$("#mun2" + value1).text('②')
		$("#mun3" + value1).text('❸')
		$("#mun4" + value1).text('④')
	} else if (value2 == '4') {
		$("#mun1" + value1).text('①')
		$("#mun2" + value1).text('②')
		$("#mun3" + value1).text('③')
		$("#mun4" + value1).text('❹')
	}

//	if (value1 == '') {
		var counter = parseInt($("#span_list_size").text());
		var msg = '';
		for (var i = counter; i > 0; i--) {
			var q_no = $("#q_" + i).text();
			var answer = $("#span_answer" + i).text();
			if (answer.length > 0) {
				if (msg == '') {
					msg = q_no + ":" + answer;
				} else {
					msg = msg + "|" + q_no + ":" + answer;
				}
			}
		}
		$("#span_answer_total").text(msg);
//	}
}

function goPage(value1) {
	var param;
	//var url = "${path}/survey_servlet/" + value1 + ".do";
	var url = $("#span_path").text() + "/survey_servlet/" + value1 + ".do";

	if (value1 == 'list' || value1 == 'list_2') {
		param = {
			"list_gubun": $("#span_list_gubun").text(),
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text(),
			"search_date_s": $("#span_search_date_s").text(),
			"search_date_e": $("#span_search_date_e").text(),
			"search_date_check": $("#span_search_date_check").text()
		}
	} else if (value1 == 'view' || value1 == 'viewProc' || value1 == 'delProc' || value1 == 'modify') {
		param = {
			"no": $("#span_no").text(),
			"answer": $("#span_answer").text()
		}
	} else if (value1 == 'saveProc') {
		param = {
			"answer_total": $("#span_answer_total").text()
		}
	} else if (value1 == 'chugaProc' || value1 == 'modifyProc') {
		param = {
			"question": $("#question").val(),
			"no": $("#span_no").text(),
			"ans1": $("#ans1").val(),
			"ans2": $("#ans2").val(),
			"ans3": $("#ans3").val(),
			"ans4": $("#ans4").val(),
			"status": $("#status").val(),
			"syear": $("#syear option:selected").val(),
			"smonth": $("#smonth option:selected").val(),
			"sday": $("#sday option:selected").val(),
			"lyear": $("#lyear option:selected").val(),
			"lmonth": $("#lmonth option:selected").val(),
			"lday": $("#lday option:selected").val()
		}
	}

	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: function(data) {
			if (value1 == 'list' || value1 == 'list_2') {
				$("#result").html(data);
				if ($("#span_search_date_check").text() == "O") {
					$("input[id=search_date_check]:checkbox").prop("checked", true);
				} else {
					$("input[id=search_date_check]:checkbox").prop("checked", false);
				}
			} else if (value1 == 'chugaProc' || value1 == 'delProc') {
				suntaek_proc('list', '1', '');
			} else {
				$("#result").html(data);
			}
		}
	});
}
