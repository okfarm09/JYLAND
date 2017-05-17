<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="best_wrap">베스트</div>
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
					<td style="text-align: left">
						<b>${recent.catname}</b>
						<a href='boarddetail.jy?seq=${recent.seq}'>${recent.title}</a>
						&nbsp;&nbsp;<span id="commentCount">[${recent.commentcount}]</span>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>