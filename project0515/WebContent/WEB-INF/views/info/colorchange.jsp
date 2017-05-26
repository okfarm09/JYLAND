<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="utf-8"/>
<div>
<div class="doc_title">${doc_title}</div>
<div class="color_choose">
	<input type="radio" name="color" value="#4e4e4e" checked="checked" class='_color'>default
	<input type="radio" name="color" value="#ffcfcf" class='_color'>pink
</div>
<span class="hover_cursor" onclick="color_apply()">적용</span>
</div>
<script>
	function color_apply() {
		$(".bg_wrap").css("background", $('input:radio[name="color"]:checked').val());
	}
</script>
