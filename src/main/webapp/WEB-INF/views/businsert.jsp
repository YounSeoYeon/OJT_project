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
</head>
<body>
	<section id="codeinsertform">
		<h1>업체코드등록폼</h1>
		<form class="form" method="post">
			<ul>
				 <li>
                	<label>업체코드</label><br>
                	<input type="text"placeholder=" ">
                	<button class="" id="">중복확인</button>
                </li>
                <li>
                	<label>대표자명</label><br>
                	<input type="text"placeholder=" ">
                </li>
                <li>
                	<label>상호</label><br>
                	<input type="text">
                </li>
                <li>
                	<label>사업자 등록번호</label><br>
                	<input type="text">
                </li>
                <li>
                	<label>종목명</label><br>
                	<input type="text">
                </li>
                <li>
                	<label>전화번호</label><br>
                	<input type="text">&nbsp;<input type="text">&nbsp;<input type="text">
                </li>
                <li>
                	<label>팩스번호</label><br>
                	<input type="text">&nbsp;<input type="text">&nbsp;<input type="text">
                </li>
                <li>
                	<label>주소</label><br>
                	<input type="text">
                </li>
                <li>
                	<label>대표이메일</label><br>
                	<input type="text">@<input type="text">
                </li>
                <li class="">
	                <button>등록</button>
                </li>
			</ul>
		</form>
	</section>
</body>
</html>