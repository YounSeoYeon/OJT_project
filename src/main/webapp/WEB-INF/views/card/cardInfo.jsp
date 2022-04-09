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
			<div id="contents">
				<div id="tabButtons">
					<button class="selected">카드 계정</button>
					<button>업체</button>
					<button>프로젝트</button>
					<button>지출</button>
				</div>
				<div id="options">
					<div id="cardTypes">
						<input type="radio" id="all" name="card_type" value="-1" checked />
		  				<label for="all">전체</label>
						<input type="radio" id="company" name="card_type" value="0" />
		  				<label for="company">법인</label>
						<input type="radio" id="personal" name="card_type" value="1" />
		  				<label for="personal">개인</label>
	  					<input type="radio" id="reset" name="card_type" value="2" />
		  				<label for="reset">초기화</label>
					</div>
					<div id="searchBar">
						<!-- 검색 키워드 선택 -->
						<select class="filter">
							<option value="card_name" selected>카드 명의자</option>
							<option value="card_no">카드 번호</option>
							<option value="card_ep">유효 기간</option>
							<option value="card_id">카드 관리 계정</option>
						</select>
						<input type="text" id="searchInput" name="search" placeholder="검색 키워드 입력" />
						<input type="button" class="searchBtn" value="검색" />
					</div>
				</div>
				<div id="cardInfo">
					<!-- 카드 정보 목록 부분 - Ajax 처리 -->
				</div>
				<div id="bottomButtons">
					<!-- pagination -->
					<div id="paginationBox"></div>
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