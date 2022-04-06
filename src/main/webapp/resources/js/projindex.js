/**
 * projindex.js
 */
 
$(function(){
	console.log("start");
	const inbtn = document.querySelector('.in');
	const moditybtn = document.querySelector('.modify');
	const outbtn = document.querySelector('.out');
//	const bustype =document.querySelector("radio[name='bustype']:checked");
//	const bustype =document.querySelectorAll("radio[name='bustype']");
//	console.log(bustype);
	
	/* 등록버튼 눌렀을때 */
	$(inbtn).on('click', function(){
		console.log("프로젝트 추가창");
		window.open('./projinsert', '프로젝트 코드등록', 'width=1280, height=600, top=10, left=10');
	});	
});


	/* ------------------------------------------- */
	
	// radio 버튼 클릭시 value값 db전달 -> 해당 radio값 value에 맞는 업체들 리스트에 보여줌(기존 전체있는 top 부분에 값 넣음) // 프로젝트명에 민간,공공 프로젝트 표시해주기
	function move(){		
	 	var value = $("input:radio[name='bustype']:checked").val();		//radio 클릭한 값
        console.log(value);
        
        $.ajax({
				url:"projfilter/"+value,
				type:"post",
				data:
				{"value":value},
				dataType:'text',
				success:function(result){	 /* 전체,공공,민간 값 받아오기 */
					console.log(result);
					var topnum = result.split('<div class="top">');	/* 가져온 프로젝트리스트들 / 없으면 1임 */
					console.log(topnum.length);
					
					/* ---------------------------------- */
					if(result !='' && topnum.length>1){	
					$('.topcard').empty();
					$('.topcard').append(result);
					}				
					else {
                    $('.topcard').empty();
                    $('.topcard').css({"margin-left" : "0 !important"});
                    $('.topcard').append('<span id="notFound">');
                  	$('.topcard').append('<p style="font-weight: bold; color:#474646 ;font-size: 30px; text-align: center; margin:30px;">필터 결과가 없습니다.</p>');
                    $('.topcard').append('</span>');                   
                	}
                
                	/* --------------------------------- */
					
				},
				error:function(){
					alert("실패");
				}
			});		
	}	
		
	
	// search 부분 클릭시 검색키워드 값 db전달 
	// 둘다 값 가져오기
	function input(){		
	 	//const search = document.getElementById('searchinput').value;	//검색한 값
	 	const search = $("input:text[name='search']").val();	//검색한 값
		console.log(search);
		
		$.ajax({
				url:"projsearchfilter/"+search,
				type:"post",
				data:
				{"search":search},
				dataType:'text',
				success:function(result){	 /* 전체,공공,민간 값 받아오기 */
					console.log(result);
					var topnum = result.split('<div class="top">');	/* 가져온업체리스트들 / 없으면 1임 */
					console.log(topnum.length);
					
					/* ---------------------------------- */
					if(result !='' && topnum.length>1){	
					$('.topcard').empty();
					$('.topcard').append(result);
					}				
					else {
                    $('.topcard').empty();
                    $('.topcard').css({"margin-left" : "0 !important"});
                    $('.topcard').append('<span id="notFound">');
                  	$('.topcard').append('<p style="font-weight: bold; color:#474646 ;font-size: 30px; text-align: center; margin:30px;">필터 결과가 없습니다.</p>');
                    $('.topcard').append('</span>');                   
                	}
                
                	/* --------------------------------- */
					
				},
				error:function(){
					console.log(value);
					console.log(search);
					alert("실패");
				}
			});		
	}
	