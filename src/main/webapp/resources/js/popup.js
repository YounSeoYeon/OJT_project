/**
 * index.js
 */
 
$(function(){
	console.log("start");
	const check = document.querySelector('.check');	
	
	/* 관리하기 눌렀을때 */
	$(check).on('click', function(){
		console.log("관리하기 이동");
		
		// 본인팝업창 닫고 부모창 이동
		window.opener.location.href=window.opener.location;		// 부모창 새로고침 
  		//window.close();
	});
	
});