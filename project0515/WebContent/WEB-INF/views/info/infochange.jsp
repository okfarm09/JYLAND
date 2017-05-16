<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="utf-8"/>
<div>
<div class="doc_title">${doc_title}</div>
<div class="write_table">
<form id="_info_form" method="POST">
<input type="hidden" value="${login.id }" name="id" />
<input type="hidden" name="rpwd" id="_rpwd_2" />
<span>닉네임</span>
<input type="text" value="${login.nickname }" class="_info_input" name="nickname" />
<span>새 비밀번호</span>
<input type="password" value="" class="_info_input" name="upwd" placeholder="Input New Password" id="_npwd"
oninput="check_color()" />
<span>재확인</span>
<input type="password" value="" class="_info_input" placeholder="Repeat New Password" id="_npwd2"
oninput="check_color()" />
<span>이메일</span>
<input type="text" value="${login.email }" class="_info_input" name="email" />
</form>
<span class="hover_cursor" onclick="chack_pwd()">변경</span>
</div>
</div>

<div class="modal" id="_password_check">
<span onclick='$("#_password_check").css("display", "none")' class="close"
		title="Close Map">&times;</span>
	<div class="container modal-content">
		<label><b>Password</b></label>
			<input type="password" placeholder="Enter Password"
				class="pass" id="_rpwd" onclick="clear_rwpd()" required>
			<button type="button" class="loginbtn" onclick="change_info('${login.upwd}')">확인</button>
	</div>
</div>

