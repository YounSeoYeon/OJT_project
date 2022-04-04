<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>카드 계정 수정</title>
		<link rel="stylesheet" href="<c:url value='/resources/css/updateCard.css' />" />
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/updateCard.js' />"></script>
	</head>
	<body>
		<div id="wrapper">
			<h1>카드 계정 수정</h1>
			<form id="updateCardForm">
				<div>
					<label for="card_idx">카드 계정</label>
					<input type="text" name="card_idx" value="${card.card_idx}" readonly />
				</div>
				
				<div id="cardNoArea"> 
					<label for="card_no">카드 번호</label>
					<input type="text" class="card_no num" name="card_no1" maxlength='4' value="${(card.card_no).split('-')[0]}"/> -
					<input type="text" class="card_no num" name="card_no2" maxlength='4' value="${(card.card_no).split('-')[1]}"/> -
					<input type="text" class="card_no num" name="card_no3" maxlength='4' value="${(card.card_no).split('-')[2]}"/> -			
					<input type="text" class="card_no num" name="card_no4" maxlength='4' value="${(card.card_no).split('-')[3]}"/>
					<span class="card_no_error error"></span>
				</div>
				
				<div>
					<label for="card_name">카드 명의</label>
					<input type="text" id="card_name" name="card_name" value="${card.card_name}"/>
					<span class="card_name_error error"></span>
				</div>
				
				<div>
					<label for="card_ep">유효 기간</label>
					<input type="hidden" id="card_ep" value="${card.card_ep}" />
					<select class="card_ep" id="card_ep_year"></select>년  -
					<select class="card_ep" id="card_ep_month"></select>월
				</div>
				
				<c:choose>
					<c:when test="${card.card_type eq 0}">
						<div id="cardPWArea">
							<label for="card_pw">카드 비밀번호</label>
							<input type="password" id="card_pw" class="num"  name="card_pw" maxlength='4'  value="${card.card_pw}"/>
							<span class="card_pw_error error"></span>
						</div>
					</c:when>
				</c:choose>
								
				<input type="submit" value="수정"/>			
			</form>
		</div>
	</body>
</html>