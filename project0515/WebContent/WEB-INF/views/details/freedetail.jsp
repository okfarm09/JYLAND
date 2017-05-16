<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:requestEncoding value="utf-8" />
<style>
<!--
#map2 {
	width: 300px;
	height: 300px;
}

.buttons {
	float: right;
	display: inline;
	padding: 0;
	width: 40%;
}

.buttons span {
	display: inline-block;
	width: 15%;
	background: #4e4e4e;
	color: white;
	text-align: center;
}
-->
</style>
<div class="boarddetail_wrap">
<form method="POST" id="_detail_form">
<input type="hidden" value="${freedetail.seq}" name="seq" />
<input type="hidden" value="${login.id}" name="id" id="_login_id" />
<input type="hidden" value="${freedetail.content}" name="content" />
<input type="hidden" value="${freedetail.title}" name="title" />
<input type="hidden" value="${freedetail.location}" name="location" />
<input type="hidden" value="${freedetail.upload}" name="upload" />
<input type="hidden" value="${freedetail.regionid}" name="regionid" />
<input type="hidden" value="${freedetail.catid}" name="catid" />
</form>
<input type="hidden" value="${login.id }" id="_comment_id" />
	<div class="details">
		<div class="title_box">
			<div class="box_title">${freedetail.title}</div>
			<div class="box_date">${freedetail.id} | ${freedetail.wdate}</div>
		</div>
		<div class="">
			<img src="<%=request.getContextPath()%>/img/view.gif"><span>${freedetail.readcount}</span>
			<img src="<%=request.getContextPath()%>/img/like.gif"><span>${freedetail.likecount}</span>
			<img src="<%=request.getContextPath()%>/img/hate.gif"><span>${freedetail.hatecount}</span>
		</div>
		<div class="detail_content">
			<div>
			<c:if test="${freedetail.location ne 'none' }">
			<input type="hidden" value="${freedetail.lat}" id="detail_lat" />
			<input type="hidden" value="${freedetail.lng}" id="detail_lng" />
			<div id="map2">
			<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBsfCP6fzs0kw5N8hmmptZAgV5uChrSyA8&callback=initMap">
			</script>
			
			</div>
			</c:if>
			</div>
			<div>
			<c:if test="${not fn:containsIgnoreCase(freedetail.upload, 'back') }">
			<img  src="<%=request.getContextPath()%>/upload/${freedetail.upload}" id="_detail_img" >
			</c:if>
			</div>
			<div>${freedetail.content}</div>
		</div>
		<div class="buttons">
		<span onclick="url_board1();" class=" hover_cursor">목록</span>
		<c:if test="${login.id eq freedetail.id }">
			<span onclick="detail_update();" class=" hover_cursor">수정</span>
			<span onclick="detail_delete();" class=" hover_cursor">삭제</span>
		</c:if>
		</div>
		<br/>
	</div>
	<hr>
	<div class="reply">
		<div class="reply_title">댓글</div>
		<div class="reply_list"></div>
		<c:if test="${not empty login.id }">
			<div class="reply_write">
				<input id="inputreply" type="text" class="reply_txt"/><input type="button" value="댓글입력" class="write_btn">
			</div>		
		</c:if>
	</div>
</div>
<script>
	function detail_update() {
		$("#_detail_form").attr("action","detailupdate.jy").submit();
	}
	function detail_delete() {
		$("#_detail_form").attr("action","detaildelete.jy").submit();
	}
