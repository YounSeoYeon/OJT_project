/**
 * index.js
 */
 
$(function(){
	console.log("start");
	const list = document.querySelector('.list');
	const check = document.querySelector('.check');
	
	/* 업체리스트 눌렀을때 */
/*	$(list).on('click', function(){
		console.log("리스트창 이동");
		
		// jsp에서 onClick으로 본인팝업창 닫고 부모창 이동				 
		window.opener.location.href="../";	// 리스트 화면으로 이동
	});	*/
	
	/* 관리하기 눌렀을때 */
	$(check).on('click', function(){
		console.log("관리하기 이동");
		
		// 본인팝업창 닫고 부모창 이동
		window.opener.location.href=window.opener.location;		// 부모창 새로고침 
  		//window.close();
	});
	
});