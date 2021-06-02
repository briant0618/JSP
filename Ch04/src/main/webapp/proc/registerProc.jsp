<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> RegisterProc </title>
</head>
<body>
	<h3> UseBean ActionTag </h3>


	<%
	request.setCharacterEncoding("UTF-8");
	/* 원래 Parameter 수신
	 String name = request.getParameter("name");
	 String gender = request.getParameter("gender");
	 String [] hobbies = request.getParameterValues("hobby");
	 String addr = request.getParameter("addr");
	 RegisterBean rb = new RegisterBean();
	 rb.setName(name); 
	 rb.setGender(Integer.parseInt(gender)); 
	 rb.setHobby(hobbies); 
	 rb.setAddr(addr); */

	
	%>
	<!-- 또 다른 수신 기능 => UseBean으로 Parameter 수신하자.. -->
	<jsp:useBean id="rb" class = "sub1.RegisterBean">
		<jsp:setProperty property="name" name="rb"/>
		<jsp:setProperty property="gender" name="rb"/>
		<jsp:setProperty property="hobby" name="rb"/>
		<jsp:setProperty property="addr" name="rb"/>
	</jsp:useBean>


	<p>
		Name : <%= rb.getName() %>
		gender : <%= rb.getGender()== 1 ? "Male" : "Female" %>
		Hobby : <% for(String hobby : rb.getHobby()){
				out.print(hobby + ",");
			}
		%>
		
		Addr : <%= rb.getAddr()  %>
	</p>
</body>
</html>