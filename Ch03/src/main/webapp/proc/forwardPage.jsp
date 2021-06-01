<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> Forward Page </title>
</head>
<body>
	<h2> Forward Page </h2>
	<%
		pageContext.forward("../3_1_Request.jsp");
	%>
	
	

</body>
</html>