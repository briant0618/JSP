package kr.co.jboard2.service.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import kr.co.jboard2.dao.MemberDao;
import kr.co.jboard2.service.CommonService;

public class CheckNameService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		String name = req.getParameter("name");
		
		int count = MemberDao.getInstance().selectMemberCount(name);
		
		
		// Count를 Json데이터로 형성 -> result = 1 or 0 [회원 있다 = 1 / 회원 같은거 없다 = 0 ]
		JsonObject json = new JsonObject();
		json.addProperty("result", count);
		
		
		return "json:"+json.toString();
	}

}
