<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>카드 계정 등록</title>
		<link rel="stylesheet" href="<c:url value='/resources/css/card/insertCard.css' />" />
		<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/card/insertCard.js' />"></script>
	</head>
	<body>
		<div id="wrapper">
			<h1>카드 계정 등록</h1>
			<form id="addCardForm">
				<div>
					<label for="card_idx">카드 계정</label>
					<!-- 카드 유형 선택 -->
					<select id="card_type">
						<option value="" selected>카드 유형 선택</option>
						<option value="0">법인 카드</option>
						<option value="1">개인 카드</option>
					</select>
					<input type="text" id="card_idx" class="num" name="card_idx" placeholder="id" maxlength='8'/> 
					<button class="checkCardIndexBtn disabled">중복 검사</button>
					<span class="card_index_error error"></span>
				</div>
				
				<div id="cardNoArea"> 
					<label for="card_no">카드 번호</label>
					<input type="text" class="card_no num" name="card_no1" maxlength='4'/> -
					<input type="text" class="card_no num" name="card_no2" maxlength='4'/> -
					<input type="text" class="card_no num" name="card_no3" maxlength='4'/> -			
					<input type="text" class="card_no num" name="card_no4" maxlength='4'/>
					<span class="card_no_error error"></span>
				</div>
				
				<div>
					<label for="card_name">카드 명의</label>
					<input type="text" id="card_name" name="card_name" />
					<span class="card_name_error error"></span>
				</div>
				
				<div>
					<label for="card_ep">유효 기간</label>
					<select class="card_ep" id="card_ep_year"></select>년  -
					<select class="card_ep" id="card_ep_month"></select>월
				</div>
				
				<div id="cardPWArea">
					<label for="card_pw">카드 비밀번호</label>
					<input type="password" id="card_pw" class="num" name="card_pw" maxlength='4'/>
					<span class="card_pw_error error"></span>
				</div>
								
				<input type="submit" class="disabled" value="추가"/>			
			</form>
		</div>
	</body>
</html>