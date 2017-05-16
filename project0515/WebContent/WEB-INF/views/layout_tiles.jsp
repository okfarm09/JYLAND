<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />
<tiles:insertAttribute name="common" />
<!DOCTYPE html>
<html>
<head>
<tiles:insertAttribute name="header" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/modal.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/write.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/font-awesome/css/font-awesome.min.css" />
<style type="text/css">
</style>
</head>
<body>
	<div class="body_wrap">
		<div class="header_wrap">
			<div class="bg_wrap">
				<tiles:insertAttribute name="top_inc"/>
				<tiles:insertAttribute name="top_member"/>
			</div>
			<div class="nav_wrap">
				<tiles:insertAttribute name="top_menu"/>
			</div>
		</div>

		<div class="content_wrap">
			<tiles:insertAttribute name="main"/>
		</div>

		<div class="footer_wrap">
			<tiles:insertAttribute name="bottom_inc"/>
		</div>
	</div>

	<div class="chat_wrap"><tiles:insertAttribute name="chat_addon"/></div>
</body>
</html>