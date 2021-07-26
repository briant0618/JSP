package kr.co.farmstory2.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.service.CommonService;
import kr.co.farmstory2.dao.MemberDao;
import kr.co.farmstory2.VO.MemberVO;

public class RegisterService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		if(req.getMethod().equals("GET")) {
			// Get
			
			return "/user/register.jsp";
			
		}else {
			// Post
			// 전송 데이터 수신
			String uid   = req.getParameter("uid");
			String pass1 = req.getParameter("pass1");
			String name  = req.getParameter("name");
			String nick  = req.getParameter("nick");
			String email = req.getParameter("email");
			String hp    = req.getParameter("hp");
			String zip   = req.getParameter("zip");
			String addr1 = req.getParameter("addr1");
			String addr2 = req.getParameter("addr2");
			String regip = req.getRemoteAddr();
			
			MemberVO mv = new MemberVO();
			mv.setUid(uid);
			mv.setPass(pass1);
			mv.setName(name);
			mv.setNick(nick);
			mv.setEmail(email);
			mv.setHp(hp);
			mv.setZip(zip);
			mv.setAddr1(addr1);
			mv.setAddr2(addr2);
			mv.setRegip(regip);
			
			MemberDao.getInstance().insertMember(mv);
			
			// 리다이렉트
			return "redirect:/Farmstory2/user/login.do?success=101";
		}
	}
}