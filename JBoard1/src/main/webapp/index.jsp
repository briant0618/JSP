<%@page import="kr.co.jboard1.bean.MemberBean"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

	MemberBean mb = (MemberBean) session.getAttribute("sessMember");

	if(mb == null){
		// 로그인 안햇어욥!
		pageContext.forward("./user/login.jsp");
	}else{
		// 로그인을 했어욥!
		pageContext.forward("./list.jsp");
	}
%>