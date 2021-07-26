<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>글보기</title>
    <link rel="stylesheet" href="/JBoard2/css/style.css"/>
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
    			var textarea = $(this).parent().prev();
    			
    			if(mode == '수정'){
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
    					url: '/JBoard1/proc/commentUpdate.jsp',
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
                    <td><input type="text" name="title" value="${article.title}" readonly/></td>
                </tr>
                <c:if test="${article.file > 0}">
	                <tr>
	                    <td>첨부파일</td>
	                    <td>
	                        <a href="/JBoard2/download.do?seq=${article.fb.seq}">${article.fb.oldName}</a>
	                        <span>${article.fb.download}회 다운로드</span>
	                    </td>
	                </tr>
                </c:if>
                <tr>
                    <td>내용</td>
                    <td>
                        <textarea name="content" readonly>${article.content}</textarea>
                    </td>
                </tr>
            </table>
            <div>
                <a href="#" class="btnDelete">삭제</a>
                <a href="/JBoard2/modify.do" class="btnModify">수정</a>
                <a href="/JBoard2/list.do" class="btnList">목록</a>
            </div>  
            
            <!-- 댓글리스트 -->
            <section class="commentList">
                <h3>댓글목록</h3>
                <c:forEach var="comment" items="${comments}">
	                <article class="comment">
	                    <span>
	                        <span>${comment.nick}</span>
	                        <span>${comment.rdate.substring(2, 10)}</span>
	                    </span>
	                    <textarea name="comment" readonly>${comment.content}</textarea>
	                    <div>
	                        <a href="#">삭제</a>
	                        <a href="#">수정</a>
	                    </div>
	                </article>
                </c:forEach>
                
                <c:if test="${article.comment == 0}">
	                <p class="empty">
	                    등록된 댓글이 없습니다.
	                </p>
                </c:if>
            </section>

            <!-- 댓글입력폼 -->
            <section class="commentForm">
                <h3>댓글쓰기</h3>
                <form action="/JBoard2/comment.do" method="post">
                	<input type="hidden" name="parent" value="${article.seq}"/>
                	<input type="hidden" name="uid" value="${sessMember.uid}"/>
                    <textarea name="comment"></textarea>
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