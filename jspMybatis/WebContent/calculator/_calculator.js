function num(value1) {
	var sik = $("#calcul").val();
	$("#calcul").val(sik + value1);
}
function gyesan(value1) {
	if (value1 == '+' || value1 == '-' || value1 == '/' || value1 == '*') {
		var sik = $("#calcul").val();
		var last = sik.charAt(sik.length - 1);
		if (last == '+' || last == '-' || last == '*' || last == '/') {
			var slice = sik.slice(0, -1);
			$("#calcul").val(slice);
		}
		var save = $("#calcul").val() + value1;
		$("#calcul").val(save);
	} else {
		var sik = $("#calcul").val()
		var last = sik.charAt(sik.length - 1);
		if (last == '+' || last == '-' || last == '*' || last == '/') {
			var slice = sik.slice(0, -1);
			$("#calcul").val(slice);
		}
		var save = eval($("#calcul").val());
		$("#save").text(save);
		$("#calcul").val('');
	}
	// 			if ($("#save").text()=='') {
	// 				$("#save").text(save);
	// 				$("#calcul").text('');
	// 				$("#gyesan").text(value1);
	// 			}else{
	// 				if ($("#gyesan").text()=='+') {
	// 					var save = eval($("#save").text())+eval($("#calcul").text())
	// 					$("#save").text(save);
	// 					$("#calcul").text('');
	// 					if (value1=='') {
	// 						$("#gyesan").text('');
	// 					}else{
	// 						$("#gyesan").text(value1);
	// 					}
	// 				}
	// 			}
}
function c() {
	$("#calcul").text('');
	$("#save").text('');
	$("#gyesan").text('');
}