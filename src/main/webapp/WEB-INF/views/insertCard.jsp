<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>카드 계정 등록</title>
	</head>
	<body>
		<form>
			<label for="card_idx">카드 계정</label><br/>
			<input type="text" id="card_idx" name="card_idx" /> <button>중복 검사</button><br/>
			
			<label for="card_no">카드 번호</label><br/>
			<input type="text" class="card_no" name="card_no1" /> -
			<input type="text" class="card_no" name="card_no2" /> -
			<input type="text" class="card_no" name="card_no3" /> -			
			<input type="text" class="card_no" name="card_no4" /><br/>
			
			<label for="card_name">카드 명의</label><br/>
			<input type="text" id="card_name" name="card_name" /><br/>
			
			<lables for="card_ep">유효 기간</lables><br/>
			<input type="text" class="card_ep" name="card_ep-year" /> -			
			<input type="text" class="card_ep" name="card_ep-montj" /><br/>
			
			<input type="radio" id="company" name="cardType" value="company">
			<label for="company">법인</label>
			<input type="radio" id="personal" name="cardType" value="personal">
			<label for="personal">개인</label><br/>
			
			<input type="submit" value="추가"/>			
		</form>
	</body>
</html>