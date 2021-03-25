var path = $("#span_path").text();

$(document).ready(function() {
	suntaek_proc('list', '1', '');
});

function search() {
	$("#span_search_option").text($("#search_option").val());
	$("#span_search_data").text($("#search_data").val());
	suntaek_proc('list', '1', '');
}

function suntaek_all() {
	$("#span_search_option").text("");
	$("#span_search_data").text("");
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

function goPage(value1) {
	var param;
	//var url = "${path}/guestbook_servlet/" + value1 + ".do";
	var url = $("#span_path").text() + "/guestbook_servlet/" + value1 + ".do";

	if (value1 == 'list') {
		param = {
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text()
		}
	} else if (value1 == 'chugaProc' || value1 == 'modifyProc' || value1 == 'delProc') {
		param = {
			"no": $("#span_no").text(),
			"passwd": $("#passwd").val(),
			"name": $("#name").val(),
			"content": $("#content").val(),
			"email": $("#email").val()
		}
	} else if (value1 == 'del_passwdChk') {
		param = {
			"no": $("#span_no").text()
		}
	}

	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: function(data) {
			if (value1 == 'list' || value1 == 'listAll') {
				$("#result").html(data);
			} else if (value1 == 'chugaProc' || value1 == 'delProc' || value1 == 'modifyProc') {
				suntaek_proc('list', '1', '');
			} else {
				$("#result").html(data);
			}
		}
	});
}

function modify(value1, value2) {
	$("#span_no").text(value1);
	$("#name").val($("#modify_name" + value2).text());
	$("#email").val($("#modify_email" + value2).text());
	$("#content").val($("#modify_content" + value2).text());
	$("#passwd").val('');
	$("#btnInsert").css("display", "none");
	$("#btnModify").css("display", "");
}