<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>카드 계정 관리</title>
		<link rel="stylesheet" href="<c:url value='/resources/css/card/cardInfo.css'/>" />
		<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/card/cardInfo.js'/>"></script>
	</head>
	<body>
		<div id="wrapper">
			<h1>코드 관리</h1>
			<div id="content">
				<div id="tabButtons">
					<button class="selected">카드 계정</button>
					<button>업체</button>
					<button>프로젝트</button>
					<button>지출</button>
				</div>
				<div id="cardTypes">
					<input type="radio" id="all" name="card_type" value="-1" checked />
	  				<label for="all">전체</label>
					<input type="radio" id="company" name="card_type" value="0" />
	  				<label for="company">법인</label>
					<input type="radio" id="personal" name="card_type" value="1" />
	  				<label for="personal">개인</label>
				</div>
				<div id="cardInfo">
	  				<table>
	  					<thead>
	  						<tr>
								<th><input type="checkbox" id="checkAll"/></th>
								<th>카드 명의자</th>
								<th>카드 번호</th>
								<th>유효 기간</th>
								<th>카드 관리 계정</th>
							</tr>
	  					</thead>
	  					<tbody id="cardList"></tbody>
	  				</table>
	  				<div id="submitButtons">
	  					<button class="addBtn">추가</button>
	  					<button class="updateBtn">수정</button>
	  					<button class="deleteBtn">삭제</button>
	  				</div>
				</div>
			</div>
		</div>
		<script>
			
		</script>
	</body>
</html>