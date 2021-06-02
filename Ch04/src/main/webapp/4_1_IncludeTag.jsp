<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 4_1_IncludeTag </title>
<!-- 날짜 : 2021/06/02
     이름 : 김동현
     내용 : JSP include tag 
     
     include 지시자
	- 공통의 전역 파일 삽입하는 지시자
	- 일반적 UI모듈 , 공통의 전역파일 의 변수를 참조할때 사용
	- 정적 타임에 삽입 -> 코드상에서 삽입되어있는 타임
	
	include Tag
	- 공통의 전역 파일 삽입하는 태그
	- 일반적 UI모듈울 삽입할때 사용 [UI module = header/footer/main]
	- 동적 타임에 삽입. -> 출력할 떄 삽입되어 있는 타임.
     
     -->
</head>
<body>	
	<h3> Include Tag </h3>
	
	
	<h4> include 지시자 삽입 </h4>
	
	<%@ include file = "./inc/_header.jsp" %>
	
	<%@ include file = "./inc/_footer.jsp" %>
	
	<h4> include tag로 삽입 </h4>
	
	<jsp:include page = "./inc/_header.jsp"/>
	<jsp:include page = "./inc/_footer.jsp"/>
	
</body>
</html>