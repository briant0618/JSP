<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%-- index.jsp는 login 유무에따라 이동 페이지 변경되게 설정!  --%>

<c:choose>
	<c:when test="${sessMember eq null}">
		<jsp:forward page="/user/login.do"/>
	</c:when>
	<c:otherwise>
		<jsp:forward page="/list.do"/>
	</c:otherwise>
</c:choose>

