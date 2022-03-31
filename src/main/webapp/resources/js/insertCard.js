/**
 * insertCard.js
 */

$(function(){
	// 카드 유형 미 선택시 중복 검사버튼, 추가 버튼 비활성화
	$('#cardType').on('change', function(){
		let cardType = $('#cardType').val();
		
		console.log(cardType)
		
		if(cardType === '') {
			$('.duplicateCheckBtn, input[type=submit]').attr('disabled', 'disabled');
			$('.duplicateCheckBtn, input[type=submit]').addClass('disabled');
		}else {
			$('.duplicateCheckBtn, input[type=submit]').removeAttr('disabled');
			$('.duplicateCheckBtn, input[type=submit]').removeClass('disabled');
			
			// 카드가 법인 카드면 카드 비밀번호 입력란 생성
			if(cardType === '0'){
				$('#cardPWArea').css('display', 'block');
			}else {
				$('#cardPWArea').css('display', 'none');
			}
		}
	});
});