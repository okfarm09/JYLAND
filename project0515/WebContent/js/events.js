//채팅 아이콘 이벤트
$(".chat").on({
	"click" : function() {
		if ($(this).attr("src") == "./img/chat.png") {
			$(".chat").attr("src", "./img/chathide.png");
			$("#_chatbox").css("display", "block");
		} else if ($(this).attr("src") == "./img/chathide.png") {
			$(".chat").attr("src", "./img/chat.png");
			$("#_chatbox").css("display", "none");
		}
	}
});