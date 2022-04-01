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
<script src="<c:url value='./resources/js/buscheck.js'/>"></script>
</head>
<body>
	<section id="mainWrap">
		<h1>업체코드등록폼</h1>
		<!--  action="./dbinsert" -->
		<div id="codeInsert">
		<form class="codeInsertForm" name="codeInsertForm" onsubmit="validate()" method="post" enctype="multipart/form-data">
			<ul>
				 <li id="codeli">
                	<label>업체코드</label><br>
                	<input type="text" id="bus_code" name="bus_code">
                	<button class="buscodecheck" id="buscodecheck">중복확인</button>
                	<span id="checkresult"></span>
                </li>
                <li>
                	<label>대표자명</label><br>
                	<input type="text" id="bus_rep" name="bus_rep">
                </li>
                <li>
                	<label>상호</label><br>
                	<input type="text" id="bus_nm" name="bus_nm">
                </li>
                <li>
                	<label>사업자 등록번호</label><br>
                	<input type="text" id="bus_reg_no" name="bus_reg_no">
                </li>
                <li>
                	<label>종목명</label><br>
                	<input type="text" id="bus_item" name="bus_item">
                </li>
                <li>
                	<label>전화번호</label><br>
                	<input type="text" id="bus_tel1" name="bus_tel1">&nbsp;<input type="text" id="bus_tel2" name="bus_tel2">&nbsp;<input type="text" id="bus_tel3" name="bus_tel3">
                </li>
                <li>
                	<label>팩스번호</label><br>
                	<input type="text" id="bus_fax1" name="bus_fax1">&nbsp;<input type="text" id="bus_fax2" name="bus_fax2">&nbsp;<input type="text" id="bus_fax3" name="bus_fax3">
                </li>
                <li>
                	<label>주소</label><br>
                	<input type="text" id="busAddress" name="busAddress">
                </li>
                <li>
                	<label>대표이메일</label><br>
                	<input type="text" id="bus_email" name="bus_email">@<input type="text">
                </li>
                <!-- <li class="insertBtn">
	                <span id="checkAll">등록</span>
                </li> -->
			</ul>
			<input type="submit" value="등록">
		</form>
		</div>
	</section>
</body>
</html>