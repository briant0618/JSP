/**
	날짜 : 2021/06/08
	이름 : 김동현
	내용 : 휴대폰번호 중복체크 스크립트
 */
 var isHpOk = false;
 
 
 
var isHpOk = false;

$(function(){
    		
	$('input[name=hp]').focusout(function(){
		
		var hp = $(this).val(); 
		var param = {'hp': hp};
		    			
		$.ajax({
			url: '/Farmstory1/user/proc/checkHp.jsp',
			type: 'get',
			data: param,
			dataType: 'json',
			success: function(data){
				if(data.result == 1){
					$('.resultHp').css('color', 'red').text('이미 사용중인 휴대폰번호 입니다.');
					isHpOk = false;
				}else{
					$('.resultHp').css('color', 'green').text('사용 가능한 휴대폰번호 입니다.');
					isHpOk = true;
				}
			}    				
		});
	});
});