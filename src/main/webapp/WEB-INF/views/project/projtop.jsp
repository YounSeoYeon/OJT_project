<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 리스트</title>
</head>
<body>
<c:forEach items="${vo}" var="vo" begin="0" varStatus="status">
	<div class="top">
		<div class="check">
			<input type="checkbox" id="bustype" name="project" value="${vo.proj_idx}">
		</div>
		<div class="nm">${vo.proj_nm}</div>
		<div class="amount">${amountList[status.index]} 원</div>
		<div class="code">${vo.proj_code }</div>
		<div class="buyer">${vo.proj_buyer}</div>
		<div class="date">시작 : ${vo.proj_start_date}<br>마감 : ${vo.proj_end_date}
		</div>
	</div>
	<hr>
</c:forEach>
</body>
</html>