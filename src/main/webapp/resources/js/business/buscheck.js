/**
 * 
 */


$(function(){
	
	//업체코드 중복검사
	$('#buscodecheck').on('click',function(){
		event.preventDefault();
		buscode = $('#bus_code').val();
		console.log("1.중복확인버튼클릭,"+buscode+","+buscode.length);
		
		if(buscode.length<1){
			console.log("코드를 입력하십시오");
			$('#bus_code').focus();
			return false;
		}else{
			
			$.ajax({
				url:"buscodecheck",
				type:"post",
				data:{"buscode":buscode},
				dataType:'text',
				success:function(result){
					if(result==0){
						alert("사용가능한 코드입니다");
						$('#checkresult').html("");
						$('#checkresult').html("사용가능");
						$('#checkresult').attr('color','green');
						checkresult = $('#codeli span').html();
						console.log(checkresult);
					}
					else if(result==2){
						alert("코드유형이 잘못되었습니다. 첫번째 자리에 p(공공)나 c(민간)가 와야합니다.");
						$('#checkresult').html("");			
						$('#checkresult').html("잘못된코드");
						$('#checkresult').attr('color','red');
						$('#bus_code').value = "";
						checkresult = $('#codeli span').html();
						console.log(checkresult);
						$('#bus_code').focus();
						return false;
					}					
					else{
						alert("중복된코드입니다. 다른코드를 사용하십시오");		
						$('#checkresult').html("");			
						$('#checkresult').html("중복코드");
						$('#checkresult').attr('color','red');
						$('#bus_code').value = "";
						checkresult = $('#codeli span').html();
						console.log(checkresult);
						$('#bus_code').focus();
						return false;
					}
				},
				error:function(){
					alert("실패");
				}
			});		
		}		
	});
});


 // 등록버튼 누르고 유효성검사
	function validate(form){
		console.log("등록버튼 누르고 유효성검사");
		
		let checkresult = $('#codeli span').html();
		let codeInsertForm = document.querySelector('#codeInsertForm');
		let buscode = codeInsertForm.bus_code.value;
		let busRep =  codeInsertForm.bus_rep.value;
		let busNm = codeInsertForm.bus_nm.value;
		let busRegNo =  codeInsertForm.bus_reg_no.value;
		let busItem =  codeInsertForm.bus_item.value;
		let busEmail =  codeInsertForm.querySelector('#bus_email1').value;
		let busEmai2 =  codeInsertForm.querySelector('#bus_email2').value;
		
		let busTel1 = codeInsertForm.querySelector('#bus_tel1').value;
		let busTel2 = codeInsertForm.querySelector('#bus_tel2').value;
		let busTel3 = codeInsertForm.querySelector('#bus_tel3').value;
		let busFax1 = codeInsertForm.querySelector('#bus_fax1').value;
		let busFax2 = codeInsertForm.querySelector('#bus_fax2').value;
		let busFax3 = codeInsertForm.querySelector('#bus_fax3').value;
						

		// 1.코드중복검사 한번더
		$.ajax({
			url:"buscodecheck",
			type:"post",
			data:{"buscode":buscode},
			dataType:'text',
			success:function(result){
				if(result==0){
					$('#checkresult').html("");
					$('#checkresult').html("사용가능");
					checkresult = $('#codeli span').html();
					console.log(checkresult);
					return true;
				}else{
					alert("중복된코드입니다. 다른코드를 사용하십시오");		
					$('#checkresult').html("");			
					$('#checkresult').html("중복코드");
					$('#checkresult').attr('color','red');
					$('#bus_code').value = "";
					checkresult = $('#codeli span').html();
					console.log(checkresult);
					$('#bus_code').focus();
					return false;
				}
			},
			error:function(){
				alert("실패");
				return false;
			}
		});

		// 2.전화번호 팩스번호 9~11자리로 입력하도록 o
		// 3.사업자번호 10자리로 o
		// 4.업체코드-p or c 형식 + 코드번호
		
		// 2. 전화번호 팩스번호 각칸별 자리수맞추고 합쳐서 9~11자리로
		if(
			(busTel1.length>3) || 
			(busTel2.length>4) || 
			(busTel3.length>4) ||
			(busTel1.length+busTel2.length+busTel3.length <9) 
		){
			alert("전화번호를 다시 확인해주세요. 9~11자리 인지 확인해주세요");
			return false;
		}

		else if(
			(busFax1.length>3) || 
			(busFax2.length>4) || 
			(busFax3.length>4) ||
			(busFax1.length+busFax2.length+busFax3.length <9) 
		){
			alert("팩스번호를 다시 확인해주세요. 9~11자리 인지 확인해주세요");
			return false;
		}

		// 3.사업자번호 10자리로 o
		else if(
			busRegNo.length!=10
		){
			alert("사업자번호를 다시 확인해주세요. 사업자번호는 10자리입니다");
			return false;
		}

		// 4.업체코드-p or c 형식 
		else if(buscode.substring(0,1)!='p' && buscode.substring(0,1) !='c'){
			alert("업체코드 형식을 확인해주세요. 시작문자가 p(공기업) or c(민간) 이어야합니다. 업체코드 예)p001 ");
			return false;
		}

		// 마지막 : 다 썼는지 확인
		else if((!check(buscode, "업체코드를")) || (checkresult!='사용가능')){	//업체코드가 비어있거나,사용가능표시가 아닐때
			console.log(checkresult);
			return false;
			}
			else if(!check(busRep, "대표자명을")){
			return false;}
			else if(!check(busNm, "상호를")){
			return false;}
			else if(!check(busRegNo, "사업자번호를")){
			return false;}
			else if(!check(busItem, "종목명을")){
			return false;}
			else if(!check(busTel1, "전화번호를")){
			return false;}
			else if(!check(busTel2, "전화번호를")){
			return false;}
			else if(!check(busTel3, "전화번호를")){
			return false;}
			else if(!check(busFax1, "팩스번호를")){
			return false;}
			else if(!check(busFax2, "팩스번호를")){
			return false;}
			else if(!check(busFax3, "팩스번호를")){
			return false;}
			else if(!check(busAddress, "회사주소를")){
			return false;}
			else if(!check(busEmail, "이메일을")){
			return false;}
			else if(!check(busEmai2, "이메일을")){
			return false;}
			else{
				alert('유효성검사완료');
				form.action="./dbinsert";
				return true;
			}	
	
	    
		function check(value, dataName) {
	        if (value == null || value=="") {
	            alert(dataName + " 입력해주세요!");
	            return false;
	        }else{
	        	return true;        
	        	}
	    	}
	   }

	   