<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>index</title>
		<link href="<c:url value='/resources/css/business/index2.css'/>" rel="stylesheet" type="text/css">
		<script src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
		<script src="<c:url value='/resources/js/business/index.js'/>"></script>
		<script src="<c:url value='/resources/js/business/busdelete.js'/>"></script>
		<script type="text/javascript">
			$(function() {
				var chkObj = document.getElementsByName("bustypes"); // 개별체크박스들 배열로
				var allchk = chkObj.length; // 체크된거 배열 갯수
		
				// 전체체크박스 누르면 전체 다 체크됨
				$("input[name='allcheck']").click(function() {
					var chkList = $("input[name='bustypes']");
					for (var i = 0; i < chkList.length; i++) {
						chkList[i].checked = this.checked;
					}
				});
		
				// 개별체크박스 체크
				$("input[name='bustypes']").click(function() {
					if ($("input[name='bustype']:checked").length == allchk) {
						$("input[name='allcheck']")[0].checked = true;
					} else {
						$("input[name='allcheck']")[0].checked = false;
					}
				});
			});
		</script>
		
</head>
	<body>
		<div id="wrapper">
			<h1>코드 관리</h1>
			<div id="contents">
				<div id="tabButtons">
					<button onclick="location.href= '/'">카드 계정</button>
					<button onclick="location.href='/index'">업체</button>
					<button onclick="location.href='/projindex'">프로젝트</button>
					<button onclick="location.href='#'">지출</button>
				</div> 
				<div id="options">
					<div class="bustype">
						<input type="radio" id="all" name="bustype" value="a" onClick="move()" checked="checked"><label>전체</label>
						<input type="radio" id="public" name="bustype" value="p" onClick="move()"><label>공공</label> 
						<input type="radio" id="corp" name="bustype" value="c" onClick="move()"><label>민간</label>
					</div>
					<div class="search">
						<label>업체검색</label>&nbsp;&nbsp;
						<input type="text" id="searchinput" name="search" placeholder="업체,대표자,사업자번호,전화번호,주소 키워드입력" onChange='move()'>&nbsp;
						<input class="searchbtn" type="button" value="검색">
					</div>
				</div>
				<div class="bus_list_wrap">
					<div class="bus_list">
						<div class="top first">
								<div class="check"><input type="checkbox" name="allcheck" id="allcheck"></div>
								<div class="buscode">업체코드</div>
								<div class="name">업체명</div>
								<div class="busnum">사업자번호</div>
								<div class="ceo">대표자</div>
								<div class="etc">전화번호</div>
						</div>
						<div class="topcard">
							<jsp:include page="/WEB-INF/views/business/top.jsp" flush='true' />
						</div>
					</div>				
				</div>	
				<div id="bottomButtons">
		  				<button class="in">추가</button>
						<button class="modify">수정</button>
						<button class="out">삭제</button>						
						<button class="excel">엑셀파일</button>
				</div>
			</div>
		</div>
	</body>
</html>
