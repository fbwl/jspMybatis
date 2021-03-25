<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>

<table border="0" align="center" width="100%">
	<tr>
		<td colspan="7">
			<h2>게시글 목록</h2>
		</td>
	</tr>
	<tr>
		<td colspan="7">
			<select name="search_option" id="search_option">
			<c:choose>
				<c:when test="${search_option == 'writer' }">
					<option value="" >- 선택 -</option>
					<option value="writer" selected="selected">작성자</option>
					<option value="subject" >제목</option>
					<option value="content" >내용</option>
					<option value="writer_subject_content" >작성자+제목+내용</option>
				</c:when>
				<c:when test="${search_option == 'subject' }">
					<option value="" >- 선택 -</option>
					<option value="writer" >작성자</option>
					<option value="subject" selected="selected">제목</option>
					<option value="content" >내용</option>
					<option value="writer_subject_content" >작성자+제목+내용</option>
				</c:when>
				<c:when test="${search_option == 'content' }">
					<option value="" >- 선택 -</option>
					<option value="writer" selected="selected">작성자</option>
					<option value="subject" >제목</option>
					<option value="content" selected="selected">내용</option>
					<option value="writer_subject_content" >작성자+제목+내용</option>
				</c:when>
				<c:when test="${search_option == 'writer_subject_content' }">
					<option value="" >- 선택 -</option>
					<option value="writer" >작성자</option>
					<option value="subject" >제목</option>
					<option value="content" >내용</option>
					<option value="writer_subject_content" selected="selected">작성자+제목+내용</option>
				</c:when>
				<c:otherwise>
					<option value="" selected="selected">- 선택 -</option>
					<option value="writer" >작성자</option>
					<option value="subject" >제목</option>
					<option value="content" >내용</option>
					<option value="writer_subject_content" >작성자+제목+내용</option>
				</c:otherwise>
			</c:choose>
			</select>
			<input type="text" name="search_data" id="search_data" value="${search_data }" style="width: 150px">
			&nbsp;
			<input type="button" value="검색" onclick="search();">
		</td>
	</tr>
	<tr>
		<td style="padding: 10px 0px 5px;">전체 ${totalRecord }건입니다.</td>
	</tr>
<c:if test="${totalRecord==0 }">
	<tr>
		<td height="200">
			<table border="1" align="center" style="width: 100%; height: 200px;">
				<tr>
					<td align="center">등록된 내용이 없습니다.</td>
				</tr>
			</table>
		</td>
	</tr>
</c:if>
<c:if test="${totalRecord>0 }">
	<tr>
		<td style="padding: 0 0 20px 0;">
			<table border="1" align="center" style="width: 100%;">
				<tr>
					<td>순번</td>
					<td>제목</td>
					<td>작성자</td>
					<td>등록일</td>
					<td>조회수</td>
					<td>ip</td>
					<td>회원번호</td>
					<td>공지</td>
					<td>비밀글</td>
					<td>자식글여부</td>
				</tr>
				<c:forEach var="dto" items="${list }">
					<tr>
						<c:if test="${dto.noticeNo > 0 }">
							<td>공지</td>
						</c:if>
						<c:if test="${dto.noticeNo == 0 }">
							<td>${number}</td>
						</c:if>
						<td>
							<c:forEach var="i" begin="2" end="${dto.stepNo }" step="1"><!-- varStatus="status" -->
								&nbsp;
							</c:forEach>
							<c:if test="${dto.stepNo > 1 }">
								<c:set var="reVar" value="[Re]:"/>
							</c:if>
							<c:if test="${dto.stepNo <= 1 }">
								<c:set var="reVar" value=""/>
							</c:if>
							
							<a href="#" onclick="suntaek_proc('view', '', '${dto.no}');">${reVar } ${dto.subject }</a>
						</td>
						<td>${dto.writer}</td>
						<td>${dto.regiDate}</td>
						<td>${dto.hit}</td>
						<td>${dto.ip}</td>
						<td>${dto.memberNo}</td>
						<td>${dto.noticeNo}</td>
						<td>${dto.secretGubun}</td>
						<td>${dto.child_counter}</td>
					</tr>
					<c:set var="number" value="${number=number-1 }"></c:set>
				</c:forEach>
			</table>
		</td>
	</tr>
	<c:set var="tempgubun" value="list"></c:set>
	<c:if test="${totalRecord > 0 }">
		<tr>
			<td colspan="40" align="center">
				<a href="#" onclick="suntaek_proc('${tempgubun}','1','');">[첫페이지]</a>
				&nbsp;&nbsp; 
				<c:if test="${startPage > blockSize }">
					<a href="#" onclick="suntaek_proc('${tempgubun}','${startPage -blockSize}','');">[이전10개]</a>
				</c:if> 
				<c:if test="${startPage <=blockSize }"> [이전10개] 
				</c:if>
				&nbsp;&nbsp; 
				<c:forEach var="i" begin="${startPage}" end="${lastPage}" step="1">
					<c:if test="${i == pageNumber}"> [${i}]</c:if>
					<c:if test="${i != pageNumber}">
						<a href="#" onclick="suntaek_proc('${tempgubun}','${i}','')">${i}</a>
					</c:if>
				</c:forEach> &nbsp;&nbsp; 
				<c:if test="${lastPage < totalPage }">
					<a href="#" onclick="suntaek_proc('${tempgubun}','${startPage + blockSize}','');">[다음10개]</a>
				</c:if> 
				<c:if test="${lastPage >= totalPage }"> [다음10개] </c:if>
				&nbsp;&nbsp; 
				<a href="#" onclick="suntaek_proc('${tempgubun}','${totalPage}','');">[끝페이지]</a>
			</td>
		</tr>
	</c:if>
</c:if>
<tr>
	<td colspan="7" height="50" align="right">
		<button type="button" onclick="suntaek_all();">전체목록</button>
		<button type="button" onclick="suntaek_proc('chuga','','');">글쓰기</button>
	</td>
</tr>
</table>