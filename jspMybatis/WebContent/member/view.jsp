<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>

<h2>회원상세정보</h2>
<table border="1" align="center" width="80%">
	<tr>
		<td width="150">아이디</td>
		<td>${dto.id }</td>
	</tr>
	<tr>
		<td width="150">이름</td>
		<td>${dto.name }</td>
	</tr>
	<tr>
		<td width="150">성별</td>
		<td><c:if test="${dto.gender.equals('M') }">남</c:if> <c:if
				test="${dto.gender.equals('F') }">여</c:if></td>
	</tr>
	<tr>
		<td width="150">출생연도</td>
		<td>${dto.bornYear }</td>
	</tr>
	<tr>
		<td width="150">이메일</td>
		<td>${dto.email }</td>
	</tr>
	<tr>
		<td>우편번호</td>
		<td>${dto.postcode }</td>
	</tr>
	<tr>
		<td>주소</td>
		<td>${dto.address }</td>
	</tr>
	<tr>
		<td>상세주소</td>
		<td>${dto.detailAddress }</td>
	</tr>
	<tr>
		<td>참고주소</td>
		<td>${dto.extraAddress }</td>
	</tr>
	<tr>
		<td>가입일</td>
		<td>${dto.regiDate }</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<button type="button" onclick="suntaek_proc('modify_passwdChk','','${dto.no}');" >수정하기</button>
			<button type="button" onclick="suntaek_proc('del_passwdChk','','${dto.no}');" >삭제하기</button>
		</td>
	</tr>
</table>