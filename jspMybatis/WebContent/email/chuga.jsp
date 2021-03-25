<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp"%>
<form name="selectBirthdayForm">
<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2">
			<h2>이메일 보내기</h2>
		</td>
	</tr>
	<tr>
		<td style="align: center;">발신자 이름</td>
		<td>
			<input type="text" name="fromName" id="fromName">
		</td>
	</tr>
	<tr>
		<td style="align: center;">발신자 이메일</td>
		<td>
			<input type="text" name="fromEmail" id="fromEmail">
		</td>
	</tr>
	<tr>
		<td style="align: center;">수신자 이메일</td>
		<td>
			<c:if test="${list!=null }">
			<input type="text" name="toEmail" id="toEmail" value="${list }">
			</c:if>
			<c:if test="${list==null }">
			<input type="text" name="toEmail" id="toEmail">
			</c:if>
			<button type="button" id="btnBirthday">수신자(생일)</button>
		</td>
	</tr>
	<tr>
		<td style="align: center;">제목</td>
		<td>
			<input type="text" name="subject" id="subject">
		</td>
	</tr>
	<tr>
		<td style="align: center;">내용</td>
		<td>
			<textarea name="content" id="content" style="width:300px; height: 100px;" wrap="hard"></textarea>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2" height="50px;">
			<button type="button" id="btnSave">메일보내기</button>
		</td>
	</tr>
</table>
</form>
<script>
	$(document).ready(function() {
		$("#fromName").focus();
		
		$("#btnSave").click(function() {
			if (confirm('이메일 발송')) {
				goPage('chugaProc');
			}
		})
		
		$("#btnBirthday").click(function() {
			go('selectBirthday');
		})
	})
	
	function go(value1) {
		var param = {};
		var url = "${path}/email_servlet/" + value1 + ".do";
		$.ajax({
			type : "post",
			data : param,
			url : url,
			success : function(data) {
				$("#result").html(data);
			}
		})
	}
	
</script>