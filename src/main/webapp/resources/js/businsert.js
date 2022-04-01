/**
 * businsert.js
 */
 
$(function(){
	console.log("start");
	var buscode = $('#bus_code').val();
	var checkresult = $('#codeli span').html();

	//코드 중복검사
	$('#buscodecheck').on('click',function(e){
		e.preventDefault();
		buscode = $('#bus_code').val();
		console.log("중복확인버튼클릭,"+buscode+","+buscode.length);
		
		if(buscode.length<1){
			console.log("코드를 입력하십시오");
			$('#bus_code').focus();
			return;
		}else{
			
			$.ajax({
				url:"buscodecheck",
				type:"post",
				data:{"buscode":buscode},
				dataType:'text',
				success:function(result){
					if(result==0){
						console.log("사용가능한 코드입니다");
						$('#checkresult').html("사용가능");
						$('#checkresult').attr('color','green');
						checkresult = $('#codeli span').html();
						console.log(checkresult);
					}else{
						console.log("중복된코드입니다. 다른코드를 사용하십시오");					
						$('#checkresult').html("중복코드");
						$('#checkresult').attr('color','red');
						$('#buscode').value = "";
						$('#buscode').focus();
						checkresult = $('#codeli span').html();
						console.log(checkresult);
						$('#bus_code').focus();
						return;
					}
				},
				error:function(data, textStatus, result){
					alert("실패");
					console.log(data);
					console.log(result);
					console.log(textStatus);
				}
			});		
		}
		
	});

	// 유효성검사 - 안쓴거 있으면 안넘어감
	// $('#checkAll').on('click',function(){
	// 	console.log("전체유효성검사");
	// 	console.log($('#busRep').val());
	// 	//코드없음 or 중복코드
	// 	console.log(checkresult);
	// 	if(checkresult=="" || checkresult=='중복코드'){
	// 		alert("업체코드 다시설정하세요");
	// 		$('#buscode').focus();
	// 	}else if($('#busRep').val()==""){
	// 		alert("대표자명 입력하세요");
	// 	}else if($('#busNm').val()==""){
	// 		alert("상호 입력하세요");
	// 	}else if($('#busRegNo').val()==""){
	// 		alert("사업자번호 입력하세요");
	// 	}else if($('#busItem').val()==""){
	// 		alert("종목명 입력하세요");
	// 	}else if($('#busTel1').val()==""||$('#busTel2').val()==""||$('#busTel3').val()==""){
	// 		alert("전화번호 입력하세요");
	// 	}else if($('#busFax1').val()==""||$('#busFax2').val()==""||$('#busFax3').val()==""){
	// 		alert("팩스번호 입력하세요");
	// 	}else if($('#busAddress').val()==""){
	// 		alert("주소 입력하세요");
	// 	}else if($('#busEmail').val()==""){
	// 		alert("이메일 입력하세요");
	// 	}
	// 	else{
	// 		alert("확인");
	// 		window.location.replace('./dbinsert');
	// 	}
	// });
	$('#checkAll').on('click',function(){
		alert("확인");
		var buscode = $('#buscode').val();
		var busRep = $('#busRep').val();
		var busNm = $('#busNm').val();
		var busRegNo = $('#busRegNo').val();
		var busItem = $('#busItem').val();
		var busTel1 = $('#busTel1').val();
		var busTel2 = $('#busTel2').val();
		var busTel3 = $('#busTel3').val();
		var busFax1 = $('#busFax1').val();
		var busFax2 = $('#busFax2').val();
		var busFax3 = $('#busFax3').val();
		var busAddress = $('#busAddress').val();
		var busEmail = $('#busEmail').val();
		window.location.replace('./dbinsert/buscode/busRep/busNm/busRegNo/busItem/busTel1/busTel2/busTel3/busFax1/busFax2/busFax3/busAddress/busEmail');
	});
});