<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>index</title>
<link href="<c:url value='./resources/css/index.css'/>" rel="stylesheet" type="text/css">
<script src="<c:url value='./resources/js/jquery-3.6.0.min.js'/>"></script>
<script src="<c:url value='./resources/js/index.js'/>"></script>
</head>
<body>
	<section id="mainWrap">
		<h1>코드관리-업체</h1>
		<div id="codeMiddle">
			<ul id="codemenu">
				<li>카드계정</li>
				<li>업체</li>
				<li>프로젝트</li>
				<li>지출</li>
			</ul>
			<form id="codemenuBus"> 
				<div class="bustype">
					<input type="radio" id="all" name="bustype" value="전체"><label>전체</label>
					<input type="radio" id="public" name="bustype" value="공공"><label>공공</label> 
					<input type="radio" id="corp" name="bustype" value="민간"><label>민간</label>
				</div>
				<hr>
				<div class="bus_list_wrap">
					<div class="bus_list">
						<div class="top">
								<div class="check"><input type="checkbox" value="allcheck" name="bustype"></div>
								<div class="name">업체명</div>
								<div class="number">사업자번호</div>
								<div class="ceo">대표자</div>
								<div class="buscode">업체코드</div>
								<div class="etc">기타</div>
						</div>
					<hr>
						<c:forEach items="${vo}" var="buslist" begin="0">
								<div class="top">
									<div class="check"><input type="checkbox" value="allcheck" name="bustype"></div>
									<div class="name">${buslist.bus_nm}</div>
									<div class="number">${buslist.bus_reg_no}</div>
									<div class="ceo">${buslist.bus_rep}</div>
									<div class="buscode">${buslist.bus_code}</div>
									<div class="etc">${buslist.bus_tel}</div>
								</div>
					<hr>
					 	</c:forEach>
					</div>				
				</div>				
				<div class="inoutBtn">
					<span class="in">추가</span>
					<span class="modify">수정</span>
					<span class="out">삭제</span>
				</div>
			</form>
		</div>
	</section>
</body>
</html>
