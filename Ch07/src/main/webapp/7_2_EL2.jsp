<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTP-8">
	
	<!-- 
		날짜 : 2021/06/28
		이름 : 김동현
		내용 : jsp 표현언어 내장객체 -->
		
	<title></title>
</head>
<body>
	<h3>2.표현 언어 내장객체</h3>
	<%
		pageContext.setAttribute("name", "김유신");
		request.setAttribute("name", "김춘추");
		session.setAttribute("name", "장보고");
		application.setAttribute("name", "강감찬");
	%>
	
	<p>
		<!-- 애들이 그냥 name만 하면 pagecontext의 name만 참고해요.. -> xx-scope써야 각각을 참조함니다. -->
		pageContext name : ${pageScope.name}<br/>
		request name : ${requestScope.name}<br/>
		session name : ${sessionScope.name}<br/>
		application name : ${applicationScope.name}<br/>
	
	
	</p>
	
		
</body>
</html>