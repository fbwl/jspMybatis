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
	var url = $("#span_path").text() + "/board_servlet/" + value1 + ".do";

	if (value1 == 'list') {
		param = {
			"tbl": $("#span_tbl").text(),
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text()
		}
	} else if (value1 == 'reply' || value1 == 'modify' || value1 == 'del') {
		param = {
			"no": $("#span_no").text()
		}
	} else if (value1 == 'chugaProc' || value1 == 'modifyProc' || value1 == 'delProc') {
		param = {
			"no": $("#span_no").text(),
			"tbl": $("#span_tbl").text(),
			"writer": $("#writer").val(),
			"email": $("#email").val(),
			"passwd": $("#passwd").val(),
			"subject": $("#subject").val(),
			"content": $("#content").val(),
			"noticeGubun": $("#noticeGubun").val(),
			"secretGubun": $("#secretGubun").val()
		}
	} else if (value1 == 'view') {
		param = {
			"no": $("#span_no").text(),
			"tbl": $("#span_tbl").text(),
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text(),
			"view_passwd": $("#view_passwd").val()
		}
	}
	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: function(data) {
			if (value1 == "chugaProc") {
				suntaek_proc('list', '1', '');
			} else if (value1 == "modifyProc") {
				$("#result").html(data);
				if ($("#span_passwd").text() == 'T') {
					suntaek_proc('view', '', $("#span_no").text());
				} else {
					alert('비밀번호 오류');
					suntaek_proc('modify', '', $("#span_no").text());
				}
			} else if (value1 == "delProc") {
				$("#result").html(data);
				if ($("#span_passwd").text() == 'T') {
					suntaek_proc('list', '1', '');
				} else {
					alert('비밀번호 오류');
					suntaek_proc('view', '', $("#span_no").text());
				}
			} else if (value1 == "view") {
				$("#result").html(data);
				comment_suntaek_proc('commentList', '1', $("#span_no").text());
			} else {
				$("#result").html(data);
			}
		}
	})
}

function clickChk(value1) {
	if (value1 == 'noticeGubun') {
		if ($("input:checkbox[name=noticeGubunCheckBox]").is(":checked") == true) {
			$("#noticeGubun").val("T");
		} else {
			$("#noticeGubun").val("");
		}
	} else if (value1 == 'secretGubun') {
		if ($("input:checkbox[name=secretGubunCheckBox]").is(":checked") == true) {
			$("#secretGubun").val("T");
		} else {
			$("#secretGubun").val("");
		}
	}
}

function comment_suntaek_proc(value1, value2, value3, value4) {
	if (value2 != "0") {
		$("#span_commentPageNumber").text(value2);
	}
	if (value3 != "0") {
		$("#span_no").text(value3);
	}
	if (value4 != "0") {
		$("#span_comment_no").text(value4);
	}
	comment_goPage(value1, value4)
}


function comment_goPage(value1, value4) {
	var param;
	var url = $("#span_path").text() + "/board_servlet/" + value1 + ".do";

	if (value1 == 'commentList') {
		param = {
			"commentPageNumber": $("#span_commentPageNumber").text(),
			"no": $("#span_no").text()
		}
	} else if (value1 == 'commentProc') {
		param = {
			"commentPageNumber": $("#span_commentPageNumber").text(),
			"no": $("#span_no").text(),
			"writer": $("#comment_writer").val(),
			"passwd": $("#comment_passwd").val(),
			"content": $("#comment_content").val()
		}
	} else if (value1 == 'commentModifyProc') {
		param = {
			"commentPageNumber": $("#span_commentPageNumber").text(),
			"board_no": $("#span_no").text(),
			"comment_no": $("#span_comment_no").text(),
			"writer": $("#comment_writer").val(),
			"passwd": $("#comment_passwd").val(),
			"content": $("#comment_content").val()
		}
	} else if (value1 == 'commentDelProc') {
		var passwd = prompt('비밀번호');
		param = {
			"board_no": $("#span_no").text(),
			"comment_no": $("#span_comment_no").text(),
			"passwd": passwd
		}
	}

	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: function(data) {
			if (value1 == 'commentProc') {
				suntaek_proc('view', '', $("#span_no").text());
			} else if (value1 == 'commentModifyProc' || value1 == 'commentDelProc') {
				$("#CommentResult").html(data);
				if ($("#span_passwd").text() == 'T') {
					$("#span_comment_no").text('');
					$("#span_passwd").text('');
				} else {
					alert('비밀번호 오류');
				}
				suntaek_proc('view', '', $("#span_no").text());
			} else {
				$("#CommentResult").html(data);
			}
		}
	});
}

function modify(value1, value2) {
	$("#span_comment_no").text(value1);
	$("#comment_writer").val($("#modify_writer" + value2).text());
	$("#comment_content").val($("#modify_content" + value2).text());
	$("#passwd").val('');
	$("#btnInsert").css("display", "none");
	$("#btnModify").css("display", "");
	$("#btnModifyCancel").css("display", "");
}

function modifyCancel(value1) {
	$("#span_comment_no").text('');
	$("#comment_writer").val(value1);
	$("#comment_content").val('');
	$("#passwd").val('');
	$("#btnInsert").css("display", "");
	$("#btnModify").css("display", "none");
	$("#btnModifyCancel").css("display", "none");
}

//function del(value1, value2) {
//	$("#span_comment_no").text(value1);
//	$("#passwd").val('');
//	$("#passwd"+value2).css("display", "");
//	$("#btnCommentDel"+value2).css("display", "none");
//	$("#btnCommentDelProc"+value2).css("display", "");
//}