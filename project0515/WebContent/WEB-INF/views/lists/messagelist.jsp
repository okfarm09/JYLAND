<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />
<div class="boardlist_wrap">
	<div class="doc_title">${doc_title}</div>
	<form name='frmForm1' id='_frmFormSearch' method='post' action=''>
		<input type='hidden' name='pageNumber' id='_pageNumber'
			value='${(empty pageNumber)?0:pageNumber}' /> <input type='hidden'
			name='recordCountPerPage' id='_recordCountPerPage'
			value='${(empty recordCountPerPage)?10:recordCountPerPage}' />
	</form>
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
					<th>CONTENT</th>
					<th>SENDER</th>
					<th>DATE</th>
					<th>STATUS</th>
				</tr>
			</thead>
			<tbody id="_msg_tbody">
			</tbody>
		</table>
	</div>
	<div class="paging_wrap">
		<div class="page_list">
			<jsp:include page='/WEB-INF/views/common/paging.jsp' flush='false'>
				<jsp:param value='${pageNumber}' name='pageNumber' />
				<jsp:param value='${pageCountPerScreen}' name='pageCountPerScreen' />
				<jsp:param value='${recordCountPerPage}' name='recordCountPerPage' />
				<jsp:param value='${totalRecordCount}' name='totalRecordCount' />
			</jsp:include>
		</div>
	</div>
</div>

<div class="modal" id="_msg_detail">
	<span onclick='clear_msg()' class="close"
		title="Close Join">&times;</span>
	<form class="modal-content animate" method="POST" id="_msg_form">
		<div class="container"> <label><b>보낸이</b></label>
			<span class="id" id="_detail_sender"></span><label><b>보낸 날짜</b></label>
			<span class="id" id="_detail_wdate"></span><label><b>내용</b></label>
			<div id="_ocon"></div>
			<div class="clearfix">
				<button type="button" class="cancelbtn"
					onclick='clear_msg()'>닫기</button>
				<button type="button" class="signupbtn" id="_msg_btn">답장</button>
			</div>
		</div>
	</form>
</div>
<script>
$(function() {
	msg_list();
});
function clear_msg() {
	$("#_detail_sender").html("");
	$("#_detail_wdate").html("");
	$("#_ocon").html("");
	$("#_msg_detail").css("display", "none")
}
var msglist="<c:if test='${empty msglist }'>" + 
	"<tr><td colspan='5'>받은 쪽지가 없습니다.</td></tr>"
	"</c:if>"+ 
	"<c:forEach items='${msglist }' var='msg' varStatus='vs'>"+ 
	"<tr class='_hover_tr'>"+ 
	"<td>${fn:length(msglist)-vs.count+1}</td>"+ 
	"<td style='text-align: left'>"+ 
	"<span class='hover_cursor' onclick=\"show_msg('${msg.seq }')\">"+ 
	"${msg.content }</span>"+ 
	"</td>"+ 
	"<td>${msg.sid }</td>"+ 
	"<td>${msg.wdate }</td>"+ 
	"<td>"+ 
	"<c:if test='${msg.readchk eq 0 }'>미확인</c:if>"+ 
	"<c:if test='${msg.readchk eq 1 }'>읽음</c:if>"+ 
	"</td></tr>"+ 
	"</c:forEach>";
function msg_list() {
	$.ajax({
		url : "recievedmessages.jy",
		data : {
			rid : "${login.id}"
		},
		success: function(data) {
			$("#_msg_tbody").html(msglist);
		}
	});
	
}
function show_msg(seq) {
	$.ajax({
		url : "messagedetail.jy",
		method : "POST",
		data : {
			seq : seq
		},
		success: function(data) {
			$("#_detail_sender").html(data.sid);
			$("#_detail_wdate").html(data.wdate);
			$("#_ocon").html(data.content);
			$("#_msg_detail").css("display", "block");
			msg_list();
		}
		
	});
}
</script>
<style>
<!--
#_ocon {
	width: 100%;
	height: 400px;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	box-sizing: border-box;
}
-->
</style>
