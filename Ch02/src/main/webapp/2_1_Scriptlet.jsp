<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>2_1_Scriptlet</title>
</head>
<body>
    <!-- 날짜 : 2021/05/31
    	 이름 : 김동현
    	 내용 : JSP Scriptlet --> 
	<h3> Scriptlet 기초 </h3>
	<% 
		// Scriptlet 영역 [ 프로그래밍 코드 영역 ]
		int var1 = 1;
		boolean var2 = true;
		double var3  = 1.23;
		String var4 = "Shit";
	
		// 출력
		out.print("<h4>var1 : " + var1 + "</h4>" );
		out.print("<h4>var2 : " + var2 + "</h4>" );
		
	%>
	<!-- 표현식[expression]으로 출력 -->
	<h4> var3 : <%= var3 %></h4>
	<h4> var4 : <%= var4 %></h4>



</body>
</html>