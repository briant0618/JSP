<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<!-- 
		날짜 : 2021/06/28
		이름 : 김동현
		내용 : jsp 표현언어 내장객체 -->

<title>Insert title here</title>
</head>
<body>
	<h3>1.표현 언어 [Expression Language]</h3>
	 <%
	 String name = "hong";
	 int num1 = 1;
	 int num2 = 2;
	 
	 // JSP 내장 객체
	 pageContext.setAttribute("name", name);
	 request.setAttribute("num1", num1);
	 request.setAttribute("num2", num2);
	%>
	
	
	<!--	
	<p>	
		name : <%= name  %><br/> 
		num1 : <%= num1  %><br/> 
		num2 : <%= num2  %><br/> 
		num1 + num2 : <%= num1 + num2  %><br/> 
	</p>									-->
	
	
	<h4> 표현 언어 간접 참고! </h4>
	<!-- 표현언어는 scriptlet에서 직접 참조를 할수가 없다. => JSP 내장객체를 통해서 간접 참조를 한다. -->
	<p>	
		name : ${name} <br />
		num1 : ${num1} <br />
		num2 : ${num2} <br />
		num1 + num2 = ${num1 + num2} 
	</p>
		
	
	
	
	
</body>
</html>