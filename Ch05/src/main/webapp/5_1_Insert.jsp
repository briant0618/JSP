<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> JDBC Insert </title>
	<!-- 날짜 : 2021/06/02
     이름 : 김동현
     내용 : JDBC insert
     -->
</head>
<body>
	<h3> JDBC Insert </h3>
	
	<a href = "./5_2_Select.jsp"> [직원 목록 보기] </a><br/>
	
	<h4> 직원 등록 </h4>
	
	<form action = "./proc/InsertProc.jsp" method = "post">
		<table border = "1">
			<tr>
				<td> ID </td>
				<td> <input type = "text" name = "uid"> </td>
			</tr>
			<tr>
				<td> Name </td>
				<td> <input type = "text" name = "name"> </td>
			</tr>
			<tr>
				<td> Phone </td>
				<td> <input type = "text" name = "hp"> </td>
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
				<input type = "submit" value = "직원등록">
			</tr>
		</table>
	</form>
	

</body>
</html>