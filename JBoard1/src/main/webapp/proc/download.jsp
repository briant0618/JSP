<%@page import="kr.co.jboard1.bean.FileBean"%>
<%@page import="kr.co.jboard1.dao.ArticleDao"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.net.URLEncoder"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 전송 데이터 수신
	request.setCharacterEncoding("utf-8");
	String seq = request.getParameter("seq");
	
	// DAO 객체 가져오기
	ArticleDao dao = ArticleDao.getInstance();
	
	// 파일 정보 가져오기
	FileBean fb = dao.selectFile(seq);
	
	// 파일 다운로드 횟수 +1 업데이트	
	dao.updateFileDownload(seq);
	
	// 파일 다운로드 response 헤더수정
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(fb.getOriName(), "utf-8"));
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "private");
	
	// response 객체 파일 스트림 작업
	String path = request.getServletContext().getRealPath("/file");
	
	File file = new File(path+"/"+fb.getNewName());
	
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
	
	while(true){
		int data = bis.read();
		
		if(data == -1){
			break;
		}
		bos.write(data);
	}
	
	bos.close();
	bis.close();
%>

