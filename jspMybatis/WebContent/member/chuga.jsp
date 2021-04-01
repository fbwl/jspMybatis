<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>

<h2>회원가입</h2>
<form name="DirForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td width="150">아이디</td>
			<td><input type="text" name="id_${proc }" id="id_${proc }" value="">
				<button type="button" onclick="id_check_div();">아이디찾기</button>
				<button type="button" onclick="id_check_win();">아이디찾기(새창)</button>
				<div style="display: none;">
				<input type="text" name="idChk_${proc}" id="idChk_${proc}" value="">
				</div>
				<br><span id="label_id"></span>
			</td>
		</tr>
		<tr>
			<td width="150">비밀번호</td>
			<td><input type="password" name="passwd_${proc}" id="passwd_${proc}" value=""></td>
		</tr>
		<tr>
			<td width="150">비밀번호 확인</td>
			<td><input type="password" name="passwdChk_${proc}" id="passwdChk_${proc}" value=""></td>
		</tr>
		<tr>
			<td width="150">이름</td>
			<td><input type="text" name="name_${proc}" id="name_${proc}" value=""></td>
		</tr>
		<tr>
			<td width="150">성별</td>
			<td>
				<input type="radio" name="genderRadio" value="M" onclick="suntaek_gender('M');" checked="checked">남 &nbsp;&nbsp;
				<input type="radio" name="genderRadio" value="F" onclick="suntaek_gender('F');">여
				<div style="display: none;">
				<input type="text" id="gender_${proc}" name="gender_${proc}" value="M">
				</div>
			</td>
		</tr>
		<tr>
			<td width="150">생년월일(ex:19980101)</td>
			<td><input type="text" name="bornYear_${proc}" id="bornYear_${proc}" value=""></td>
		</tr>
		<tr>
			<td width="150">이메일</td>
			<td><input type="text" name="email_${proc}" id="email_${proc}" value=""></td>
		</tr>
		<tr>
			<td>주소</td>
			<td>
				<input type="text" name="postcode" id="sample6_postcode_${proc}" placeholder="우편번호"> 
				<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"> 
				<input type="text" name="address" id="sample6_address_${proc}" placeholder="주소"><br> 
				<input type="text" id="sample6_detailAddress_${proc}" placeholder="상세주소" name="detailAddress"> 
				<input type="text" id="sample6_extraAddress_${proc}" placeholder="참고항목" name="extraAddress">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="suntaek_proc('chugaProc','','');">등록하기</button>
				<button type="button" onclick="suntaek_proc('list','1','');">목록으로</button>
			</td>
		</tr>
	</table>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</form>