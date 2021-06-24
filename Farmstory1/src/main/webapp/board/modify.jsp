<%@page import="java.util.List"%>
<%@page import="kr.co.farmstory1.bean.ArticleBean"%>
<%@page import="kr.co.farmstory1.dao.ArticleDao"%>
<%@page import="kr.co.farmstory1.bean.MemberBean"%>
<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "../_header.jsp" %>

<%

//로그인 안하고 list에 페이지 요청할 경우
	if(mb == null){
		response.sendRedirect("/Farmstory1/user/login.jsp?success=101");
		return;
	}	
		
	request.setCharacterEncoding("utf-8");
	String seq = request.getParameter("seq");
		
	// 수정할 글 가져오기!	
	 
	ArticleDao dao = ArticleDao.getInstance();

	ArticleBean article = dao.selectArticle(seq);
	
	request.setCharacterEncoding("utf-8");
	String group = request.getParameter("group");
	String cate  = request.getParameter("cate");
	String path = "./_aside_"+group+".jsp";
%>
<jsp:include page="<%= path %>"/>

	<section id="board" class="modify">
            <h3>글수정</h3>
            <article>
                <form action="/Farmstory1/board/proc/modify.jsp" method = "post">
                	<input type = "hidden" name = "seq" value = "<%= seq %>"/>
                    <table>
                        <tr>
                            <td>제목</td>
                            <td><input type="text" name="title" value="<%= article.getTitle() %>" placeholder="제목을 입력하세요."/></td>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td>
                                <textarea name="content"><%= article.getContent() %></textarea>                                
                            </td>
                        </tr>
                        <!-- 
                        <tr>
                            <td>첨부</td>
                            <td><input type="file" name="file"/></td>
                        </tr>
                        multipart를 proc과 여기에 추가로 정의해주면 파일도 수정가능! but 지금은 안하겟습니다. -> 따로 해보자.
                        -->
                    </table>
                    <div>
                        <a href="/Farmstory1/board/list.jsp?group=<%= group %>&cate=<%= cate %>&seq=<%= seq %>" class="btnCancel">취소</a>
                        <!-- 주의 = 수정 취소도 번호 들고가서 취소해야한다! -->
                        <input type="submit"  class="btnWrite" value="수정완료">
                    </div>
                </form>
            </article>
        </section>
 </article>
</section>
</div>       
<%@ include file = "../_footer.jsp" %>
</body>
</html>