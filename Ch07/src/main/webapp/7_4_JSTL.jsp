<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix ="f" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTP-8">
	<!-- 
		날짜 : 2021/06/28
		이름 : 김동현
		내용 : JSTL [ Java Standards Tag Library ] -->
<title>4.JSTL [Java 표준태그]</title>
</head>
<body>
	<h2> JSTL 비교하기! </h2>
	
	
	<h4> ScriptLet </h4>
	<%
	
	
		// Scriptlet 변수 선언
		
		String str = "Hello";
		out.print("<p>str : "+ str + " </p> ");
		
		int num1 = 1;
		int num2 = 2;
		int num3 = 3;
		
		
		
		//  
		if(num1 < num2 ){
			out.print("<p> num1 은 num2 보다 작다.</p>");
		
		}else{
			out.print("<p> num1 은 num2 보다 크다.</p>");
		}
		
		
		if(num1 > num2 ){
			out.print("<p> num1 은 num2 보다 크다.</p>");
		
		}else if( num2 > num3){
			out.print("<p> num2 는 num3 보다 크다.</p>");
		}else{
			out.print("<p> num3가 가장 크다.</p>");
			
		}
		
		// 반복문
		
		for(int i = 1; i <= 5; i++){
			out.print("<p>" + i + ".Hello JSTL </p>");
		}
		
		String people [] = {"김유신","김춘추","장보고","강감찬","이순신"};
		
		for(String person : people){
			out.print("<p>" + person + " </p>");
		}
		
		int sum = 0;
		
		for(int k =1; k <= 10; k ++){
			sum += k;
		}
		out.print("<p> 1 ~ 10까지의 합 : " + sum + "</p>");
		
		
		// 문자열 처리
		String hello = "Hello Korea";
		out.print("<p> 문자열 길이 : " + hello.length() + "</p>");
		out.print("<p> 문자열 자르기 : " + hello.substring(2, 10) + "</p>");
		out.print("<p> 문자열 교체: " + hello.replace("korea","busan") + "</p>");
		out.print("<p> 문자열 index : " + hello.indexOf("e") + "</p>");
	
	%>
	
	<h4> JSTL로 ScriptLet 표현하기.</h4>
	
		<!-- 1.변수 선언 = core library의 c태그 사용! -->
		
		<c:set var = "str" value = "Hello"/>
		<p> str : ${str}</p>
		
		<c:set var ="num1" value = "1"/>
		<c:set var ="num2" value = "2"/>
		<c:set var ="num3" value = "3"/>
		
		<!-- 2.조건문 -->
		<c:if test="${num1 lt num2}" />
		<p> num1은 num2보다 작다. </p>
		
		<!-- 2-1.if else 구문 -->
		<c:choose>
			<c:when test="${num1 gt num2}">
				<p>num1은 num2보다 크다.</p>
			</c:when>
			<c:otherwise>
				<p>num1은 num2보다 작다</p>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${num1 gt num2}">
				<p>num1은 num2보다 크다.</p>
			</c:when>
			<c:when test="${num2 gt num3}">
				<p>num2은 num3보다 크다.</p>
			</c:when>
			<c:otherwise>
				<p>num3가 가장크다.</p>
			</c:otherwise>
		</c:choose>
		
		
		<!--  반복문 -->
		<c:forEach var="i" begin = "1" end = "5">
			<p> ${i}.Hello JSTL</p>
		</c:forEach>
		
		<c:set var ="people"> 김유신,김춘추,장보고,강감찬,이순신</c:set>
		<c:forEach var = "person" items = "${people}">
			<p>${person}</p>
		</c:forEach>
			
		
		<c:set var ="sum" value ="0"/>
		<c:forEach var = "k" begin = "1" end ="10">
			<c:set var ="sum" value = "${sum+k}"/>
		</c:forEach>
		
		<p> 1 ~ 10 까지의 합 : ${sum}</p>
		
		<!--  문자열 처리 -->
		
		<c:set var = "hello" value = "Hello Korea"/>
		<p>문자열 길이 : ${f:length(hello)}</p>
		<p>문자열 자르기 : ${f:substring(hello,2,10)}</p>
		<p>문자열 길이 : ${f:replace(hello,"Korea","Busan")}</p>
		<p>문자열 길이 : ${f:indexOf(hello,"e")}</p>

	

</body>
</html>