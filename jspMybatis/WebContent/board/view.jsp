<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/inc_header.jsp"%>

<c:choose>
	<c:when test="${imsiPage == 'viewPasswdPage' }">
		<table border="1" align="center" width="80%">
			<tr>
				<td colspan="2">
					<h2>글상세보기 (비밀글)</h2>
				</td>
			</tr>
			<tr>
				<td width="150">비밀번호 : </td>
				<td>
					<input type="password" name="view_passwd" id="view_passwd">
					<button type="button" id="btnViewPasswd" onclick="suntaek_proc('view', '', '${no}');">확인</button>
				</td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<table border="1" align="center" width="80%">
			<tr>
				<td colspan="2">
					<h1>글상세보기</h1>
					<input type="text" name="no" value="${dto.no }">
				</td>
			</tr>
			<tr>
				<td width="150">작성자 : </td>
				<td>${dto.writer }</td>
			</tr>
			<tr>
				<td>제목 : </td>
				<td>${dto.subject }</td>
			</tr>
			<tr>
				<td>내용 : </td>
				<td id="content">${dto.content }</td>
			</tr>
			<tr>
				<td>이메일 : </td>
				<td>${dto.email }</td>
			</tr>
			<tr>
				<td>조회수 : </td>
				<td>${dto.hit }</td>
			</tr>
			<tr>
				<td>현재글의 답변글 : </td>
				<td>${dto.child_counter }</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" onclick="suntaek_proc('chuga', '', '');">글쓰기</button>
					
					<c:if test="${dto.noticeNo == 0 }">
						<button type="button" onclick="suntaek_proc('reply', '', '${dto.no}');">답변쓰기</button>
					</c:if>
					
					<button type="button" onclick="suntaek_proc('modify', '', '${dto.no}');">수정하기</button>
					
					<c:if test="${dto.child_counter == 0 }">
						<button type="button" onclick="suntaek_proc('del', '', '${dto.no}');">삭제하기</button>
					</c:if>
					
					<button type="button" onclick="suntaek_proc('list', '1', '');">목록으로</button>
				</td>
			</tr>
			<tr>
				<td colspan="2" height="50px">
					<table border="1" width="100%" align="center">
						<tr>
							<td width="100px">이전글 : </td>
							<td>
								<c:if test="${dto.preSubject == null }">
									이전글이 없습니다.
								</c:if>
								<c:if test="${dto.preSubject != null }">
									<a href="#" onclick="suntaek_proc('view', '', '${dto.preNo}');">${dto.preSubject }</a>
								</c:if>
							</td>
						</tr>
						<tr>
							<td width="100px">다음글 : </td>
							<td>
								<c:if test="${dto.nxtSubject == null }">
									다음글이 없습니다.
								</c:if>
								<c:if test="${dto.nxtSubject != null }">
									<a href="#" onclick="suntaek_proc('view', '', '${dto.nxtNo}');">${dto.nxtSubject }</a>
								</c:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" height="50px" style="padding: 20 0 0 0;">
					<a name="comment"></a>
					<div style="display: none;">
						commentPageNumber : <span id="span_commentPageNumber"></span><br>
					</div>
					<div id="CommentResult"></div>
				</td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>