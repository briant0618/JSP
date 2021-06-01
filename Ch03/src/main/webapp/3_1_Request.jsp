<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 3_1_Request </title>
<!-- 날짜 : 2021/06/01
     이름 : 김동현
     내용 : JSP 내장 객체 -->
</head>
<body>
	<h1>JSP Request 내장 객체</h1>
	<form action = "./proc/login.jsp" method = "post" >
		<table border = "1">
			<tr>
				<td> ID </td>
				<td>	
				<input type = "text" name = "uid" ></td>	
			</tr>
			<tr>	
				<td> PW </td>
				<td><input type = "password" name = "pw" ></td>
			</tr>	
			<tr>	
				<td colspan = "2" align = "center">
					<input type = "submit" value = "Login">
				</td>
			</tr>
		</table>
	</form>
	
	<h4> 회원 가입 </h4>
	<form action = "./proc/register.jsp" method = "post">
		<table border = "1">
			<tr>
				<td>이름</td>
				<td><input type = "text" name = "name"></td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<label><input type = "radio" name = "gender" value = "1"> Male </label>
					<label><input type = "radio" name = "gender" value = "2"> Female </label>
					</td>
			</tr>
			<tr>
				<td>취미</td>
				<td>
					<label><input type = "checkbox" name = "hobby" value = "등산"> 등산하기 </label>
					<label><input type = "checkbox" name = "hobby" value = "독서"> 독서하기 </label>
					<label><input type = "checkbox" name = "hobby" value = "여행"> 여행하기 </label>
					<label><input type = "checkbox" name = "hobby" value = "음악"> 음악듣기 </label>
					<label><input type = "checkbox" name = "hobby" value = "영화"> 영화보기 </label>
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<select name = "addr">
						<option value = "서울"> 서울특별시 </option>
						<option value = "대전"> 대전광역시 </option>
						<option value = "대구"> 대구광역시 </option>
						<option value = "부산"> 부산광역시 </option>
						<option value = "광주"> 광주광역시 </option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan = "2" align ="right">
					<input type = "submit" value = "등록하기">
				</td>
			</tr>
		</table>

	</form>
	
	<h4> client 정보 확인 </h4>
	<table border = "1">
		<tr>
			<td>Header 정보</td>
			<td><%= request.getHeader("User-Agent") %></td>
		</tr>
		<tr>
			<td>통신규약</td>
			<td><%= request.getProtocol() %></td>
		</tr>
		<tr>
			<td>서버 이름</td>
			<td><%= request.getServerName() %></td>
		</tr>
		<tr>
			<td>요청주소</td>
			<td><%= request.getRequestURL() %></td>
		</tr>
		<tr>
			<td>요청경로</td>
			<td><%=  request.getRequestURI() %></td>
		</tr>
		<tr>
			<td>Client IP</td>
			<td><%=  request.getRemoteAddr() %></td>
		</tr> 
		
	</table>

</body>
</html>