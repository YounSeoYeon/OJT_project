/**
 * index.js
 */
 
$(function(){
	console.log("start");
	const list = document.querySelector('.list');
	const check = document.querySelector('.check');
	
	/* 업체리스트 눌렀을때 */
	$(list).on('click', function(){
		console.log("리스트창 이동");
		/*self.close();
		parent.opener.location.href="./";*/	// 처음 메인화면으로 이동
		parent.close();
		window.close();
		self.close();
		window.location.href="./";
	});
	
	/* 관리하기 눌렀을때 */
	$(check).on('click', function(){
		console.log("관리하기 이동");
		/*self.close();		
		parent.opener.location.href="./index";*/	// 처음 메인화면으로 이동
		parent.close();
		window.close();
		self.close();
		window.location.href="./index";
	});
	
});