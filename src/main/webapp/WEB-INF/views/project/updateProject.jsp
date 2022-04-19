<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>카드 계정 수정</title>
		<link rel="stylesheet" href="<c:url value='/resources/css/project/updateProject.css' />" />
		<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/resources/js/project/updateProject.js' />"></script>
	</head>
	<body>
		<div id="wrapper">
			<h1>프로젝트 정보 수정</h1>
			<form id="updateProjectForm">
				<div>
					<label for="proj_code">ID</label>
					<input type="text" id="proj_code" class="num" name="proj_code" value="${project.proj_code}" readonly />
				</div>
				
				<div> 
					<label for="proj_nm">프로젝트 명</label>
					<input type="text" id="proj_nm" name="proj_nm" value="${project.proj_nm}"/>
					<span class="proj_nm_error error"></span>
				</div>
				
				<div>
					<label for="proj_start_date">계약 기간</label>
					<input type="datetime-local" id="proj_start_date" name="proj_start_date" value="${project.proj_start_date}"/> ~
					<input type="datetime-local" id="proj_end_date" name="proj_end_date" value="${project.proj_end_date}"/> 					
					<span class="proj_date_error error"></span>
				</div>
				
				<div>
					<label for="proj_buyer">발주처</label>
					<input type="text" id="proj_buyer" name="proj_buyer" value="${project.proj_buyer}"/>
					<span class="proj_buyer_error error"></span>
				</div>
				
				<div>
					<label for="proj_amount">계약 금액</label>
					<input type="text" id="proj_amount" class="num" name="proj_amount" value="${amount}"/> 원
					<span class="proj_amount_error error"></span>
				</div>
								
				<input type="submit" value="수정"/>			
			</form>
		</div>
	</body>
</html>