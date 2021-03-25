<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/inc_header.jsp"%>
<table border="1" align="center" width="95%">
	<tr>
		<td style="padding: 0 0 20 0;">
			이름 : <input type="text" id="comment_writer" name="comment_writer" size="10" value="${cookName }"> 
			비밀번호 : <input type="password" id="comment_passwd" name="comment_passwd" size="10" value=""><br> 
			댓글 : <input type="text" id="comment_content" name="comment_content" size="40">
			<button type="button" id="btnInsert" onclick="comment_suntaek_proc('commentProc','1','${no}')" style="display:">확인</button>
			<button type="button" id="btnModify" onclick="comment_suntaek_proc('commentModifyProc','1','${no}')" style="display: none;">수정</button>
			<button type="button" id="btnModifyCancel" onclick="modifyCancel('${cookName}')" style="display: none;">취소</button>
		</td>
	</tr>
</table>

<c:if test="${totalRecord > 0 }">
	<table border="0" align="center" width="95%">
		<c:forEach var="dto" items="${list }">
		<c:set var="k" value="${k = k + 1 }"></c:set>
			<tr>
				<td style="padding: 0 0 10 0;">
					<table border="0" align="center" style="width: 100%;">
						<tr>
							<td id="modify_writer${k }">${dto.writer }</td>
							<td>(${dto.regiDate })</td>
							<td align="right" id="passwd${k }" style="display: none;">
								비밀번호 : <input type="password" id="comment_passwd_del${dto.comment_no }" >
							</td>
						</tr>
						<tr>
							<td id="modify_content${k }" colspan="2">${dto.content }</td>
							<td align="right">
								<button type="button" onclick="modify('${dto.comment_no}','${k }');">수정</button>
<%-- 								<button type="button" id="btnCommentDel${k }" onclick="del('${dto.comment_no}','${k }');">삭제</button> --%>
<%-- 								<button type="button" id="btnCommentDelProc${k }" onclick="comment_suntaek_proc('commentDelProc','','${dto.board_no}','${dto.comment_no }');" style="display: none;">확인</button> --%>
								<button type="button" id="btnCommentDelProc${k }" onclick="comment_suntaek_proc('commentDelProc','','${dto.board_no}','${dto.comment_no }');">삭제</button>
							</td>
						</tr>
					</table>
					<hr>
				</td>
			</tr>
			<c:set var="number" value="${number = number - 1 }"></c:set>
		</c:forEach>
		<c:set var="tempgubun" value="commentList"></c:set>
		<c:if test="${totalRecord > 0 }">
			<tr>
				<td colspan="40" align="center">
					<a href="#" onclick="comment_suntaek_proc('${tempgubun}','1','${no }');">[첫페이지]</a>
					&nbsp;&nbsp; 
					<c:if test="${startPage > blockSize }">
						<a href="#" onclick="comment_suntaek_proc('${tempgubun}','${startPage -blockSize}','${no }');">[이전10개]</a>
					</c:if> 
					<c:if test="${startPage <=blockSize }"> [이전10개] 
					</c:if>
					&nbsp;&nbsp; 
					<c:forEach var="i" begin="${startPage}" end="${lastPage}" step="1">
						<c:if test="${i == commentPageNumber }"> [${i}]</c:if>
						<c:if test="${i != commentPageNumber }">
							<a href="#" onclick="comment_suntaek_proc('${tempgubun}','${i}','${no }')">${i}</a>
						</c:if>
					</c:forEach> &nbsp;&nbsp; 
					<c:if test="${lastPage < totalPage }">
						<a href="#" onclick="comment_suntaek_proc('${tempgubun}','${startPage + blockSize}','${no }');">[다음10개]</a>
					</c:if> 
					<c:if test="${lastPage >= totalPage }"> [다음10개] </c:if>
					&nbsp;&nbsp; 
					<a href="#" onclick="comment_suntaek_proc('${tempgubun}','${totalPage}','${no }');">[끝페이지]</a>
				</td>
			</tr>
		</c:if>
	</table>
</c:if>