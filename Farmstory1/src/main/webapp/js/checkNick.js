/**
	날짜 : 2021/06/08
	이름 : 김동현
	내용 : 닉네임 중복체크 스크립트
 */
var regNick = /^[a-z가-힣0-9]{2,10}$/;

// 최종 유효성 검증에 사용할 상태변수 선언
var isNickOk = false;

$(function(){
    		
	$('input[name=nick]').focusout(function(){
		
		var nick = $(this).val(); 
		var param = {'nick': nick};
		    			
		$.ajax({
			url: '/Farmstory1/user/proc/checkNick.jsp',
			type: 'get',
			data: param,
			dataType: 'json',
			success: function(data){
				if(data.result == 1){
					$('.resultNick').css('color', 'red').text('이미 사용중인 닉네임 입니다.');
					isNickOk = false;
				}else{
					
					if(regNick.test(nick)){
						$('.resultNick').css('color', 'green').text('사용 가능한 닉네임 입니다.');
						isNickOk = true;	
					}else{
						$('.resultNick').css('color', 'red').text('닉네임은 영문, 한글, 숫자포함 최소 2 ~ 10자 입니다.');
						isNickOk = false; 
					}
				}
			}    				
		});
	});
});