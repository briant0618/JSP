package kr.co.jboard2.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.jboard2.dao.MemberDao;
import kr.co.jboard2.service.CommonService;
import kr.co.jboard2.vo.MemberVO;

public class LoginService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		if(req.getMethod().equals("GET")) {
			// Get
			return "/user/login.jsp";
		}else {
			// Post
			// 전송 데이터 수신
			String uid  = req.getParameter("uid");
			String pass = req.getParameter("pass");
			
			MemberVO mv = MemberDao.getInstance().selectMember(uid, pass);
			
			if(mv != null) {
				// 회원이 맞으면
				
				// 사용자 정보객체 세션 저장
				HttpSession sess = req.getSession();
				sess.setAttribute("sessMember", mv);
				
				return "redirect:/JBoard2/list.do";
				
			}else {
				// 회원이 아니면
				return "redirect:/JBoard2/user/login.do?success=100";
			}
		}
	}

}

