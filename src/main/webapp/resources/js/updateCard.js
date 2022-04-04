/**
 * insertCard.js
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
		
		/*** 유효성 검사 ***/
		// 카드 번호
		$('.card_no').each(function(index){
			if($(this).val() == '') {
				$('.card_no_error').css({
					'display': 'block',
					'color': 'red'
				});
				$('.card_no_error').text('카드 번호를 입력헤주세요.');
				$(this).focus();
				return false;
			}else if($(this).val().length < 4) {
				$('.card_no_error').css({
					'display': 'block',
					'color': 'red'
				});
				$('.card_no_error').text('카드 번호가 유효하지 않습니다.');
				$(this).focus();
				return false;
			}
		});
		
		// 카드 명의
		if($('#card_name').val() == '') {
			$('.card_name_error').css({
				'display': 'block',
				'color': 'red'
			});
			$('.card_name_error').text('카드 명의자 이름을 입력헤주세요.');
			$(this).focus();
			return false;
		}
		
		// 카드 비밀번호
		if($('#card_pw').val() == '') {
			$('.card_pw_error').css({
				'display': 'block',
				'color': 'red'
			});
			$('.card_pw_error').text('카드 비밀번호를 입력헤주세요.');
			$(this).focus();
			return false;
		}
		
		
		/*** Form Data ***/
		// card_idx
		let card_idx = $('input[name=card_idx]').val();
		
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
		
		$.ajax({
			type: 'post',
			url: '/updateCard',
			data: {
				"card_idx": card_idx,
				"card_no": card_no,
				"card_pw": card_pw,
				"card_name": card_name,
				"card_ep": card_ep,
			},
			success: function(result) {
				if(result != '') {
					alert('카드를 정상적으로 수정했습니다.');
					opener.parent.location.reload();
					window.close();
				}
			},
			error: function(error){
				console.log(error);
			}
		});
	});
});