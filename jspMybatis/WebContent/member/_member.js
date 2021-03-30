var path = $("#span_path").text();

$(document).ready(function() {
	if ($("#span_menu_gubun").text() == 'member_index') {
		suntaek_proc('list', '1', '');
	} else if ($("#span_menu_gubun").text() == 'member_login2') {
		suntaek_proc('login', '', '');
	} else if ($("#span_menu_gubun").text() == 'member_chuga2') {
		suntaek_proc('chuga', '', '');
	} else if ($("#span_menu_gubun").text() == 'member_modify2') {
		suntaek_proc('modify_passwdChk', '', $("#span_no").text());
	}
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
	
	if (value1 == 'cancel'){
		if ($("#span_menu_gubun").text() == 'member_index') {
			suntaek_proc('list', '1', '');
		} else if ($("#span_menu_gubun").text() == 'member_modify2') {
			location.href=$("#span_path").text() + "/index.do";
		}
	}
	
	if (value1 == 'list') {
		param = {
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text()
		}
	} else if (value1 == 'modify_passwdChk' || value1 == 'del_passwdChk') {
		param = {
			"no": $("#span_no").text()
		}
	} else if (value1 == 'modify' || value1 == 'delProc') {
		param = {
			"no": $("#span_no").text(),
			"passwd": $("#passwd").val()
		}
	} else if (value1 == 'chugaProc' || value1 == 'modifyProc') {
		param = {
			"no": $("#span_no").text(),
			"id": $("#id_" + $("#span_proc").text()).val(),
			"passwd": $("#passwd_" + $("#span_proc").text()).val(),
			"passwdChk": $("#passwdChk_" + $("#span_proc").text()).val(),
			"name": $("#name_" + $("#span_proc").text()).val(),
			"gender": $("#gender_" + $("#span_proc").text()).val(),
			"bornYear": $("#bornYear_" + $("#span_proc").text()).val(),
			"postcode": $("#sample6_postcode_" + $("#span_proc").text()).val(),
			"address": $("#sample6_address_" + $("#span_proc").text()).val(),
			"detailAddress": $("#sample6_detailAddress_" + $("#span_proc").text()).val(),
			"extraAddress": $("#sample6_extraAddress_" + $("#span_proc").text()).val(),
			"regiDate": $("#regiDate_" + $("#span_proc").text()).val(),
			"email": $("#email_" + $("#span_proc").text()).val()
		}
	} else if (value1 == 'view') {
		param = {
			"no": $("#span_no").text(),
			"pageNumber": $("#span_pageNumber").text(),
			"search_option": $("#span_search_option").text(),
			"search_data": $("#span_search_data").text()
		}
	} else if (value1 == 'loginProc') {
		if ($("#id_login").val() == "") {
			alert("아이디 입력");
			$("#id_login").select();
			$("#id_login").focus();
			return false;
		} else if ($("#passwd_login").val() == "") {
			alert("비밀번호 입력");
			$("#passwd_login").select();
			$("#passwd_login").focus();
			return false;
		} else {
			param = {
				"id": $("#id_login").val(),
				"passwd": $("#passwd_login").val()
			}
		}
	}
	$("#span_proc").text(value1);
	
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
		$("#gender_" + $("#span_proc").text()).val(value1);
	} else {
		$("#gender_" + $("#span_proc").text()).val(value1);
	}
}

//$(document).ready(function() {
//	$("#id").focus();
//
//	$("#btnChuga").click(function() {
//		if ($("#idChk").val() == $("#id").val()) {
//			if (confirm('가입?')) {
//				suntaek_proc('chugaProc', '1', '');
//			}
//		} else {
//			alert('아이디 중복확인');
//		}
//	})
//
//});

function id_check_div() {
	var id = $("#id_" + $("#span_proc").text()).val();
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
			$("#idChk_" + $("#span_proc").text()).val(data);
			if ($("#idChk_" + $("#span_proc").text()).val().length > 0) {
				$("#label_id").html("사용 가능");
				$("#label_id").css('color', 'green');
				$("#label_id").css('font-size', '8px');
			} else {
				$("#label_id").html("id 중복");
				$("#label_id").css('color', 'green');
				$("#label_id").css('font-size', '8px');
				$("#id_" + $("#span_proc").text()).val("");
			}
		}
	});
}

function id_check_win() {
	window.open("${path}/member_servlet/id_check_win_open.do", "aaa",
		"width=640, height=480, toolbar=no, menubar=no, scrollbars=no, resizeble=yes");
}

function sample6_execDaumPostcode() {
	new daum.Postcode(
		{
			oncomplete: function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수

				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				if (data.userSelectedType === 'R') {
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== ''
						&& /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== ''
						&& data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
							+ data.buildingName
							: data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if (extraAddr !== '') {
						extraAddr = ' (' + extraAddr + ')';
					}
					// 조합된 참고항목을 해당 필드에 넣는다.
					document.getElementById("sample6_extraAddress_" + $("#span_proc").text()).value = extraAddr;

				} else {
					document.getElementById("sample6_extraAddress_" + $("#span_proc").text()).value = '';
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('sample6_postcode_' + $("#span_proc").text()).value = data.zonecode;
				document.getElementById("sample6_address_" + $("#span_proc").text()).value = addr;
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById("sample6_detailAddress_" + $("#span_proc").text())
					.focus();
			}
		}).open();
}

