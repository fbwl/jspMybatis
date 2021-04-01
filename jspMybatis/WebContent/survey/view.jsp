<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>
설문조사<br>
<div style="display: none;">
span_answer : <span id="span_answer"></span>
</div>
<table border="1" align="center" style="width: 100%">
	<tr>
		<td>Q) ${dto.question }</td>
	</tr>
	<tr>
		<td>
			<a href="#" onclick="check_answer('','1');"><font style="font-family: 'MS Gothic';"><span id="mun1">①</span></font></a>
			<a href="#" onclick="check_answer('','1');">${dto.ans1 }</a>
		</td>
	</tr>
	<tr>
		<td>
			<a href="#" onclick="check_answer('','2');"><font style="font-family: 'MS Gothic';"><span id="mun2">②</span></font></a>
			<a href="#" onclick="check_answer('','2');">${dto.ans2 }</a>
		</td>
	</tr>
	<tr>
		<td>
			<a href="#" onclick="check_answer('','3');"><font style="font-family: 'MS Gothic';"><span id="mun3">③</span></font></a>
			<a href="#" onclick="check_answer('','3');">${dto.ans3 }</a>
		</td>
	</tr>
	<tr>
		<td>
			<a href="#" onclick="check_answer('','4');"><font style="font-family: 'MS Gothic';"><span id="mun4">④</span></font></a>
			<a href="#" onclick="check_answer('','4');">${dto.ans4 }</a>
		</td>
	</tr>
	<tr>
		<td colspan="10" height="50" align="right">
			<button type="button" onclick="suntaek_proc('viewProc','','');">설문조사 저장하기</button>
			<button type="button" onclick="suntaek_proc('list','1','');">목록으로</button>
			<button type="button" onclick="suntaek_proc('modify','','${dto.no}');">수정</button>
			<button type="button" onclick="suntaek_proc('delProc','','${dto.no}');">삭제</button>
		</td>
	</tr>
</table>