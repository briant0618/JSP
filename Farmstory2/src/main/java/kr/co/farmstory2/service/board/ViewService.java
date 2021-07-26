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
		
		// �α����� ���� �ʰ� List �������� ��û�� ���
		if(mv == null){
			return "redirect:/Farmstory2/user/login.do?success=101";
			
		}
		
		// ���� ������ ����
		String seq = req.getParameter("seq");
		
		// Dao ��ü ��������
		ArticleDao dao = ArticleDao.getInstance();
		
		// �� ��������
		ArticleVO article = dao.selectArticle(seq);
		
		// �ش� �� ��ȸ�� ������Ʈ
		dao.updateArticleHit(seq);
		
		// ��� ��������
		List<ArticleVO> comments = dao.selectComments(seq);
		
		req.setAttribute("article", article);
		req.setAttribute("comments", comments);
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		return "/board/view.jsp?group=$group&cate=$cate";
	}
	
}

