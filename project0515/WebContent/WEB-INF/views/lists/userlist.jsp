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
	<div class="list_table">
		<table>
			<colgroup>
				<col style="width: 20%;" />
				<col style="width: auto;" />
				<col style="width: 5%;" />
				<col style="width: 5%;" />
				<col style="width: 5%;" />
				<col style="width: 5%;" />
			</colgroup>
			<thead>
				<tr>
					<th>ID</th>
					<th>PASSWORD</th>
					<th>NICKNAME</th>
					<th>EMAIL</th>
					<th>AUTH</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty userlist}">
					<tr>
						<td colspan="5">회원이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${userlist}" var="user" varStatus="vs">
					<tr class="_hover_tr">
						<td style="text-align: left"><a
							href='userinfo.jy?id=${user.id}'> ${user.id}</a>
							</td>
						<td>${user.upwd}</td>
						<td>${user.nickname}</td>
						<td>${user.email}</td>
						<td>
							<c:if test="${user.auth eq 3 }">
								<div>사용자</div>
							</c:if>
							<c:if test="${user.auth eq 2 }">
								<div>관리자</div>
							</c:if>
							<c:if test="${user.auth eq 1 }">
								<div>운영자</div>
							</c:if>
						</td>
						<td><i class='fa fa-times hover_cursor deleteuser' onclick="delete_user('${user.id}')"></i></td>
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
		</div>
	</div>
</div>
<script>
 function delete_user(id) {
	 alert("안 돼 못 지워줘 돌아가 "+id);
	 $.ajax({
		 url: "deleteuser.jy",
		 type : 'POST',
		 data: {
			 id: id
		 },
		 success: function(data) {
			 console.log("ssssssssssssssssssssssssssssssss");
			 url_userlist();
		 },
		 error : function() {
			 console.log(id);
			 console.log("dddddddddddddddddddddddddddddddd");
		 }
	 });
 }
 </script>
