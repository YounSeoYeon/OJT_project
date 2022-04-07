/**
 * cardInfo.js
 */

$(function(){
	// 첫 페이지 로딩 시 전체 목록 & 페이지 정보 가져오기
	getCardList({"card_type": -1});
	getPage({"card_type": -1, "page": 1, "range": 1});
	
	// 전역 변수
	let card_type = $('input[name=card_type]:checked').val();
	let keyword, word;
	
	// 전체 체크/ 해제
	$('#checkAll').on('click', function(){	
		const checked = $('#checkAll').is(':checked');
		
		if(checked)
			$('input:checkbox').prop('checked',true);
		else 
			$('input:checkbox').prop('checked',false);
	});
	
	// 카드 타입 선택
	$('input:radio[name="card_type"]').on('change', function(){
		let card_type = $('input[name=card_type]:checked').val();
		let data = {"card_type": card_type};
		getCardList(data);
		getPage(data); // 페이지
	});
	
	// 검색 버튼 클릭
	$('.searchBtn').on('click', function(e){
		e.preventDefault();
		
		let data = {"card_type": card_type};
		
		keyword = $('.keyword').val();
		word = $('#searchInput').val();
		
		if(keyword != "" && word != ""){
			data["keyword"] = keyword;
			data["word"] = word;
			getCardList(data); // 카드 목록
			getPage(data); // 페이지
			
			// 검색 영역 초기화 하기
			$('.keyword').val('');
			$('#searchInput').val('');
			
		}else if(keyword == "") {
			alert('키워드를 선택해주세요.');
		}else {
			alert('검색어를 입력해주세요.');
		}
	});
	
	// 추가 버튼 클릭
	$('.addBtn').on('click', function(e){
		e.preventDefault();
		const width = 500;
		const height = 500;
		
		let left = (document.body.offsetWidth / 2) - (width / 2);
		let top = (document.body.offsetHeight / 2) - (height / 2);
		
		window.open('/card/insertCardView', '카드 등록 창' , `width=${width},height=${height},top=${top},left=${left}`)
	});
	
	// 수정 버튼 클릭
	$('.updateBtn').on('click', function(e){
		e.preventDefault();
		let checked = $('input:checkbox[name=card]:checked').length;
		
		if(checked > 1) alert('항목을 1개만 선택해주세요.');
		else if(checked < 1) alert('수정할 항목을 선택해주세요.');
		else {
			let checkedIndex = $('input:checkbox[name=card]:checked').val();
			//console.log(checkedIndex);
			
			const width = 500;
			const height = 500;
			
			let left = (document.body.offsetWidth / 2) - (width / 2);
			let top = (document.body.offsetHeight / 2) - (height / 2);
			
			window.open('/card/updateCardView/'+checkedIndex, '카드 수정 창' , `width=${width},height=${height},top=${top},left=${left}`)
		}
	});
	
	// 삭제 버튼 클릭
	$('.deleteBtn').on('click', function(e){
		e.preventDefault();
		let checked = $('input:checkbox[name=card]:checked').length;
		
		if(checked < 1) alert('삭제할 항목을 선택해주세요.');
		else {
			let answer = confirm('해당 항목을 정말로 삭제하시겠습니까?');
			
			if(answer){
				let checkedIDs = [];	//체크된 index값을 담을 배열
				
				// 체크된 항목의 index 값 배열에 담기
				$('input:checkbox[name=card]:checked').each(function(){
					checkedIDs.push($(this).val());
				});
				
				/*** Ajax ***/			
				$.ajax({
					type: 'post',
					url: '/card/deleteCard',
					data: {'idArray': checkedIDs},
					success: function(result){
						if(result != 0){
							alert('카드를 정상적으로 삭제했습니다.');
							window.location.href = '/card';
						}
					},
					error: function(error) {
						console.log(error);
					}
				});
			};
		};
	});
	
	// 페이지 버튼 클릭
	$(document).on('click', '.pageLink' ,function(){
		// pagination 정보
		let page = $('#pagination').attr('data-page');
		let range = $('#pagination').attr('data-range');
		let rangeSize = $('#pagination').attr('data-rangeSize');
		
		// active 클래스 처리
		$('.pageItem.active').removeClass('active');
		$(this).parent().addClass('active');
		
		let condition = $(this).attr('id');
		
		// 조건별 페이지 범위 계산
		switch (condition) {
			case 'prev':
				page = parseInt((range - 2) * rangeSize) + 1;	// 이전 페이지 범위의 가장 앞 페이지로 이동
				range = parseInt(range) - 1;
				break;
			case 'next':
				page = parseInt((range * rangeSize)) + 1; // 다음 페이지 범위의 가장 앞 페이지로 이동
				range = parseInt(range) + 1;
				break;
			default:
				page = $(this).text();
				break;
		}
		
		// Ajax
		let card_type = $('input[name=card_type]:checked').val();
		let data = {"card_type":card_type, "keyword": keyword, "word": word, "page": page, "range": range};
		getCardList(data); // 카드 목록
		getPage(data); // 페이지
	});
	
	// 카드 목록 불러오기 함수
	function getCardList(data){
		// console.log(data);
		$.ajax({
			type: 'post',
			url: '/card/cardList',
			data: data,
			success: function(result) {
				$('#cardList').empty();
				$('#cardList').html(result);
			},
			error: function(error) {
				console.log(error);
			}
		});
	};
	
	// 페이지 범위 불러오기 함수
	function getPage(data) {
		$.ajax({
			type: 'POST',
			url: '/card/page',
			data: data,
			success: function(result) {
				$('#paginationBox').empty();
				$('#paginationBox').html(result);
			}
		})
	}
});