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
	<div class="modal-content animate">
		<div class="container"> <label><b>보낸이</b></label>
			<span class="id" id="_detail_sender"></span><label><b>보낸 날짜</b></label>
			<span class="id" id="_detail_wdate"></span><label><b>내용</b></label>
			<div id="_cont" class="_content_div"></div>
			<div class="clearfix">
				<button type="button" class="cancelbtn"
					onclick='clear_msg()'>닫기</button>
				<button type="button" class="signupbtn" id="_rpl_btn" onclick='reply_msg()'>답장</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" id="_send_msg">
	<span onclick='cancel_msg()' class="close"
		title="Close Join">&times;</span>
	<form class="modal-content animate" method="POST" id="_msg_form">
		<div class="container">
			<input type="hidden" value="${login.id }" name="sid" id="_sid"> <label><b>받는이</b></label>
			<input type="text" placeholder="Enter ID" name="rid" class="id"
				value='' readonly="readonly" id="_rid"> <label><b>내용</b></label>
			<div onClick="this.contentEditable='true';" id="_ocon" class="_content_div"></div>
			<div class="clearfix">
				<button type="button" class="cancelbtn"
					onclick='cancel_msg()'>취소</button>
				<button type="button" class="signupbtn" id="_msg_btn">보내기</button>
			</div>
		</div>
	</form>
</div>

<script>
$(function() {
	msg_list();
	$("#_msg_btn").click(function(e) {
		$.ajax({
			url: "writemessage.jy",
			method: "POST",
			data : {
				rid: $("#_rid").val(),
				sid: $("#_sid").val(),
				content: $("#_ocon").html()
			},
			success: function(data) {
				$("#_send_msg").css("display", "none");
				$("#_ocon").html("");
			}
		});
	});
});
function reply_msg() {
	var rid=$("#_detail_sender").html();
	clear_msg();
	$("#_rid").attr("value",rid);
	$("#_send_msg").css("display", "block");
}
function cancel_msg() {
	$("#_ocon").html("");
	$("#_send_msg").css("display", "none");
}
function clear_msg() {
	$("#_detail_sender").html("");
	$("#_detail_wdate").html("");
	$("#_cont").html("");
	$("#_msg_detail").css("display", "none");
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
			$("#_cont").html(data.content);
			$("#_msg_detail").css("display", "block");
			msg_list();
		}
		
	});
}
</script>
<style>
<!--
._content_div {
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
