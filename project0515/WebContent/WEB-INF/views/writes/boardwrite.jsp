<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:requestEncoding value="utf-8" />
<script type="text/javascript">
	function map_event() {
		$("#_map_select").css("display", "block");
		google.maps.event.trigger(map, "resize");			
	}
	$(function() {
		$("#_free_write_btn").click(function(e) {
			$("#_hidden_content").attr("value", $("._content").html());
			$("#_freewrite").attr("action","boardwriteAf.jy").submit();
		});
		initMap();
		$.ajax({
			url : "region.jy",
			success : function(data) {
				var s="";
				$.each(data,function(index, value){
					s=s+"<option value='"+index+"' >"
					+value+"</option>"
				});
				$("._region_select").html(s);
			}
		});
		$("._region_select").click(function() {
			$("._regionid").attr("value", $("._region_select option:selected").val());
		});
	});
</script>
<script>
	function initMap() {
		var seoul = {
			lat : 37.566631,
			lng : 126.978370
			//시청
		};
		var markers = [];
		var map = new google.maps.Map(document.getElementById('map'), {
			zoom : 16,
			center : seoul
		});
		google.maps.event.addListener(map, 'click', function(event) {
			placeMarker(event.latLng);
		});
		function placeMarker(location) {
			clearMarkers();
			deleteMarkers();
			var marker = new google.maps.Marker({
				position : location,
				map : map
			});
			marker.setMap(map);
			markers.push(marker);
			map.setCenter(location);
			$("._location_latlng").attr("value", location);
		}
		function setMapOnAll(map) {
			for (var i = 0; i < markers.length; i++) {
				markers[i].setMap(map);
			}
		}
		function clearMarkers() {
			setMapOnAll(null);
		}
		function deleteMarkers() {
			clearMarkers();
			markers = [];
		}
	}
</script>
<script type="text/javascript">
$(function() {
	$("._content").on("click", function() {
		$(this).focus();
	});
});
</script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBsfCP6fzs0kw5N8hmmptZAgV5uChrSyA8&callback=initMap">
</script>
<div class="boardwrite_wrap">
	<div class="doc_title">${doc_title}</div>
	<div class="write_table">
		<form action=""   id="_freewrite" method="POST" enctype="multipart/form-data">
		<input type="hidden" value="${login.id }" name="id">
		<input type="hidden" value="${catid }" name="catid">
			<span>제목</span> <input type="text" name="title" class="_title"> 
			<span>사진</span> <input type="file" name="fileload" class="_upload"> 
			<span>지도</span> <div class="_location">
				<img src="<%=request.getContextPath()%>/img/map.png"
					class="_map_icon" id="_map_icon_id" onclick='map_event()' />
					<input type="hidden" name="location" class="_location_latlng" value="none">
					<input type="hidden" name="regionid" class="_regionid" value="0">
			</div>
			<span>내용</span>
			<div class="_content" onClick="this.contentEditable='true';"></div>
			<input type="hidden" name="content" id="_hidden_content">
		</form>
		<div class="write_btn">
			<img src="<%=request.getContextPath()%>/img/write.gif"
				class="_write_btn" id="_free_write_btn" />
		</div>
	</div>
</div>
<div class="modal" id="_map_select">
<span onclick='$("#_map_select").css("display", "none")' class="close"
		title="Close Map">&times;</span>
	<div class="container modal-content">
		<div id="map"></div>
		<div id="region">
			<select class="_region_select">
			
			</select>
		</div>
	</div>
</div>
