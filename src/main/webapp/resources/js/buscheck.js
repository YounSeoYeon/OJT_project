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
			return;
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
					}else{
						alert("중복된코드입니다. 다른코드를 사용하십시오");		
						$('#checkresult').html("");			
						$('#checkresult').html("중복코드");
						$('#checkresult').attr('color','red');
						$('#bus_code').value = "";
						checkresult = $('#codeli span').html();
						console.log(checkresult);
						$('#bus_code').focus();
						return;
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
		var checkresult = $('#codeli span').html();
		var codeInsertForm = document.querySelector('#codeInsertForm');
		codeInsertForm.querySelector('#bus_tel1').value;
		var buscode = codeInsertForm.bus_code.value;
		var busRep =  codeInsertForm.bus_rep.value;
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
				
		if((!check(buscode, "업체코드를")) || (checkresult!='사용가능')){	//업체코드가 비어있거나,사용가능표시가 아닐때
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
			/*else if(!check(busTel, "전화번호를")){
			return false;}
			else if(!check(busFax, "팩스번호를")){
			return false;}*/
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