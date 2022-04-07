/**
 * updateCard.js
 */

$(function(){
	// input num : 숫자만 입력
	$('input.num').on('propertychange change keyup paste input', function(){
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
	});
	
	// 카드 번호 입력 - 다음 칸 이동
	$('input[name=card_no1]').on('keyup', function() {
	    if(this.value.length == 4) {
	       $('input[name=card_no2]').focus();
	    }
	});
	$('input[name=card_no2]').on('keyup', function() {
		if(this.value.length == 4) {
			$('input[name=card_no3]').focus();
		}
	});
	$('input[name=card_no3]').on('keyup', function() {
		if(this.value.length == 4) {
			$('input[name=card_no4]').focus();
		}
	});
	$('input[name=card_no4]').on('keyup', function() {
		if(this.value.length == 4) {
			$('input[name=card_name]').focus();
		}
	});
	
	/****** 유효 기간 ******/
	let now = new Date(); 
	let year = now.getFullYear(); 
	let mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1); 
	
	let selectedYear = $('#card_ep').val().split('-')[0];
	let selectedMonth = $('#card_ep').val().split('-')[1];
	
	//년도 option 만들기 
	for(let i = year ; i <= year+20 ; i++) { 
		$('#card_ep_year').append('<option value="' + i + '">' + i + '</option>'); 
	} 
	
	// 월별 option 만들기 
	for(let i=1; i <= 12; i++) {
		let mm = i > 9 ? i : "0"+i ; 
		$('#card_ep_month').append('<option value="' + mm + '">' + mm + '</option>'); 
	}
	
	// default로 현재 날짜 선택
	$("#card_ep_year > option[value="+selectedYear+"]").attr("selected", "true"); 
	$("#card_ep_month > option[value="+selectedMonth+"]").attr("selected", "true"); 

	
	// 엔터키 submit 안 되게 
	$(document).on('keydown', function(e) {
		if (e.keyCode == 13) e.preventDefault();
	});
	
	// submit
	$('#updateCardForm').on('submit',  function(e) {
		e.preventDefault();
		
		/*** Form Data ***/
		// card_id
		let card_id = $('input[name=card_id]').val();
		
		// card_no
		let card_no = '';
		$('.card_no').each(function (index) {
		     if(index < 3) card_no += $(this).val() + '-';
		     else card_no += $(this).val();
		});
		
		// card_name
		let card_name = $('#card_name').val();
		
		// card_ep
		let card_ep = $('#card_ep_year').val() + '-' + $('#card_ep_month').val() + '-00'; 
		
		// card_pw
		let card_pw = $('#card_pw').val() || null;
		
		if(checkValidate()){
			$.ajax({
				type: 'post',
				url: '/card/updateCard',
				data: {
					"card_id": card_id,
					"card_no": card_no,
					"card_pw": card_pw,
					"card_name": card_name,
					"card_ep": card_ep,
				},
				success: function(result) {
					if(result != 0) {
						alert('카드를 정상적으로 수정했습니다.');
						opener.parent.location.reload();
						window.close();
					}
				},
				error: function(error){
					console.log(error);
				}
			});
		};
	});
	
	/*** 유효성 검사 ***/
	function checkValidate(){
		let validation = true;

		// 카드 번호
		$('.card_no').each(function(){
			if(this.value.length < 4) {
				$(this).addClass('hasError');
				$(this).focus();
			}else $(this).removeClass('hasError');
		});
		
		if($('.card_no').is('.hasError')) {
			$('.card_no_error').css({
				'display': 'block',
				'color': 'red'
			});
			$('.card_no_error').text('유효하지 않은 카드번호입니다.');
			validation = false;
		}else {
			$('.card_no_error').css('display', 'none');
		}
		
		
		// 카드 명의
		let name_validation = true;
		if($('#card_name').val() == '') {
			$('.card_name_error').css({
				'display': 'block',
				'color': 'red'
			});
			$('.card_name_error').text('카드 명의자를 입력해주세요.');
			$(this).focus();
			validation = false;
		}else {
			$('.card_name_error').css('display', 'none');
		};
		
		// 카드 비밀번호
		let pw_validation = true;
		if($('#card_type').val() === '0') {
			if($('#card_pw').val().length < 4) {
				$('.card_pw_error').css({
					'display': 'block',
					'color': 'red'
				});
				let errorMsg = $('#card_pw').val() == '' ? '비밀번호를 입력하세요.' : '유효하지 않은 비밀번호입니다.';
				$('.card_pw_error').text(errorMsg);
				$(this).focus();
				validation = false;
			}else {
				$('.card_pw_error').css('display', 'none');
			};
		};
		
		return validation;
	};
});