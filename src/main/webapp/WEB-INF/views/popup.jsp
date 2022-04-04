<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>등록후 popup</title>
<link href="<c:url value='/resources/css/popup.css'/>" rel="stylesheet" type="text/css">
<script src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
<script src="<c:url value='/resources/js/popup.js'/>"></script>
</head>
<body>
	<section id="mainWrap">
		<h1>등록후 팝업창</h1>
		<div id="codeMiddle">
			<h3>완료되었습니다</h3>
			<ul id="popupmenu">
				<!-- <li class="list" onClick="self.close();">업체리스트</li> -->
				<li class="check" onClick="self.close();">관리하기</li>
			</ul>
		</div>
	</section>
</body>
</html>
