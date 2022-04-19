<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업체 리스트</title>
</head>
<body>
<c:forEach items="${vo}" var="vo" begin="0">
	<div class="top">
		<div class="check">
			<%-- <input type="checkbox" id="bustype" name="bustypes" value="${vo.bus_idx}"> --%>
			<input type="checkbox" id="check" name="bustypes" value="${vo.bus_idx}">
		</div>
		<div class="buscode">${vo.bus_code}</div>
		<div class="name">${vo.bus_nm}</div>
		<div class="busnum">${vo.bus_reg_no }</div>
		<div class="ceo">${vo.bus_rep}</div>
		<div class="etc">${vo.bus_tel}</div>
	</div>
	<hr>
</c:forEach>
</body>
</html>