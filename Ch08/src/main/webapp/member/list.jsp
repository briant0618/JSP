<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> list </title>
	<!-- 날짜 : 2021/06/02
     이름 : 김동현
     내용 : JDBC insert
     -->
</head>
<body>
	<h3> list </h3>
	
	<a href = "/Ch08/member/register.do"> [직원 등록] </a><br/>
	
	<h4> 직원 목록 </h4>
	
	<table border = "1">
		<tr>
			<th> ID </th>
			<th> Name </th>
			<th> HP</th>
			<th> 직급 </th>
			<th> 부서 </th>
			<th> 입사일 </th>
			<th> 관리 </th>
		</tr>
		<tr>
			<td> a101 </td>
			<td> Hong </td>
			<td> 010-1234-1111 </td>
			<td> 사원 </td>
			<td> 101 </td>
			<td> 21-06-30 </td>
			<td> 
				<a href = "/Ch08/member/modify.do"> 수정 </a>
				<a href = "#"> 삭제 </a>
			</td>
			
		</tr>
	
	
	
	
	</table>
	

</body>
</html>