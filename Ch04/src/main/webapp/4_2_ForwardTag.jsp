<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 4_1_IncludeTag </title>
<!-- 날짜 : 2021/06/02
     이름 : 김동현
     내용 : JSP forward Tag -->
</head>
<body>
	<h3> Forward의 ActionTag </h3>
	
	<!-- 
		forward 태그
		- 서버 시스템 내에서 페이지 요청을 처리하는 pageContext 내장 객체의 기능
		- 최초 요청에 대한 주소가 반영
		- JSP ActionTag로 기능을 제공..
		- 잘 안쓰긴해요..
	-->
	
		<jsp:forward page="./4_1_IncludeTag.jsp"></jsp:forward>

</body>
</html>