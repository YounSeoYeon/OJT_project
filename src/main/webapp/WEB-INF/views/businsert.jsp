<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업체코드등록</title>
<link href="<c:url value='./resources/css/businsert.css'/>" rel="stylesheet" type="text/css">
<script src="<c:url value='./resources/js/jquery-3.6.0.min.js'/>"></script>
<script src="<c:url value='./resources/js/businsert.js'/>"></script>
</head>
<body>
	<section id="mainWrap">
		<h1>업체코드등록폼</h1>
		<div id="codeInsert">
		<form class="form">
			<ul>
				 <li id="codeli">
                	<label>업체코드</label><br>
                	<input type="text" id="buscode">
                	<button class="buscodecheck" id="buscodecheck">중복확인</button>
                	<span id="checkresult"></span>
                </li>
                <li>
                	<label>대표자명</label><br>
                	<input type="text" id="busRep">
                </li>
                <li>
                	<label>상호</label><br>
                	<input type="text" id="busNm">
                </li>
                <li>
                	<label>사업자 등록번호</label><br>
                	<input type="text" id="busRegNo">
                </li>
                <li>
                	<label>종목명</label><br>
                	<input type="text" id="busItem">
                </li>
                <li>
                	<label>전화번호</label><br>
                	<input type="text" id="busTel1">&nbsp;<input type="text" id="busTel2">&nbsp;<input type="text" id="busTel3">
                </li>
                <li>
                	<label>팩스번호</label><br>
                	<input type="text" id="busFax1">&nbsp;<input type="text" id="busFax2">&nbsp;<input type="text" id="busFax3">
                </li>
                <li>
                	<label>주소</label><br>
                	<input type="text" id="busAddress">
                </li>
                <li>
                	<label>대표이메일</label><br>
                	<input type="text" id="busEmail">@<input type="text">
                </li>
                <li class="insertBtn">
	                <span id="checkAll">등록</span>
                </li>
			</ul>
		</form>
		</div>
	</section>
</body>
</html>