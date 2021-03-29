var path = $("#span_path").text();

$(document).ready(function() {
	if ($("#span_menu_gubun").text() == 'mall_index') {
		suntaek_proc('mall_list', '1', '');
	}
});

function suntaek_proc(value1, value2, value3) {
	$("#span_proc").text(value1);
	if (value2 != "0") {
		$("#span_pageNumber").text(value2);
	}
	if (value3 != "0") {
		$("#span_no").text(value3);
	}
	goPage(value1);
}

function search() {
	$("#span_search_option").text($("#search_option").val());
	$("#span_search_data").text($("#search_data").val());
	suntaek_proc('mall_list', '1', '');
}

//function suntaek_proc(value1, value2, value3) {
//	if (value1 == "mall_list") {
//		$("#span_proc").text(value1);
//		$("#span_no").text("");
//	} else if (value1 == "mall_view") {
//		$("#span_proc").text(value1);
//		$("#span_no").text(value3);
//	} else if (value1 == "mall_search") {
//		value1 = 'mall_list';
//		$("#span_proc").text(value1);
//		$("#span_no").text("");
//		$("#span_search_option").text($("#search_option").val());
//		$("#span_search_data").text($("#search_data").val());
//	} else if (value1 == "cart_insertProc") {
//		$("#span_amount").text($("#amount option:selected").val());
//		$("#span_proc").text(value1);
//	} else if (value1 == "cartList") {
//		$("#span_proc").text(value1);
//		$("#span_no").text("");
//		$("#span_amount").text("");
//	} else if (value1 == "cart_view") {
//		value1 = 'mall_view';
//		$("#span_proc").text(value1);
//		$("#span_no").text(value3);
//	} else if (value1 == "cart_clear") {
//		if (confirm("장바구니 비우기")) {
//			$("#span_proc").text(value1);
//			$("#span_no").text("");
//			$("#span_amount").text("");
//		} else {
//			return;
//		}
//	}
//
//	if (value2 != '') {
//		$("#span_pageNumber").text(value2);
//	}
//	goPage(value1);
//}

function chk_cart() {
	if ($("#checkAll").prop("checked")) {
		$("input[name=chk]").prop("checked", true);
	} else {
		$("input[name=chk]").prop("checked", false);
	}
};

function goPage(value1) {
	var param = {};
	var url = $("#span_path").text() + "/mall_servlet/" + value1 + ".do";

	if (value1 == "mall_list") {
		param.pageNumber = $("#span_pageNumber").text();
		param.search_option = $("#span_search_option").text();
		param.search_data = $("#span_search_data").text();
	} else if (value1 == "mall_view") {
		param.no = $("#span_no").text();
		param.search_option = $("#span_search_option").text();
		param.search_data = $("#span_search_data").text();
	} else if (value1 == "cart_insertProc") {
		$("#span_amount").text($("#amount option:selected").val());
		param.productNo = $("#span_no").text();
		param.amount = $("#span_amount").text();
	} else if (value1 == "cartList") {
		$("#span_amount").text("");
		param.pageNumber = $("#span_pageNumber").text();
		// 			param.search_option = $("#span_search_option").text();
		// 			param.search_data = $("#span_search_data").text();
	} else if (value1 == "cart_clear") {
		if (confirm("장바구니 비우기")) {
			$("#span_amount").text("");
			var chk_no = '';
			$('input[name=chk]:checked').each(function(index) {
				if (index != 0) {
					chk_no += ',';
				}
				chk_no += $(this).val();
			});
			alert(chk_no);
			param.chk_no = chk_no;
		} else {
			return;
		}
	}

	$.ajax({
		type: "post",
		data: param,
		url: url,
		success: function(data) {
			if (value1 == "cart_insertProc") {
				if (confirm("장바구니로 이동")) {
					suntaek_proc("cartList", '1', '');
				} else {
					$("#span_amount").text('');
					suntaek_proc('mall_view', '', $("#span_no").text());
				}
			} else if (value1 == 'cart_clear') {
				value1 = 'cartList';
				suntaek_proc(value1, '1', '');
			} else if (value1 == 'modifyProc') {
				alert("modifyProc");
				suntaek_proc('mall_view', '', $("#span_no").text());
			} else {
				$("#result").html(data);
			}
		}
	});
}