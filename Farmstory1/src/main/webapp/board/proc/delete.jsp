<%@page import="kr.co.farmstory1.dao.ArticleDao"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	request.setCharacterEncoding("utf-8");
	
	String group = request.getParameter("group");
	String cate = request.getParameter("cate");
	String seq = request.getParameter("seq");
	
	ArticleDao.getInstance().deleteArticle(seq);
	
	response.sendRedirect("/Farmstory1/board/view.jsp?group="+group+"&cate="+cate+"&seq="+seq);
%>	