<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> Session 정보 확인.. </title>
</head>
<body>
	<h3> User Session Confirm..! </h3>
	
	<%
		String SID = session.getId();
		String name = (String)session.getAttribute("name");
		String uid = (String)session.getAttribute("uid");
	
	%>	
	<p>
		User Session Id = <%= SID %> <br/>
		User name = <%= name %> <br/>
		User id = <%= uid %> <br/> 
	
	</p>
	
	
</body>
</html>