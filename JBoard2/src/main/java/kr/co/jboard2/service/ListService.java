package kr.co.jboard2.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jboard2.dao.ArticleDao;
import kr.co.jboard2.vo.ArticleVO;

public class ListService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		String pg = req.getParameter("pg");
		
		int total = ArticleDao.getInstance().selectCountArticle();
		int lastPageNum = getLastPageNum(total);
		int currentPage = getCurrentPage(pg);
		int start = getLimitStart(currentPage);
		int pageStartNum = getPageStartNum(total, start);
		int groups[] = getPageGroup(currentPage, lastPageNum);
		
		List<ArticleVO> articles = ArticleDao.getInstance().selectArticles(start);
		
		req.setAttribute("articles", articles);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageStartNum", pageStartNum);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("groups", groups);
		
		
		return "/list.jsp";
	}// requestProc end...
	
	// Paging 작업 이제 Service 안에서 진행함.
	
	public int[] getPageGroup(int currentPage, int lastPageNum) { // 페이징 10개씩 그룹 나누기
		
		int groupCurrent = (int) Math.ceil(currentPage / 10.0);
		int groupStart = (groupCurrent - 1) * 10 + 1;
		int groupEnd = groupCurrent * 10;
		
		if(groupEnd > lastPageNum) {
			groupEnd = lastPageNum; // 글들이 lastPage보다 크지않게 하기위해서
		}
		
		int[] groups = {groupStart, groupEnd}; 
		
		return groups;	// 2개 이상 return이 불가능 -> array써서 1개로 만들어줌	
	}
	
	public int getPageStartNum(int total, int start) { // 각페이지에 삭제된 글도 있을테니 보기 편하게 글숫자 순서대로 번호 만들기
		return total - start;
	}
	
	public int getLimitStart(int currentPage) {  // 그룹 나누기에서 현재 페이지 작업
		return (currentPage - 1) * 10;
	}
	
	public int getCurrentPage(String pg) { // 페이징 현재 페이지 표기 작업
		
		int currentPage = 1;
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		
		return currentPage;
	}
	
	public int getLastPageNum(int total) { // 페이징 작업 마지막 페이지 계산 구문
		
		int lastPageNum = 0;
		
		if(total % 10 == 0){
			lastPageNum = total / 10;
		}else{
			lastPageNum = total / 10 + 1;
		}
		
		return lastPageNum;
	}
}