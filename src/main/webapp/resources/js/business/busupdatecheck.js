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
	let busEmail =  codeInsertForm.querySelector('#bus_email').value;
	let busTel1 = codeInsertForm.querySelector('#bus_tel').value;
	let busFax1 = codeInsertForm.querySelector('#bus_fax').value;
	
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