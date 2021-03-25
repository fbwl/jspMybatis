<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>

<table border="1" align="center" width="80%">
	<tr>	
		<td colspan="2"><h2>글수정하기</h2></td>
	</tr>
	<tr>
		<td style="align: center;">작성자</td>
		<td><input type="text" name="writer" id="writer" value="${dto.writer }" readonly="readonly"></td>
	</tr>
	<tr>
		<td style="align: center;">이메일</td>
		<td><input type="text" name="email" id="email" value="${dto.email }"></td>
	</tr>
	<tr>
		<td style="align: center;">비밀번호</td>
		<td><input type="text" name="passwd" id="passwd"></td>
	</tr>
	<tr>
		<td style="align: center;">제목</td>
		<td><input type="text" name="subject" id="subject" value="${dto.subject }"></td>
	</tr>
	<tr>
		<td style="align: center;">내용</td>
		<td><textarea name="content" id="content"
				style="width: 300px; height: 100px;">${dto.content }</textarea></td>
	</tr>
	<tr>
		<td style="align: center;">공지글</td>
		<c:if test="${dto.noticeNo > 0}">
		<td><input type="text" name="noticeGubun" id="noticeGubun" value="T">
			<input type="checkbox" name="noticeGubunCheckBox" id="noticeGubunCheckBox" value="T"
			onclick="clickChk('noticeGubun');" checked="checked">공지글 체크</td>
		</c:if>
		<c:if test="${dto.noticeNo == 0}">
		<td><input type="text" name="noticeGubun" id="noticeGubun">
			<input type="checkbox" name="noticeGubunCheckBox" id="noticeGubunCheckBox" value="T"
			onclick="clickChk('noticeGubun');">공지글 체크</td>
		</c:if>
	</tr>
	<tr>
		<c:if test="${dto.secretGubun == 'T'}">
		<td style="align: center;">비밀글</td>
		<td><input type="text" name="secretGubun" id="secretGubun" value="T">
			<input type="checkbox" name="secretGubunCheckBox" id="secretGubunCheckBox" value="T"
			onclick="clickChk('secretGubun');" checked="checked">비밀글 체크</td>
		</c:if>
		<c:if test="${dto.secretGubun != 'T'}">
		<td style="align: center;">비밀글</td>
		<td><input type="text" name="secretGubun" id="secretGubun">
			<input type="checkbox" name="secretGubunCheckBox" id="secretGubunCheckBox" value="T"
			onclick="clickChk('secretGubun');">비밀글 체크</td>
		</c:if>
	</tr>
	<tr>
		<td align="center" colspan="2" height="50px">
			<button type="button" onclick="suntaek_proc('modifyProc','','${dto.no}')">수정하기</button>
			<button type="button" onclick="suntaek_proc('list','1','')">목록으로</button>
		</td>
	</tr>
</table>
