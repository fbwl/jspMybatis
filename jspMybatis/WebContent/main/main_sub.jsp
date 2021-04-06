<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>
<html>
<head>
<title>title</title>
<!-- Bootstrap cdn 설정 -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- carousel를 구성할 영역 설정 -->
	<div style="width: 80%; margin: 0px;">
		<!-- carousel를 사용하기 위해서는 class에 carousel와 slide 설정한다. -->
		<!-- carousel는 특이하게 id를 설정해야 한다.-->
		<div id="carousel-example-generic" class="carousel slide">
			<!-- carousel의 지시자 -->
			<!-- 지시자라고는 하는데 ol태그의 class에 carousel-indicators를 넣는다. -->
			<ol class="carousel-indicators">
				<!-- li는 이미지 개수만큼 추가하고 data-target은 carousel id를 가르킨다. -->
				<!-- data-slide-to는 순서대로 0부터 올라가고 0은 active를 설정한다. -->
				<!-- 딱히 이 부분은 옵션별로 설정하게 없다. -->
				<li data-target="#carousel-example-generic" data-slide-to="0"
					class="active"></li>
				<li data-target="#carousel-example-generic" data-slide-to="1"></li>
				<li data-target="#carousel-example-generic" data-slide-to="2"></li>
				<li data-target="#carousel-example-generic" data-slide-to="3"></li>
			</ol>
			<!-- 실제 이미지 아이템 -->
			<!-- class는 carousel-inner로 설정하고 role은 listbox에서 설정한다. -->
			<div class="carousel-inner" role="listbox">
				<!-- 이미지의 개수만큼 item을 만든다. 중요한 포인트는 carousel-indicators의 li 태그 개수와 item의 개수는 일치해야 한다. -->
				<div class="item active">
					<!-- 아미지 설정- -->
					<img src="${path }/attach/image/member_static.jpg" alt=""
						data-animated="${path }/attach/image/member_animated.gif"
						data-static="${path }/attach/image/member_static.jpg"
						class="img-anim" style="width: 100%;">
					<!-- 캡션 설정 (생략 가능) -->
					<!-- 글자 색은 검은색 -->
					<div class="carousel-caption" style="color: black;">회원관리</div>
				</div>
				<div class="item">
					<img src="${path }/attach/image/board_static.jpg" alt=""
						data-animated="${path }/attach/image/board_animated.gif"
						data-static="${path }/attach/image/board_static.jpg"
						class="img-anim" style="width: 100%;">
					<div class="carousel-caption" style="color: black;">게시판</div>
				</div>
				<div class="item">
					<img src="${path }/attach/image/shop_static.jpg" alt=""
						data-animated="${path }/attach/image/shop_animated.gif"
						data-static="${path }/attach/image/shop_static.jpg"
						class="img-anim" style="width: 100%;">
					<div class="carousel-caption" style="color: black;">상품관리, 쇼핑몰</div>
				</div>
				<div class="item">
					<img src="${path }/attach/image/chart_static.jpg" alt=""
						data-animated="${path }/attach/image/chart_animated.gif"
						data-static="${path }/attach/image/chart_static.jpg"
						class="img-anim" style="width: 100%;">
					<div class="carousel-caption" style="color: black;">설문조사, 차트</div>
				</div>
				<div class="item">
					<img src="${path }/attach/image/email_static.jpg" alt=""
						data-animated="${path }/attach/image/email_animated.gif"
						data-static="${path }/attach/image/email_static.jpg"
						class="img-anim" style="width: 100%;">
					<div class="carousel-caption" style="color: black;">이메일 전송</div>
				</div>
			</div>
			<!-- 왼쪽 화살표 버튼 -->
			<!-- href는 carousel의 id를 가르킨다. -->
			<a class="left carousel-control" href="#carousel-example-generic"
				role="button" data-slide="prev"> <!-- 왼쪽 화살표 --> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			</a>
			<!-- 오른쪽 화살표 버튼 -->
			<!-- href는 carousel의 id를 가르킨다. -->
			<a class="right carousel-control" href="#carousel-example-generic"
				role="button" data-slide="next"> <!-- 오른쪽 화살표 --> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			</a>
		</div>
	</div>
</body>
</html>

<!-- <div style="border: 0px solid gold; float: left; width: 18%; padding-right: 10px; margin-bottom: 50px;"> -->
<%-- 	회원관리 <img src="${path }/attach/image/board_static.gif" alt="" --%>
<%-- 		data-animated="${path }/attach/image/board_animated.gif" --%>
<%-- 		data-static="${path }/attach/image/board_static.gif" class="img-anim" --%>
<!-- 		style="width: 100%;"> -->
<!-- </div> -->

