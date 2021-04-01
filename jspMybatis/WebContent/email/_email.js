var path = $("#span_path").text();

$(document).ready(function() {
	if ($("#span_menu_gubun").text() == 'email_index') {
		goPage('send');
	}
});

function sendEmail() {
	if ($("#fromName").val() != "" && $("#fromEmail").val() != "" && $("#toEmail").val() != "" && $("#subject").val() != "" && $("#content").val() != "") {
		if (confirm('이메일 발송 확인')) {
			goPage('sendProc');
		}
	} else {
		alert('입력 확인');
	}
}

function birthday() {
	goPage('selectBirthday');
}

function goPage(value1) {
	var method_type = "post";

	if (value1 == 'sendProc') {
		var param = {
			"fromName": $("#fromName").val(),
			"fromEmail": $("#fromEmail").val(),
			"toEmail": $("#toEmail").val(),
			"subject": $("#subject").val(),
			"content": $("#content").val()
		}
	}

	var url = $("#span_path").text() + "/email_servlet/" + value1 + ".do";

	$.ajax({
		type: method_type,
		data: param,
		url: url,
		success: function(data) {
			if (value1 == 'selectBirthday') {
				$("#toEmail").val(data);
			} else {
				$("#result").html(data);
			}
		}
	});
}