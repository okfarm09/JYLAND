<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />
<style>
<!--
#commentCount {
	font-size: 70%;
	color: #6c8757;
}
-->
</style>
<div class="boardlist_wrap">
	<form name="frmForm1" id="_frmFormSearch" method="post" action="">
		<input type="hidden" name="pageNumber" id="_pageNumber"
			value="${(empty pageNumber)?0:pageNumber}" /> <input type="hidden"
			name="recordCountPerPage" id="_recordCountPerPage"
			value="${(empty recordCountPerPage)?10:recordCountPerPage}" />
	</form>
	<div class="doc_title">${doc_title}</div>
	<c:if test="${(not empty login.id) }">
		<div class="upper_list_btn">
			<img src="<%=request.getContextPath()%>/img/write.gif"
				class="write_btn hover_cursor" onclick="url_boardwrite('${catid}')" />
		</div>
	</c:if>
	<div class="list_table">
		<table>
			<colgroup>
				<col style="width: 5%;" />
				<col style="width: auto;" />
				<col style="width: 5%;" />
				<col style="width: 5%;" />
				<col style="width: 5%;" />
			</colgroup>
			<thead>
				<tr>
					<th>NO</th>
					<th>TITLE</th>
					<th>WRITER</th>
					<th>DATE</th>
					<th>COUNT</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty GlobalNoticeList}">
					<tr>
						<td colspan="5">전체 공지가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${GlobalNoticeList}" var="global">
					<tr class="_hover_tr">
						<td>전체공지</td>
						<td style="text-align: left"><a
							href='boarddetail.jy?seq=${global.seq}'> ${global.title}</a></td>
						<td><c:if test="${global.id ne login.id }">
								<span class="hover_cursor"
									onclick="url_user_info('${global.id}')">${global.id}</span>
							</c:if> <c:if test="${global.id eq login.id }">
								<span class="hover_cursor" onclick="url_my_info();">${global.id}</span>
							</c:if></td>
						<td>${global.wdate}</td>
						<td>${global.readcount}</td>
					</tr>
				</c:forEach>
				
				<c:if test="${empty LocalNoticeList}">
					<tr>
						<td colspan="5">게시판 공지가 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${LocalNoticeList}" var="local">
					<tr class="_hover_tr">
						<td>${doc_title}</td>
						<td style="text-align: left"><a
							href='boarddetail.jy?seq=${local.seq}'> ${local.title}</a></td>
						<td><c:if test="${local.id ne login.id }">
								<span class="hover_cursor"
									onclick="url_user_info('${local.id}')">${local.id}</span>
							</c:if> <c:if test="${local.id eq login.id }">
								<span class="hover_cursor" onclick="url_my_info();">${local.id}</span>
							</c:if></td>
						<td>${local.wdate}</td>
						<td>${local.readcount}</td>
					</tr>
				</c:forEach>
				
				<c:if test="${empty boardlist}">
					<tr>
						<td colspan="5">작성된 글이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${boardlist}" var="board" varStatus="vs">
					<tr class="_hover_tr">
						<td>${fn:length(boardlist)-vs.count+1}</td>
						<td style="text-align: left"><a
							href='boarddetail.jy?seq=${board.seq}'> ${board.title}</a>
							&nbsp;&nbsp;<span id="commentCount">[${board.commentcount}]</span></td>
						<td><c:if test="${board.id ne login.id }">
								<span class="hover_cursor"
									onclick="url_user_info('${board.id}')">${board.id}</span>
							</c:if> <c:if test="${board.id eq login.id }">
								<span class="hover_cursor" onclick="url_my_info();">${board.id}</span>
							</c:if></td>
						<td>${board.wdate}</td>
						<td>${board.readcount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="paging_wrap">
			<div class="page_list">
				<jsp:include page="/WEB-INF/views/common/paging.jsp" flush="false">
					<jsp:param value="${pageNumber}" name="pageNumber" />
					<jsp:param value="${pageCountPerScreen}" name="pageCountPerScreen" />
					<jsp:param value="${recordCountPerPage}" name="recordCountPerPage" />
					<jsp:param value="${totalRecordCount}" name="totalRecordCount" />
				</jsp:include>
			</div>
			<c:if test="${(not empty login.id) }">
				<div class="lower_list_btn">
					<img src="<%=request.getContextPath()%>/img/write.gif"
						class="write_btn hover_cursor"
						onclick="url_boardwrite('${catid}')" />
				</div>
			</c:if>
		</div>
	</div>
</div>

