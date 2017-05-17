<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>

<body>
	<!-- 로그인한 상태일 경우와 비로그인 상태일 경우의 chat_id설정 -->
	<c:if test="${(login.id ne '') and !(empty login.id)}">
		<input type="hidden" value='${login.id }' id='chat_id' />
	</c:if>
	<c:if test="${(login.id eq '') or (empty login.id)}">
		<input type="hidden" value='<%=session.getId().substring(0, 6)%>'
			id='chat_id' />
	</c:if>
	<!-- 	채팅창 -->
	<div id="_chatbox" style="display: none">
		<fieldset>
			<div id="messageWindow"></div>
			<br /> <input id="inputMessage" type="text"/>
			<input type="submit" value="send" onclick="send()" />
		</fieldset>
	</div>
	<img class="chat" src="./img/chat.png" />
</body>
<!-- 아이콘 클릭시 채팅창 열고 닫기 -->
<script>
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
</script>
<script type="text/javascript">
	var textarea = document.getElementById("messageWindow");
	var webSocket = new WebSocket('ws://localhost:8090/project0515/broadcasting');
	var inputMessage = document.getElementById('inputMessage');
	webSocket.onerror = function(event) {
		onError(event)
	};
	webSocket.onopen = function(event) {
		onOpen(event)
	};
	webSocket.onmessage = function(event) {
		onMessage(event)
	};
	function onMessage(event) {
		var message = event.data.split("|");
		var sender = message[0];
		var content = message[1];
		if (content == "") {
			console.log(content + "aaaaaa");
		} else {
			var id_true=false;
			$.ajax({
				url:"getalluserlist.jy",
				success: function(data) {
					$.each(data, function(index, val) {
						if(sender==val.id) {
							id_true=true;
							return;
						}
					});
					if(id_true==false) {
						if (content.includes("/")) {
							if (content.includes(("/" + $("#chat_id").val()))) {
								var temp = content.replace("/" + $("#chat_id").val(), "(귓속말) :").split(":");
								if (temp[1].trim() == "") {
								} else {
									$("#messageWindow").html($("#messageWindow").html() + "<p class='whisper'>"
										+ sender + content.replace("/" + $("#chat_id").val(), "(귓속말) :") + "</p>");
								}
							} else {
							}
						} else {
							if (content.includes("!")) {
								$("#messageWindow").html($("#messageWindow").html()
									+ "<p class='chat_content'><b class='impress'>" + sender + " : " + content + "</b></p>");
							} else {
								$("#messageWindow").html($("#messageWindow").html()
									+ "<p class='chat_content' style='color:#"+sender+"'>" + sender + " : " + content + "</p>");
							}
						}
					}else {
						if (content.includes("/")) {
							if (content.includes(("/" + $("#chat_id").val()))) {
								var temp = content.replace("/" + $("#chat_id").val(), "(귓속말) :").split(":");
								if (temp[1].trim() == "") {
								} else {
									$("#messageWindow").html($("#messageWindow").html() + "<p class='whisper'>"
										+ sender+"(회원)" + content.replace("/" + $("#chat_id").val(), "(귓속말) :") + "</p>");
								}
							} else {
							}
						} else {
							if (content.includes("!")) {
								$("#messageWindow").html($("#messageWindow").html()
									+ "<p class='chat_content'><b class='impress'>" + sender+"(회원)" + " : " + content + "</b></p>");
							} else {
								$("#messageWindow").html($("#messageWindow").html()
									+ "<p class='chat_content' >" + sender+"(회원)" + " : " + content + "</p>");
							}
						}
					}
				}
			});
		}
		var elem = document.getElementById('messageWindow');
		elem.scrollTop = elem.scrollHeight;
	}
	function onOpen(event) {
		$("#messageWindow").html("<p class='chat_content'>채팅에 참여하였습니다.</p>");
	}
	function onError(event) {
		alert(event.data);
	}
	function send() {
		var msg11="";
		if (inputMessage.value == "") {
		} else {
			if((inputMessage.value).includes(":)")) {
				msg11=(inputMessage.value).replace(/:\)/g, 
				"<i class='fa fa-smile-o' style='font-size:50px'></i>&nbsp;");
				$("#messageWindow").html($("#messageWindow").html()
						+ "<p class='chat_content' style='font-weight: bold'>나 : " + msg11 + "</p>");
			}else {
				msg11=inputMessage.value;
				$("#messageWindow").html($("#messageWindow").html()
					+ "<p class='chat_content' style='font-weight: bold'>나 : " + msg11 + "</p>");	
			}
		}
		webSocket.send($("#chat_id").val() + "|" + inputMessage.value);	
		var elem = document.getElementById('messageWindow');
		elem.scrollTop = elem.scrollHeight;
		inputMessage.value = "";
	}
	$("#inputMessage").keyup(function(e){ 
	    var code = e.which; 
	    if(code==13)e.preventDefault();
	    if(code==13){
	    	send();
	    } 
	});
	// 	채팅이 많아져 스크롤바가 넘어가더라도 자동적으로 스크롤바가 내려가게함
</script>