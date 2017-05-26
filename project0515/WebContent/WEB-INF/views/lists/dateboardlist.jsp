<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />

<div class="boardlist_wrap">
	<div class="doc_title">${doc_title}</div>

	<table border=1>
		<tr>
			<td>날자선택</td>
			<td><select id="year" name="year">
			</select></td>
			<td><select id="month" name="month">
			</select></td>
		</tr>
	</table>
	<input type="button" id="getdateboard" value="가져오기">
</div>

<table class="list_table" style="width: 85%;">
	<tbody id="info"></tbody>
</table>

<script>
	$().ready(function() {
		var sYear = 2000
		var eYear = 2019
		var sMonth = 1
		var eMonth = 12

		var strYear = "";
		var strMonth = "";

		for (var i = sYear; i <= eYear; i++) {
			strYear += "<option value="+i+">" + i + "</option>";
		}

		for (var i = sMonth; i <= eMonth; i++) {
			strMonth += "<option value="+i+">" + i + "</option>";
		}

		$("#year").html(strYear);
		$("#month").html(strMonth);

	})

	$("#getdateboard")
			.click(
					function() {
						var year = $("#year option:selected").val();
						var month = $("#month option:selected").val();
						var adate = new Date(year + "-" + month);
						var bdate = dateToYYYYMMDD(adate);

						$.ajax({
									url : 'getDateBoardList.jy',
									type : 'POST',
									data : {
										'wdate' : bdate
									},
									success : function(data) {
										console.log(data);
										base(data);
									},
									error : function() {
										console.log("eee");
									}

								})
					})
	function dateToYYYYMMDD(date) {
		function pad(num) {
			num = num + '';
			return num.length < 2 ? '0' + num : num;
		}

		return date.getFullYear() + '-' + pad(date.getMonth() + 1);
	}
	
	function base(data) {
		
		str = "<tr><td>날자</td><td>제목</td><td>아이디</td></tr>";
		$.each(data, function(i) {
			str += "<tr><td>" + data[i].wdate
					+ "</td><td><a href=boarddetail.jy?seq="+data[i].seq+">"+data[i].title
					+ "</a>"+"</td>" + "<td>"
					+ data[i].id
					+ "</td></tr>"

		})
		$("#info").html(str);
	}
</script>
