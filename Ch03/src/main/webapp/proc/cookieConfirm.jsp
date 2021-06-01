<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> Client CookieConfirm </title>
</head>
<body>
	<h3> Client에 저장된 Cookie를 서버로 다시 전송하기 위해서 CookieConfirm</h3>
	<%
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie : cookies){
	%>	
		<p>
			Cookie Name : <%= cookie.getName() %> <br />
			Cookie Value : <%= cookie.getValue() %> <br />
		</p>		
	<%
		}
	%>


</body>
</html>