package service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import service.CommonService;
import vo.MemberVO;

public class RegisterService implements CommonService{
	
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getMethod().equals("GET")) {
			// get전송
			return "/member/register.jsp";
		}else {
			// post전송
			
			// 전송 Parameter 수신
			
			
			
			String uid = req.getParameter("uid");
			String name = req.getParameter("name");
			String hp = req.getParameter("hp");
			String pos = req.getParameter("pos");
			String dep = req.getParameter("dep");
			
			MemberVO vo = new MemberVO();
			vo.setUid(uid);
			vo.setName(name);
			vo.setHp(hp);
			vo.setPos(pos);
			vo.setDep(dep);
			
			MemberDao.getInstance().insertMember(vo);
			// DB 엑세스
			
			

			return "redirect:/Ch08/member/list.do"; 
			
		}
		
	}
	
		
	
}
