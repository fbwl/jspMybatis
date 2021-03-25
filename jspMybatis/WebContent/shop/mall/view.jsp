<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../../include/inc_header.jsp"%>
<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2">
			<h1>상품상세보기</h1>
			<input type="text" name="no" value="${dto.no }">
		</td>
	</tr>
	<tr>
		<td width="150">상품명 : </td>
		<td>${dto.name }</td>
	</tr>
	<tr>
		<td>가격 : </td>
		<td>${dto.price }</td>
	</tr>
	<tr>
		<td>상세설명 : </td>
		<td>${dto.description }</td>
	</tr>
	<tr>
		<td>상품사진 : </td>
		<td>
			<c:choose>
				<c:when test="${dto.product_img == '-,-,-' }">
					<a href="#" onclick="suntaek_proc('view','','${dto.no}');" >이미지X</a>
				</c:when>
				<c:otherwise>
					<c:set var="temp1" value="${fn:split(dto.product_img,',')[0] }"></c:set>
					<c:set var="temp2" value="${fn:split(temp1,'|')[0] }"></c:set>
					<c:set var="temp3" value="${fn:split(temp1,'|')[1] }"></c:set>
					<img src="${path }/attach/product_img/${temp3}" alt="${dto.name}" 
					title="${dto.name }" style="width: 50px;" height="50px;">
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td>등록일 : </td>
		<td>${dto.regi_date }</td>
	</tr>
	<tr>
		<td>장바구니수량 : </td>
		<td>
			<select id="amount">
				<c:forEach var="i" begin="1" end="10" step="1">
					<option value="${i }">${i }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<button type="button" onclick="suntaek_proc('cart_insertProc', '', '${dto.no}');">장바구니담기</button>
			<button type="button" onclick="suntaek_proc('buyNow', '', '${dto.no}');">바로구매</button>
			<button type="button" onclick="suntaek_proc('list', '1', '');">목록으로</button>
		</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td colspan="2" height="50px"> -->
<!-- 			<table border="1" width="100%" align="center"> -->
<!-- 				<tr> -->
<!-- 					<td width="100px">이전상품 : </td> -->
<!-- 					<td> -->
<%-- 						<c:if test="${dto.preName == null }"> --%>
<!-- 							이전상품이 없습니다. -->
<%-- 						</c:if> --%>
<%-- 						<c:if test="${dto.preName != null }"> --%>
<%-- 							<a href="#" onclick="GoPage('view','${dto.preNo}');">${dto.preName }</a> --%>
<%-- 						</c:if> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td width="100px">다음상품 : </td> -->
<!-- 					<td> -->
<%-- 						<c:if test="${dto.nxtName == null }"> --%>
<!-- 							다음상품이 없습니다. -->
<%-- 						</c:if> --%>
<%-- 						<c:if test="${dto.nxtName != null }"> --%>
<%-- 							<a href="#" onclick="GoPage('view','${dto.nxtNo}');">${dto.nxtName }</a> --%>
<%-- 						</c:if> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 		</td> -->
<!-- 	</tr> -->
</table>

<script>
</script>