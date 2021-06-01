<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<title>register</title>
</head>
<body>
	<h3> 회원 가입 정보 수신.</h3>
	
	<% 
		request.setCharacterEncoding("UTF-8");
	
	  	String name = request.getParameter("name");
	  	String gender = request.getParameter("gender");
	  	String[] hobbies = request.getParameterValues("hobby");
	  	// hobby는 중복선택이기 때문에 뒤에 Values를 쓴다. 
	  	// Java에서 마지막에 s를 더붙이면 웬만하면 배열을 return하는 것이라고 생각하자.
	  	String addr = request.getParameter("addr");
	  	
	%>
	
	<p>
		Name : <%= name %><br/>
		Gender : 
		<%= gender.equals("1") ? "남자" : "여자" %><br />
		Hobby : 
		<%
			for(String hobby : hobbies){
				out.print(hobby + ",");
			}
		
		%><br/>
		Address : <%= addr %><br/>
	
	
	</p>
	
	<a href = "../3_1_Request.jsp">뒤로 가기</a>
</body>
</html>