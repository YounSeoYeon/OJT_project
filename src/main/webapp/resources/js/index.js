/**
 * index.js
 */
 
$(function(){
	console.log("start");
	const inbtn = document.querySelector('.in');
	$(inbtn).on('click', function(){
		console.log("코드추가창");
		window.open("./businsert","업체코드등록","width=1280, height=600, top=10, left=10");	
	});
	
	/*	
	var info = document.querySelector('.infos');
	var infos = info.innerHTML;
	*/
});