package kr.co.farmstory2.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.VO.TermsVO;
import kr.co.farmstory2.dao.MemberDao;
import kr.co.farmstory2.service.CommonService;

public class TermsService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		TermsVO tv = MemberDao.getInstance().selectTerms();
		
		req.setAttribute("tv", tv);
		
		return "/user/terms.jsp";
	}

}