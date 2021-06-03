<%@page import="sub1.MemberBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%
	 // Select에서 수정할 데이터를 받아와야합니다.
	 
	 //parameter 우선 수신

	 request.setCharacterEncoding("UTF-8");


	 String uid = request.getParameter("uid"); 
	 
	 String host = "jdbc:mysql://13.209.73.49:3306/siopmy";
	 String user = "briant";
	 String pass = "k2917h06";
	 
	 MemberBean mb = new MemberBean();
	 
	 try{
	 // 1단계
	 Class.forName("com.mysql.jdbc.Driver");
	 
	 // 2단계
	 Connection conn = DriverManager.getConnection(host, user , pass);
	 
	 // 3단계
	 Statement stmt = conn.createStatement();
	 
	 // 4단계
	 String sql = "SELECT * FROM `Member` WHERE `uid` = '"+ uid + "'; ";
	 ResultSet rs = stmt.executeQuery(sql);
	 
	 // 5단계 
	 if(rs.next()){
		 mb.setUid(rs.getString(1));
		 mb.setName(rs.getString(2));
		 mb.setHp(rs.getString(3));
		 mb.setPos(rs.getString(4));
		 mb.setDep(rs.getInt(5));
		 mb.setRdate(rs.getString(6)); 
	 }
	 // 6단계
	 conn.close();
	 
	 
	 }catch(Exception e){
		 e.printStackTrace();
	 }
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> JDBC Update </title>
	<!-- 날짜 : 2021/06/02
     이름 : 김동현
     내용 : JDBC insert
     -->
</head>
<body>
	<h3> JDBC Update </h3>
	
	<a href = "./5_2_Select.jsp"> [직원 목록 보기] </a><br/>
	
	<h4> 직원 수정 </h4>
	
	<form action = "./proc/UpdateProc.jsp" method = "post">
		<table border = "1">
			<tr>
				<td> ID </td>
				<td><input type="text" name="uid" readonly value="<%= mb.getUid() %>"/></td>
			</tr>
			<tr>
				<td> Name </td>
				<td> <input type = "text" name = "name" value = "<%= mb.getName()%>"> </td>
			</tr>
			<tr>
				<td> Phone </td>
				<td> <input type = "text" name = "hp" value = "<%= mb.getHp()%>"> </td>
			</tr>
			<tr>
				<td> 직급 </td>
				<td>
					<select name = "pos">
						<option> 사원 </option>
						<option> 대리 </option>
						<option> 과장 </option>
						<option> 차장 </option>
						<option> 부장 </option>
					</select>
				</td>
			</tr>
			<tr>
				<td> 부서 </td>
				<td>
					<select name = "dep">
						<option value = "101"> 영업1부 </option>
						<option value = "102"> 영업2부 </option>
						<option value = "103"> 영업3부 </option>
						<option value = "104"> 영업지원부 </option>
						<option value = "105"> 인사부 </option>
					</select>
				</td>	
			</tr>
			<tr>
				<td colspan = "2" align = "right">
				<input type = "submit" value = "직원정보 수정"/>
			</tr>
		</table>
	</form>
	

</body>
</html>