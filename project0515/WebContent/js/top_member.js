// 	아이디와 비밀번호가 맞지 않을 경우 가입버튼 비활성화를 위한 변수설정
var idCheck = 0;
var pwdCheck = 0;
// 아이디 체크하여 가입버튼 비활성화, 중복확인.
function checkId() {
	var inputed = $('.id').val();
	$.ajax({
		data : {
			id : inputed
		},
		url : "checkId.jy",
		success : function(data) {
			if (inputed == "" && data == '0') {
				$(".signupbtn").prop("disabled", true);
				$(".signupbtn").css("background-color", "#aaaaaa");
				$("#checkaa").css("background-color", "#FFCECE");
				idCheck = 0;
			} else if (data == '0') {
				$("#checkaa").css("background-color", "#B0F6AC");
				idCheck = 1;
				if (idCheck == 1 && pwdCheck == 1) {
					$(".signupbtn").prop("disabled", false);
					$(".signupbtn").css("background-color", "#4CAF50");
					signupCheck();
				}
			} else if (data == '1') {
				$(".signupbtn").prop("disabled", true);
				$(".signupbtn").css("background-color", "#aaaaaa");
				$("#checkaa").css("background-color", "#FFCECE");
				idCheck = 0;
			}
		}
	});
}
// 재입력 비밀번호 체크하여 가입버튼 비활성화 또는 맞지않음을 알림.
function checkPwd() {
	var inputed = $('.pass').val();
	var reinputed = $('#repwd').val();
	if (reinputed == "" && (inputed != reinputed || inputed == reinputed)) {
		$(".signupbtn").prop("disabled", true);
		$(".signupbtn").css("background-color", "#aaaaaa");
		$("#repwd").css("background-color", "#FFCECE");
	} else if (inputed == reinputed) {
		$("#repwd").css("background-color", "#B0F6AC");
		pwdCheck = 1;
		if (idCheck == 1 && pwdCheck == 1) {
			$(".signupbtn").prop("disabled", false);
			$(".signupbtn").css("background-color", "#4CAF50");
			signupCheck();
		}
	} else if (inputed != reinputed) {
		pwdCheck = 0;
		$(".signupbtn").prop("disabled", true);
		$(".signupbtn").css("background-color", "#aaaaaa");
		$("#repwd").css("background-color", "#FFCECE");

	}
}
// 닉네임과 이메일 입력하지 않았을 경우 가입버튼 비활성화
function signupCheck() {
	var nickname = $("#nickname").val();
	var email = $("#email").val();
	if (nickname == "" || email == "") {
		$(".signupbtn").prop("disabled", true);
		$(".signupbtn").css("background-color", "#aaaaaa");
	} else {
	}
}
// 캔슬버튼 눌렀을 눌렀을시 인풋박스 클리어
$(".cancelbtn").click(function() {
	$(".id").val(null);
	$(".pass").val('');
	$(".signupbtn").prop("disabled", true);
	$(".signupbtn").css("background-color", "#aaaaaa");
});