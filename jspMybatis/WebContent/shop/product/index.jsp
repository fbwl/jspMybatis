<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp"%>

menu_gubun : ${menu_gubun }
<br>
naljaMap : ${naljaMap }
<br>
ip : ${ip }
<br>
proc :
<span id="span_proc">${proc }</span>
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

<script>
	$(document).ready(function() {
		<c:if test="${menu_gubun == 'product_index'}">
		suntaek_proc('list', '1', '');
		</c:if>
	});

	function suntaek_proc(value1, value2, value3) {
		if (value1 == "chuga") {
			$("#span_no").text("");
		} else if (value1 == "chugaProc") {

		} else if (value1 == "list") {
			$("#span_no").text("");
		} else if (value1 == "view") {

		} else if (value1 == "modify") {

		} else if (value1 == "modifyProc") {

		} else if (value1 == "delete") {
			if (confirm("삭제")) {

			} else {
				suntaek_proc('view','',value3);
				return;
			}
		}

		if (value1 != '') {
			$("#span_proc").text(value1);
		}
		if (value2 != '') {
			$("#span_pageNumber").text(value2);
		}
		if (value3 != '') {
			$("#span_no").text(value3);
		}

		goPage(value1);
	}

	function goPage(value1) {
		var param = {};
		var process_data;
		var content_type;
		var url = "${path}/product_servlet/" + value1 + ".do";

		if (value1 == "chuga") {
			param = {}
		} else if (value1 == "chugaProc" || value1 == 'modifyProc') {
			process_data = false;
			content_type = false;

			param = new FormData();

			param.append("no", $("#span_no").text());
			param.append("name", $("#name").val());
			param.append("price", $("#price").val());
			param.append("description", $("#description").val());

			// 			console.log($('input[name=file]')[0].files[0]);
			// 			console.log($('input[name=file]')[1].files[0]);
			// 			console.log($('input[name=file]')[2].files[0]);
			// 			return;

			var file_counter = parseInt($('input[name="file"]').length);
			for (i = 0; i < file_counter; i++) {
				param.append(i, $('input[name="file"]')[i].files[0]);
			}
		} else if (value1 == 'list') {
			process_data = false;
			content_type = false;

			param = new FormData();

			param.append("pageNumber", $("#span_pageNumber").text());
			param.append("search_option", $("#span_search_option").text());
			param.append("search_data", $("#span_search_data").text());
		} else if (value1 == 'view') {
			param.no = $("#span_no").text();
		} else if (value1 == 'modify') {
			param.no = $("#span_no").text();
		} else if (value1 == 'delete') {
			param.no = $("#span_no").text();
		}
		$.ajax({
			type : "post",
			data : param,
			processData : process_data,
			contentType : content_type,
			url : url,
			success : function(data) {
				$("#result").html(data);
				if (value1 == "chugaProc" || value1 == 'modifyProc'
						|| value1 == 'delete') {
					suntaek_proc('list', '1', '');
				}
			}
		});
	}
</script>