var path = $("#span_path").text();

$(document).ready(function() {
	if ($("#span_menu_gubun").text() == 'product_index') {
		suntaek_proc('list', '1', '');
	}
});

function suntaek_all() {
	$("#span_search_option").text("");
	$("#span_search_data").text("");
	suntaek_proc('list', '1', '');
}

function search() {
	$("#span_search_option").text($("#search_option").val());
	$("#span_search_data").text($("#search_data").val());
	suntaek_proc('list', '1', '');
}

function suntaek_proc(value1, value2, value3) {
	$("#span_proc").text(value1);
	if (value2 != "0") {
		$("#span_pageNumber").text(value2);
	}
	if (value3 != "0") {
		$("#span_no").text(value3);
	}
	goPage(value1);
}

function goPage(value1, value3) {
	var param = {};
	var process_data;
	var content_type;
	var url = $("#span_path").text() + "/product_servlet/" + value1 + ".do";

	if (value1 == "chuga") {
		param = {}
	} else if (value1 == "chugaProc" || value1 == 'modifyProc') {
		process_data = false;
		content_type = false;

		param = new FormData();

		param.append("no", $("#span_no").text());
		param.append("name", $("#name").val());
		param.append("price", $("#price").val());
		param.append("description", $("#description").val());

		// 			console.log($('input[name=file]')[0].files[0]);
		// 			console.log($('input[name=file]')[1].files[0]);
		// 			console.log($('input[name=file]')[2].files[0]);
		// 			return;

		var file_counter = parseInt($('input[name="file"]').length);
		for (i = 0; i < file_counter; i++) {
			param.append(i, $('input[name="file"]')[i].files[0]);
		}
	} else if (value1 == 'list') {
		process_data = false;
		content_type = false;

		param = new FormData();

		param.append("pageNumber", $("#span_pageNumber").text());
		param.append("search_option", $("#span_search_option").text());
		param.append("search_data", $("#span_search_data").text());
	} else if (value1 == 'view' || value1 == 'modify') {
		param.no = $("#span_no").text();
	} else if (value1 == 'del') {
		if (confirm("삭제")) {
			param.no = $("#span_no").text();
		} else {
			suntaek_proc('view', '', value3);
			return;
		}
	}
	$.ajax({
		type: "post",
		data: param,
		processData: process_data,
		contentType: content_type,
		url: url,
		success: function(data) {
			$("#result").html(data);
			if (value1 == "chugaProc" || value1 == 'modifyProc' || value1 == 'del') {
				suntaek_proc('list', '1', '');
			}
		}
	});
}
