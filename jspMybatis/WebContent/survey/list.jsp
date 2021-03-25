<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<!-- <input type="text" name="searchField"><br> -->
<table border="1" width="80%" align="center">
	<tr>
		<td colspan="10">
			<h2>설문조사 목록</h2>
		</td>
	</tr>
	<tr>
		<td colspan="7">
			<select name="search_option" id="search_option">
			<c:choose>
				<c:when test="${search_option == 'question' }" >
					<option value="">- 선택 -</option>
					<option value="question" selected="selected">질문내용</option>
				</c:when>
				<c:otherwise>
					<option value="" selected="selected">- 선택 -</option>
					<option value="question">질문내용</option>
				</c:otherwise>
			</c:choose>
			</select>
			
			<input type="text" name="search_data" id="search_data" value="" style="width: 150px">
			<input type="text" name="search_date_s" id="search_date_s" value="${search_date_s }" style="width: 150px"> ~
			<input type="text" name="search_date_e" id="search_date_e" value="${search_date_e }" style="width: 150px">
			<input type="checkbox" name="search_date_check" id="search_date_check" value="O" onclick="checkboxChk();"><span style="color:blue; font-size: 9px;">[날짜 검색시 체크]</span>&nbsp;
			<input type="button" value="검색" onclick="search();">
		</td>
	</tr>
	<tr>
		<td style="padding:10px 0px 5px;" colspan="10">전체 ${totalRecord }건</td>
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
		<td>순번</td>
		<td>질문</td>
		<td>기간</td>
		<td>참여수</td>
		<td>상태</td>
   	</tr>   
	<c:forEach var="dto" items="${list }">
		<tr>
			<td>${number }</td>
			<td><a href="#" onclick="suntaek_proc('view','','${dto.no}');">${dto.question }</a></td>
			<td>${dto.start_date }<br>${dto.last_date }</td>
			<td>${dto.survey_counter }</td>
			<td>${dto.status }</td>
		</tr>
		<c:set var="number" value="${number=number-1 }"></c:set>
	</c:forEach>
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
	<tr>
		<td colspan="7" height="50" align="right">
			<button type="button" onclick="suntaek_list('all');">전체 설문목록</button>
			<button type="button" onclick="suntaek_list('ing');">진행중인 설문목록</button>
			<button type="button" onclick="suntaek_list('end');">종료된 설문목록</button>
			<button type="button" onclick="suntaek_proc('chuga','','');">등록하기</button>
		</td>
	</tr>
</table>