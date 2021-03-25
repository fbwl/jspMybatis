<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>
<table border="1" align="center" width="100%">
	<tr>
		<td colspan="7">
			<h2>회원관리</h2>
		</td>
	</tr>
	<tr>
		<td colspan="100">
			<select name="search_option" id="search_option">
			<c:choose>
				<c:when test="${search_option == 'id' }">
					<option value="" >- 선택 -</option>
					<option value="id" selected="selected">ID</option>
					<option value="name" >이름</option>
					<option value="gender" >성별</option>
					<option value="id_name_gender" >ID+이름+성별</option>
				</c:when>
				<c:when test="${search_option == 'name' }">
					<option value="" >- 선택 -</option>
					<option value="id" >ID</option>
					<option value="name" selected="selected">이름</option>
					<option value="gender" >성별</option>
					<option value="id_name_gender" >ID+이름+성별</option>
				</c:when>
				<c:when test="${search_option == 'gender' }">
					<option value="" >- 선택 -</option>
					<option value="id" >ID</option>
					<option value="name" >이름</option>
					<option value="gender" selected="selected">성별</option>
					<option value="id_name_gender" >ID+이름+성별</option>
				</c:when>
				<c:when test="${search_option == 'id_name_gender' }">
					<option value="" >- 선택 -</option>
					<option value="id" >ID</option>
					<option value="name" >이름</option>
					<option value="gender" >성별</option>
					<option value="id_name_gender" selected="selected">ID+이름+성별</option>
				</c:when>
				<c:otherwise>
					<option value="" >- 선택 -</option>
					<option value="id" >ID</option>
					<option value="name" >이름</option>
					<option value="gender" >성별</option>
					<option value="id_name_gender" >ID+이름+성별</option>
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
					<td>번호</td>
					<td>아이디</td>
					<td>비밀번호</td>
					<td>이름</td>
					<td>성별</td>
					<td>출생연도</td>
					<td>이메일</td>
					<td>우편번호</td>
					<td>주소</td>
					<td>상세주소</td>
					<td>참고주소</td>
					<td>가입일</td>
		
				</tr>
				<c:forEach var="dto" items="${list }" >
					<tr>
						<td><c:out value="${dto.no }"></c:out></td>
						<td><a href="#" onclick="suntaek_proc('view','','${dto.no}')"><c:out value="${dto.id }"></c:out></a></td>
						<td><c:out value="${dto.passwd }"></c:out></td>
						<td><c:out value="${dto.name }"></c:out></td>
						<td><c:out value="${dto.gender }"></c:out></td>
						<td><c:out value="${dto.bornYear }"></c:out></td>
						<td><c:out value="${dto.email }"></c:out></td>
						<td><c:out value="${dto.postcode }"></c:out></td>
						<td><c:out value="${dto.address }"></c:out></td>
						<td><c:out value="${dto.detailAddress }"></c:out></td>
						<td><c:out value="${dto.extraAddress }"></c:out></td>
						<td><c:out value="${dto.regiDate }"></c:out></td>
					</tr>
					<c:set var="number" value="${number=number-1 }"></c:set>
				</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<c:set var="tempgubun" value="list"></c:set>
		<c:if test="${totalRecord > 0 }">
			<tr>
				<td colspan="40" align="center">
					<a href="#" onclick="suntaek_proc('${tempgubun}','1','');">[첫페이지]</a>
					&nbsp;&nbsp; 
					<c:if test="${startPage > blockSize }">
						<a href="#" onclick="suntaek_proc('${tempgubun}','${startPage -blockSize}','');">[이전10개]</a>
					</c:if> 
					<c:if test="${startPage <=blockSize }"> [이전10개] </c:if>
					&nbsp;&nbsp;
					<c:forEach var="i" begin="${startPage}" end="${lastPage}" step="1">
						<c:if test="${i == pageNumber}"> [${i}]</c:if>
						<c:if test="${i != pageNumber}">
							<a href="#" onclick="suntaek_proc('${tempgubun}','${i}','')">${i}</a>
						</c:if>
					</c:forEach>
					&nbsp;&nbsp; 
					<c:if test="${lastPage < totalPage }">
						<a href="#" onclick="suntaek_proc('${tempgubun}','${startPage + blockSize}','');">[다음10개]</a>
					</c:if> 
					<c:if test="${lastPage >= totalPage }"> [다음10개] </c:if>
					&nbsp;&nbsp;
					<a href="#" onclick="suntaek_proc('${tempgubun}','${totalPage}','');">[끝페이지]</a>
				</td>
			</tr>
		</c:if>
	</tr>
</c:if>
<tr>
	<td colspan="70" height="50" align="right">
		<button type="button" onclick="suntaek_all();">전체목록</button>
		<button type="button" onclick="suntaek_proc('chuga','1','');">회원등록</button>
	</td>
</tr>
</table>