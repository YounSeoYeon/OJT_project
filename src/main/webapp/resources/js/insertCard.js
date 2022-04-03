/**
 * insertCard.js
 */

$(function(){
	// input type=number 입력 수 제한
	$('input[type=number][maxlength]').on('input', function(ev) {
	    var maxlength = $(this).attr('maxlength');
	    var value = $(this).val();
	    if (value && value.length >= maxlength) {
	    	// 글자수 이상은 잘리게
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
	$('.checkCardIndexBtn').on('click', function(e){
		e.preventDefault(); // submit 방지
		
		let cardType = $('#cardType').val();
		let index = $('#card_idx').val();
		
		if(cardType === '0') cardType = 'C';
		else cardType = 'P';
		
		let data = cardType + index;
		// console.log(data);
		
		$.ajax({
			type: 'post',
			url: '/checkCardIndex',
			data: {"data": data },
			success: function(result) {
				// console.log(result)
				let $error = $('.card_index_error');
				if(result == 0) {
					$('#card_idx_check').val('Y');
					$error.css({
						'display': 'block',
						'color': 'green'
					});
					$error.text('사용 가능한 아이디입니다.');
				}else {
					$('#card_idx').focus();
					$error.css({
						'display': 'block',
						'color': 'red'
					});
					$error.text('이미 사용중인 아이디입니다.');
				}
			},
			error: function(e){
				console.log(e);
			}
		});
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
	let day = (now.getDate()) > 9 ? ''+(now.getDate()) : '0'+(now.getDate()); 
	
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
	
	// submit
	$('#addCardForm').on('submit',  function(e) {
		e.preventDefault();
		
		/*** 유효성 검사 ***/
		// 중복 검사
		if ($("#card_idx_check").val() != 'Y') {
			$('.card_index_error').css({
				'display': 'block',
				'color': 'red'
			});
			$('.card_index_error').text('계정 증복체크를 해주세요.');
			$("#card_idx").focus();
			return false;
		}
		
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
		if($('#cardType').val() === '0') {
			if($('#card_pw').val() == '') {
				$('.card_pw_error').css({
					'display': 'block',
					'color': 'red'
				});
				$('.card_pw_error').text('카드 비밀번호를 입력헤주세요.');
				$(this).focus();
				return false;
			}
		}
		
		
		/*** Form Data ***/
		// card_type
		let card_type = $('#cardType').val();
		
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
		
		$.ajax({
			type: 'post',
			url: '/insertCard',
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
				console.log(result)
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
	});
});