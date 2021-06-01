<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> Client Cookie </title>
	<!-- 날짜 : 2021/06/01
     	 이름 : 김동현
     	 내용 : JSP Cookie -->
</head>
<body>
	<h2> Cookie </h2>
	
	<form action = "./proc/cookieProc.jsp">
		<table border = "1">
			<tr>
				<td> ID </td>
				<td><input type = "text" name ="uid"></td>
			</tr>
			<tr>
				<td> Name </td>
				<td><input type = "text" name ="name"></td>
			</tr>
			<tr>
				<td colspan = "2" align ="right">
				<input type = "submit" value ="Cookie전송"></td>
			</tr>
		</table>	
	</form>

</body>
</html>