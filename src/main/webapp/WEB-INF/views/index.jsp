<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>index</title>
<link href="<c:url value='/resources/css/index.css'/>" rel="stylesheet" type="text/css">
<script src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
<script src="<c:url value='/resources/js/index.js'/>"></script>
<script src="<c:url value='/resources/js/busdelete.js'/>"></script>
<script type="text/javascript">
	$(function(){
		var chkObj = document.getElementsByName("bustype"); // 개별체크박스들 배열로
		var allchk = chkObj.length; // 체크된거 배열 갯수
		
		// 전체체크박스 누르면 전체 다 체크됨
		$("input[name='allcheck']").click(function(){
			var chkList = $("input[name='bustype']");
			for(var i=0; i<chkList.length; i++){
				chkList[i].checked = this.checked;
			}
		});
		
		// 개별체크박스 체크
		$("input[name='bustype']").click(function(){
			if($("input[name='bustype']:checked").length==allchk){
				$("input[name='allcheck']")[0].checked = true;
			}
			else{
				$("input[name='allcheck']")[0].checked = false;
			}
		});
	});
	
	function bdelete(){
		var url = "./busdelete";
		var valueArr = new Array(); // 체크된거 넣을 배열 생성
		var list = $("input[name='bustype']"); // 체크된 리스브(배열)
		
		for(var i=0; i<list.length; i++){
			if(list[i].checked){
				valueArr.push(list[i].value); // 체크된것의 업체코드 넣기
			}
		}
		if(valueArr.length==0){ // 체크딘 업체 없으면
			alert("선택된 업체가 없습니다");
		}
		else{
			var check = confirm("정말 삭제하나요?");
			$.ajax({
				url:url, 			//위에서 선언한 전송할 url
				type:'post',
				data : {
					'valueArr' : valueArr
				},
				success:function(result){
					if(result=1){
						alert("삭제완료");
						location.replace("redirect:/");
					}
					else{
						alert("삭제실패");
					}
				}
			});
		}
	}
</script>
</head>
<body>
	<section id="mainWrap">
		<h1>코드관리-업체</h1>
		<div id="codeMiddle">
			<ul id="codemenu">
				<li>카드계정</li>
				<li>업체</li>
				<li>프로젝트</li>
				<li>지출</li>
			</ul>
			<form id="codemenuBus"> 
				<div class="codesearch">
					<div class="bustype">
						<input type="radio" id="all" name="bustype" value="전체"><label>전체</label>
						<input type="radio" id="public" name="bustype" value="공공"><label>공공</label> 
						<input type="radio" id="corp" name="bustype" value="민간"><label>민간</label>
					</div>
					<div class="search">
						<label>업체검색</label>&nbsp;&nbsp;<input type="text" name="search" placeholder="키워드검색">
						<input class="searchbtn" type="submit" value="검색">
					</div>
				</div>
				<hr>
				<div class="bus_list_wrap">
					<div class="bus_list">
						<div class="top first">
								<div class="check"><input type="checkbox" name="allcheck" id="allcheck"></div>
								<div class="name">업체명</div>
								<div class="number">사업자번호</div>
								<div class="ceo">대표자</div>
								<div class="buscode">업체코드</div>
								<div class="etc">기타</div>
						</div>
					<hr>
						<c:forEach items="${vo}" var="buslist" begin="0">
								<div class="top">
									<div class="check"><input type="checkbox" id="bustype" name="bustype" value="${buslist.bus_idx}"></div>
									<div class="name">${buslist.bus_nm}</div>
									<div class="number">${buslist.bus_reg_no}</div>
									<div class="ceo">${buslist.bus_rep}</div>
									<div class="buscode">${buslist.bus_code}</div>
									<div class="etc">${buslist.bus_tel}</div>
								</div>
					<hr>
					 	</c:forEach>
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
