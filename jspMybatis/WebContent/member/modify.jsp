<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>
<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2"><h2>회원정보수정</h2></td>
	</tr>
	<tr>
		<td width="150">아이디</td>
		<td><input type="hidden" name="id" id="id_${proc }" value="${dto.id }">${dto.id }</td>
	</tr>
	<tr>
		<td width="150">비밀번호</td>
		<td><input type="password" name="passwd" id="passwd_${proc }" value=""></td>
	</tr>
	<tr>
		<td width="150">비밀번호 확인</td>
		<td><input type="password" name="passwdChk" id="passwdChk_${proc }" value=""></td>
	</tr>
	<tr>
		<td width="150">이름</td>
		<td><input type="text" name="name" id="name_${proc }" value="${dto.name }"></td>
	</tr>
	<tr>
		<td width="150">성별</td>
		<td>
			<c:if test="${dto.gender.equals('M') }">
				<input type="radio" name="genderRadio" value="M" onclick="suntaek_gender('M');" checked="checked">남 &nbsp;&nbsp;
				<input type="radio" name="genderRadio" value="F" onclick="suntaek_gender('F');">여
				<input type="text" id="gender_${proc }" name="gender" value="M">
			</c:if> 
			<c:if test="${dto.gender.equals('F') }">
				<input type="radio" name="genderRadio" value="M" onclick="suntaek_gender('M');" >남 &nbsp;&nbsp;
				<input type="radio" name="genderRadio" value="F" onclick="suntaek_gender('F');" checked="checked">여
				<input type="text" id="gender_${proc }" name="gender" value="F">
			</c:if>
		</td>
	</tr>
	<tr>
		<td width="150">출생연도</td>
		<td><input type="text" name="bornYear" id="bornYear_${proc }" value="${dto.bornYear }"></td>
	</tr>
	<tr>
		<td width="150">이메일</td>
		<td><input type="text" name="email" id="email_${proc }" value="${dto.email }"></td>
	</tr>
	<tr>
		<td>주소</td>
		<td>
			<input type="text" name="postcode" id="sample6_postcode_${proc }" placeholder="우편번호" value="${dto.postcode }"> 
			<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"> 
			<input type="text" name="address" id="sample6_address_${proc }" placeholder="주소" value="${dto.address }">
			<br> 
			<input type="text" id="sample6_detailAddress_${proc }" placeholder="상세주소" name="detailAddress" value="${dto.detailAddress }"> 
			<input type="text" id="sample6_extraAddress_${proc }" placeholder="참고항목" name="extraAddress"  value="${dto.extraAddress }">
		</td>

	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" onclick="suntaek_proc('modifyProc','','${dto.no}');" value="수정하기">
			<input type="button" onclick="suntaek_proc('cancel','1','');" value="취소">
		</td>
	</tr>
</table>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
