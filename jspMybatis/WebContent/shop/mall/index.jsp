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
amount :
<span id="span_amount">${amount }</span>
<br>

<div id="result"></div>

<script type="text/javascript">
	$(document).ready(function() {
		<c:if test="${menu_gubun == 'mall_index'}">
		suntaek_proc('mall_list', '1', '');
		</c:if>
	});

	function suntaek_proc(value1, value2, value3) {
		if (value1 == "mall_list") {
			$("#span_proc").text(value1);
			$("#span_no").text("");
		} else if (value1 == "mall_view") {
			$("#span_proc").text(value1);
			$("#span_no").text(value3);
		} else if (value1 == "mall_search") {
			value1 = 'mall_list';
			$("#span_proc").text(value1);
			$("#span_no").text("");
			$("#span_search_option").text($("#search_option").val());
			$("#span_search_data").text($("#search_data").val());
		} else if (value1 == "cart_insertProc") {
			$("#span_amount").text($("#amount option:selected").val());
			$("#span_proc").text(value1);
		} else if (value1 == "cartList") {
			$("#span_proc").text(value1);
			$("#span_no").text("");
			$("#span_amount").text("");
		} else if (value1 == "cart_view") {
			value1 = 'mall_view';
			$("#span_proc").text(value1);
			$("#span_no").text(value3);
		} else if (value1 == "cart_clear") {
			if (confirm("장바구니 비우기")) {
				$("#span_proc").text(value1);
				$("#span_no").text("");
				$("#span_amount").text("");
			} else {
				return;
			}
		}

		if (value2 != '') {
			$("#span_pageNumber").text(value2);
		}
		goPage(value1);
	}

	function goPage(value1) {
		var param = {};
		var url = "${path}/mall_servlet/" + value1 + ".do";
		var no = $("#span_no").text();

		if (value1 == "mall_list") {
			param.pageNumber = $("#span_pageNumber").text();
			param.search_option = $("#span_search_option").text();
			param.search_data = $("#span_search_data").text();
		} else if (value1 == "mall_view") {
			param.no = $("#span_no").text();
			param.search_option = $("#span_search_option").text();
			param.search_data = $("#span_search_data").text();
		} else if (value1 == "cart_insertProc") {
			param.productNo = $("#span_no").text();
			param.amount = $("#span_amount").text();
		} else if (value1 == "cartList") {
			param.pageNumber = $("#span_pageNumber").text();
			// 			param.search_option = $("#span_search_option").text();
			// 			param.search_data = $("#span_search_data").text();
		} else if (value1 == "cart_clear") {
			var chk_no = '';
			$('input[name=chk]:checked').each(function(index) {
				if (index != 0) {
					chk_no += ',';
				}
				chk_no += $(this).val();
			});
			alert(chk_no);
			param.chk_no = chk_no;
		}

		$.ajax({
			type : "post",
			data : param,
			url : url,
			success : function(data) {
				if (value1 == "cart_insertProc") {
					if (confirm("장바구니로 이동")) {
						suntaek_proc("cartList", '1', '');
					} else {
						suntaek_proc("mall_view", '', no);
					}
				} else if (value1 == 'cart_clear') {
					value1 = 'cartList';
					suntaek_proc(value1, '1', '');
				} else if (value1 == 'modifyProc') {
					alert("modifyProc");
					suntaek_proc('view', '', $("#span_no").text());
				} else {
					$("#result").html(data);
				}
			}
		});
	}
</script>
