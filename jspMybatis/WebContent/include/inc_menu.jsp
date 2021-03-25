<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

	<c:set var="gubun"
		value="${fn:substring(menu_gubun,0,fn:indexOf(menu_gubun,'_')) }" />
	${gubun }
	<table border="0" align="center">
		<tr>
			<td colspan="15" align="right" style="padding: 5px 20px 10px;">
				<c:if test="${sessionScope.cookNo==null || sessionScope.cookNo==0 }">
					<a href="${path }/member_servlet/indexL.do">로그인</a>&nbsp;&nbsp;
					<a href="${path }/member_servlet/indexC.do">회원가입</a>
				</c:if> <c:if
					test="${sessionScope.cookNo != null && sessionScope.cookNo > 0 }">
	${sessionScope.cookName } 님 환영합니다.
            <a href="${path}/member_servlet/indexM.do">[회원정보 수정]</a>
            [회원탈퇴]
            <a href="${path }/member_servlet/logout.do">[로그아웃]</a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td style="padding: 0px 20px;" id="home"><a href="${path }/index.do">HOME</a></td>
			<td style="padding: 0px 20px;" id="member"><a href="${path }/member_servlet/index.do">회원관리</a></td>
			<td style="padding: 0px 20px;" id="memo"><a href="${path }/memo_servlet/index.do">메모장</a></td>
			<td style="padding: 0px 20px;" id="guestbook"><a href="${path }/guestbook_servlet/index.do">방명록</a></td>
			<td style="padding: 0px 20px;" id="survey"><a href="${path }/survey_servlet/index.do">설문조사</a></td>
			<td style="padding: 0px 20px;" id="survey2"><a href="${path }/survey_servlet/index2.do">문제은행</a></td>
			<td style="padding: 0px 20px;" id="board"><a href="${path }/board_servlet/index.do">게시판</a></td>
			<td style="padding: 0px 20px;" id="product"><a href="${path }/product_servlet/index.do">Mall(상품관리)</a></td>
			<td style="padding: 0px 20px;" id="mall"><a	href="${path }/mall_servlet/index.do">Mall(쇼핑몰)</a></td>
			<td style="padding: 0px 20px;" id="chart"><a href="${path }/chart_servlet/index.do">Chart</a></td>
			<td style="padding: 0px 20px;" id="smtpEmail"><a href="${path }/email_servlet/index.do">Email</a></td>
			<td style="padding: 0px 20px;" id=""><a href="#">관리자</a></td>
		</tr>
	</table>
	<c:choose>
		<c:when test="${gubun=='index' }">
			<script>
				$("#home").css("background-color", "silver");
			</script>
		</c:when>
		<c:when test="${gubun=='member' }">
			<script>
				$("#member").css("background-color", "silver");
			</script>
		</c:when>
		<c:when test="${gubun=='memo' }">
			<script>
				$("#memo").css("background-color", "silver");
			</script>
		</c:when>
		<c:when test="${gubun=='guestbook' }">
			<script>
				$("#guestbook").css("background-color", "silver");
			</script>
		</c:when>
		<c:when test="${gubun=='survey' }">
			<script>
				$("#survey").css("background-color", "silver");
			</script>
		</c:when>
		<c:when test="${gubun=='survey2' }">
			<script>
				$("#survey2").css("background-color", "silver");
			</script>
		</c:when>
		<c:when test="${gubun=='board' }">
			<script>
				$("#board").css("background-color", "silver");
			</script>
		</c:when>
		<c:when test="${gubun=='chart' }">
			<script>
				$("#chart").css("background-color", "silver");
			</script>
		</c:when>
		<c:when test="${gubun=='smtpEmail' }">
			<script>
				$("#smtpEmail").css("background-color", "silver");
			</script>
		</c:when>
	</c:choose>
</body>
</html>