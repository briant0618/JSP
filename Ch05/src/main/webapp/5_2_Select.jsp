<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="sub1.MemberBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%

//parameter 우선 수신

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
	 
	 List<MemberBean> members = new ArrayList<>();
	 
	 
	 try{
	 // 1단계 - JDBC driver load
	 	Class.forName("com.mysql.jdbc.Driver");
	 	
	 // 2단계 - DB 접속
	 	Connection conn = DriverManager.getConnection(host, user , pass);
	 
	 // 3단계 - Sql 실행 객체 생성
	 	Statement stmt = conn.createStatement();
	 
	 // 4단계 - Sql 실행
	 	String sql = "SELECT * FROM `Member` ORDER BY `rdate`";
	 	ResultSet rs = stmt.executeQuery(sql);
	
	//5단계 - Sql ResultSet 처리. [ Select문 실행 결과 = rs문 -> table에 처리해야합니다. = 자료구조를 만들어야한다. (list만들기)]
		while(rs.next()){
			MemberBean mb = new MemberBean();
			
			mb.setUid(rs.getString(1));
			mb.setName(rs.getString(2));
			mb.setHp(rs.getString(3));
			mb.setPos(rs.getString(4));
			mb.setDep(rs.getInt(5));
			mb.setRdate(rs.getString(6));
			
			members.add(mb);
			
		}
	
	
	//6단계 - db 접속 종료
		conn.close();
	
	}catch(Exception e){
		e.printStackTrace();
	}

%>




<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> JDBC Select </title>
	<!-- 날짜 : 2021/06/02
     이름 : 김동현
     내용 : JDBC Select
     -->
</head>
<body>
	<h3> Select </h3>
	
	<a href = "./5_1_Insert.jsp"> [직원 등록하기] </a>
	
	<table border = "1">
		<tr>
			<th> ID : </th>
			<th> Name : </th>
			<th> Hp : </th>
			<th> 직급 : </th>
			<th> 부서 : </th>
			<th> 입사일 : </th>
			<th> 기타 : </th>
		</tr>
		<% for(MemberBean mb : members){ %>
		
		
		<tr>
			<td><%= mb.getUid() %></td>
			<td><%= mb.getName() %></td>
			<td><%= mb.getHp() %></td>
			<td><%= mb.getPos() %></td>
			<td><%= mb.getDep() %></td>
			<td><%= mb.getRdate().substring(2, 10) %></td>
			<td>
				<a href = "#"> Edit </a>
				<a href = "#"> Delete </a>
			</td>
		</tr>
		<% } %>
	</table>	
</body>
</html>