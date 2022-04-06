/**
 * insertCard.js
 */

$(function(){
	// input num : 숫자만 입력
	$('input.num').on('propertychange change keyup paste input', function(){
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
	});
	
	// 카드 유형 선택
	$('#card_type').on('change', function(){
		let card_type = $('#card_type').val();
		// 카드 유형 미 선택시 중복 검사버튼 비 활성화
		if(card_type === '') {
			$('.checkCardIndexBtn').attr('disabled', 'disabled');
			$('.checkCardIndexBtn').addClass('disabled');
		}else {
			$('.checkCardIndexBtn').removeAttr('disabled');
			$('.checkCardIndexBtn').removeClass('disabled');
		}
		
		// 카드가 법인 카드면 카드 비밀번호 입력란 생성
		if(card_type === '0'){
			$('#cardPWArea').css('display', 'block');
		}else {
			$('#cardPWArea').css('display', 'none');
		}
	});
	
	// 카드 계정 중복 확인
	$('.checkCardIndexBtn').on('click', function(e){
		e.preventDefault(); // submit 방지
		
		let card_type = $('#card_type').val();
		let index = $('#card_idx').val();
		
		if(card_type === '0') card_type = 'C';
		else card_type = 'P';
		
		let data = card_type + index;
		// console.log(data);
		
		$.ajax({
			type: 'post',
			url: '/card/checkCardIndex',
			data: {'index': data },
			success: function(result) {
				// console.log(result)
				let $error = $('.card_index_error');
				if(result == 0) {
					$error.css({
						'display': 'block',
						'color': 'green'
					});
					$error.text('사용 가능한 아이디입니다.');
					$('input[type=submit]').removeAttr('disabled');
					$('input[type=submit]').removeClass('disabled');
				}else {
					$('#card_idx').focus();
					$error.css({
						'display': 'block',
						'color': 'red'
					});
					$error.text('이미 사용중인 아이디입니다.');
					$('input[type=submit]').attr('disabled', 'disabled');
					$('input[type=submit]').addClass('disabled');
				}
			},
			error: function(e){
				console.log(e);
			}
		});
	});
	
	
	// 카드 번호 4자리 입력 시 다음 칸 이동
	$('.card_no').each(function(index){
		$(this).on('keyup', function(){
			if(this.value.length == 4) 
				$(this).next().focus();
		});
	});
	
	
	
	/****** 유효 기간 ******/
	let now = new Date(); 
	let year = now.getFullYear(); 
	let mon = (now.getMonth() + 1) > 9 ? ''+(now.getMonth() + 1) : '0'+(now.getMonth() + 1); 
	
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
	$("#card_ep_year > option[value="+year+"]").attr("selected", "true"); 
	$("#card_ep_month > option[value="+mon+"]").attr("selected", "true"); 

	
	// 엔터키 submit 안 되게 
	$(document).on('keydown', function(e) {
		if (e.keyCode == 13) e.preventDefault();
	});
	
	/**** 추가 버튼 클릭 ****/
	$('#addCardForm').on('submit',  function(e) {
		e.preventDefault();
		
		/*** Form Data ***/
		// card_type
		let card_type = $('#card_type').val();
		
		// card_id
		let card_id = $('#card_idx').val();
		
		// card_idx
		let card_idx;
		if(card_type === '0') card_idx = 'C' + card_id;
		else card_idx = 'P' + card_id;
		
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
		
		/*** Ajax ***/		
		if(checkValidate()){
			$.ajax({
				type: 'post',
				url: '/card/insertCard',
				data: {
					"card_idx": card_idx,
					"card_no": card_no,
					"card_id": card_id,
					"card_pw": card_pw,
					"card_name": card_name,
					"card_ep": card_ep,
					"card_type": card_type,
				},
				success: function(result) {
					if(result != '') {
						alert('카드를 정상적으로 등록했습니다.');
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