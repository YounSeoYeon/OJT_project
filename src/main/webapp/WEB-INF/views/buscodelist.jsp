<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>index</title>
<link href="<c:url value='/resources/css/index.css'/>" rel="stylesheet" type="text/css">
<script src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
</head>
<body>
	<section id="mainWrap">
		<h1>업체 전체보기</h1>
		<div id="codeMiddle">
			<ul id="codemenu">
				<li>카드계정</li>
				<li>업체</li>
				<li>프로젝트</li>
				<li>지출</li>
			</ul>
			<form id="codemenuBus"> 
				<div class="codesearch">
					<div class="bustype">
						<input type="radio" id="all" name="bustype" value="전체"><label>전체</label>
						<input type="radio" id="public" name="bustype" value="공공"><label>공공</label> 
						<input type="radio" id="corp" name="bustype" value="민간"><label>민간</label>
					</div>
					<div class="search">
						<label>업체검색</label>&nbsp;&nbsp;<input type="text" name="search" placeholder="키워드검색">
						&nbsp;<input class="searchbtn" type="submit" value="검색">
					</div>
				</div>
				<hr>
				<div class="bus_list_wrap">
					<div class="bus_list">
						<div class="top first">
								<div class="check">인덱스</div>
								<div class="name">업체명</div>
								<div class="number">사업자번호</div>
								<div class="ceo">대표자</div>
								<div class="buscode">업체코드</div>
								<div class="etc">기타</div>
						</div>
					<hr>
						<c:forEach items="${vo}" var="buslist" begin="0">
								<div class="top">
									<div class="check">${buslist.bus_idx}</div>
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
				<a href="<c:url value='/index'/>">관리하기</a>
				</div>
			</form>
		</div>
	</section>
</body>
</html>
