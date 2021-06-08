/**
	날짜 : 2021/06/08
	이름 : 김동현
	내용 : Form Tag 유효성 검사 스크립트 - 유효하지 않는 유형 찾아서 false 시킴
 */

	$(function(){
    		
    		//유효성 검증
    		$('#regForm').submit(function(){
    			if(isUidOk == false){
    				alert('아이디가 유효하지 않습니다.');
    				return false;
    			}
    			if(isPassOk == false){
    				alert('비밀번호가 유효하지 않습니다.');
    				return false;
    			}
    			if(isNameOk == false){
    				alert('이름이 유효하지 않습니다.');
    				return false;
    			}
    			if(isNickOk == false){
    				alert('별명이 유효하지 않습니다.');
    				return false;
    			}
    			if(isEmailOk == false){
    				alert('이메일이 유효하지 않습니다.');
    				return false;
    			}
    			if(isHpOk == false){
    				alert('휴대폰이 유효하지 않습니다.');
    				return false;
    			}
    			return true;
    		});
    	});
    