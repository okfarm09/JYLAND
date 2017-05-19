<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
.notice {
	width: 100%;
	height: 50px;
	overflow: hidden;
	background-color: #fff;
}

.rolling {
	position: relative;
	width: 100%;
	height: auto;
}

.rolling li {
	width: 100%;
	height: 50px;
	line-height: 50px;
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
	<div>베스트</div>
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
					<td style="text-align: left"><b>${best.catname}</b> <a
						href='boarddetail.jy?seq=${best.seq}'>${best.title}</a>
						&nbsp;&nbsp;<span id="commentCount">[${best.commentcount}]</span>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<div class="recent_wrap">
	<div>최신글</div>
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