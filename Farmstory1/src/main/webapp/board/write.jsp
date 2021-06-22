<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file = "../_header.jsp" %>
<% 
	// 각각의 게시판을 더 만들지 않고 하나의 list에 4개에 각각 들어가기 위해서 parameter로 불러옴!
	request.setCharacterEncoding("utf-8");
	String group = request.getParameter("group");
	String cate  = request.getParameter("cate");
	String path = "./_aside_" + group + ".jsp";
%>
<jsp:include page = "<%= path %>"/>

	<section id="board" class="write">
	    <h3>글쓰기</h3>
	    <article>
	        <form action="#">
	            <table>
	                <tr>
	                    <td>제목</td>
	                    <td><input type="text" name="title" placeholder="제목을 입력하세요."/></td>
	                </tr>
	                <tr>
	                    <td>내용</td>
	                    <td>
	                        <textarea name="content"></textarea>                                
	                    </td>
	                </tr>
	                <tr>
	                    <td>첨부</td>
	                    <td><input type="file" name="file"/></td>
	                </tr>
	            </table>
	            <div>
	                <a href="/Farmstory1/board/list.jsp?group=<%= group %>&cate=<%= cate %>" class="btnCancel">취소</a>
	                <input type="submit"  class="btnWrite" value="작성완료">
	            </div>
	        </form>
	    </article>
	</section>
<%@ include file = "../_footer.jsp" %>
</body>
</html>