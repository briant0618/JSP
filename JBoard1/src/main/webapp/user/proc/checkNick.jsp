<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("utf-8");
	String nick = request.getParameter("nick");
	
	//DB 정보
	String host = "jdbc:mysql://13.209.73.49:3306/siopmy";
	 String user = "briant";
	 String pass = "k2917h06";
	int count = -1;
	
	try{
		// 1단계
		Class.forName("com.mysql.jdbc.Driver");
		// 2단계
		Connection conn = DriverManager.getConnection(host, user, pass);
		// 3단계
		Statement stmt = conn.createStatement();
		// 4단계
		String sql = "SELECT COUNT(`nick`) FROM `JBoard_Member` WHERE `nick`='"+nick+"';";
		ResultSet rs = stmt.executeQuery(sql);
		// 5단계
		if(rs.next()){
			count = rs.getInt(1);
		}
		
		// 6단계
		conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	
	// JSON 결과 데이터 전송
	JsonObject json = new JsonObject();
	json.addProperty("result", count);
	
	out.print(json);
%>
