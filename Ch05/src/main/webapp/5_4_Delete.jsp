<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

	 // parameter 우선 수신
	 
	 request.setCharacterEncoding("UTF-8");


	 String uid = request.getParameter("uid"); 
	 
	 //DB 정보
	 String host = "jdbc:mysql://13.209.73.49:3306/siopmy";
	 String user = "briant";
	 String pass = "k2917h06";
	 
	 
	 try{
	 // 1단계 - JDBC driver load
	 	Class.forName("com.mysql.jdbc.Driver");
	 	
	 // 2단계 - DB 접속
	 	Connection conn = DriverManager.getConnection(host, user , pass);
	 
	 // 3단계 - Sql 실행 객체 생성
	 	Statement stmt = conn.createStatement();
	 
	 // 4단계 - Sql 실행
		String sql = "DELETE FROM `Member` WHERE `uid`='" + uid + "';";
		stmt.executeUpdate(sql);
		
	 // 5단계 - Sql Resultset 처리
	 
	 // 6단계 - DB 종료
	 	conn.close();
	 
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	
	 
	 // Redirect
	 response.sendRedirect("./5_2_Select.jsp");
%>

	