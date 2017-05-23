function chack_pwd() {
	if ($("#_npwd").val() != $("#_npwd2").val()) {
		$("#_npwd2").val("");
		$("#_npwd2").attr("placeholder", "Wrong You Morron!");
	} else {
		$("#_password_check").css("display", "block");
	}
}
function change_info(upwd) {
	var rpwdd = $("#_rpwd").val();
	var rpwd="";
	$.ajax({
		url : "checkpass.jy",
		method : "POST",
		data : {
			rpwd : rpwdd
		},
		success : function(data) {
			rpwd=data;
			if ((upwd + "") != data) {
				$("#_rpwd").css("background-color", "#FFCECE");
				$("#_rpwd").attr("placeholder", "Retry You Morron!");
				$("#_rpwd").val("");
			} else if ((upwd + "") == data) {
				$("#_rpwd_2").attr("value", (upwd + ""));
				$("#_info_form").attr("action", "infochangeAf.jy").submit();
			}
		}
	});
}
function clear_rwpd() {
	$("#_rpwd").css("background-color", "#FFFFFF");
}
function check_color() {
	if ($("#_npwd").val() != $("#_npwd2").val()) {
		$("#_npwd2").css("background-color", "#FFCECE");
	} else {
		$("#_npwd2").css("background-color", "#B0F6AC");
	}
}