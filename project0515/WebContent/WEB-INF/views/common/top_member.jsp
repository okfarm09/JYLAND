<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="login_wrap">
	<c:if test="${(empty login ) or (login.id eq '') }">
		<span id="_join" onclick='$("#_joinsung").css("display", "block")'>JOIN</span>
		<span id="_login" onclick='$("#_loginyo").css("display", "block")'>LOGIN</span>
	</c:if>
	<c:if test="${!(empty login ) }">
		<span id="_login" onclick='url_my_info();'>MY INFO</span>
		<span id="_login" onclick='url_logout();'>LOGOUT</span>
	</c:if>
</div>
<div id="_joinsung" class="modal">
	<span onclick='$("#_joinsung").css("display", "none")' class="close"
		title="Close Join">&times;</span>
	<form class="modal-content animate" action="join.jy">
		<div class="container">


			<label><b>ID</b></label> <input type="text" placeholder="Enter ID"
				name="id" required class="id" oninput="checkId()" id="checkaa">
			<label> <b>Nickname</b></label> <input type="text"
				placeholder="Enter Nickname" name="nickname" required class="id"
				id="nickname" oninput="checkId()"> <label><b>Email</b></label>
			<input type="text" placeholder="Enter Email" name="email" required
				class="id" id="email" oninput="checkId()"> <label> <b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="upwd"
				required class="pass" oninput="checkPwd()"> <label>
				<b>Repeat Password</b>
			</label> <input type="password" placeholder="Repeat Password"
				name="psw-repeat" required class="pass" id="repwd"
				oninput="checkPwd()"> <input type="checkbox"
				checked="checked"> Remember me

			<div class="clearfix">
				<button type="button" class="cancelbtn"
					onclick='$("#_joinsung").css("display", "none")'>Cancel</button>
				<button type="submit" class="signupbtn" disabled="disabled">Sign
					Up</button>
			</div>
		</div>
	</form>
</div>

<div id="_loginyo" class="modal">
	<span onclick='$("#_loginyo").css("display", "none")' class="close"
		title="Close Login" class="cancelbtn">&times;</span>

	<form class="modal-content animate" action="login.jy" method="post">

		<div class="container">
			<label><b>ID</b></label> <input type="text" placeholder="Enter ID"
				name="id" class="id" required> <label><b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="upwd"
				class="pass" required>

			<button type="submit" class="loginbtn">Login</button>
			<input type="checkbox" checked="checked"> Remember me
		</div>

		<div class="container" style="background-color: #f1f1f1">
			<button type="button"
				onclick='$("#_loginyo").css("visibility", "hidden")'
				class="cancelbtn2">Cancel</button>
			<span class="psw">Forgot <a href="#">password?</a></span>
		</div>
	</form>
</div>