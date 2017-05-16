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
	<c:if test="${(not empty login.id) }">
	<div class="upper_list_btn">
		<img src="<%=request.getContextPath()%>/img/write.gif"
			class="write_btn hover_cursor" onclick="url_freewrite()" />
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
				<c:if test="${empty freelist}">
					<tr>
						<td colspan="5">작성된 글이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${freelist}" var="free" varStatus="vs">
					<tr class="_hover_tr">
						<td>${fn:length(freelist)-vs.count+1}</td>
						<td style="text-align: left">
							<a href='freedetail.jy?seq=${free.seq}'> ${free.title}</a>
							&nbsp;&nbsp;<span id="commentCount">[${free.commentcount}]</span></td>
						<td>
							<c:if test="${free.id ne login.id }">
								<span class="hover_cursor" onclick="url_user_info('${free.id}')">${free.id}</span>
							</c:if>
							<c:if test="${free.id eq login.id }">
								<span class="hover_cursor" onclick="url_my_info();">${free.id}</span>
							</c:if>
						</td>
						<td>${free.wdate}</td>
						<td>${free.readcount}</td>
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
		<c:if test="${(not empty login.id) }">
		<div class="lower_list_btn">
			<img src="<%=request.getContextPath()%>/img/write.gif"
				class="write_btn hover_cursor" onclick="url_freewrite()" />
		</div>
		</c:if>
	</div>
</div>

