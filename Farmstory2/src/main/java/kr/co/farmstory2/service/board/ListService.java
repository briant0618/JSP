package kr.co.farmstory2.service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.farmstory2.VO.ArticleVO;
import kr.co.farmstory2.dao.ArticleDao;
import kr.co.farmstory2.service.CommonService;

public class ListService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		// _header���� �޾ƿ� group ���� ���� + cate ���� ����
		String group = req.getParameter("group");
		String cate  = req.getParameter("cate");
		String pg    = req.getParameter("pg");

		
		
		
		
		int total = ArticleDao.getInstance().selectCountArticle(cate);
		int lastPageNum = getLastPageNum(total);
		int currentPage = getCurrentPage(pg);
		int start = getLimitStart(currentPage);
		int pageStartNum = getPageStartNum(total, start);
		int groups[] = getPageGroup(currentPage, lastPageNum);
		
		List<ArticleVO> articles = ArticleDao.getInstance().selectArticles(cate, start);
		
		req.setAttribute("articles", articles);
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageStartNum", pageStartNum);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("groups", groups);
		
		// category �߰� �������ؼ� �޾ƿ°� �Ѹ���
		req.setAttribute("cate", cate);
		
		
		return "/board/list.jsp";
	}

	// Paging �۾� ���� Service �ȿ��� ������.
	
		public int[] getPageGroup(int currentPage, int lastPageNum) { // ����¡ 10���� �׷� ������
			
			int groupCurrent = (int) Math.ceil(currentPage / 10.0);
			int groupStart = (groupCurrent - 1) * 10 + 1;
			int groupEnd = groupCurrent * 10;
			
			if(groupEnd > lastPageNum) {
				groupEnd = lastPageNum; // �۵��� lastPage���� ũ���ʰ� �ϱ����ؼ�
			}
			
			int[] groups = {groupStart, groupEnd}; 
			
			return groups;	// 2�� �̻� return�� �Ұ��� -> array�Ἥ 1���� �������	
		}
		
		public int getPageStartNum(int total, int start) { // ���������� ������ �۵� �����״� ���� ���ϰ� �ۼ��� ������� ��ȣ �����
			return total - start;
		}
		
		public int getLimitStart(int currentPage) {  // �׷� �����⿡�� ���� ������ �۾�
			return (currentPage - 1) * 10;
		}
		
		public int getCurrentPage(String pg) { // ����¡ ���� ������ ǥ�� �۾�
			
			int currentPage = 1;
			
			if(pg != null) {
				currentPage = Integer.parseInt(pg);
			}
			
			return currentPage;
		}
		
		public int getLastPageNum(int total) { // ����¡ �۾� ������ ������ ��� ����
			
			int lastPageNum = 0;
			
			if(total % 10 == 0){
				lastPageNum = total / 10;
			}else{
				lastPageNum = total / 10 + 1;
			}
			
			return lastPageNum;
		}
	}
	
	
