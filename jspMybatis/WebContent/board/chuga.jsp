<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>

<table border="1" align="center" width="80%">
	<tr>
		<c:if test="${dto.no>0 }">		
		<td colspan="2"><h2>답변글쓰기</h2></td>
		</c:if>
		<c:if test="${dto.no==null }">	
		<td colspan="2"><h2>게시글쓰기</h2></td>
		</c:if>
	</tr>
	<tr>
		<td style="align: center;">작성자</td>
		<td><input type="text" name="writer" id="writer"></td>
	</tr>
	<tr>
		<td style="align: center;">이메일</td>
		<td><input type="text" name="email" id="email"></td>
	</tr>
	<tr>
		<td style="align: center;">비밀번호</td>
		<td><input type="text" name="passwd" id="passwd"></td>
	</tr>
	<tr>
		<td style="align: center;">제목</td>
		<c:if test="${dto.no>0 }">		
		<td><input type="text" name="subject" id="subject" value="${dto.subject }"></td>
		</c:if>
		<c:if test="${dto.no==null }">
		<td><input type="text" name="subject" id="subject"></td>
		</c:if>
	</tr>
	<tr>
		<td style="align: center;">내용</td>
		<c:if test="${dto.no>0 }">		
		<td><textarea name="content" id="content"
				style="width: 300px; height: 100px;">${dto.content }</textarea></td>
		</c:if>
		<c:if test="${dto.no==null }">
		<td><textarea name="content" id="content"
				style="width: 300px; height: 100px;"></textarea></td>
		</c:if>
	</tr>
	<tr>
		<td style="align: center;">공지글</td>
		<td><input type="text" name="noticeGubun" id="noticeGubun">
			<input type="checkbox" name="noticeGubunCheckBox" id="noticeGubunCheckBox" value="T"
			onclick="clickChk('noticeGubun');">공지글 체크</td>
	</tr>
	<tr>
		<td style="align: center;">비밀글</td>
		<td><input type="text" name="secretGubun" id="secretGubun">
			<input type="checkbox" name="secretGubunCheckBox" id="secretGubunCheckBox" value="T"
			onclick="clickChk('secretGubun');">비밀글 체크</td>
	</tr>
	<tr>
		<td align="center" colspan="2" height="50px">
			<c:if test="${dto.no>0 }">
			<button type="button" id="btnChuga" onclick="suntaek_proc('chugaProc', '', '${dto.no}');">등록하기</button>
			</c:if>
			<c:if test="${dto.no==null }">
			<button type="button" id="btnChuga" onclick="suntaek_proc('chugaProc', '', '');">등록하기</button>
			</c:if>
			<button type="button" id="btnList" onclick="suntaek_proc('list', '1', '');">목록으로</button>
		</td>
	</tr>
</table>