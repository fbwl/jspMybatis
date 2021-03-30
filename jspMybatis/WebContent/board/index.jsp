<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>
<div style="display: none;">
menu_gubun : ${menu_gubun }
<br>
naljaMap : ${naljaMap }
<br>
ip : ${ip }
<br>
path :
<span id="span_path">${path }</span>
<br>
tbl :
<span id="span_tbl">${tbl }</span>
<br>
proc :
<span id="span_proc">${proc }</span>
<br>
passwd :
<span id="span_passwd"></span>
<br>
pageNumber :
<span id="span_pageNumber">${pageNumber }</span>
<br>
no :
<span id="span_no">${no }</span>
<br>
comment_no :
<span id="span_comment_no">${comment_no }</span>
<br>
search_option :
<span id="span_search_option">${search_option }</span>
<br>
search_data :
<span id="span_search_data">${search_data }</span>
<br>
commentPageNumber :
<span id="span_commentPageNumber">${commentPageNumber }</span>
<br>
</div>
<div id="result"></div>

<script src="${path}/board/_board.js">
	
</script>