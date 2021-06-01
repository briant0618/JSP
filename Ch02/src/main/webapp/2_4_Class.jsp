<%@page import="sub1.Account"%>
<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>2_4_Class</title>
</head>
<body>
	<h3> JSP Class 실습</h3>
	<!-- 날짜 : 2021/05/31
    	 이름 : 김동현
    	 내용 : JSP Class -->

	<%
		Account kb = new Account("국민은행","121-1-1-1021","김유신",100000);
		Account wr = new Account("우리은행","121-1-1-1021","김유신",100000);
		
		kb.deposit(40000);
		kb.withdraw(7000);
		kb.show(out);
		
		wr.deposit(20000);
		wr.withdraw(5000);
		wr.show(out);
	
	%>
</body>
</html>