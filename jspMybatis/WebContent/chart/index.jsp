<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp"%>
<div style="display: none;">
	menu_gubun : <span id="span_menu_gubun">${menu_gubun }</span><br>
	naljaMap : ${naljaMap }<br>
	ip : ${ip }<br>
	proc :<span id="span_proc">${proc }</span><br>
	path :<span id="span_path">${path }</span><br>
	list_gubun : <span id="span_list_gubun">${list_gubun }</span><br>
	pageNumber : <span id="span_pageNumber">${pageNumber }</span><br>
	no : <span id="span_no">${no }</span><br> 
</div>
<!-- 구글 차트 라이브러리 로딩 -->
<script src="https://www.google.com/jsapi">
	
</script>
<div id="result2"></div>

<script src="${path}/chart/_chart.js"></script>