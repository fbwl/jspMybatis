<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>
<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2"><h2>로그인</h2></td>
	</tr>
	<tr>
		<td width="150">아이디</td>
		<td><input type="text" name="id_login" id="id_login" value=""></td>
	</tr>
	<tr>
		<td width="150">비밀번호</td>
		<td><input type="password" name="passwd_login" id="passwd_login" value=""></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button type="button" onclick="suntaek_proc('loginProc','','');">로그인</button>
			<button type="button" onclick="suntaek_proc('chuga','','');">회원가입</button>
		</td>
	</tr>
</table>
