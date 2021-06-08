/**
	날짜 : 2021/06/08
	이름 : 김동현
	내용 : 비밀번호 일치 여부 확인
 */

var isPassOk = false;

$(function(){
	$('input[name=pass2]').focusout(function(){
		
		var pass1 = $('input[name=pass1]').val();
		var pass2 = $('input[name=pass2]').val();
		
		if(pass1 == pass2){
			$('.resultPass').css('color', 'green').text('비밀번호가 일치 합니다.');
			isPassOk = true;
		}else{
			$('.resultPass').css('color', 'red').text('비밀번호가 일치 하지 않습니다.');
			isPassOk = false;
		}
	});
});


