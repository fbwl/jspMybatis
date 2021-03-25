var path = $("#span_path").text();

$(document).ready(function() {
	suntaek_proc('list', '1', '');
});

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
	var url = $("#span_path").text() + "/memo_servlet/" + value1 + ".do";

	if (value1 == 'list') {
		param = {
			"no": $("#span_no").text(),
			"pageNumber": $("#span_pageNumber").text()
		}
	} else if (value1 == 'writeProc') {
		param = {
			"writer": $("#writer").val(),
			"content": $("#content").val()
		}
	} else if (value1 == 'modifyProc' || value1 == 'delProc') {
		param = {
			"no": $("#span_no").text(),
			"writer": $("#writer").val(),
			"content": $("#content").val()
		}
	}

	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: function(data) {
			if (value1 == 'writeProc' || value1 == 'modifyProc' || value1 == 'delProc') {
				suntaek_proc('list', '1', '');
			} else {
				$("#result").html(data);
			}
		}
	});
}

function modify(value1,value2,value3) {
	$("#span_no").text(value1);
	$("#writer").val(value2);
	$("#content").val(value3);
	$("#btnInsert").css("display","none");
	$("#btnModify").css("display","");
}