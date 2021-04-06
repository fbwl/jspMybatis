<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp"%>

<h2>이메일 보내기</h2>
<table border="1" align="center" width="80%">
	<tr>
		<td style="align: center;">발신자 이름</td>
		<td>
			<input type="text" name="fromName" id="fromName" value="${sessionScope.cookName }">
		</td>
	</tr>
	<tr>
		<td style="align: center;">발신자 이메일</td>
		<td>
			<input type="text" name="fromEmail" id="fromEmail" value="${sessionScope.cookEmail }">
		</td>
	</tr>
	<tr>
		<td style="align: center;">수신자 이메일</td>
		<td>
			<input type="text" name="toEmail" id="toEmail">
			<button type="button" id="btnBirthday" onclick="birthday();">수신자 가져오기(생일)</button>
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
			<textarea name="content" id="content" style="width:100%; height: 150px;" wrap="hard"></textarea>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2" height="50px;">
			<button type="button" id="btnSave" onclick="sendEmail();">메일보내기</button>
		</td>
	</tr>
</table>
