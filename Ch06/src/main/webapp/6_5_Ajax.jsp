<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
<title> A-JAX </title>
<!-- 날짜 : 2021/06/03
	 이름 : 김동현
	 내용 : AJAX 
	
	AJAX(Asynchronous JavaScript And XML) = 비동기 JS + XML 
	- 일반적인 HTTP 통신과 다르게 부분적 통신방식 (비동기 통신)
	- JQuery Library의 AJAX 함수를 이용해 통신함
	- 통신결과 data로 주로 JSON을 다룬다. -->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			
			var tdUid = $('table tr:nth-child(1) > td:nth-child(2)');
			var tdName = $('table tr:nth-child(2) > td:nth-child(2)');
			var tdHp = $('table tr:nth-child(3) > td:nth-child(2)');
			var tdAge = $('table tr:nth-child(4) > td:nth-child(2)');
			
			$('button').click(function(){
				// AJAX 통신에서 기본 옵션 4개.
				$.ajax({ 
					url : './6_1_JsonData.jsp',
					type : 'get',  // 데이터 전송 방식 [ Get or Post ]
					dataType : 'json',  // server에서 client로 전달할때의 데이터 타입
					success: function(data){  // function(매개변수)
						
						tdUid.text(data.uid);
						tdName.text(data.name);
						tdHp.text(data.hp);
						tdAge.text(data.age);
						
					} 
				
					
				});  // json표기 따름 
				
			});
			
		});
		
	</script>

	
</head>
<body>

	<h3> AJAX 통신</h3>
	<button> Data Loading </button>


	<table border = "1">
	
		<tr>
			<td> ID </td>
			<td> </td>
		</tr>
		<tr>
			<td> Name </td>
			<td> </td>
		</tr>
		<tr>
			<td> Hp </td>
			<td> </td>
		</tr>
		<tr>
			<td> Age </td>
			<td> </td>
		</tr>
	</table>

</body>
</html>