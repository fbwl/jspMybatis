<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp"%>

<table border="0" align="center" width="80%">
	<tr>
		<td colspan="7">
			<h2>장바구니 목록</h2>
		</td>
	</tr>
	<tr>
		<td style="padding: 10px 0px 5px;">전체 ${totalRecord }건입니다.</td>
	</tr>
<c:if test="${totalRecord==0 }">
	<tr>
		<td height="200">
			<table border="1" align="center" style="width: 100%; height: 200px;">
				<tr>
					<td align="center">장바구니가 비어있습니다.</td>
				</tr>
			</table>
		</td>
	</tr>
</c:if>
<c:if test="${totalRecord>0 }">
	<tr>
		<td style="padding: 0 0 20px 0;">
			<table border="1" align="center" style="width: 100%;">
				<tr>
					<td><input type="checkbox" id="checkAll" name="checkAll" onclick="chk_cart()"></td>
					<td>상품사진</td>
					<td>상품명</td>
					<td>가격</td>
					<td>구매수량</td>
					<td>금액</td>
					<td>장바구니등록일</td>
					<td></td>
				</tr>
				<c:set var="sum_money" value="0"/>
				<c:set var="i" value="1"></c:set>
				<c:forEach var="dto" items="${list }">
					<span id="b_${dto.no }" style="display: none;">${dto.memberNo },${dto.productNo },${dto.amount }</span>
					<tr>
						<td><input type="checkbox" id="chk" name="chk" value="${dto.no }"></td>
						<td>
							<c:choose>
								<c:when test="${dto.product_img == '-,-,-' }">
									<a href="#" onclick="suntaek_proc('view','','${dto.no}');" >이미지X</a>
								</c:when>
								<c:otherwise>
									<c:set var="temp1" value="${fn:split(dto.product_img,',')[0] }"></c:set>
									<c:set var="temp2" value="${fn:split(temp1,'|')[0] }"></c:set>
									<c:set var="temp3" value="${fn:split(temp1,'|')[1] }"></c:set>
									<a href="#" onclick="suntaek_proc('mall_view','','${dto.productNo}');" >
									<img src="${path }/attach/product_img/${temp3}" alt="${dto.product_name}" 
									title="${dto.product_name }" style="width: 50px;" height="50px;"></a>
								</c:otherwise>
							</c:choose>
						
						</td>
						<td>${dto.product_name }</td>
						<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.product_price}"></fmt:formatNumber></td>
						<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.amount}"></fmt:formatNumber></td>
						<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.buy_money}"></fmt:formatNumber></td>
						<td>${dto.regi_date}</td>
					</tr>
					<c:set var="i" value="${i = i + 1}"></c:set>
					<c:set var="sum_money" value="${sum_money = sum_money + dto.buy_money}"></c:set>
					<c:set var="number" value="${number = number - 1 }"></c:set>
				</c:forEach>
				<tr>
					<td colspan="10" align="right" >합계금액 : <fmt:formatNumber type="number" maxFractionDigits="3" value="${sum_money}"/>원</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="7" height="50" align="center">
			<c:set var="tempgubun" value="cartList"></c:set>
			<c:if test="${totalRecord > 0 }">
				<tr>
					<td colspan="4" align="center">
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
		</td>
	</tr>
</c:if>
<tr>
	<td colspan="7" height="50" align="right">
		<button type="button" onclick="suntaek_proc('cart_clear','','');">선택 상품 삭제</button>
		<button type="button" onclick="suntaek_proc('list','','');">쇼핑하기</button>
<!-- 		<button type="button" onclick="suntaek_proc('cartList','','');">장바구니</button> -->
		<button type="button" onclick="suntaek_proc('buy','','');">주문하기</button>
	</td>
</table>

<script>
	
</script>