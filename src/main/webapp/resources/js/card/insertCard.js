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
			$('#checkIDBtn').attr('disabled', 'disabled');
			$('#checkIDBtn').addClass('disabled');
		}else {
			$('#checkIDBtn').removeAttr('disabled');
			$('#checkIDBtn').removeClass('disabled');
		}
		
		// 카드가 법인 카드면 카드 비밀번호 입력란 생성
		if(card_type === '0'){
			$('#cardPWArea').css('display', 'block');
		}else {
			$('#cardPWArea').css('display', 'none');
		}
	});
	
	// 카드 계정 중복 확인
	$('#checkIDBtn').on('click', function(e){
		e.preventDefault(); // submit 방지
		
		// input
		let card_type = $('#card_type').val();
		let id = $('#card_id').val();
		
		// 입력 값이 없으면 경고 창
		if(id == '') {
			$('#card_id').focus();
			alert('아이디를 입력해주세요.'); 
		}
		else {
			// card_id
			if(card_type === '0') card_type = 'C';
			else card_type = 'P';
			
			let card_id = card_type + id;
			
			let condition = 'card_id';
			let error = $('.card_id_error');
			let data = {'key': 'card_id', 'value': card_id};
			let focus = $('#card_id');
			
			checkDuplicate(condition, data, error, focus);
		};
	});
	
	// 카드 번호 중복 체크
	$('#checkNoBtn').on('click', function(e){
		e.preventDefault();
		
		// card_no
		let card_no = '';
		$('.card_no').each(function (index) {
			if($(this).val() != '' & $(this).val().length == 4){
				if(index < 3) card_no += $(this).val() + '-';
				else card_no += $(this).val();
			}else {
				card_no = '';
			}
		});
		
		// 유효 하지 않은 번호 이면 경고창
		if(card_no == '') {
			$('#card_no1').focus();
			alert('유효한 카드 번호를 입력해주세요.');
		}
		else {
			let condition = 'card_no';
			let error = $('.card_no_error');
			let data = {'key': 'card_no', 'value': card_no};
			let focus = $('#card_no1');
			
			checkDuplicate(condition, data, error, focus);
		};
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
		// input
		let card_type = $('#card_type').val();
		let id = $('#card_id').val();
		
		// card_id
		let card_id;
		if(card_type === '0') card_id = 'C' + id;
		else card_id = 'P' + id;
		
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
					"card_id": card_id,
					"card_no": card_no,
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
	
	/*** 중복 체크 ***/
	function checkDuplicate(condition, data, error, focus){
		$.ajax({
			type: 'post',
			url: '/card/checkDuplicate',
			data: data,
			success: function(result) {
				// console.log(result)
				if(result == 0) {
					error.css({
						'display': 'block',
						'color': 'green'
					});
					if(condition == 'card_no'){
						error.text('등록 가능한 카드 번호입니다.');
						$('#checkNo').val('true');
					}else{
						error.text('사용 가능한 아이디입니다.');
						$('#checkId').val('true');
					}
					$('input[type=submit]').removeAttr('disabled');
					$('input[type=submit]').removeClass('disabled');
				}else {
					focus.focus();
					error.css({
						'display': 'block',
						'color': 'red'
					});
					if(condition == 'card_no') {
						error.text('이미 등록된 카드 번호입니다.');
						$('#checkNo').val('false');
					}else{
						error.text('이미 사용중인 아이디입니다.');
						$('#checkId').val('false');
					}
					$('input[type=submit]').attr('disabled', 'disabled');
					$('input[type=submit]').addClass('disabled');
				}
			},
			error: function(e){
				console.log(e);
			}
		});
	}
	
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