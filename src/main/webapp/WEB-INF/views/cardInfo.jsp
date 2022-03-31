<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>카드 계정 관리</title>
		<link rel="stylesheet" href="<c:url value='/resources/css/cardInfo.css' />" />
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/cardInfo.js' />"></script>
	</head>
	<body>
		<div id="wrapper">
			<h1>코드 관리</h1>
			<form id="content">
				<div id="tabButtons">
					<button class="selected">카드 계정</button>
					<button>업체</button>
					<button>프로젝트</button>
					<button>지출</button>
				</div>
				<div id="cardTypes">
					<input type="radio" id="all" name="cardType" value="all" checked />
	  				<label for="all">전체</label>
					<input type="radio" id="company" name="cardType" value="company" />
	  				<label for="company">법인</label>
					<input type="radio" id="personal" name="cardType" value="personal" />
	  				<label for="personal">개인</label>
				</div>
				<div id="cardInfoList">
	  				<table>
	  					<tr>
	  						<th><input type="checkbox" id="checkAll"/></th>
	  						<th>카드 명의자</th>
	  						<th>카드 번호</th>
	  						<th>유효 기간</th>
	  						<th>카드 관리 계정</th>
	  						<th>카드 유형</th>
	  					</tr>
	  					<c:choose>
	  						<c:when test="${ not empty cardList }">
		  						<c:forEach items="${cardList}" var="card" >
			  						<tr>
			  							<td><input type="checkbox" id="${card.card_idx}" /></td>
			  							<td>${card.card_name}</td>
			  							<td>${card.card_no}</td>
			  							<td>${card.card_ep}</td>
			  							<td>${card.card_id}</td>
			  							<td>${card.card_type}</td>
			  						</tr>
			  					</c:forEach>
		  					</c:when>
		  					<c:otherwise>
		  						<tr>
		  							<td colspan="5">카드 정보가 존재하지 않습니다.</td>
		  						</tr>
		  					</c:otherwise>
	  					</c:choose>
	  				</table>
	  				<div id="submitButtons">
	  					<button class="addBtn">추가</button>
	  					<button>삭제</button>
	  				</div>
				</div>
			</form>
		</div>
		<script>
			
		</script>
	</body>
</html>