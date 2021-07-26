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
		
		
		// Count�� Json�����ͷ� ���� -> result = 1 or 0 [ȸ�� �ִ� = 1 / ȸ�� ������ ���� = 0 ]
		JsonObject json = new JsonObject();
		json.addProperty("result", count);
		
		
		return "json:"+json.toString();
	}

}