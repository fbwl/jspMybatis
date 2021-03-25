<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>

<table border="1" align="center" width="80%">
	<tr>	
		<td colspan="2"><h2>글삭제</h2></td>
	</tr>
	<tr>
		<td style="align: center;">비밀번호</td>
		<td><input type="text" name="passwd" id="passwd"></td>
	</tr>
	<tr>
		<td align="center" colspan="2" height="50px">
			<button type="button" id="btnDelete" onclick="suntaek_proc('delProc','','${no}')">삭제하기</button>
			<button type="button" id="btnList"  onclick="suntaek_proc('list','1','')">목록으로</button>
		</td>
	</tr>
</table>