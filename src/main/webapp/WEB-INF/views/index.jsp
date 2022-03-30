<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>index</title>
</head>
<body>
	<h1>index입니다 ${serverTime}</h1>
	<section id="mainWrap">
		<h1>코드관리-업체</h1>
		<div>
			<ul id="codemenu">
				<li>카드계정</li>
				<li>업체</li>
				<li>프로젝트</li>
				<li>지출</li>
			</ul>
			<form>

				<!-- <select id="bustype">
				<option value="1">공공</option>
				<option value="2">민간</option>
				</select> -->
 
				<input type="radio" id="public" name="bustype" value="공공"><label>공공</label> 
				<input type="radio" id="corp" name="bustype" value="민간"><label>민간</label>
					
				공공<input type="checkbox" value="공공" name="bustype"> 
				민간<input type="checkbox" value="민간" name="bustype">
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
					 	</c:forEach>
					</div>				
				</div>
				
				
				<hr>
				
				<div>추가</div>
				<div>삭제</div>
			</form>
		</div>
	</section>
</body>
</html>
