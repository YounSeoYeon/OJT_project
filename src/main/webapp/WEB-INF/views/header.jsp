<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<style type="text/css">
button {
	width: 100px;
	padding: 10px;
	font-size: 16px;
	background-color: white;
	border-radius: 10px;
	cursor: pointer;
	margin: 0 10px;
}

button:hover {
	color: white;
	background-color: gray;
}

#tabButtons button:first-child,
#submitButtons button:first-child {
	margin: 0 10px 0 0;
}

#tabButtons {
	margin-bottom: 30px;
}

#tabButtons button.selected {
	color: white;
	background-color: black;
}
</style>
</head>
<body>
	<header>
		<div id="tabButtons">
			<button onclick="location.href= '/'">카드 계정</button>
			<button onclick="location.href='/index'">업체</button>
			<button onclick="location.href='/projindex'">프로젝트</button>
			<button onclick="location.href='#'">지출</button>
		</div> 
	</header>
</body>
</html>
