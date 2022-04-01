/**
 * delete
 */
 
 	function bdelete(){
		
		if(confirm("업체코드를 삭제하시겠습니까?") == true){
		location.href="./busdelete";
		}
		else{
			return;
		}
		}