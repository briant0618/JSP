  
/**
	날짜 : 2021/06/08
	이름 : 김동현
	내용 : 이메일 중복체크 스크립트
 */
// 정규표현식 -> 이메일은 이메일에 전송하는 방식이라서 정규표현식을 잘 안씀.
	var isEmailOk = false; 
 
$(function(){
    		
	$('input[name=email]').focusout(function(){
		
		var email = $(this).val(); 
		var param = {'email': email};
		    			
		$.ajax({
			url: '/JBoard2/user/checkEmail.do',
			type: 'get',
			data: param,
			dataType: 'json',
			success: function(data){
				if(data.result == 1){
					$('.resultEmail').css('color', 'red').text('이미 사용중인 이메일 입니다.');
					isEmailOk = false;
				}else{
					$('.resultEmail').css('color', 'green').text('사용 가능한 이메일 입니다.');
					isEmailOk = true;
				}
			}    				
		});
	});
});