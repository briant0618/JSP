<%@page import="kr.co.jboard1.bean.MemberBean"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.jboard1.db.DBConfig"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String regip = request.getRemoteAddr(); // 유동변경 IP기 때문에 그냥 조회해서 사용함.
	
	// 세션에서 정보 가져오기
	MemberBean mb = (MemberBean) session.getAttribute("sessMember");
	String uid = mb.getUid();
	
	
	try{
		
		// 1 ~ 2 단계
		Connection conn = DBConfig.getInstance().getConnection();		
		// 3단계
		String sql = "INSERT INTO `JBoard_Article` SET ";
			   sql += " `title` =?,";
			   sql += " `content` =?,";
			   sql += " `uid` =?,";
			   sql += " `regip` =?,";
			   sql += " `rdate`= NOW();";
			      // parent 와 comment는 댓글에 나머지는 안쓸거기때문에 5개 사용
			   
		PreparedStatement psmt = conn.prepareStatement(sql);  //statement 대신 사용.
			      
		psmt.setString(1, title);
		psmt.setString(2, content);
		psmt.setString(3, uid);
		psmt.setString(4, regip);
		
		psmt.executeUpdate();
		
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
	
	response.sendRedirect("/JBoard1/list.jsp");

%>