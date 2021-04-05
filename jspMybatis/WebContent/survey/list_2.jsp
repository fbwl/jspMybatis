<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>
<h2>설문지</h2>
<br>
<div style="display: none">
total : <span id="span_answer_total"></span><br>
span_list_size : <span id="span_list_size">${list.size() }</span><br>
</div>
<c:forEach var="dto" items="${list }">
<div style="display: none">
<a named="a_${dto.no }"></a><br>
q_no : <span id="q_${number }">${dto.no }</span><br>
span_answer${number } : <span id="span_answer${number }"></span>
</div>
	<table border="1" align="center" style="width: 100%">
		<tr>
			<td>문항 ${number}) ${dto.question }</td>
		</tr>
		<tr>
			<td>
				<a href="#a_${dto.no }" onclick="check_answer('${number}','1');"><font style="font-family: 'MS Gothic';"><span id="mun1${number }">①</span></font></a>
				<a href="#a_${dto.no }" onclick="check_answer('${number}','1');">${dto.ans1 }</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="#a_${dto.no }" onclick="check_answer('${number}','2');"><font style="font-family: 'MS Gothic';"><span id="mun2${number }">②</span></font></a>
				<a href="#a_${dto.no }" onclick="check_answer('${number}','2');">${dto.ans2 }</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="#a_${dto.no }" onclick="check_answer('${number}','3');"><font style="font-family: 'MS Gothic';"><span id="mun3${number }">③</span></font></a>
				<a href="#a_${dto.no }" onclick="check_answer('${number}','3');">${dto.ans3 }</a>
			</td>
		</tr>
		<tr>
			<td>
				<a href="#a_${dto.no }" onclick="check_answer('${number}','4');"><font style="font-family: 'MS Gothic';"><span id="mun4${number }">④</span></font></a>
				<a href="#a_${dto.no }" onclick="check_answer('${number}','4');">${dto.ans4 }</a>
			</td>
		</tr>
	</table>
	<div style="display: none;">${number = number-1}</div>
	<br><br><br>
</c:forEach>
<table>
	<tr>
		<td colspan="10" height="50" align="right">
			<button type="button" onclick="suntaek_proc('saveProc','','');">설문저장</button>
			<button type="button" onclick="suntaek_proc('list_2','1','');">목록으로</button>
		</td>
	</tr>
</table>