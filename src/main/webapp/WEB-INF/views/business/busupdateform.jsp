<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업체코드수정폼</title>
<!-- resources 경로 servlet-context에 지정해놓음 -->
<link href="<c:url value='/resources/css/business/businsert.css'/>" rel="stylesheet" type="text/css">
<script src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
<script src="<c:url value='/resources/js/business/busupdatecheck.js'/>"></script>
<script src="<c:url value='/resources/js/business/index.js'/>"></script>
</head>
<body>
	<section id="mainWrap">
		<h1>업체코드 수정폼</h1>		
		<div id="codeInsert">
			<form method="post" action="<c:url value='/busupdate/${vo.bus_idx}'/>" id="codeInsertForm" onsubmit="return validate(this)">
				<ul>
					 <li id="codeli">
	                	<label>업체코드</label><br>
	                	<input name="bus_idx" value="${vo.bus_idx}" readonly="readonly" />
	                	<input type="text" id="bus_code" name="bus_code" value="${vo.bus_code}" readonly="readonly" />
	                	
	                	<!-- 업체코드는 변경 못하도록 -->
	                	<!-- <button class="buscodecheck" id="buscodecheck">중복확인</button>
	                	<span id="checkresult"></span> -->
	                </li>
	                <li>
	                	<label>대표자명</label><br>
	                	<input type="text" id="bus_rep" name="bus_rep" value="${vo.bus_rep}">
	                </li>
	                <li>
	                	<label>상호</label><br>
	                	<input type="text" id="bus_nm" name="bus_nm" value="${vo.bus_nm}">
	                </li>
	                <li>
	                	<label>사업자 등록번호</label><br>
	                	<input type="text" id="bus_reg_no" name="bus_reg_no" value="${vo.bus_reg_no}" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
	                </li>
	                <li>
	                	<label>종목명</label><br>
	                	<input type="text" id="bus_item" name="bus_item" value="${vo.bus_item}">
	                </li>
	                <li>
	                	<label>전화번호</label><br>
	                	<input type="text" id="bus_tel1" name="bus_tel" value="${vo.bus_tel}" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>
	                </li>
	                 <li>
	                	<label>팩스번호</label><br>
	                	<input type="text" id="bus_fax1" name="bus_fax" value="${vo.bus_fax}" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>
	                </li>
	                <li>
	                	<label>주소</label><br>
	                	<input type="text" id="busAddress" name="busAddress" value="${vo.busAddress}">
	                </li>
	                <li>
	                	<label>대표이메일</label><br>
	                	<input type="text" id="bus_email1" name="bus_email" value="${vo.bus_email}" />
	                </li>
				</ul>
				<input type="submit" value="수정">
				<input type="reset" value="취소">
			</form>
		</div>
	</section>
</body>
</html>