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
<link href="<c:url value='/resources/css/business/businsert.css'/>" rel="stylesheet" type="text/css">
<script src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
<script src="<c:url value='/resources/js/business/buscheck.js'/>"></script>
<script src="<c:url value='/resources/js/business/index.js'/>"></script>
</head>
<body>
	<section id="mainWrap">
		<h1>업체코드등록폼</h1>
		<!--  action="./dbinsert" -->
		<!--  onsubmit="validate()"  -->
		<!--  class="codeInsertForm" name="codeInsertForm"  -->
		<!-- onsubmit누르면 일단 유효성검사 하고 action적용됨 -->
		
		<div id="codeInsert">
			<form method="post" id="codeInsertForm" onsubmit="return validate(this)">
				<ul>
					 <li id="codeli">
	                	<label>업체코드</label><br>
	                	<input type="text" id="bus_code" name="bus_code" placeholder="예) p001/c001">
	                	<button class="buscodecheck" id="buscodecheck">중복확인</button>
	                	<span id="checkresult"></span>&nbsp;&nbsp;<label>(공기업:p 민간기업:c) 코드앞에 넣어서 입력하세요 예) p001/c001</label>
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
	                	<input type="text" id="bus_reg_no" name="bus_reg_no" placeholder="10자리 숫자만 입력하세요" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">&nbsp;&nbsp;<label>사업자번호 숫자만 입력</label>
	                </li>
	                <li>
	                	<label>종목명</label><br>
	                	<input type="text" id="bus_item" name="bus_item">
	                </li>
	               <li>
	                	<label>전화번호</label><br>
	                	<input type="text" id="bus_tel1" name="bus_tel" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">&nbsp;
	                	<input type="text" id="bus_tel2" name="bus_tel" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">&nbsp;
	              		<input type="text" id="bus_tel3" name="bus_tel" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
	                </li>
	                <li>
	                	<label>팩스번호</label><br>
	                	<input type="text" id="bus_fax1" name="bus_fax" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">&nbsp;
	                	<input type="text" id="bus_fax2" name="bus_fax" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">&nbsp;
	                	<input type="text" id="bus_fax3" name="bus_fax" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
	                </li>
	                <li>
	                	<label>주소</label><br>
	                	<input type="text" id="busAddress" name="busAddress">
	                </li>
	                <li>
	                	<label>대표이메일</label><br>
	                	<input type="text" id="bus_email1" name="bus_email">@<input type="text" id="bus_email2" name="bus_email">
	                </li>
	                <!-- <li class="insertBtn">
		                <span id="checkAll">등록</span>
	                </li> -->
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