<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />
<style>
<!--
#_ocon {
	width: 100%;
	height: 400px;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}
-->
</style>
<script type="text/javascript">
$(function() {
	$("#_msg_btn").click(function(e) {
		$.ajax({
			url: "writemessage.jy",
			method: "POST",
			data : {
				rid: $("#_rid").val(),
				sid: $("#_sid").val(),
				content: $("#_ocon").html()
			},
			success: function(data) {
				$("#_send_msg").css("display", "none");
				$("#_ocon").html("");
			}
		});
	});
});
function cancel_msg() {
	$("#_ocon").html("");
	$("#_send_msg").css("display", "none")
}
</script>
<div>
	<div class="doc_title">${doc_title}</div>
	<div class="write_table">
		<span>아이디</span>
		<div>${userInfo.id }</div>
		<span>닉네임</span>
		<div>${userInfo.nickname }</div>
		<span>이메일</span>
		<div>${userInfo.email }</div>
		<p>&nbsp;</p>
		<span onclick='$("#_send_msg").css("display", "block")'
			class="hover_cursor">쪽지 보내기</span> <span onclick="#"
			class="hover_cursor">신고하기</span>
			<span onclick="#" class="hover_cursor">차단하기</span> 
			<span onclick="url_mylist('${userInfo.id}')" class="hover_cursor">전체 게시글</span>
			<span onclick="url_mycomment('${userInfo.id}')" class="hover_cursor">전체 댓글</span>
	</div>
</div>
<div class="modal" id="_send_msg">
	<span onclick='cancel_msg()' class="close"
		title="Close Join">&times;</span>
	<form class="modal-content animate" method="POST" id="_msg_form">
		<div class="container">
			<input type="hidden" value="${login.id }" name="sid" id="_sid"> <label><b>받는이</b></label>
			<input type="text" placeholder="Enter ID" name="rid" class="id"
				value='${userInfo.id }' readonly="readonly" id="_rid"> <label><b>내용</b></label>
			<div onClick="this.contentEditable='true';" id="_ocon"></div>
			<div class="clearfix">
				<button type="button" class="cancelbtn"
					onclick='cancel_msg()'>취소</button>
				<button type="button" class="signupbtn" id="_msg_btn">보내기</button>
			</div>
		</div>
	</form>
</div>