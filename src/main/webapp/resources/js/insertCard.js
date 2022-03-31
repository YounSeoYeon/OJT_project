/**
 * insertCard.js
 */

$(function(){
	// input type=number 입력 수 제한
	$('input[type=number][maxlength]').on('input', function(ev) {
	    var maxlength = $(this).attr('maxlength');
	    var value = $(this).val();
	    if (value && value.length >= maxlength) {
	    	$(this).val(value.substr(0, maxlength));
	    }
	});
	
	// 카드 유형 미 선택시 중복 검사버튼, 추가 버튼 비활성화
	$('#cardType').on('change', function(){
		let cardType = $('#cardType').val();
		
		//console.log(cardType)
		
		if(cardType === '') {
			$('.checkCardIndexBtn, input[type=submit]').attr('disabled', 'disabled');
			$('.checkCardIndexBtn, input[type=submit]').addClass('disabled');
		}else {
			$('.checkCardIndexBtn, input[type=submit]').removeAttr('disabled');
			$('.checkCardIndexBtn, input[type=submit]').removeClass('disabled');
			
			// 카드가 법인 카드면 카드 비밀번호 입력란 생성
			if(cardType === '0'){
				$('#cardPWArea').css('display', 'block');
			}else {
				$('#cardPWArea').css('display', 'none');
			}
		}
	});
	
	// 카드 계정 중복 확인
	$('.checkCardIndexBtn').on('click', function(){
		let cardType = $('#cardType').val();
		let index = $('#card_idx').val();
		
		if(cardType === '0') cardType = 'C';
		else cardType = 'P';
		
		let data = cardType + index;
		console.log(data);
		
//		$.ajax({
//			type: 'post',
//			url: '/checkCardIndex',
//			data: data,
//			success: function(result) {
//				
//			},
//			error: function(e){
//				console.log(e);
//			}
//		});
	});
});