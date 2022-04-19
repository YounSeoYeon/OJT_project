<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>index</title>
<link href="<c:url value='/resources/css/business/index.css'/>" rel="stylesheet" type="text/css">
<script src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
<script src="<c:url value='/resources/js/business/index.js'/>"></script>
<script src="<c:url value='/resources/js/business/busdelete.js'/>"></script>
<script type="text/javascript">
	$(function(){
		var chkObj = document.getElementsByName("bustypes"); // 개별체크박스들 배열로
		var allchk = chkObj.length; // 체크된거 배열 갯수
		
		// 전체체크박스 누르면 전체 다 체크됨
		$("input[name='allcheck']").click(function(){
			var chkList = $("input[name='bustypes']");
			for(var i=0; i<chkList.length; i++){
				chkList[i].checked = this.checked;
			}
		});
		
		// 개별체크박스 체크
		$("input[name='bustypes']").click(function(){
			if($("input[name='bustype']:checked").length==allchk){
				$("input[name='allcheck']")[0].checked = true;
			}
			else{
				$("input[name='allcheck']")[0].checked = false;
			}
		});
	});
	
</script>
</head>
<body>
	<section id="mainWrap">
		<div id="codeMiddle">
			<jsp:include page='../header.jsp' />
			<form id="codemenuBus"> 
				<div class="codesearch">
					<div class="bustype">
						<input type="radio" id="all" name="bustype" value="a" onClick="move()" checked="checked"><label>전체</label>
						<input type="radio" id="public" name="bustype" value="p" onClick="move()"><label>공공</label> 
						<input type="radio" id="corp" name="bustype" value="c" onClick="move()"><label>민간</label>
					</div>
					<div class="search">
						<label>업체검색</label>&nbsp;&nbsp;<input type="text" id="searchinput" name="search" placeholder="업체,대표자,사업자번호,전화번호,주소 키워드입력" onChange='move()'>
						&nbsp;<input class="searchbtn" type="button" value="검색">
					</div>
					<button class="excel">엑셀 파일 생성</button>
				</div>
				<hr>
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
					<hr>
						<div class="topcard">
							<jsp:include page="/WEB-INF/views/business/top.jsp" flush='true' />
						</div>
					</div>				
				</div>				
				<div class="inoutBtn">
					<span class="in">추가</span>
					<span class="modify">수정</span>
					<span class="out">삭제</span>
				</div>
			</form>
		</div>
	</section>
</body>
</html>
