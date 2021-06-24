<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.farmstory1.db.Sql"%>
<%@page import="kr.co.farmstory1.db.DBConfig"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("utf-8");
	String email = request.getParameter("email");
	
	int count = -1;
	
	try{
	// 1 ~ 2단계
		Connection conn = DBConfig.getInstance().getConnection();
	
	// 3단계 [repactoring한거임 ㅎㅎ]
		PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COUNT_EMAIL);
		psmt.setString(1, email);
		
	// 4단계
		ResultSet rs = psmt.executeQuery();
	
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
