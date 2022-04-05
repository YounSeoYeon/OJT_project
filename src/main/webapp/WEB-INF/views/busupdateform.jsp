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
<link href="<c:url value='/resources/css/businsert.css'/>" rel="stylesheet" type="text/css">
<script src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
<script src="<c:url value='/resources/js/busupdatecheck.js'/>"></script>
<script src="<c:url value='/resources/js/index.js'/>"></script>
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
	                	<input type="text" id="bus_reg_no" name="bus_reg_no" value="${vo.bus_reg_no}">
	                </li>
	                <li>
	                	<label>종목명</label><br>
	                	<input type="text" id="bus_item" name="bus_item" value="${vo.bus_item}">
	                </li>
<%-- 	               <li>
	                	<label>전화번호</label><br>
	                	<c:forEach items="${fn:split('${vo.bus_tel}', ',')}" var="item">
	                		<input type="number" id="bus_tel1" name="bus_tel" value=${item }/>&nbsp;<input type="number" id="bus_tel2" name="bus_tel" value="${item}">&nbsp;<input type="number" id="bus_tel3" name="bus_tel" value="${item}">
	                	</c:forEach>
	                </li> --%>
	                <li>
	                	<label>전화번호</label><br>
	                	<input type="number" id="bus_tel1" name="bus_tel" value="${vo.bus_tel}"/>
	                </li>
<%-- 	                <li>
	                	<label>팩스번호</label><br>
	                	<c:forEach items="${fn:split('${vo.bus_fax}', ',')}" var="item">
	                		<input type="number" id="bus_fax1" name="bus_fax" value="${item}">&nbsp;<input type="number" id="bus_fax2" name="bus_fax" value="${item}">&nbsp;<input type="number" id="bus_fax3" name="bus_fax" value="${item}">
	                	</c:forEach>
	                </li> --%>
	                 <li>
	                	<label>팩스번호</label><br>
	                	<input type="number" id="bus_fax1" name="bus_fax" value="${vo.bus_fax}"/>
	                </li>
	                <li>
	                	<label>주소</label><br>
	                	<input type="text" id="busAddress" name="busAddress" value="${vo.busAddress}">
	                </li>
	                <%-- <li>
	                	<label>대표이메일</label><br>
	                	<c:forEach items="${fn:split('${vo.bus_email}', ',')}" var="item">
	                		<input type="text" id="bus_email1" name="bus_email" value=${item}>@<input type="text" id="bus_email2" name="bus_email" value="${item}">
	                	</c:forEach>
	                </li> --%>
	                <li>
	                	<label>대표이메일</label><br>
	                	<input type="text" id="bus_email1" name="bus_email" value="${vo.bus_email}" />
	                </li>
	                <!-- <li class="insertBtn">
		                <span id="checkAll">등록</span>
	                </li> -->
				</ul>
				<input type="submit" value="수정">
				<input type="reset" value="취소">
			</form>
		</div>
	</section>
</body>
</html>