<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp"%>

<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2"><h2>상품수정</h2></td>
	</tr>
	<tr>
		<td style="align: center;">상품명</td>
		<td><input type="text" name="name" id="name" value="${dto.name }"></td>
	</tr>
	<tr>
		<td style="align: center;">가격</td>
		<td><input type="text" name="price" id="price" value="${dto.price }"></td>
	</tr>
	<tr>
		<td style="align: center;">상품설명</td>
		<td><textarea name="description" id="description" style="width: 300px; height: 100px;" wrap="hard">${dto.description }</textarea></td>
	</tr>
	<tr>
		<td style="align: center;">현재이미지</td>
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
		<td style="align: center;">상품이미지</td>
		<td>
			<input type="file" name="file">
			<input type="file" name="file">
			<input type="file" name="file">
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2" height="50px">
			<button type="button" id="btnModify" onclick="suntaek_proc('modifyProc','0','${dto.no}');">수정하기</button>
			<button type="button" id="btnList" onclick="suntaek_proc('list','1','');">목록으로</button>
		</td>
	</tr>
</table>
