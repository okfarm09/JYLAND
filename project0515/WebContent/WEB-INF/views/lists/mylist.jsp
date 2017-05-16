<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="utf-8" />
<style>
<!--
#commentCount {
	font-size : 70%;
	color : #6c8757;
}
-->
</style>
<div class="boardlist_wrap">
	<form name="frmForm1" id="_frmFormSearch" method="post" action="">
	<input type="hidden" name="pageNumber" id="_pageNumber" value="${(empty pageNumber)?0:pageNumber}"/>						
	<input type="hidden" name="recordCountPerPage" id="_recordCountPerPage" value="${(empty recordCountPerPage)?10:recordCountPerPage}"/>	
	</form>				
	<div class="doc_title">${doc_title}</div>
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
				<c:if test="${empty mylist}">
					<tr>
						<td colspan="5">작성된 글이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${mylist}" var="list" varStatus="vs">
					<tr class="_hover_tr">
						<td>${fn:length(mylist)-vs.count+1}</td>
						<td style="text-align: left">
							<a href='freedetail.jy?seq=${list.seq}'> ${list.title}</a>
							&nbsp;&nbsp;<span id="commentCount">[${list.commentcount}]</span></td>
						<td><span class="hover_cursor" onclick="url_user_info('${list.id}')">${list.id}</span></td>
						<td>${list.wdate}</td>
						<td>${list.readcount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="paging_wrap">
		<div class="page_list">
			<jsp:include page="/WEB-INF/views/common/paging.jsp" flush="false">
				<jsp:param value="${pageNumber}" name="pageNumber"/>
				<jsp:param value="${pageCountPerScreen}" name="pageCountPerScreen"/>
				<jsp:param value="${recordCountPerPage}" name="recordCountPerPage"/>
				<jsp:param value="${totalRecordCount}" name="totalRecordCount"/>							
			</jsp:include>
		</div>
	</div>
</div>

