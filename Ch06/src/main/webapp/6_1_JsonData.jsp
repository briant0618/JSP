<%@ page  contentType="application/json;charset=UTF-8" pageEncoding="UTF-8"%>


<%
	/*
		날짜 : 2021/06/03
		이름 : 김동현
		내용 : JSON
		
		
		JSP 문서 선언 => HTML아니라서 타입 변경해야함.
	*/
	
	
	String json = "{\"uid\" : \"a101\" , \"name\" : \"홍길동\" , \"hp\" : \"010-1011-1111\" , \"age\" : 25}"; // JSON은 {}로 시작함 "안에 "를 또 넣으려면 Escape처리 해줘야한다.[\ 표시]
 
	out.print(json);
	


%>
 