</script>
<script>
function initMap() {
	var location = {
		lat: Number($("#detail_lat").val()),
		lng: Number($("#detail_lng").val())
	};
	var map = new google.maps.Map(document.getElementById('map2'), {
		zoom: 16,
		center: location
	});
	var marker = new google.maps.Marker({
		position: location,
		map: map
	});
}
</script>
<script>
$(function() {
	$("#_detail_img").each(function() {
		$(this).load(function() {
			var imgWidth=this.naturalWidth;
			if(imgWidth>500) {
				$(this).css("width", "500px");
			}
		});
	});
});
</script>
<script type="text/javascript">
function cancel(seq) {
	 $("#_reply_input"+seq).css("display", "none");
}
function getcomment(){
    $.ajax({
         url:"getCommentList.jy",
         data: {boardseq: "${freedetail.seq}"},
         success : function(data) {
            var a = "<table border='1' class='list_table' >" +
            	"<colgroup>" +
				"<col style='width: auto;' />" +
					"<col style='width: 5%;' />" +
					"<col style='width: 5%;' />" +
					"<col style='width: 5%;' />" +
					"<col style='width: 5%;' />" +
					"<col style='width: 5%;' />" +
				"</colgroup>"
            $.each(data,function(i,obj){
            if(obj.seqReply==0) {
               a += "<tr><td style='text-align: left' class='reply_content hover_cursor' onclick=\"comment_comment('"+obj.seq+"')\">" + 
               obj.content + "</td><td>" + 
               obj.id + "</td><td>" + obj.wdate + "</td><td>"
               + obj.likecount + "</td><td>" + obj.hatecount + "</td>";
               if($("#_login_id").val()==obj.id) {
            	   a += "<td><i class='fa fa-times hover_cursor' onclick=\"delete_comment('"+obj.seq+"')\"></i></td>";
               }
               a += "</tr>" + 
               "<c:if test='${not empty login.id }'>" + 
               "<tr style='display:none' id='_reply_input"+obj.seq+"' class='_reply_inputs'>"+"<td colspan='5'>"+ 
               "<input type='text' id='_reply_txt"+obj.seq+"'/><input type='button' value='댓글입력' id='_reply_btn"+ 
               obj.seq+"' onclick='reply_comment("+obj.seq+")'>"+ 
               "<input type='button' value='취소' onclick=\"cancel('"+obj.seq+"')\"></td>"+"</tr>" +
               "</c:if>";
            }else {
            	a += "<tr><td style='text-align: left' class='reply_content' onclick=\"comment_comment('"+obj.seq+"')\">&nbsp;&nbsp;&nbsp;" + 
                obj.content + "</td><td>" + obj.id + "</td><td>" + obj.wdate + "</td><td>"
                + obj.likecount + "</td><td>" + obj.hatecount + "</td>";
                if($("#_login_id").val()==obj.id) {
             	   a += "<td><i class='fa fa-times hover_cursor' onclick=\"delete_comment('"+obj.seq+"')\"></i></td>";
                }
                a +="</tr>";
            }
	})
            a += "</table>";
            $(".reply_list").html(a);
            
         },
         error : function(request, status, error) {
              console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
      })    
 }
 function delete_comment(seq) {
	 alert("안 돼 못 지워줘 돌아가 "+seq);
	 $.ajax({
		 url: "deleteComment.jy",
		 data: {
			 boardseq : "${freedetail.seq}",
			 seqReply : seq
		 },
		 success: function(data) {
			 getcomment();
		 }
	 });
 }
 function reply_comment(seq) {
	 $.ajax({
		url:"replyComment.jy",
		data:{
			id : $("#_comment_id").val(),
	        boardseq : "${freedetail.seq}",
	        content : $("#_reply_txt"+seq).val(),
	        seqReply : seq
		},
		success:function(data) {
			$("#_reply_txt"+seq).val("");
			getcomment();
		}
	 });
 }
 function comment_comment(seq) {
	 $("._reply_inputs").css("display", "none");
	 $("#_reply_input"+seq).css("display", "table-row");
}
getcomment();

$(".write_btn").click(function() {  
   $.ajax({
      type:'post',
      url:"writeComment.jy",
      dataType:'text',
      data : {
         id : $("#_comment_id").val(),
         boardseq : "${freedetail.seq}",
         content : $(".reply_txt").val()
   },
      success : function(data) {
         $(".reply_txt").val("");
         getcomment();
         
      },
      error : function(request, status, error) {
           alert("message:"+request.responseText+"\n"+"error:"+error);
   }   
   })
});

$("#inputreply").keyup(function(e){ 
    var code = e.which; 
    if(code==13)e.preventDefault();
    if(code==13){
    	eReply();
    } 
});

</script>