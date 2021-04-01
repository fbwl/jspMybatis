<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>
<h2>방명록</h2>
<table border="1" align="center" width="100%">
	<tr>
		<td colspan="100">
			<select name="search_option" id="search_option">
				<c:choose>
					<c:when test="${search_option == 'name' }">
						<option value="">- 선택 -</option>
						<option value="name" selected="selected">이름</option>
						<option value="content">내용</option>
						<option value="name_content">이름+내용</option>
					</c:when>
					<c:when test="${search_option == 'content' }">
						<option value="">- 선택 -</option>
						<option value="name">이름</option>
						<option value="content" selected="selected">내용</option>
						<option value="name_content">이름+내용</option>
					</c:when>
					<c:when test="${search_option == 'name_content' }">
						<option value="">- 선택 -</option>
						<option value="name">이름</option>
						<option value="content">내용</option>
						<option value="name_content" selected="selected">이름+내용</option>
					</c:when>
					<c:otherwise>
						<option value="">- 선택 -</option>
						<option value="name">이름</option>
						<option value="content">내용</option>
						<option value="name_content">이름+내용</option>
					</c:otherwise>
				</c:choose>
			</select> 
			<input type="text" name="search_data" id="search_data" value="${search_data }" style="width: 150px"> &nbsp; 
			<input type="button" value="검색" onclick="search();">
		</td>
	</tr>
	<tr>
		<td>이름 :</td>
		<td><input type="text" name="name" id="name" value="${sessionScope.cookName }"></td>
	</tr>
	<tr>
		<td>비밀번호 :</td>
		<td><input type="password" name="passwd" id="passwd" size="20"></td>
	</tr>
	<tr>
		<td>이메일 :</td>
		<td><input type="text" name="email" id="email" size="40" value="${sessionScope.cookEmail }"></td>
	</tr>
	<tr>
		<td>내용 :</td>
		<td><input type="text" name="content" id="content" size="40"></td>
	</tr>
	<tr>
		<td colspan="100" align="center">
			<button type="button" id="btnInsert" onclick="suntaek_proc('chugaProc','','');" style="display:">등록</button>
			<button type="button" id="btnModify" onclick="suntaek_proc('modifyProc','','${no}');" style="display: none;">수정</button>
		</td>
	</tr>
	<c:if test="${totalRecord==0 }">
		<tr>
			<td height="200" colspan="100">
				<table border="1" align="center" style="width: 100%; height: 200px;">
					<tr>
						<td align="center">등록된 내용이 없습니다.</td>
					</tr>
				</table>
			</td>
		</tr>
	</c:if>
	<c:if test="${totalRecord>0 }">
		<table border="1" align="center" style="width: 100%; height: 200px;">
			<c:forEach var="dto" items="${list }">
			<c:set var="k" value="${k = k + 1 }"></c:set>
				<tr>
					<td>이름</td>
					<td id="modify_name${k }">${dto.name}</td>
					<td>날짜</td>
					<td>${dto.regi_date}</td>
					<td align="center" width="95px" rowspan="3">
						<button type="button" onclick="modify('${dto.no}','${k }');">수정</button>
						<button type="button" onclick="suntaek_proc('del_passwdChk','','${dto.no}');">삭제</button>
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td colspan="3" id="modify_email${k }">${dto.email}</td>
				</tr>
				<tr>
					<td colspan="4" id="modify_content${k }">${dto.content}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
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
</table>
<table align="center" width="100%">
	<tr>
		<td colspan="70" height="50" align="right">
			<button type="button" onclick="suntaek_all();">전체목록</button>
		</td>
	</tr>
</table>
</body>
</html>