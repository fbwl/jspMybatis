<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp"%>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<form name="DirForm">
	<h2>아이디 검색</h2>
	<table border="1" align="center" width="550">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" id="id" value="${id }">
			<span id="spanMsg"></span>
			<br>
			<input type="text" name="result" id="result" value="" style="width: 100px;">
		</tr>
		<tr>
			<td colspan="2" align="center" style="height: 50px;">
				<button type="button" onclick="search();">검색</button>
				<span id="spanView" style="display: none;">
					<button onclick="save();">적용</button>
				</span>
			</td>
		</tr>
	</table>
</form>
<script>
	function search() {
		var id = $("#id").val();
		
		if (id=='') {
			alert('아이디 입력');
			$("#id").focus();
			return;
		}
		
		var url = "${path}/member_servlet/id_check.do";
		$.ajax({
			type : "post",
			data : $('form').serialize(),
			url : url,
			success : function(data) {
				$("#id").val(id);
				$("#result").val(data);
				
				if ($("#result").val().length > 0) {
					$("#spanMsg").html("사용 가능");
					$("#spanMsg").css('color','blue');
					$("#spanMsg").css('font-size','7px');
					$("#spanView").show();
				}else {
					$("#spanMsg").html("ID 중복");
					$("#spanMsg").css('color','gray');
					$("#spanMsg").css('font-size','7px');
					$("#spanView").hide();
				}
			}
		});
		
// 		DirForm.method = "post";
// 		DirForm.action = "${path}/member_servlet/id_check_win_open_Proc.do";
// 		DirForm.submit();
	}
	
	function save() {
		var id = $("#id").val();
		var result = $("#result").val();
		if (id==result && id.length>0 && result.length) {
			var id = document.getElementById("id").value;
			opener.document.getElementById("id").value=id;
			window.close();
		} else {
			alert('다시 검색');
			$("#id").val('');
			$("#result").val('');
			$("#id").focus();
		}
	}
</script>