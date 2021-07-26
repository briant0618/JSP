<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../_header.jsp" %>

<jsp:include page="./_aside_${group}.jsp"/>

<section id="board" class="view">
    <h3>글보기</h3>
    <table>
        <tr>
            <td>제목</td>
            <td><input type="text" name="title" value="${article.title}" readonly/></td>
        </tr>
        <tr>
            <td>첨부파일</td>
            <td>
                <a href="#"></a>
                <span>7회 다운로드</span>
            </td>
        </tr>
        <tr>
            <td>내용</td>
            <td>
                <textarea name="content" readonly>${article.content}</textarea>
            </td>
        </tr>
    </table>
    <div>
        <a href="/Farmstory2/board/delete.do" class="btnDelete">삭제</a>
        <a href="/Farmstory2/board/modify.do?group=${group}&cate=${cate}" class="btnModify">수정</a>
        <a href="/Farmstory2/board/list.do?group=${group}&cate=${cate}" class="btnList">목록</a>
    </div>  
    
    <!-- 댓글리스트 -->
    <section class="commentList">
        <h3>댓글목록</h3>
        <article class="comment">
            <span>
                <span>길동이</span>
                <span>20-05-13</span>
            </span>
            <textarea name="comment" readonly>댓글 샘플입니다.</textarea>
            <div>
                <a href="#">삭제</a>
                <a href="#">수정</a>
            </div>
        </article>
        <p class="empty">
            등록된 댓글이 없습니다.
        </p>
    </section>

    <!-- 댓글입력폼 -->
    <section class="commentForm">
        <h3>댓글쓰기</h3>
        <form action="#">
            <textarea name="comment"></textarea>
            <div>
                <a href="#" class="btnCancel">취소</a>
                <input type="submit" class="btnWrite" value="작성완료"/>
            </div>
        </form>
    </section>
</section>
</div>

<%@ include file="../_footer.jsp" %>