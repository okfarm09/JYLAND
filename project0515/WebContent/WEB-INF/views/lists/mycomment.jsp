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
.oridel:hover {
	cursor: pointer;
	color : gray;
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
					<td colspan="5">삭제된 댓글입니다. </td>
					</c:if>
					<c:if test="${comment.delflag eq 0}">
						<td>${fn:length(mycomment)-vs.count+1}</td>
						<td style="text-align: left">
							<p onclick="oridel('${comment.boardseq}')" class="oridel">${comment.content}</p>
						</td>
						
						<td>${comment.id }</td>
						<td>${comment.wdate}</td>
						<td>${comment.likecount}</td>
						<td>${comment.hatecount}</td>
						<c:if test="${comment.id eq login.id || login.auth eq 1 || login.auth eq 2}" >
							<td><i class='fa fa-times hover_cursor' onclick="delete_comment('${comment.seq}', '${comment.boardseq}', '${comment.id}')"></i></td>
						</c:if>
						<c:if test="${comment.id ne login.id}">
							<td> </td>
						</c:if>
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
 function delete_comment(seq, boardseq, id) {
	 alert("안 돼 못 지워줘 돌아가 "+seq+" "+boardseq);
	 $.ajax({
		 url: "deleteComment.jy",
		 data: {
			 boardseq : boardseq,
			 seq : seq,
			 id : id
		 },
		 success: function(data) {
			 url_mycomment(id);
		 }
	 });
 }
 
 function oridel(boardseq){
	 $.ajax({
		 url: "getdel.jy",
		 data: {
			 'seq' : boardseq
		 },
		 type:"POST",
		 success : function(data) {
			if(data.del==0) {
				location.href="boarddetail.jy?seq="+boardseq;
				
			} else {
				alert('삭제 된 놈이야')
			}
		 },
		 error : function(xhr, status, aaa) {
			 alert("err")
			
		 }
	 })
 }
	  
	
 
 

 </script>