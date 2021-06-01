<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> Redirect Page </title>
</head>
<body>
	<h3> Redirect Page </h3>
	
	<%
		response.sendRedirect("../3_1_Request.jsp");
	%>

</body>
</html>