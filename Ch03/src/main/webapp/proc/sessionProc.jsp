<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> SessionProc </title>
	<!-- 날짜 : 2021/06/01
     	 이름 : 김동현
     	 내용 : JSP SessionProc -->
</head>
<body>	
	<h3> User Session 처리</h3>
	<%
		request.setCharacterEncoding("UTF-8");
	
		String uid = request.getParameter("uid");	
		String name = request.getParameter("name");	
		
		session.setAttribute("uid", uid);
		session.setAttribute("name", name);
	
	%>
	
	<h4> User 정보 session 저장완료 </h4>
	<a href = "./sessionConfirm.jsp"> User Session 확인 </a>
	
</body>
</html>