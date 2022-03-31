/**
 * cardInfo.js
 */

$(function(){
	$('.addBtn').on('click', function(e){
		e.preventDefault();
		const width = 500;
		const height = 500;
		
		let left = (document.body.offsetWidth / 2) - (width / 2);
		let top = (document.body.offsetHeight / 2) - (height / 2);
		
		window.open('/insertCard', '카드 등록 창' , `width=${width},height=${height},top=${top},left=${left}`)
	});
});