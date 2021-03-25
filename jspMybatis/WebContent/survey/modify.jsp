<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<form name="DirForm" >
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2">설문조사 수정</td>
		</tr>
		<tr>
			<td style="align:center;">question</td>
			<td><input type="text" name="question" id="question" value="${dto.question }"></td>
		</tr>
		<tr>
			<td style="align:center;">ans1</td>
			<td><input type="text" name="ans1" id="ans1" value="${dto.ans1 }"></td>
		</tr>
		<tr>
			<td style="align:center;">ans2</td>
			<td><input type="text" name="ans2" id="ans2" value="${dto.ans2 }"></td>
		</tr>
		<tr>
			<td style="align:center;">ans3</td>
			<td><input type="text" name="ans3" id="ans3" value="${dto.ans3 }"></td>
		</tr>
		<tr>
			<td style="align:center;">ans4</td>
			<td><input type="text" name="ans4" id="ans4" value="${dto.ans4 }"></td>
		</tr>
		<tr>
			<td style="aling:center;">status</td>
			<td>
				<input type="radio" name="status" id="status" value="1">진행중
				<input type="radio" name="status" id="status" value="0">종료
			</td>
		</tr>
		<tr>
			<td>start_date</td>
			<td>
				<select name="syear" id="syear">
					<c:forEach var="i" begin="${naljaMap['syear']-1 }" end="${naljaMap['syear']+1 }" step="1">
						<c:if test="${i==naljaMap['syear'] }">
							<option value="${i }" selected="selected">${i }</option>
						</c:if>
						<c:if test="${i!=naljaMap['syear'] }">
							<option value="${i }" >${i }</option>
						</c:if>
					</c:forEach>
				</select>
				<select name="smonth" id="smonth">
					<c:forEach var="i" begin="1" end="12" step="1">
						<c:if test="${i==naljaMap['smonth'] }">
							<option value="${i }" selected="selected">${i }</option>
						</c:if>
						<c:if test="${i!=naljaMap['smonth'] }">
							<option value="${i }" >${i }</option>
						</c:if>
					</c:forEach>
				</select>
				<select name="sday" id="sday">
					<c:forEach var="i" begin="1" end="31" step="1">
						<c:if test="${i==naljaMap['sday'] }">
							<option value="${i }" selected="selected">${i }</option>
						</c:if>
						<c:if test="${i!=naljaMap['sday'] }">
							<option value="${i }" >${i }</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>end_date</td>
			<td>
				<select name="lyear" id="lyear">
					<c:forEach var="i" begin="${naljaMap['lyear']-1 }" end="${naljaMap['lyear']+1 }" step="1">
						<c:if test="${i==naljaMap['lyear'] }">
							<option value="${i }" selected="selected">${i }</option>
						</c:if>
						<c:if test="${i!=naljaMap['lyear'] }">
							<option value="${i }" >${i }</option>
						</c:if>
					</c:forEach>
				</select>
				<select name="lmonth" id="lmonth">
					<c:forEach var="i" begin="1" end="12" step="1">
						<c:if test="${i==naljaMap['lmonth'] }">
							<option value="${i }" selected="selected">${i }</option>
						</c:if>
						<c:if test="${i!=naljaMap['lmonth'] }">
							<option value="${i }" >${i }</option>
						</c:if>
					</c:forEach>
				</select>
				<select name="lday" id="lday">
					<c:forEach var="i" begin="1" end="31" step="1">
						<c:if test="${i==naljaMap['lday'] }">
							<option value="${i }" selected="selected">${i }</option>
						</c:if>
						<c:if test="${i!=naljaMap['lday'] }">
							<option value="${i }" >${i }</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<button type="button" onclick="suntaek_proc('modifyProc','','${dto.no}');">수정</button>
				<button type="button" onclick="suntaek_proc('list','1','');">목록</button>
			</td>
		</tr>
	</table>
</form>