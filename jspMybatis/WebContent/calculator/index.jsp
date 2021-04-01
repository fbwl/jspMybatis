<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/inc_header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td colspan="10"><input id="calcul"
				style="width: 279px; height: 100px; text-align: right;"></td>
		</tr>
		<tr>
			<td colspan="10"><span id="save" style="text-align: right;">결과</span></td>
		</tr>
		<tr align="center">
			<td id="7" onclick="num('7');" height="50px" width="70px">7</td>
			<td id="8" onclick="num('8');" width="70px">8</td>
			<td id="9" onclick="num('9');" width="70px">9</td>
			<td id="+" onclick="gyesan('+');" width="70px">+</td>
		</tr>
		<tr align="center">
			<td id="4" onclick="num('4');" height="50px">4</td>
			<td id="5" onclick="num('5');">5</td>
			<td id="6" onclick="num('6');">6</td>
			<td id="-" onclick="gyesan('-');">-</td>
		</tr>
		<tr align="center">
			<td id="1" onclick="num('1');" height="50px">1</td>
			<td id="2" onclick="num('2');">2</td>
			<td id="3" onclick="num('3');">3</td>
			<td id="*" onclick="gyesan('*');">*</td>
		</tr>
		<tr align="center">
			<td id="c" onclick="c();" height="50px">C</td>
			<td id="0" onclick="num('0');">0</td>
			<td id="=" onclick="gyesan('=');">=</td>
			<td id="/" onclick="gyesan('/');">/</td>
		</tr>
		<tr align="center">
			<td id="c" onclick="gyesan('ce');" height="50px">CE</td>
			<td id="0" onclick="num('0');">0</td>
			<td id="=" onclick="gyesan('=');">=</td>
			<td id="/" onclick="gyesan('/');">/</td>
		</tr>
	</table>
</body>
<script src="${path }/calculator/_calculator.js">
</script>
</html>