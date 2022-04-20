/**
 *  insertProject.js
 */

$(function(){
	/***** 금액 입력 EVENT *****/
	// 금액 천단위 콤마 생성
	function withComma(num) {
		return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	}
	// 금액 콤마 해제
	function withoutComma(num) {
		return num.toString().replace(/,/g, '');
	}
	
	// 금액 입력란 숫자만 입력, 천단위 콤마 생성
	$('#proj_amount').on('propertychange change keyup paste input', function(){
		$(this).val($(this).val().replace(/[^0-9]/g, ''));
		
		let amount = $(this).val() || '0';
		let amountWithComma = withComma(parseInt(amount));
		$(this).val(amountWithComma);
	});
	
	// submit
	$('#codeInsertForm').on('submit', function(e){
		e.preventDefault();
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

		    if (!found) values.push({name: k, value: v});
		    
		    return values;
		}
		
		console.log(formData)
		
		if(validate($(this))) {
			$.ajax({
				type : 'post',
				url: '/projdbinsert',
				data : formData,
				success:  function(result){
					$('body').html(result);
				},
				error: function(e){
					console.log(e)
				}
			});
		};
	});
	
	//프로젝트코드 중복검사
	$('#projcodecheck').on('click',function(){
		event.preventDefault();
		projcode = $('#proj_code').val();
		console.log("1.중복확인버튼클릭,"+projcode+","+projcode.length);
		
		if(projcode.length<1){
			console.log("코드를 입력하십시오");
			$('#proj_code').focus();
			return;
		}else{
			
			$.ajax({
				url:"projcodecheck",
				type:"post",
				data:{"projcode":projcode},
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

	// 등록버튼 누르고 유효성검사
	function validate(form){
		console.log("등록버튼 누르고 유효성검사");
		var checkresult = $('#codeli span').html();
		var codeInsertForm = document.querySelector('#codeInsertForm');
		var projcode = codeInsertForm.proj_code.value;
		var projbuyer =  codeInsertForm.proj_buyer.value;
		let projnm = codeInsertForm.proj_nm.value;
		let projamount =  codeInsertForm.proj_amount.value;
		let startdate =  codeInsertForm.proj_start_date.value;
		let enddate =  codeInsertForm.proj_end_date.value;
				
		if((!check(projcode, "프로젝트 코드를")) || (checkresult!='사용가능')){	// 코드가 비어있거나,사용가능표시가 아닐때
			console.log(checkresult);
			return false;
		}else if(!check(projbuyer, "업체명을")){
			return false;
		}else if(!check(projnm, "프로젝트명을")){
			return false;
		}else if(!check(projamount, "계약금액을")){
			return false;
		}
//			else if(!check(startdate, "프로젝트 시작날짜를")){
//			return false;}
//			else if(!check(enddate, "완성날짜를")){
//			return false;}
		else{
			alert('유효성검사완료');
			return true;
		}	
	
		function check(value, dataName) {
	        if (value == null || value=="") {
	            alert(dataName + " 입력해주세요!");
	            return false;
	        }else{
	        	return true;        
    		}
    	};
	};
});