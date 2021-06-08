<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("utf-8");
	String uid   = request.getParameter("uid");
	String pass1 = request.getParameter("pass1");
	String name  = request.getParameter("name");
	String nick  = request.getParameter("nick");
	String email = request.getParameter("email");
	String hp    = request.getParameter("hp");
	String zip 	 = request.getParameter("zip");
	String addr1 = request.getParameter("addr1");
	String addr2 = request.getParameter("addr2");
	String regip = request.getRemoteAddr();
	
	//DB 정보
	 String host = "jdbc:mysql://13.209.73.49:3306/siopmy";
	 String user = "briant";
	 String pass = "k2917h06";
	
	try{
		// 1단계
			Class.forName("com.mysql.jdbc.Driver");
		// 2단계
			Connection conn = DriverManager.getConnection(host, user, pass);
		// 3단계
			Statement stmt = conn.createStatement();
		// 4단계
			String sql  = "INSERT INTO `JBoard_Member` SET ";
		           sql += "`uid` = '" + uid + "',";
		           sql += "`pass`= PASSWORD('" + pass1 + "'),";
		           sql += "`name`= '" + name + "',";
		           sql += "`nick`= '" + nick + "',";
		           sql += "`email`= '" + email + "',";
		           sql += "`hp`= '" + hp + "',";
		           sql += "`zip`= '" + zip + "',";
		           sql += "`addr1`= '" + addr1 + "',";
		           sql += "`addr2`= '" + addr1 + "',";
		           sql += "`regip`= '" + regip + "',";
		           sql += "`rdate`= NOW();";
		
        	stmt.executeUpdate(sql);
	
		// 6단계
			conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	// 로그인 페이지로 리다이렉트
	response.sendRedirect("/JBoard1/user/login.jsp");
%>
