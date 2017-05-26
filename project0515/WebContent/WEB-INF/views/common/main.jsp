<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
.notice {
	height: 50px;
	overflow: hidden;
	border: solid 1px #000000;
}

.rolling {
	position: relative;
	width: 96%;
	height: auto;
	padding: 1.5% 2%;
}

.rolling li {
	width: 100%;
	height: 100%;
}

.main_title {
	font-size: 16pt;
	margin: 10px 0 7px;
}

#best_table tr, #recent_table tr{
	height: 25px;
	font-size: 11pt;
}

._hover_tr>td:FIRST-CHILD {
	color: #8f8f8f;
	padding-right: 8px;
}

#commentCount {
	font-size: 8pt;
}
</style>

<div class="notice">
	<ul class="rolling">
		<c:forEach items="${noticelist}" var="notice">
			<li><a href="boarddetail.jy?seq=${notice.seq}"><span>[공지]</span>
					${notice.title}</a></li>
		</c:forEach>
	</ul>
</div>

<div class="best_wrap">
	<div class="main_title"><strong>베스트</strong></div>
	<div id="best_table">
		<table>
			<colgroup>
				<col style="width: 10%;" />
				<col style="width: auto;" />
			</colgroup>
			<c:if test="${empty bestlist}">
				<tr>
					<td colspan="5">작성된 글이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach items="${bestlist}" var="best">
				<tr class="_hover_tr">
					<td>${best.dateForMain}</td>
					<td style="text-align: left"><b class="hover_cursor" onclick="url_board(${best.catid});">${best.catname}</b> <a
						href='boarddetail.jy?seq=${best.seq}'>${best.title}&nbsp;</a>
						&nbsp;&nbsp;<span id="commentCount">[${best.commentcount}]</span>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<div class="recent_wrap">
	<div class="main_title"><strong>최신글</strong></div>
	<div id="recent_table">
		<table>
			<colgroup>
				<col style="width: 10%;" />
				<col style="width: auto;" />
			</colgroup>
			<c:if test="${empty recentlist}">
				<tr>
					<td colspan="5">작성된 글이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach items="${recentlist}" var="recent" varStatus="vs">
				<tr class="_hover_tr">
					<td>${recent.dateForMain}</td>
					<td style="text-align: left"><b>${recent.catname}</b> <a
						href='boarddetail.jy?seq=${recent.seq}'>${recent.title}</a>
						&nbsp;&nbsp;<span id="commentCount">[${recent.commentcount}]</span>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<script>
	$(document).ready(function() {
		var height = $(".notice").height(); 
		var num = $(".rolling li").length;
		var max = height * num;
		var move = 0; 
		function noticeRolling() {
			move += height; 
			$(".rolling").animate({
				"top" : -move
			}, 600, function() { 
				if (move >= max) {
					$(this).css("top", 0); 
					move = 0; 
				}	
				;
			});
		}
		;
		noticeRollingOff = setInterval(noticeRolling, 2000);
		$(".rolling").append($(".rolling li").first().clone());

		$(".rolling_stop").click(function() {
			clearInterval(noticeRollingOff);
		});
		$(".rolling_start").click(function() {
			noticeRollingOff = setInterval(noticeRolling, 1000); 
		});
	});
</script>