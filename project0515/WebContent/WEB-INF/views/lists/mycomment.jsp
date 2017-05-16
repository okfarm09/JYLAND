<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />
<style>
<!--
#map2 {
	width: 300px;
	height: 300px;
}
-->
</style>
<div class="boarddetail_wrap">
	<div class="doc_title">${doc_title}</div>
	<div class="list_table">
		<table>
			<colgroup>
				<col style="width: 5%;" />
				<col style="width: auto;" />
				<col style="width: 7%;" />
				<col style="width: 5%;" />
				<col style="width: 5%;" />
				<col style="width: 5%;" />
			</colgroup>
			<thead>
				<tr>
					<th>NO</th>
					<th>CONTENT</th>
					<th>WRITER</th>
					<th>DATE</th>
					<th>LIKECOUNT</th>
					<th>HATECOUNT</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty mycomment}">
					<tr>
						<td colspan="5">작성된 댓글이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach items="${mycomment}" var="comment" varStatus="vs">
					<tr class="_hover_tr">
						<c:if test="${comment.delflag eq 1}">
							<td>${fn:length(mycomment)-vs.count+1}</td>
							<td colspan="5">삭제된 댓글입니다.</td>
						</c:if>
						<c:if test="${comment.delflag eq 0}">
							<td>${fn:length(mycomment)-vs.count+1}</td>
							<td style="text-align: left"><a
								href="boarddetail.jy?seq=${comment.boardseq}">${comment.content}
							</a></td>

							<td>${comment.id }</td>
							<td>${comment.wdate}</td>
							<td>${comment.likecount}</td>
							<td>${comment.hatecount}</td>
							<td><i class='fa fa-times hover_cursor'
								onclick="delete_comment('${comment.seq}', '${comment.boardseq}')"></i></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 	<div class="paging_wrap"> -->
	<!-- 		<div class="page_list"> -->
	<%-- 			<jsp:include page="/WEB-INF/views/common/paging.jsp" flush="false"> --%>
	<%-- 				<jsp:param value="${pageNumber}" name="pageNumber"/> --%>
	<%-- 				<jsp:param value="${pageCountPerScreen}" name="pageCountPerScreen"/> --%>
	<%-- 				<jsp:param value="${recordCountPerPage}" name="recordCountPerPage"/> --%>
	<%-- 				<jsp:param value="${totalRecordCount}" name="totalRecordCount"/>							 --%>
	<%-- 			</jsp:include> --%>
	<!-- 		</div> -->
	<!-- 	</div> -->
</div>
<script>
	function delete_comment(seq, boardseq) {
		alert("안 돼 못 지워줘 돌아가 " + seq + " " + boardseq);
		$.ajax({
			url : "deleteComment.jy",
			data : {
				boardseq : boardseq,
				seq : seq
			},
			success : function(data) {
				url_mycomment('${login.id}');
			}
		});
	}
</script>