<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> Client CookieProc </title>
</head>
<body>
	<h3> Cookie 생성 and 전송</h3>
	
	<%
		// 불러오기 
		
		request.setCharacterEncoding("UTF-8");
	
		String uid = request.getParameter("uid");
		String name = request.getParameter("name");
	
		// 쿠키 생성
		
		Cookie c1 = new Cookie("uid", uid);
		Cookie c2 = new Cookie("name", name);
	
		// Client로 전송
		
		response.addCookie(c1);
		response.addCookie(c2);
	%>
	
	<h4> Cookie 전송 완료..! </h4>
	
	<a href = "./cookieConfirm.jsp"> Client Cookie 확인 </a>

</body>
</html>