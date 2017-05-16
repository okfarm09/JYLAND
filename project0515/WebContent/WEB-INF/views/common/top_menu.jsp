<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="menu">
	<ul class="menu_list">
		<li><span class="top_lists" onclick="url_board1();">자유게시판</span></li>
		<li><span class="top_lists">게시판2</span></li>
		<li><span class="top_lists">게시판3</span></li>
		<li><span class="top_lists">게시판4</span></li>
		<li><span class="top_lists">게시판5</span></li>
	</ul>
	<div class="searchbox">
		<form method="get" id="_search_form">
			<input type="text" id="_search" class="search" name="keyword" /> <span><img
				id="_searchbtn" class="searchbtn hover_cursor" src="./img/search.png"
				onclick="search()" /></span> <span id="toggle"><img
				src="./img/toggle.png" />전체게시판</span>
		</form>
	</div>
</div>
<script type="text/javascript">
	function search() {
		$("#_search_form").attr("action", "search.jy").submit();
	}
</script>