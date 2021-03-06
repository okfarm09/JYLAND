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

.box_title {
	display: inline-block;
	font-size: 18px;
}

.box_date {
	display: inline-block;
	float: right;
	font-size: 12px;
	color: #bebebe;
	padding-top: .8%;
}

.detail_views {
	float: right;
	padding-right: 2%;
	clear:both;
}

.detail_views img {
	vertical-align: middle;
}

.detail_views span {
	font-size: 12px;
	vertical-align: middle;
}

.detail_content {
	clear:both;
	padding: 2%;
	min-height: 30%;
}

.buttons {
	padding: 1% 2%;
}

.buttons span {
	float: left;
	display: inline-block;
	background: #4e4e4e;
	color: white;
	text-align: center;
	width:60px;
	padding: 1%;
	margin-right: 10px;
}

.detail_lh_btn {
	float:left;
	list-style: initial;
}

.detail_btn {
	float: right;
}

.reply {
	clear:both;
	padding:1% 2%;
	border-top: solid 1px #d41b1b;
}

/* ._comment_wdate { */
/* 	font-size: 8pt; */
/* } */
-->
</style>
<div class="boarddetail_wrap">
<form method="POST" id="_detail_form">
<input type="hidden" value="${boarddetail.seq}" name="seq" />
<input type="hidden" value="${login.id}" name="id" id="_login_id" />
<input type="hidden" value="${boarddetail.content}" name="content" />
<input type="hidden" value="${boarddetail.title}" name="title" />
<input type="hidden" value="${boarddetail.location}" name="location" />
<input type="hidden" value="${boarddetail.upload}" name="upload" />
<input type="hidden" value="${boarddetail.regionid}" name="regionid" />
<input type="hidden" value="${boarddetail.catid}" name="catid" />
</form>
<input type="hidden" value="${login.id }" id="_comment_id" />
	<div class="details">
		<div class="title_box">
			<div class="box_title">${boarddetail.title}</div>
			<div class="box_date">${boarddetail.id} | ${boarddetail.wdate}</div>
		</div>
		<div class="detail_views">
			<img src="<%=request.getContextPath()%>/img/view.gif">&nbsp;<span>${boarddetail.readcount}</span>&nbsp;&nbsp;&nbsp;
			<img src="<%=request.getContextPath()%>/img/like.gif">&nbsp;<span id="_like_count">${boarddetail.likecount}</span>&nbsp;&nbsp;&nbsp;
			<img src="<%=request.getContextPath()%>/img/hate.gif">&nbsp;<span id="_hate_count">${boarddetail.hatecount}</span>
		</div>
		<div class="detail_content">
			<div>
			<c:if test="${boarddetail.location ne 'none' }">
			<input type="hidden" value="${boarddetail.lat}" id="detail_lat" />
			<input type="hidden" value="${boarddetail.lng}" id="detail_lng" />
			<div id="map2">
			<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBsfCP6fzs0kw5N8hmmptZAgV5uChrSyA8&callback=initMap">
			</script>
			
			</div>
			</c:if>
			</div>
			<div>
			<c:if test="${not fn:containsIgnoreCase(boarddetail.upload, 'back') }">
			<img  src="<%=request.getContextPath()%>/upload/${boarddetail.upload}" id="_detail_img" >
			</c:if>
			</div>
			<div>${boarddetail.content}</div>
		</div>
		<div class="buttons">
			<div class="detail_lh_btn">
				<span onclick="like();" class=" hover_cursor">좋아요</span>
				<span onclick="hate();" class=" hover_cursor">싫어요</span>
			</div>
			<div class="detail_btn">
				<c:if test="${login.auth eq 1 || login.auth eq 2}">
					<span onclick="detail_goNotice()" class=" hover_cursor">공지로</span>
				</c:if>
				<span onclick="url_board('${boarddetail.catid}');" class=" hover_cursor">목록</span>
				<c:if test="${login.id eq boarddetail.id }">
					<span onclick="detail_update();" class=" hover_cursor">수정</span>
					<span onclick="detail_delete();" class=" hover_cursor">삭제</span>
				</c:if>
			</div>
		</div>
		<br/>
	</div>
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
	function detail_goNotice() {
		$("#_detail_form").attr("action", "goNotice.jy").submit();
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
function like() {
	$.ajax({
		url : "like.jy",
		data : {
			boardseq : "${boarddetail.seq}",
			userid : "${login.id}"
		},
		method : "POST",
		success : function(data) {
			$("#_like_count").html(data.likecount);
			$("#_hate_count").html(data.hatecount);
			alert(data.message);
		}
	});
}
function hate() {
	$.ajax({
		url : "hate.jy",
		data : {
			boardseq : "${boarddetail.seq}",
			userid : "${login.id}"
		},
		method : "POST",
		success : function(data) {
			$("#_like_count").html(data.likecount);
			$("#_hate_count").html(data.hatecount);
			alert(data.message);
		}
	});
}
function getcomment(){
    $.ajax({
         url:"getCommentList.jy",
         data: {boardseq: "${boarddetail.seq}"},
         success : function(data) {
            var a = "<table class='reply_table' >" +
            	"<colgroup>" +
				"<col style='width: auto;' />" +
					"<col style='width: 5%;' />" +
					"<col style='width: 10%;' />" +
					"<col style='width: 3%;' />" +
					"<col style='width: 3%;' />" +
					"<col style='width: 3%;' />" +
				"</colgroup>"
            $.each(data,function(i,obj){
            if(obj.seqReply==0) {
               a += "<tr>";
               if(obj.delflag==0) {
            	   a += "<td style='text-align: left' class='reply_content hover_cursor' onclick=\"comment_comment('"+obj.seq+"')\">";
	               a += obj.content; 
	               a += "</td><td>" + 
	               obj.id + "</td><td class='_comment_wdate'>" + obj.wdate + "</td><td>"
	               + obj.likecount + "</td><td>" + obj.hatecount + "</td>";
	               if($("#_login_id").val()==obj.id) {
	            	   a += "<td><i class='fa fa-times hover_cursor' onclick=\"delete_comment('"+obj.seq+"')\"></i></td>";
	               }
	               a += "</tr>";
               } else {
            	   a += "<td colspan='5'>삭제되었노라</td>" +
            	   "</tr>";
               }
               
               a += "<c:if test='${not empty login.id }'>" + 
               "<tr style='display:none' id='_reply_input"+obj.seq+"' class='_reply_inputs'>"+"<td colspan='5'>"+ 
               "<input type='text' id='_reply_txt"+obj.seq+"'/><input type='button' value='댓글입력' id='_reply_btn"+ 
               obj.seq+"' onclick='reply_comment("+obj.seq+")'>"+ 
               "</td>"+"</tr>" +
               "</c:if>";
            } else {
            	a += "<tr>";
            	if(obj.delflag==0) {
 	               a += "<td style='text-align: left' class='reply_content' >&nbsp;&nbsp;&nbsp;"+obj.content;
 	              a += "</td><td>" + obj.id + "</td><td class='_comment_wdate'>" + obj.wdate + "</td><td>"
 	                + obj.likecount + "</td><td>" + obj.hatecount + "</td>";
 	                if($("#_login_id").val()==obj.id) {
 	             	   a += "<td><i class='fa fa-times hover_cursor' onclick=\"delete_comment('"+obj.seq+"')\"></i></td>";
 	                }
 	                a +="</tr>";
                }else {
                	a += "<td colspan='5'>&nbsp;&nbsp;&nbsp;삭제되었노라</td>"+
            	   "</tr>";
                }
                
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
			 seq : seq,
			 boardseq : "${boarddetail.seq}"
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
	        boardseq : "${boarddetail.seq}",
	        content : $("#_reply_txt"+seq).val(),
	        seqReply : seq
		},
		success:function(data) {
			$("#_reply_txt"+seq).val("");
			getcomment();
		}
	 });
}
var ri=null;
var rit=0;
function comment_comment(seq) {
	 $("#_reply_input"+seq).toggle("slow");
	if(ri!=$("#_reply_input"+seq)&&ri!=null) {
		ri.hide("slow");
	}
	 ri=$("#_reply_input"+seq);
}
getcomment();

$(".write_btn").click(function() {  
   $.ajax({
      type:'post',
      url:"writeComment.jy",
      dataType:'text',
      data : {
         id : $("#_comment_id").val(),
         boardseq : "${boarddetail.seq}",
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