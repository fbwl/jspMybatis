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
	//var url = "${path}/member_servlet/" + value1 + ".do";
	var url = $("#span_path").text() + "/member_servlet/" + value1 + ".do";

	if (value1 == 'list') {
		param = {
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text()
		}
	} else if (value1 == 'chuga') {
		param = {}
	} else if (value1 == 'modify_passwdChk' || value1 == 'del_passwdChk') {
		param = {
			"no": $("#span_no").text()
		}
	} else if (value1 == 'modify') {
		param = {
			"no": $("#span_no").text(),
			"passwd": $("#passwd").val()
		}
	} else if (value1 == 'chugaProc' || value1 == 'modifyProc' || value1 == 'delProc') {
		param = {
			"no": $("#span_no").text(),
			"id": $("#id").val(),
			"passwd": $("#passwd").val(),
			"passwdChk": $("#passwdChk").val(),
			"name": $("#name").val(),
			"gender": $("#gender").val(),
			"bornYear": $("#bornYear").val(),
			"postcode": $("#sample6_postcode").val(),
			"address": $("#sample6_address").val(),
			"detailAddress": $("#sample6_detailAddress").val(),
			"extraAddress": $("#sample6_extraAddress").val(),
			"regiDate": $("#regiDate").val(),
			"email": $("#email").val()
		}
	} else if (value1 == 'view') {
		param = {
			"no": $("#span_no").text(),
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text()
		}
	}

	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: function(data) {
			if (value1 == 'list' || value1 == 'listAll') {
				$("#result").html(data);
			} else if (value1 == 'chugaProc' || value1 == 'delProc') {
				suntaek_proc('list', '1', '');
			} else if (value1 == 'modifyProc') {
				suntaek_proc('view', '0', $("#span_no").text());
			} else {
				$("#result").html(data);
			}
		}
	});
}

function suntaek_gender(value1) {
	if (value1 == 'M') {
		$("#gender").val(value1);
	} else {
		$("#gender").val(value1);
	}
}

function login() {
	var doc = document.LoginForm;
	if (doc.id.value == "") {
		alert('아이디 입력');
		doc.id.select();
		doc.id.focus();
		return false;
	} else if (doc.passwd.value == "") {
		alert('비밀번호 입력');
		doc.passwd.select();
		doc.passwd.focus();
		return false;
	} else {
		doc.method = "post";
		doc.action = "${path}/member_servlet/loginProc.do";
		doc.submit();
	}
}
function join() {
	location.href = "${path}/member_servlet/chuga.do";
}

$(document).ready(function() {
	$("#id").focus();

	$("#btnChuga").click(function() {
		if ($("#idChk").val() == $("#id").val()) {
			if (confirm('가입?')) {
				suntaek_proc('chugaProc', '1', '');
			}
		} else {
			alert('아이디 중복확인');
		}
	})

	$("#btnList").click(function() {
		suntaek_proc('list', '1', '');
	})

	$("#id_check_div").click(function() {
		id_check_div();
	})

	$("#id_check_win").click(function() {
		id_check_win();
	})
});

function id_check_div() {
	var id = $("#id").val();
	if (id == '') {
		$("#label_id").html('아이디 입력');
		$("#label_id").css('color', 'green');
		$("#label_id").css('font-size', '8px');
		return;
	}

	var param = "id=" + id;
	var url = "${path}/member_servlet/id_check.do"

	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: function(data) {
			$("#idChk").val(data);
			if ($("#idChk").val().length > 0) {
				$("#label_id").html("사용 가능");
				$("#label_id").css('color', 'green');
				$("#label_id").css('font-size', '8px');
			} else {
				$("#label_id").html("id 중복");
				$("#label_id").css('color', 'green');
				$("#label_id").css('font-size', '8px');
				$("#id").val("");
			}
		}
	});
}

function suntaek_gender(value1) {
	if (value1 == 'M') {
		$("#gender").val(value1);
	} else {
		$("#gender").val(value1);
	}
}