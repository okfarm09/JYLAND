<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="menu">
	<ul class="menu_list">
		<li><span class="top_lists" onclick="url_board(1);">자유게시판</span></li>
		<li><span class="top_lists" onclick="url_board(2);">토론게시판</span></li>
		<li><span class="top_lists" onclick="url_board(3);">사진게시판</span></li>
		<li><span class="top_lists" onclick="url_board(4);">보드게시판</span></li>
		<li><span class="top_lists" onclick="url_board(5);">신고게시판</span></li>
	</ul>
	<div class="searchbox">
		<form method="get" id="_search_form">
			<input type="text" id="_search" class="search" name="keyword" /> <span><img
				id="_searchbtn" class="searchbtn hover_cursor" src="./img/search.png"
				onclick="search()" /></span> <span id="toggle" onclick='showboardslist()'><img
				src="./img/toggle.png" />전체게시판</span>
		</form>
	</div>
</div>
<div id="_boards_list" style='display:none; width:100%;'>
	<hr/>
	<ul class="menu_list">
		<li><span class="top_lists" onclick="url_board(1);">자유게시판</span></li>
		<li><span class="top_lists" onclick="url_board(2);">토론게시판</span></li>
		<li><span class="top_lists" onclick="url_board(3);">사진게시판</span></li>
		<li><span class="top_lists" onclick="url_board(4);">보드게시판</span></li>
		<li><span class="top_lists" onclick="url_board(5);">신고게시판</span></li>
		<li><span class="top_lists" onclick="url_board(1);">자유게시판</span></li>
		<li><span class="top_lists" onclick="url_board(2);">토론게시판</span></li>
		<li><span class="top_lists" onclick="url_board(3);">사진게시판</span></li>
		<li><span class="top_lists" onclick="url_board(4);">보드게시판</span></li>
		<li><span class="top_lists" onclick="url_board(5);">신고게시판</span></li>
	</ul>
	<br/>
	<ul class="menu_list">
		<li><span class="top_lists" onclick="url_board(1);">자유게시판</span></li>
		<li><span class="top_lists" onclick="url_board(2);">토론게시판</span></li>
		<li><span class="top_lists" onclick="url_board(3);">사진게시판</span></li>
		<li><span class="top_lists" onclick="url_board(4);">보드게시판</span></li>
		<li><span class="top_lists" onclick="url_board(5);">신고게시판</span></li>
		<li><span class="top_lists" onclick="url_board(1);">자유게시판</span></li>
		<li><span class="top_lists" onclick="url_board(2);">토론게시판</span></li>
		<li><span class="top_lists" onclick="url_board(3);">사진게시판</span></li>
		<li><span class="top_lists" onclick="url_board(4);">보드게시판</span></li>
		<li><span class="top_lists" onclick="url_board(5);">신고게시판</span></li>
	</ul>
	<br/><br/>
</div>
<script type="text/javascript">
	function search() {
		$("#_search_form").attr("action", "search.jy").submit();
	}
	function showboardslist() {
		$("#_boards_list").toggle();
	}
</script>