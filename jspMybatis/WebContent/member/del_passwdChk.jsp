<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>
<table border="1" align="center" width="80%">
	<tr>
		<td width="150">비밀번호</td>
		<td><input type="password" name="passwd" id="passwd" value=""></td>
	</tr>
	<tr>
		<td colspan="2" align="center"><input type="button"
			onclick="suntaek_proc('delProc','','${no}');" value="삭제하기"></td>
	</tr>
</table>
