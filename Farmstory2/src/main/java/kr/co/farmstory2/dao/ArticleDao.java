package kr.co.farmstory2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import kr.co.farmstory2.VO.ArticleVO;
import kr.co.farmstory2.VO.FileVO;
import kr.co.farmstory2.db.DBConfig;
import kr.co.farmstory2.db.Sql;

/* dao [Try / Catch의 남발을 막기위해서 미리 class로 모듈화 시킴.]
   dao = Database Access Object
*/
public class ArticleDao {
	private static ArticleDao instance = new ArticleDao();
	private ArticleDao() {}
	
	public static ArticleDao getInstance() {
		return instance;
	} // singleTone 문법 기억해두자! get이 private를 받을수 없으니 static으로 만듦

	
	
	
	public int getPageStartNum(int total, int start) { // 각페이지에 삭제된 글도 있을테니 보기 편하게 글숫자 순서대로 번호 만들기
		return total - start;
	}
	
	public int getLimitStart(int currentPage) { // 그룹 나누기에서 현재 페이지 작업
		return (currentPage - 1 )* 10;
	}
	
	
	public int getCurrentPg(String pg) { // 페이징 현재 페이지 표기 작업
		int currentPage = 1;
		if(pg != null) {
			
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	
	public int[] getPageGroup(int currentPage, int lastPageNum) { // 페이징 10개씩 그룹 나누기
		int groupCurrent = (int)Math.ceil(currentPage / 10.0);
		int groupStart = (groupCurrent - 1) * 10 + 1;
		int groupEnd = groupCurrent * 10;
		
		if(groupEnd > lastPageNum) {
			groupEnd = lastPageNum; // 글들이 lastPage보다 크지않게 하기위해서
		}
		
		
		int [] groups = {groupStart,groupEnd};
		
		return groups; // 2개 이상 return이 불가능 -> array써서 1개로 만들어줌
		
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
	
	
	public int selectCountArticle(String cate) { // 페이징 작업 구문
		
		int total = 0; 
		
		try {
		// 1.2단계
			Connection conn = DBConfig.getInstance().getConnection();
			
		// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COUNT_ARTICLE);
			psmt.setString(1, cate);
		
		// 4단계
			ResultSet rs = psmt.executeQuery();
			
		// 5단계
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return total;
	}
	
	public int insertArticle(ArticleVO vo) {
		

		try{
			// 1,2 단계
			Connection conn = DBConfig.getInstance().getConnection();
			// 3 단계
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			
			psmt.setString(1, vo.getCate());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getContent());
			psmt.setInt(4, vo.getFile());
			psmt.setString(5, vo.getUid());
			psmt.setString(6, vo.getRegip()); // Mapping 끝
			
			// 4 단계
			psmt.executeUpdate();
			
			
			
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return selectMaxSeq();
	}

	public void insertComment(ArticleVO comment) {
		
		try{
			// 1,2 단계
			Connection conn = DBConfig.getInstance().getConnection();
			// 3 단계
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_COMMENT);
			psmt.setInt(1, comment.getParent());
			psmt.setString(2, comment.getContent());
			psmt.setString(3, comment.getUid());
			psmt.setString(4, comment.getRegip());
			// 4 단계
			psmt.executeUpdate();
			// 5 단계
			// 6 단계
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void insertFile(int seq, String fname, String newName) {
		try {
			
			// 1, 2단계
			Connection conn = DBConfig.getInstance().getConnection();
			// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, seq);
			psmt.setString(2, fname);
			psmt.setString(3, newName);
			
			// 4단계
			psmt.executeUpdate();
			// 5단계
			// 6단계
			conn.close();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int selectMaxSeq() { // 파일 첨부를 위한 글에서 번호 가져오기
		
		int seq = 0;
		
		try {
			
			Connection conn = DBConfig.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(Sql.SELECT_MAX_SEQ);
			
			if(rs.next()) {
				seq = rs.getInt(1);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return seq;
	}
	
	
	public List<ArticleVO> selectLatests() { // index에 최신 board글 띄우는 작업
		
		List<ArticleVO> latests = new ArrayList<ArticleVO>();
		
				
		try {
			
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_LATESTS); 
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) { // 15개의 글을 list로 만들어버린다.
				
				ArticleVO article = new ArticleVO();
				article.setSeq(rs.getInt(1));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setRdate(rs.getString(11).substring(2, 10));
				
				latests.add(article); // latests list에 15개의 article이 반복된다.
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return latests;
	}
	
	
	public ArticleVO selectArticle(String seq) { // view.jsp에 사용되어 글을 보기위한 구문
		
		ArticleVO article = new ArticleVO();
		FileVO fb = new FileVO();
		
		try{
			// 1.2 단계
			Connection conn = DBConfig.getInstance().getConnection();
			
			// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, seq);
			
			// 4단계
			ResultSet rs = psmt.executeQuery();
			
			// 5단계
			if(rs.next()) {
				article.setSeq(rs.getInt(1));
				article.setParent(rs.getInt(2));
				article.setComment(rs.getInt(3));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setContent(rs.getString(6));
				article.setFile(rs.getInt(7));
				article.setHit(rs.getInt(8));
				article.setUid(rs.getString(9));
				article.setRegip(rs.getString(10));
				article.setRdate(rs.getString(11));
				
				// 추가 필드 [ File없는 놈도 view되게 확장 -> 객체안에 객체가 되듯 Article안으로 fb를 넣음 ] 
				fb.setSeq(rs.getInt(12));
				fb.setParent(rs.getInt(13));
				fb.setOriName(rs.getString(14));
				fb.setNewName(rs.getString(15));
				fb.setDownload(rs.getInt(16));
				fb.setRdate(rs.getString(17));
				
				article.setFb(fb);
				
				
			}
			
			
			// 6단계
			}catch(Exception e){
				e.printStackTrace();
			}
		return article;
		
	}
	
	public List<ArticleVO> selectArticles(String cate,int start) { // 게시물 가져오기 구문
		
		List<ArticleVO> articles = new ArrayList<>(); 
		
		try{
			// 1,2단계
				Connection conn = DBConfig.getInstance().getConnection();
			
			// 3단계
				PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
				psmt.setString(1, cate);
				psmt.setInt(2, start);
			
			// 4단계
				ResultSet rs = psmt.executeQuery();
			
			// 5단계
				while(rs.next()){
					ArticleVO article = new ArticleVO();
					article.setSeq(rs.getInt(1)); 
					article.setParent(rs.getInt(2)); 
					article.setComment(rs.getInt(3)); 
					article.setCate(rs.getString(4)); 
					article.setTitle(rs.getString(5)); 
					article.setContent(rs.getString(6)); 
					article.setFile(rs.getInt(7)); 
					article.setHit(rs.getInt(8)); 
					article.setUid(rs.getString(9));	
					article.setRegip(rs.getString(10));	
					article.setRdate(rs.getString(11));	
					article.setNick(rs.getString(12));	
					
					
					articles.add(article);
				}
			// 6단계 
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		
		return articles;
	}
	
	
	public List<ArticleVO> selectComments(String parent) { // 댓글 가져오기 구문
		
		List<ArticleVO> articles = new ArrayList<>(); 
		
		try{
			// 1,2단계
				Connection conn = DBConfig.getInstance().getConnection();
			
			// 3단계
				PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COMMENTS);
				psmt.setString(1, parent);
			
			// 4단계
				ResultSet rs = psmt.executeQuery();
			
			// 5단계
				while(rs.next()){
					ArticleVO article = new ArticleVO();
					article.setSeq(rs.getInt(1)); 
					article.setParent(rs.getInt(2)); 
					article.setComment(rs.getInt(3)); 
					article.setCate(rs.getString(4)); 
					article.setTitle(rs.getString(5)); 
					article.setContent(rs.getString(6)); 
					article.setFile(rs.getInt(7)); 
					article.setHit(rs.getInt(8)); 
					article.setUid(rs.getString(9));	
					article.setRegip(rs.getString(10));	
					article.setRdate(rs.getString(11));	
					article.setNick(rs.getString(12));	
					
					
					articles.add(article);
				}
			// 6단계 
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		
		return articles;
	}
	
	
	public FileVO selectFile(String seq) { // Download 파일 조회하기
		FileVO fb = new FileVO();
		
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_FILE);
			
			psmt.setString(1, seq);
			
			ResultSet rs = psmt.executeQuery();
			
			if(rs.next()) {
				fb.setSeq(rs.getInt(1));
				fb.setParent(rs.getInt(2));
				fb.setOriName(rs.getString(3));
				fb.setNewName(rs.getString(4));
				fb.setDownload(rs.getInt(5));
				fb.setRdate(rs.getString(6));
				
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fb;
	}
	
	
	
	
	
	public void updateArticle(String title,String content,String seq) {
		
		try{
			Connection conn = DBConfig.getInstance().getConnection();
			
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, seq);
			
			psmt.executeUpdate();
			
			conn.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int updateComment(String content, String seq) {
		
		int result = 0;
		
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_COMMENT);
			
			psmt.setString(1, content);
			psmt.setString(2, seq);
			
			result = psmt.executeUpdate();
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void updateArticleHit(String seq) { // hit수 1씩 올리기 구문
		try{
			// 1.2단계
			Connection conn = DBConfig.getInstance().getConnection();
			
			// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_HIT);
			psmt.setString(1, seq);
			
			// 4단계
			psmt.executeUpdate();
					
			// 6단계
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateCommentCount(String seq,int type) { // 댓글수 +1 올리기와 -1로 내리기 위한 구문
		try{
			PreparedStatement psmt = null;
			
			// 1.2단계
			Connection conn = DBConfig.getInstance().getConnection();
			
			// 3단계
			if(type == 1) {
				psmt = conn.prepareStatement(Sql.UPDATE_COMMENT_PLUS);
			}else{
				psmt = conn.prepareStatement(Sql.UPDATE_COMMENT_MINUS);
			}
			
			psmt.setString(1, seq);
			
			// 4단계
			psmt.executeUpdate();
					
			// 6단계
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateFileDownload(String seq) { // 다운로드 횟수 1씩 올리기 구문
		try{
			// 1,2단계
			Connection conn = DBConfig.getInstance().getConnection();
			// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_FILE_DOWNLOAD);
			psmt.setString(1, seq);
			// 4단계
			psmt.executeUpdate();
			// 5단계			
			// 6단계
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void deleteArticle(String seq, String parent) {
		
		try{
			// 1,2단계
			Connection conn = DBConfig.getInstance().getConnection();
			// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_ARTICLE);
			psmt.setString(1, seq);
			psmt.setString(2, parent);
			// 4단계
			psmt.executeUpdate();
			// 5단계			
			// 6단계
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void deleteComment(String seq) {
		
		try {
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_COMMENT);
			
			psmt.setString(1, seq);
			
			
			psmt.executeUpdate();
			
			
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
