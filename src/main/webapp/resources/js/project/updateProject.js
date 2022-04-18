/**
 * updateProject.js
 */

$(function(){
	// input num : 숫자만 입력
	$('input.num').on('propertychange change keyup paste input', function(){
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
	});
	
	// 엔터키 submit 안 되게 
	$(document).on('keydown', function(e) {
		if (e.keyCode == 13) e.preventDefault();
	});
	
	// submit
	$('#updateProjectForm').on('submit',  function(e) {
		e.preventDefault();
		
		// FormData
		let formData =$(this).serialize();
		
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