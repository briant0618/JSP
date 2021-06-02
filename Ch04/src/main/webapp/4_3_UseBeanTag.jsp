<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> 4_1_IncludeTag </title>
<!-- 날짜 : 2021/06/02
     이름 : 김동현
     내용 : JSP UseBean Tag 
     
     UseBean Tag [ Bean은 JSP에서 사용하는 객체 = Class 를 의미한다.]
     - 대표적인 Bean은 form의 입력필드를 member로 갖는 객체 / table의 column을 member로 갖는 객체를 의미한다. [ Bean 쓸때는 java class처럼 src에 써야댐 ] 
     - 전송 파라미터를 수신 (getParameter)하는 기능을 포함하여 자바빈을 객체로 생성함
     - 자바빈 = form의 입력필드 / table의 column을 멤버로 하는 객체..
     
     -->
</head>
<body>
	<h3> UseBean Tag </h3>
	<h4> 회원 가입 </h4>
	<form action = "./proc/registerProc.jsp" method = "post">
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
	
	
	
	

</body>
</html>