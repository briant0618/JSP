<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> Session </title>
	<!-- 날짜 : 2021/06/01
     	 이름 : 김동현
     	 내용 : JSP Session -->
</head>
<body>
	<h2> Session 내장 객체 </h2>
	<form action = "./proc/sessionProc.jsp">
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
				<input type = "submit" name ="Session저장"></td>
			</tr>
		</table>	
	</form>
	
	<!--
	<%
		session.setAttribute("name", "Mr.Hong");
		session.setAttribute("uid", "Hong");  // session table에  SID와 name/uid의 data 기록함.
		session.setMaxInactiveInterval(60*3); // session table에 있는 data는 3분동안 유효함.
	
	%>
	<h4> 사용자 정보 세션 저장 완료</h4>
	<a href = "./proc/sessionConfirm.jsp"> Session 정보 확인. </a>
	-->
</body>
</html>