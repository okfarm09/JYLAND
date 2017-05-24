<%@page import="project.jyland.board.model.JYCat"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="menu">
	<ul class="menu_list">
		<%
		List<JYCat> bcl=(List<JYCat>)session.getAttribute("bestcategorylist");
		for(int i=0; i<bcl.size(); i++) {
			%>
		<li><span class="top_lists" onclick="url_board(<%=bcl.get(i).getCatid()%>);"><%=bcl.get(i).getCatname()%></span></li>	
			<%
		}
		%>
		<li><span class="top_lists" onclick="url_library();">서고</span></li>
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
	<ul class="menu_list">
	<%
	List<JYCat> categorylist=(List<JYCat>)session.getAttribute("categorylist");
	for(int i=0; i<categorylist.size(); i++) {
		%>
	<li><span class="top_lists" onclick="url_board(<%=categorylist.get(i).getCatid()%>);"><%=categorylist.get(i).getCatname()%></span></li>		
		<%
		if(i%10==9) {
		%>
	</ul>
	<br/><ul class="menu_list">
			<%
		}
	}
	%>

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
	function url_library() {self.location.href = "library.jy"; return false;}
	
</script>