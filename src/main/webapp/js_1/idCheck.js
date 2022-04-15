/**
 * https://cobook.tistory.com/8
 */
 
 //버튼을 누를때마다 유효성검사 실행
 $('#input_uid').focusout(function(){
		
		let userId = $('#input_uid').val(); // input_id에 입력되는 값
		
		console.log(userId);
		
		//ajax를 통해 서블릿으로 값 전송!
		$.ajax({
			url : "idCheck.do", //보낼 주소
			type : "post", //포스트 방식으로
			data : {uid: userId}, //userId라는 이름에 사용자의 입력값을 담음
			dataType : 'json',
			//보내고, 돌려받는데 성공하면
			success : function(result){ 
				if(result == 0){
					$("#checkID").html('중복된 ID');
					$("#checkID").attr('color','red');
				} else{
					$("#checkID").html('사용가능');
					$("#checkID").attr('color','green');
				} 
			},
			error : function(){
				alert("서버요청실패 : 관리자에게 문의");
			}
		})
		 
	})
