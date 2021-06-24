<%@page import="java.util.List"%>
<%@page import="kr.co.jboard1.bean.MemberBean"%>
<%@page import="kr.co.jboard1.bean.ArticleBean"%>
<%@page import="kr.co.jboard1.dao.ArticleDao"%>
<%@ page  contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>


<%  

	//Session 사용자 정보 가져오기
	MemberBean mb = (MemberBean) session.getAttribute("sessMember");

	// 로그인 안하고 list에 페이지 요청할 경우
	if(mb == null){
		response.sendRedirect("/JBoard1/user/login.jsp?success=101");
		return;
	}



	// list에서 전송된 데이터 수신
	request.setCharacterEncoding("utf-8");
	String seq = request.getParameter("seq");
	
	// Dao객체 가져오기
	ArticleDao dao = ArticleDao.getInstance();
	
	// 글 가져오기
	ArticleBean article = dao.selectArticle(seq);
	
	// 해당 글 조회수 업데이트
	dao.updateArticleHit(seq);
	
	// 댓글 가져오기
	List<ArticleBean> comments = dao.selectComments(seq); //
	
	
	


%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>글보기</title>
    <link rel="stylesheet" href="/JBoard1/css/style.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
    	jQuery(function(){
    		$('.btnCommentDel').click(function(){
    			if(confirm('정말 삭제 하시겠습니까?')){
    				return true;
    			}else{
    				return false;
    			}
    		});
    		
    		$('.btnCommentModify').click(function(e){
				e.preventDefault();
    			
				var btnThis = $(this);
    			var mode = btnThis.text();
    			var textarea = $(this).parent().prev();  // a태그의 prev는 span이고 next는 textarea라는데 원리를 다시한번 알아봅시다.
    			
    			if(mode == '수정'){ // 댓글을 수정모드로 변환하기
    				// 수정모드
    				btnThis.text('수정완료');
        			textarea.attr('readonly', false).focus();
        			textarea.css('outline', '1px gray solid');
					
    			}else{
    				// 수정완료 모드
    				var content = textarea.val();
    				var seq     = textarea.attr('data-seq');
    				var parent  = textarea.attr('data-parent');
    				
    				var jsonData = {'seq': seq,
    								'parent': parent,
    								'content': content};
    				
    				$.ajax({
    					url: '/JBoard1/proc/commentModify.jsp',
    					type: 'post',
    					data: jsonData,
    					dataType: 'json',
    					success: function(data){
    						
    						if(data.result == 1){
    							textarea.attr('readonly', true);
    							textarea.css('outline', 'none');
    							btnThis.text('수정');
    						
    							alert('수정 완료 했습니다.');
    						}  						
    					}
    				});
    				
    			}
    		});
    		
    	});
    </script>
</head>
<body>
    <div id="wrapper">
        <section id="board" class="view">
            <h3>글보기</h3>
            <table>
                <tr>
                    <td>제목</td>
                    <td><input type="text" name="title" value="<%= article.getTitle() %>" readonly/></td>
                </tr>
                <% if(article.getFile() == 1){ %>
                <tr>
                    <td>첨부파일</td>
                    <td>
                        <a href="/JBoard1/proc/download.jsp?seq=<%= article.getFb().getSeq() %>"><%= article.getFb().getOriName() %></a>
                        <span>[ 다운로드 횟수 = <%= article.getFb().getDownload() %>회 ]</span>
                    </td>
                </tr>
                <% } %>
                <tr>
                    <td>내용</td>
                    <td>
                        <textarea name="content" readonly><%= article.getContent() %></textarea>
                    </td>
                </tr>
            </table>
            <div>
            	<% if(mb.getUid().equals(article.getUid())){ %>
                <a href="/JBoard1/proc/delete.jsp?seq=<%= article.getSeq() %>" class="btnDelete">삭제</a>
                <a href="/JBoard1/modify.jsp?seq=<%= article.getSeq() %>" class="btnModify">수정</a>
                <% } %>
                <a href="/JBoard1/list.jsp" class="btnList">목록</a>
            </div>  

            
            <!-- 댓글리스트 -->
            <section class="commentList">
                <h3>댓글목록</h3>
                <% for(ArticleBean comment : comments){ %>
                <article class="comment">
                    <span>
                        <span><%= comment.getNick() %></span>
                        <span><%= comment.getRdate().substring(2, 16) %></span>
                    </span>
                    <textarea name="comment" data-seq = "<%= comment.getSeq() %>" data-parent = "<%=comment.getParent() %>" readonly><%= comment.getContent() %></textarea> <!-- 수정완료시 번호 받아와야하기때문에 사용자 정의 속성 추가. [data-속성] -->
                    
                    <!-- 내가 쓴글 일때만 수정 삭제 하려면! -->
                    <% if(comment.getUid().equals(mb.getUid())){ %>
                    <div>
                        <a href="/JBoard1/proc/commentDelete.jsp?seq=<%= comment.getSeq()%>&parent=<%= comment.getParent()%>" class ="btnCommentDel">삭제</a>
                        <a href="#" class = "btnCommentModify">수정</a>
                    </div>
                    <% } %>
                </article>
                <% } %>
                
                <% if(comments.size() == 0){ %>
                <p class="empty">
                    등록된 댓글이 없습니다.
                </p>
                <% } %>
            </section>

            <!-- 댓글입력폼 -->
            <section class="commentForm">
                <h3>댓글쓰기</h3>
	               <form action="/JBoard1/proc/comment.jsp" method="post">
	                	<input type="hidden" name="parent" value="<%= article.getSeq() %>"/>
	                	<input type="hidden" name="uid" value="<%= mb.getUid() %>"/>
                	
                	<!-- parent번호는 원 글의 Seq에서 불러오면 되는거고 article.getSeq는 글작성자고 댓글작성자는 Session에 있는 Uid이다. -->
                	
                    <textarea name="comment" required = "required"></textarea>
                    <!-- required = 폼에 전송할때 빈값이 있으면 전송이 불가능하게하는 유효성 검증장치 -->
                    <div>
                    
                        <a href="#" class="btnCancel">취소</a>
                        <input type="submit" class="btnWrite" value="작성완료"/>
                    </div>
                </form>
            </section>

        </section>
    </div>    
</body>
</html>