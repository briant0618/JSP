<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

	 // parameter 우선 수신
	 
	 request.setCharacterEncoding("UTF-8");


	 String uid = request.getParameter("uid"); 
	 String name = request.getParameter("name"); 
	 String hp = request.getParameter("hp"); 
	 String pos = request.getParameter("pos"); 
	 String dep = request.getParameter("dep"); 
	 
	 //DB 정보
	 String host = "jdbc:mysql://54.180.160.240:3306/siopmy";
	 String user = "siopmy";
	 String pass = "1234";
	 
	 
	 try{
	 // 1단계 - JDBC driver load
	 	Class.forName("com.mysql.jdbc.Driver");
	 	
	 // 2단계 - DB 접속
	 	Connection conn = DriverManager.getConnection(host, user , pass);
	 
	 // 3단계 - Sql 실행 객체 생성
	 	Statement stmt = conn.createStatement();
	 
	 // 4단계 - Sql 실행
		String sql = "INSERT INTO `Member` VALUES ('" + uid + "','" + name + "','" + hp + "','"+ pos + "','" + dep + "',NOW());";	
		stmt.executeUpdate(sql);
		
	 // 5단계 - Sql Resultset 처리
	 
	 // 6단계 - DB 종료
	 	conn.close();
	 
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	
	 
	 // Redirect
	 response.sendRedirect("../5_1_Insert.jsp");
%>

	