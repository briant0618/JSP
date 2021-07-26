package kr.co.jboard2.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.dao.ArticleDao;
import kr.co.jboard2.vo.ArticleVO;

public class ModifyService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		if(req.getMethod().equals("GET")) {
			String seq = req.getParameter("seq");
			
		 	ArticleVO articleVO = ArticleDao.getInstance().selectArticle(seq);
		 	
		 	req.setAttribute("articleVO", articleVO);
			
			return "/modify.jsp";	
			
		}else {
			
			String seq     = req.getParameter("seq");
			String title   = req.getParameter("title");
			String content = req.getParameter("content");

			ArticleDao.getInstance().updateArticle(title, content, seq);
			
			return "redirect:/JBoard2/view.do?seq="+seq;
		}
		
	}

}