<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="utf-8"/>
<script>
	function show_msg(id) {self.location.href = "messagelist.jy?rid="+id; return false;}
</script>
<div>
<div class="doc_title">${doc_title}</div>
<div class="write_table">
<span>아이디</span>
<div>${login.id }</div>
<span>닉네임</span>
<div>${login.nickname }</div>
<span>비밀번호</span>
<div>${login.upwd }</div>
<span>이메일</span>
<div>${login.email }</div>
<span>권한</span>
<c:if test="${login.auth eq 3 }">
	<div>사용자</div>
</c:if>
<c:if test="${login.auth eq 2 }">
	<div>관리자</div>
</c:if>
<c:if test="${login.auth eq 1 }">
	<div>운영자</div>
</c:if>
<p>&nbsp;</p>
<span onclick="show_msg('${login.id}')" class="hover_cursor">쪽지보기</span>
<span onclick="change_me()" class="hover_cursor">정보변경</span>
<span onclick="url_mylist('${login.id}')" class="hover_cursor">내글보기</span>
<span onclick="url_mycomment('${login.id}')" class="hover_cursor">내댓글보기</span>
<c:if test="${login.auth eq 1 }">
	<span onclick="url_userlist()" class="hover_cursor">회원관리</span>
	<span class="write_btn hover_cursor" onclick="url_boardwrite(0)" >공지글쓰기</span>
	<span onclick="createboard_modal()" class="hover_cursor">게시판생성</span>
</c:if>
</div>
</div>

<div class="modal" id="_create_board">
<span onclick='$("#_create_board").css("display", "none")' class="close"
		title="Close Map">&times;</span>
	<div class="container modal-content">
		<label><b>게시판 이름</b></label>
			<input type="text" placeholder="e.g. OO게시판"
				class="id" id="_catname" required>
			<button type="button" class="loginbtn" onclick="createboard()">생성</button>
	</div>
</div>

<script type="text/javascript">
function createboard_modal() {
	$("#_create_board").css("display", "block");
}
function createboard() {
	$.ajax({
		url : "createboard.jy",
		method : "POST",
		data : {
			catname: $("#_catname").val()
		},
		success : function() {
			$("#_catname").val("");
			$("#_create_board").css("display", "none");
		}
		
	});
}
</script>


