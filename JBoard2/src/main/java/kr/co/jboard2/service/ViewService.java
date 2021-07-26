package kr.co.jboard2.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.jboard2.dao.ArticleDao;
import kr.co.jboard2.vo.ArticleVO;
import kr.co.jboard2.vo.MemberVO;

public class ViewService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		//세션 사용정보 가져오기
		HttpSession sess = req.getSession();
		MemberVO mv = (MemberVO) sess.getAttribute("sessMember");
		
		// 로그인을 하지 않고 List 페이지를 요청할 경우
		if(mv == null){
			return "redirect:/JBoard2/user/login.do?success=101";
		}

		// 전송 데이터 수신
		String seq = req.getParameter("seq");

		// Dao 객체 가져오기
		ArticleDao dao = ArticleDao.getInstance();
		
		// 글 가져오기
		ArticleVO article = dao.selectArticle(seq);
		
		// 해당 글 조회수 업데이트
		dao.updateArticleHit(seq);
		
		// 댓글 가져오기
		List<ArticleVO> comments = dao.selectComments(seq);
		
		req.setAttribute("article", article);
		req.setAttribute("comments", comments);
		
		return "/view.jsp";
	}

}