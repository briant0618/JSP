<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-9">
	<title>2_5_Include</title>
	<!-- 날짜 : 2021/05/31
    	 이름 : 김동현
    	 내용 : JSP Include [지시자] 
    	 
    	 Include 지시자
    	 - 공통의 전역파일응 삽입하는 지시자
    	 - 일반적으로 UI module, 공통전역 파일 변수를 삽입할 때 사용
    	 - 정적타입 삽입, 참고) Include 태그는 동적타임에 삽입함..
    	 -->
</head>
<body>
	
	<h3> JSP Include 연습</h3>
	<!-- 지시자 삽입 문법. -->
	<%@ include file ="./inc/_header.jsp" %>
	<main>
		<h4> main영역</h4>
	</main>
	<%@ include file ="./inc/_footer.jsp" %>
	
	

</body>
</html>