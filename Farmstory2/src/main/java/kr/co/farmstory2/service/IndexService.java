package kr.co.farmstory2.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.VO.ArticleVO;
import kr.co.farmstory2.dao.ArticleDao;

public class IndexService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		//main�� �Խ��� �� ���� �۾�
		List<ArticleVO> latests = ArticleDao.getInstance().selectLatests();
		
		req.setAttribute("latests", latests);
		
		
		return "/index.jsp";
	}

}
