<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp"%>

<div id="result"></div>

<script>
	$(document).ready(function() {
		<c:if test="${menu_gubun == 'email_index'}">
		goPage('chuga');
		</c:if>
	})

	function goPage(value1) {
		var method_type = "post";
		var param = {
			"fromName" : $("#fromName").val(),
			"fromEmail" : $("#fromEmail").val(),
			"toEmail" : $("#toEmail").val(),
			"subject" : $("#subject").val(),
			"content" : $("#content").val()
		}
		var url = "${path}/email_servlet/" + value1 + ".do";

		$.ajax({
			type : method_type,
			data : param,
			url : url,
			success : function(data) {
				$("#result").html(data);
			}
		});
	}
</script>