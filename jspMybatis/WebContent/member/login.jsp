<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>
<form name="LoginForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2"><h2>로그인</h2></td>
		</tr>
		<tr>
			<td width="150">아이디</td>
			<td><input type="text" name="id" value=""></td>
		</tr>
		<tr>
			<td width="150">비밀번호</td>
			<td><input type="password" name="passwd" value=""></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button"
				onclick="login();" value="로그인"><input type="button"
				onclick="join();" value="가입하기"></td>
		</tr>
	</table>
</form>