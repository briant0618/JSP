package kr.co.farmstory2.service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.farmstory2.service.CommonService;
import kr.co.farmstory2.dao.ArticleDao;
import kr.co.farmstory2.VO.ArticleVO;
import kr.co.farmstory2.VO.MemberVO;

public class ViewService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession sess = req.getSession();
		MemberVO mv = (MemberVO) sess.getAttribute("sessMember");
		

		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		String path = "./_aside_"+group+".jsp";
		
		// 로그인을 하지 않고 List 페이지를 요청할 경우
		if(mv == null){
			return "redirect:/Farmstory2/user/login.do?success=101";
			
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
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		return "/board/view.jsp?group=$group&cate=$cate";
	}
	
}

