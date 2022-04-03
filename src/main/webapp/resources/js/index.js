/**
 * index.js
 */
 
$(function(){
	console.log("start");
	const inbtn = document.querySelector('.in');
	const moditybtn = document.querySelector('.modify');
	const outbtn = document.querySelector('.out');
	
	/* 등록버튼 눌렀을때 */
	$(inbtn).on('click', function(){
		console.log("코드추가창");
		/*window.open='./businsert';*/
		window.open('./businsert', '업체코드등록', 'width=1280, height=600, top=10, left=10');
	});
	
	/* 수정버튼 눌렀을때 체크박스 하나일때만 수정 / 2개이상이면 하나만 선택하라고 알람*/
	$(moditybtn).on('click', function(){
		console.log("업체수정창");
		const chkbustype = $('#bustype:checked'); // 체크된 업체들
		for(var i=0; i<chkbustype.length; i++){
			console.log(chkbustype[i].value);
		}
		console.log(chkbustype.length); // 체크된 업체들의 수
		
		
		if(chkbustype.length==1){		// 체크된 업체가 하나일때만 수정가능
			alert("수정가능")
			/*window.location.href='./busupdateform/'+chkbustype[0].value;*/
			window.open('./busupdateform/'+chkbustype[0].value,'업체수정','width=1280, height=600, top=10, left=10')
		}
		else{	// 한개가 아닐때(0 or 1이상)
			alert("다시체크하세요");		
		}		
	});
	
	$(outbtn).on('click',function(){
		console.log("삭제클릭함");
		
		const chkbustype = $('#bustype:checked'); // 체크된 업체들
		const chkvalue = new Array();
		for(var i=0; i<chkbustype.length; i++){
			chkvalue[i]=chkbustype[i].value;	
		}
		for(var i=0; i<chkbustype.length; i++){
			console.log(chkbustype[i].value);
		}
		console.log(chkbustype.length); // 체크된 업체들의 수
		
		/* ----------------- */
		
		if(chkbustype.length==0){		// 체크된 업체가 하나일때만 수정가능
			alert("삭제할 업체를 체크하세요");			
		}
		else{							// 체크 되었을때
			console.log(chkvalue);		// 체크된 업체들의 bus_idx값들 배열로 담음
			console.log(chkvalue[0]);	// 체크된 업체중 첫번째의 bus_idx값
			window.location.href='./busdelete/'+chkvalue;
		}		
		
	});
	
	
});