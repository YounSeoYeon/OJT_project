/**
 * cardInfo.js
 */

$(function(){
	getCardList();
	
	// 전체 체크/ 해제
	$('#checkAll').on('click', function(){
		const checked = $('#checkAll').is(':checked');
		
		if(checked)
			$('input:checkbox').prop('checked',true);
		else 
			$('input:checkbox').prop('checked',false);
	});
	
	// 추가 버튼 클릭
	$('.addBtn').on('click', function(e){
		e.preventDefault();
		const width = 500;
		const height = 500;
		
		let left = (document.body.offsetWidth / 2) - (width / 2);
		let top = (document.body.offsetHeight / 2) - (height / 2);
		
		window.open('/insertCardView', '카드 등록 창' , `width=${width},height=${height},top=${top},left=${left}`)
	});
	
	// 카드 타입 선택
	$('input:radio[name="card_type"]').on('click', function(){
		getCardList();
	});
	
	// 수정 버튼 클릭
	$('.updateBtn').on('click', function(e){
		e.preventDefault();
		let checked = $('input:checkbox[name=card]:checked').length;
		
		if(checked > 1) alert('항목을 1개만 선택해주세요.');
		else if(checked == 0) alert('수정할 항목을 선택해주세요.');
		else {
			let checkedIndex = $('input:checkbox[name=card]:checked').val();
			//console.log(checkedIndex);
			
			const width = 500;
			const height = 500;
			
			let left = (document.body.offsetWidth / 2) - (width / 2);
			let top = (document.body.offsetHeight / 2) - (height / 2);
			
			window.open('/updateCardView/'+checkedIndex, '카드 수정 창' , `width=${width},height=${height},top=${top},left=${left}`)
		}
	});
	
	// 카드 목록 불러오기 함수
	function getCardList(){
		let value = $('input[name=card_type]:checked').val();
		
		$.ajax({
			type: 'post',
			url: '/cardList',
			data: {"card_type": value},
			success: function(result) {
				$('#cardList').empty();
				$('#cardList').html(result);
			},
			error: function(error) {
				console.log(error);
			}
		});
	}
});