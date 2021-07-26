package kr.co.jboard2.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.dao.MemberDao;
import kr.co.jboard2.service.CommonService;
import kr.co.jboard2.vo.TermsVO;

public class TermsService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		// DAO
		TermsVO tv = MemberDao.getInstance().selectTerms();
		
		req.setAttribute("tv", tv);
		
		
		return "/user/terms.jsp";
	}

}
