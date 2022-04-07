/**
 * header.js
 */

$(function(){
	$('.topButton').each(function(){
		$(this).on('click', function(){
			let input = $(this).children().val();
			console.log(input);
			
			/** 페이지 변경 **/			
//			$.ajax({
//				type: 'post',
//				url: '/topMenu',
//				data: {'data': input},
//				success: function(result){
//					$('#contents').html(result);
//				},
//				error: function(e){
//					console.log(e);
//				}
//			});
		});
	});
});