<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>카드 계정 관리</title>
	</head>
	<body>
		<main>
			<div>
				<button>카드 계정</button>
				<button>업체</button>
				<button>프로젝트</button>
				<button>지출</button>
			</div>
			<div>
				<input type="radio" id="all" name="cardType" value="company" checked>
  				<label for="all">전체</label>
				<input type="radio" id="company" name="cardType" value="company">
  				<label for="company">법인</label>
				<input type="radio" id="personal" name="cardType" value="personal">
  				<label for="personal">개인</label>
  				
  				<table border="1">
  					<tr>
  						<th><input type="checkbox" id="checkAll"/></th>
  						<th>이름</th>
  						<th>카드번호</th>
  						<th>유효기간</th>
  						<th>카드 계정 코드</th>
  					</tr>
  					<c:forEach items="${cardList}" var="card" >
  						<tr>
  							<td><input type="checkbox" id="${card.card_idx}" /></td>
  							<td>${card.card_name}</td>
  							<td>${card.card_no}</td>
  							<td>${card.card_ep}</td>
  							<td>${card.card_id}</td>
  						</tr>
  					</c:forEach>
  				</table>
  				<button>추가</button>
  				<button>삭제</button>
			</div>
		</main>
	</body>
</html>