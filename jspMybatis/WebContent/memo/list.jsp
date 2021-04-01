<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<h2>메모장</h2>
<table width="400">
	<tr>
		<td>이름 : <input type="text" id="writer" size="20" ><br>
			내용 : <input type="text" id="content" size="35" ><br>
		</td>
	</tr>
	<tr>
		<td align="center">
			<button type="button" id="btnInsert" onclick="suntaek_proc('writeProc','','');" style="display: ">등록</button>
			<button type="button" id="btnModify" onclick="suntaek_proc('modifyProc','','${no}');" style="display: none;">수정</button>
		</td>
	</tr>
</table>
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
		   <table border="1">
			   <tr>
			      <td>No</td>
			      <td>이름</td>
			      <td>메모</td>
			      <td>날짜</td>
			      <td>수정</td>
			      <td>삭제</td>
			   </tr>   
			   <c:forEach var="dto" items="${list }">
			   <tr>
			      <td>${dto.no}</td>
			      <td>${dto.writer}</td>
			      <td>${dto.content}</td>
			      <td>${dto.regi_date}</td>
			      <td><button type="button" onclick="modify('${dto.no}','${dto.writer }','${dto.content }');">수정</button></td>
			      <td><button type="button" onclick="suntaek_proc('delProc','','${dto.no}');">삭제</button></td>
			   </tr>
			   </c:forEach>
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
			</table>
		</td>
	</tr>
</c:if>
</body>
</html>