/**
 * 
 */

 // 수정버튼 누르고 유효성검사
function validate(form){
	console.log("등록버튼 누르고 유효성검사");
	let codeInsertForm = document.querySelector('#codeInsertForm');
	codeInsertForm.querySelector('#bus_tel1').value;
	let busidx=  codeInsertForm.bus_idx.value;	
	let busRep =  codeInsertForm.bus_rep.value;
	let busNm = codeInsertForm.bus_nm.value;
	let busRegNo =  codeInsertForm.bus_reg_no.value;
	let busItem =  codeInsertForm.bus_item.value;		
	let busEmail =  codeInsertForm.querySelector('#bus_email1').value;
	let busTel1 = codeInsertForm.querySelector('#bus_tel1').value;
	let busFax1 = codeInsertForm.querySelector('#bus_fax1').value;
	
	
	// 2. 전화번호 팩스번호  9~11자리로
	if(
		(busTel1.length<9 || busTel1.length>11) 
	){
		alert("전화번호를 다시 확인해주세요. 9~11자리 인지 확인해주세요");
		return false;
	}

	else if(
		(busFax1.length<9 || busFax1.length>11) 
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
	
		if(!check(busRep, "대표자명을")){
		return false;}
		else if(!check(busNm, "상호를")){
		return false;}
		else if(!check(busRegNo, "사업자번호를")){
		return false;}
		else if(!check(busItem, "종목명을")){
		return false;}
		else if(!check(busTel1, "전화번호를")){
		return false;}
		else if(!check(busFax1, "팩스번호를")){
		return false;}
		
		else if(!check(busEmail, "이메일을")){
		return false;}
		else{
			alert('유효성검사완료');
			/*form.action="/busupdate/"+busidx;	/*8080/busupdate/5 이경로임*/ 
			/*form.action="../busupdate/"+busidx;*/	/* agw/busupdate/5 이경로임*/
			/*form.action="busupdate/"+busidx;*/	/* agw/busupdateform/busupdate/5 */
			return true;
		}	
	}

	function check(value, dataName) {
        if (value == null || value=="") {
            alert(dataName + " 입력해주세요!");
            return false;
        }
        else{
        return true;        
        }
    }