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
<link href="<c:url value='/resources/css/projinsert.css'/>" rel="stylesheet" type="text/css">
<script src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
<script src="<c:url value='/resources/js/projcheck.js'/>"></script>
<script src="<c:url value='/resources/js/index.js'/>"></script>
</head>
<body>
	<section id="mainWrap">
		<h1>프로젝트 등록폼</h1>
		<div id="codeInsert">
			<form method="post" id="codeInsertForm" onsubmit="return validate(this)">
				<ul>
					 <li id="codeli">
	                	<label>프로젝트코드</label><br>
	                	<input type="number" id="proj_code" name="proj_code" placeholder="종류,년도,번호입력,예)p2201">
	                	<button class="projcodecheck" id="projcodecheck">중복확인</button>
	                	<span id="checkresult"></span>
	                </li>
	                <li>
	                	<label>업체</label><br>
	                	<input type="text" id="proj_buyer" name="proj_buyer">
	                </li>
	                <li>
	                	<label>프로젝트명</label><br>
	                	<input type="text" id="proj_nm" name="proj_nm">
	                </li>
	                <li>
	                	<label>계약금액</label><br>
	                	<input type="number" id="proj_amount" name="proj_amount" placeholder="숫자만 입력">
	                </li>
	               <li>
	                	<label>시작날짜</label><br>
	                	<input type="date" id="proj_start_date" name="proj_start_date">
	                	</li>
	                <li>
	                	<label>완성날짜</label><br>
	                	<input type="date" id="proj_end_date" name="proj_end_date">
	                	</li>
				</ul>
				<div id="btn">
				<input type="submit" value="등록">
				<input type="reset" value="취소">
				</div>
			</form>		
		</div>
	</section>
</body>
</html>