<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<title>프로젝트 index</title>
<%-- <link href="<c:url value='/resources/css/project/projindex.css'/>" rel="stylesheet" type="text/css"> --%>
<link href="<c:url value='/resources/css/project/projindex2.css'/>" rel="stylesheet" type="text/css">
<script src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
<script src="<c:url value='/resources/js/project/projindex.js'/>"></script>
<script type="text/javascript">
	$(function(){
		var chkObj = document.getElementsByName("bustype"); // 개별체크박스들 배열로
		var allchk = chkObj.length; // 체크된거 배열 갯수
		
		// 전체체크박스 누르면 전체 다 체크됨
		$("input[name='allcheck']").click(function(){
			var chkList = $("input[name='project']");
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
	<div id="wrapper">
			<h1>코드 관리</h1>
			<div id="contents">
				<div id="tabButtons">
					<button class="selected" onclick="location.href= '/'">카드 계정</button>
					<button onclick="location.href='/index'">업체</button>
					<button onclick="location.href='/projindex'">프로젝트</button>
					<button onclick="location.href='#'">지출</button>
				</div> 
				<div id="options">
					<div class="search">
						<label>검색</label>&nbsp;&nbsp;
						<input type="text" id="searchinput" name="search" placeholder="프로젝트명,코드,업체,금액 키워드입력" onChange='move()'>&nbsp;
						<input class="searchbtn" type="button" value="검색">
					</div>
				</div>
				<div class="bus_list_wrap">
					<div class="bus_list">
						<div class="top first">
								<div class="check"><input type="checkbox" name="allcheck" id="allcheck"></div>
								<div class="nm">프로젝트명</div>
								<div class="amount">계약금액</div>
								<div class="code">프로젝트코드</div>
								<div class="buyer">업체</div>
								<div class="date">기간</div>
						</div>
						<div class="topcard">
							<jsp:include page="/WEB-INF/views/project/projtop.jsp" flush='true' />
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
