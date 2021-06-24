<%@page import="kr.co.farmstory1.db.Sql"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.co.farmstory1.bean.TermsBean"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<% 


	String host = "jdbc:mysql://13.209.73.49:3306/siopmy";
	String user = "briant";
	String pass = "k2917h06";

	TermsBean tb = new TermsBean();	
	
	try{
		// 1단계
		Class.forName("com.mysql.jdbc.Driver");
		// 2단계
		Connection conn = DriverManager.getConnection(host, user, pass);
		// 3단계
		Statement stmt = conn.createStatement();
		// 4단계
		ResultSet rs = stmt.executeQuery(Sql.SELECT_TERMS);
		
		// 5단계
		if(rs.next()){
			tb.setTerms(rs.getString(1));
			tb.setPrivacy(rs.getString(2));
		}
		// 6단계
		conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}


%>
<%@ include file = "../_header.jsp" %>
        <section id="user" class="terms">
            <table>
                <caption>사이트 이용약관</caption>
                <tr>
                    <td>
                        <textarea readonly><%= tb.getTerms() %></textarea>
                        <p>
                            <label><input type="checkbox" name="chk1"/>동의합니다.</label>
                        </p>
                    </td>
                </tr>
            </table>
            <table>
                <caption>개인정보 취급방침</caption>
                <tr>
                    <td>
                        <textarea readonly><%= tb.getPrivacy() %></textarea>
                        <p>
                            <label><input type="checkbox" name="chk2"/>동의합니다.</label>
                        </p>
                    </td>
                </tr>
            </table>
            <div>
                <a href="/Farmstory1/user/login.jsp">취소</a>
                <a href="/Farmstory1/user/register.jsp">다음</a>
            </div>
        </section>
<%@ include file = "../_footer.jsp" %>
    
</body>
</html>