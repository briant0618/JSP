<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	String success = request.getParameter("success"); // Java 변수
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link rel="stylesheet" href="/JBoard1/css/style.css"/>
    <script>
    	var success = "<%= success %>";  // JS 변수 [위의 success랑 헷갈리지말아요]
    	if(success == 100){
    		alert('해당하는 회원이 존재하지 않습니다. ID/PW를 다시 확인해주세요.')
    	}else if(success == 101){
    		alert('우선 로그인후에 접속 해주시길 바랍니다.')
    	}else if(success == 102){
    		alert('로그 아웃이 정상적으로 처리되었습니다.')
    	}
    </script>
  
</head>


<body>
    <div id="wrapper">
        <section id="user" class="login">
            <form action="/JBoard1/user/proc/login.jsp" method = "post">
                <table border="0">
                    <tr>
                        <td><img src="/JBoard1/img/login_ico_id.png" alt="아이디"/></td>
                        <td><input type="text" name="uid" placeholder="아이디를 입력" /></td>
                    </tr>
                    <tr>
                        <td><img src="/JBoard1/img/login_ico_pw.png" alt="비밀번호"/></td>
                        <td><input type="password" name="pass" placeholder="비밀번호 입력" /></td>
                    </tr>
                </table>
                <input type="submit" class="btnLogin" value="로그인"/>
            </form>

            <div class="info">
                <h3>회원로그인 안내</h3>
                <p>
                    아직 회원이 아니시면 회원으로 가입하세요.
                </p>
                <a href="/JBoard1/user/terms.jsp">회원가입</a>
            </div>
        </section>
    </div>    
</body>
</html>