<!-- <div style="border: 0px solid gold; float: left; width: 18%; padding-right: 10px; margin-bottom: 50px;"> -->
<%-- 	메모장 <img src="${path }/attach/image/board_static.gif" alt="" --%>
<%-- 		data-animated="${path }/attach/image/board_animated.gif" --%>
<%-- 		data-static="${path }/attach/image/board_static.gif" class="img-anim" --%>
<!-- 		style="height: 100%; width: 100%;"> -->
<!-- </div> -->

<!-- <div style="border: 0px solid gold; float: left; width: 18%; padding-right: 10px; margin-bottom: 50px;"> -->
<%-- 	방명록 <img src="${path }/attach/image/board_static.gif" alt="" --%>
<%-- 		data-animated="${path }/attach/image/board_animated.gif" --%>
<%-- 		data-static="${path }/attach/image/board_static.gif" class="img-anim" --%>
<!-- 		style="height: 100%; width: 100%;"> -->
<!-- </div> -->

<!-- <div style="border: 0px solid gold; float: left; width: 18%; padding-right: 10px; margin-bottom: 50px;"> -->
<%-- 	설문조사 <img src="${path }/attach/image/board_static.gif" alt="" --%>
<%-- 		data-animated="${path }/attach/image/board_animated.gif" --%>
<%-- 		data-static="${path }/attach/image/board_static.gif" class="img-anim" --%>
<!-- 		style="height: 100%; width: 100%;"> -->
<!-- </div> -->

<!-- <div style="border: 0px solid gold; float: left; width: 18%; margin-bottom: 50px;"> -->
<%-- 	설문지 <img src="${path }/attach/image/board_static.gif" alt="" --%>
<%-- 		data-animated="${path }/attach/image/board_animated.gif" --%>
<%-- 		data-static="${path }/attach/image/board_static.gif" class="img-anim" --%>
<!-- 		style="height: 100%; width: 100%;"> -->
<!-- </div> -->

<!-- <div style="border: 0px solid gold; float: left; width: 18%; padding-right: 10px"> -->
<%-- 	게시판 <img src="${path }/attach/image/board_static.gif" alt="" --%>
<%-- 		data-animated="${path }/attach/image/board_animated.gif" --%>
<%-- 		data-static="${path }/attach/image/board_static.gif" class="img-anim" --%>
<!-- 		style="height: 100%; width: 100%;"> -->
<!-- </div> -->

<!-- <div style="border: 0px solid gold; float: left; width: 18%; padding-right: 10px"> -->
<%-- 	상품관리 <img src="${path }/attach/image/board_static.gif" alt="" --%>
<%-- 		data-animated="${path }/attach/image/board_animated.gif" --%>
<%-- 		data-static="${path }/attach/image/board_static.gif" class="img-anim" --%>
<!-- 		style="height: 100%; width: 100%;"> -->
<!-- </div> -->

<!-- <div style="border: 0px solid gold; float: left; width: 18%; padding-right: 10px"> -->
<%-- 	쇼핑몰 <img src="${path }/attach/image/board_static.gif" alt="" --%>
<%-- 		data-animated="${path }/attach/image/board_animated.gif" --%>
<%-- 		data-static="${path }/attach/image/board_static.gif" class="img-anim" --%>
<!-- 		style="height: 100%; width: 100%;"> -->
<!-- </div> -->

<!-- <div style="border: 0px solid gold; float: left; width: 18%; padding-right: 10px"> -->
<%-- 	차트 <img src="${path }/attach/image/board_static.gif" alt="" --%>
<%-- 		data-animated="${path }/attach/image/board_animated.gif" --%>
<%-- 		data-static="${path }/attach/image/board_static.gif" class="img-anim" --%>
<!-- 		style="height: 100%; width: 100%;"> -->
<!-- </div> -->

<!-- <div style="border: 0px solid gold; float: left; width: 18%;"> -->
<%-- 	계산기 <img src="${path }/attach/image/board_static.gif" alt="" --%>
<%-- 		data-animated="${path }/attach/image/board_animated.gif" --%>
<%-- 		data-static="${path }/attach/image/board_static.gif" class="img-anim" --%>
<!-- 		style="height: 100%; width: 100%;"> -->
<!-- </div> -->

<script src="${path}/main/_main.js">
	
</script>