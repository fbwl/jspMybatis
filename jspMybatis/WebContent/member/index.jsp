<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>

menu_gubun :
<span id="span_menu_gubun">${menu_gubun }</span>
<br>
naljaMap : ${naljaMap }
<br>
ip : ${ip }
<br>
proc :
<span id="span_proc">${proc }</span>
<br>
path :
<span id="span_path">${path }</span>
<br>
pageNumber :
<span id="span_pageNumber">${pageNumber }</span>
<br>
no :
<span id="span_no">${no }</span>
<br>
search_option :
<span id="span_search_option">${search_option }</span>
<br>
search_data :
<span id="span_search_data">${search_data }</span>
<br>

<div id="result"></div>

<script src="${path}/member/_member.js">
	
</script>