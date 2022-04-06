<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<script src="<c:url value='/resources/js/header.js'/>"></script>
</head>
<body>
	<header>
		<h1>코드관리</h1>
		<ul id="codemenu">
			<li class="topButton"><input type="hidden" value="card"/>카드계정</li>
			<li class="topButton"><input type="hidden" value="business"/>업체</li>
			<li class="topButton"><input type="hidden" value="project"/>프로젝트</li>
			<li class="topButton"><input type="hidden" value="expenditure"/>지출</li>
		</ul>
	</header>
</body>
</html>
