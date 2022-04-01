/**
 * 
 */
 
function validate(){
	/* event.preventDefault(); */
	var checkresult = $('#codeli span').html();
	var buscode = codeInsertForm.bus_code.value;
	var busRep =  codeInsertForm.bus_rep.value;
	let busNm = codeInsertForm.bus_nm.value;
	let busRegNo =  codeInsertForm.bus_reg_no.value;
	let busItem =  codeInsertForm.bus_item.value;
	let busTel1 =  codeInsertForm.bus_tel1.value;
	let busTel2 =  codeInsertForm.bus_tel2.value;
	let busTel3 =  codeInsertForm.bus_tel3.value;
	let busFax1 =  codeInsertForm.bus_fax1.value;
	let busFax2 =  codeInsertForm.bus_fax2.value;
	let busFax3 =  codeInsertForm.bus_fax3.value;
	let busAddress =  codeInsertForm.busAddress.value;
	let busEmail =  codeInsertForm.bus_email.value;
	
	console.log(buscode+","+busRep+","+busNm);
	
	if(!check(buscode, "업체코드를") || checkresult=='중복코드'){
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
		else{
			/* window.location.href='./dbinsert/'+buscode+'/'+busRep+'/'+busNm+'/'+busRegNo+'/'+busItem+'/'+busTel1+'/'+busTel2+'/'+busTel3+'/'+busFax1+'/'+busFax2+'/'+busFax3+'/'+busEmail+'/'+busAddress; */
			console.log('유효성검사완료');
			console.log(buscode+","+busRep+","+busNm);
			/* return true; */
			window.location.href='./dbinsert/'+buscode;
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