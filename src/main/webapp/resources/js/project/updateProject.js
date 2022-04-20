/**
 * updateProject.js
 */

$(function(){
	// input num : 숫자만 입력
	$('input.num').on('propertychange change keyup paste input', function(){
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
	});
	
	/***** 금액 입력 EVENT *****/
	// 금액 천단위 콤마 생성
	function withComma(num) {
		return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	}
	// 금액 콤마 해제
	function withoutComma(num) {
		return num.toString().replace(/,/g, '');
	}
	
	// 금액 입력 시 콤마 생성
	$('#proj_amount').on('propertychange change keyup paste input', function(){
		let amount = $(this).val() || '0';
		let amountWithComma = withComma(parseInt(amount));
		$(this).val(amountWithComma);
	});
	
	// 엔터키 submit 안 되게 
	$(document).on('keydown', function(e) {
		if (e.keyCode == 13) e.preventDefault();
	});
	
	// submit
	$('#updateProjectForm').on('submit',  function(e) {
		e.preventDefault();
		
		// FormData
		let formData = $(this).serializeArray();
		
		// formData  amount 콤마 해제 -> int 형으로 변경
		let amount = parseInt(withoutComma($('#proj_amount').val()));
		
		formData = changeSerialize(formData, 'proj_amount', amount);
		
		// changeSerialize 함수
		function changeSerialize(values, k, v) {
		    let found = false;

		    for (let i = 0; i < values.length && !found; i++) {
		        if ( values[i].name == k ) { 
		            values[i].value = v;
		            found = true;
		        }
		    }

		    if (!found) 
		        values.push({name: k, value: v});
		    
		    return values;
		}
		
		// console.log(formData)

		if(checkValidate()){
			$.ajax({
				type: 'post',
				url: '/project/updateProject',
				data: formData,
				success: function(result) {
					if(result != 0) {
						alert('프로젝트 정보를 정상적으로 수정했습니다.');
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

		// 프로젝트  명
		if($('#proj_nm').val() == "") {
			$('.proj_nm_error').css({
				'display': 'block',
				'color': 'red'
			});
			$('.proj_nm_error').text('프로젝트 명을 입력해주세요.');
			$(this).focus();
			validation = false;
		}else {
			$('.proj_nm_error').css('display', 'none');
		} 
		
		// 프로젝트 시작 날짜
		if($('#proj_start_date').val() == "") {
			$('.proj_date_error').css({
				'display': 'block',
				'color': 'red'
			});
			$('proj_date_error').text('프로젝트 시작 날짜를 입력해주세요.');
			$(this).focus();
			validation = false;
		}else {
			$('.proj_date_error').css('display', 'none');
		} 
		
		// 프로젝트 만료 날짜
		if($('#proj_end_date').val() == "") {
			$('.proj_date_error').css({
				'display': 'block',
				'color': 'red'
			});
			$('.proj_date_error').text('프로젝트 만료 날짜를 입력해주세요.');
			$(this).focus();
			validation = false;
		}else {
			$('.proj_date_error').css('display', 'none');
		} 
		
		// 발주처
		if($('#proj_buyer').val() == "") {
			$('.proj_buyer_error').css({
				'display': 'block',
				'color': 'red'
			});
			$('.proj_buyer_error').text('발주처를 입력해주세요.');
			$(this).focus();
			validation = false;
		}else {
			$('.proj_buyer_error').css('display', 'none');
		} 
		
		// 계약 금액
		if($('#proj_amount').val() == "") {
			$('.proj_amount_error').css({
				'display': 'block',
				'color': 'red'
			});
			$('.proj_amount_error').text('계약 금액을 입력해주세요.');
			$(this).focus();
			validation = false;
		}else {
			$('.proj_amount_error').css('display', 'none');
		} 
		
		return validation;
	};
});