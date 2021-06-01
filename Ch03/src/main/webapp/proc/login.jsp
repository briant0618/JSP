<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>3_1_Request의 login</title>
</head>
<body>
	<h3>Request.jsp에서 전송된 데이터 [Parameter] 수신하기.</h3>
	<% 
	 	// Post전송일 경우 Jsp에서 한글이 깨져서 나온다.. -> 전송 데이터 인코딩 설정해야 정상적으로 나옴.
	 	
	 	request.setCharacterEncoding("UTF-8");
	
	
		// 전송된 데이터 수신.
		String uid= request.getParameter("uid");
		String pw = request.getParameter("pw");
	
	%>
		<p>
			Id : <%= uid %> <br/>
			PW : <%= pw %> <br/>
		</p>
	
	<a href = "../3_1_Request.jsp">뒤로 가기</a>
	
</body>
</html>