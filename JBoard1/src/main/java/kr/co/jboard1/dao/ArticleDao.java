package kr.co.jboard1.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;


import kr.co.jboard1.bean.ArticleBean;
import kr.co.jboard1.bean.FileBean;
import kr.co.jboard1.db.DBConfig;
import kr.co.jboard1.db.Sql;

/* dao [Try / Catch�� ������ �������ؼ� �̸� class�� ���ȭ ��Ŵ.]
   dao = Database Access Object
*/
public class ArticleDao {
	private static ArticleDao instance = new ArticleDao();
	private ArticleDao() {}
	
	public static ArticleDao getInstance() {
		return instance;
	} // singleTone ���� ����ص���! get�� private�� ������ ������ static���� ����

	
	
	
	public int getPageStartNum(int total, int start) { // ���������� ������ �۵� �����״� ���� ���ϰ� �ۼ��� ������� ��ȣ �����
		return total - start;
	}
	
	public int getLimitStart(int currentPage) { // �׷� �����⿡�� ���� ������ �۾�
		return (currentPage - 1 )* 10;
	}
	
	
	public int getCurrentPg(String pg) { // ����¡ ���� ������ ǥ�� �۾�
		int currentPage = 1;
		if(pg != null) {
			
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	
	public int[] getPageGroup(int currentPage, int lastPageNum) { // ����¡ 10���� �׷� ������
		int groupCurrent = (int)Math.ceil(currentPage / 10.0);
		int groupStart = (groupCurrent - 1) * 10 + 1;
		int groupEnd = groupCurrent * 10;
		
		if(groupEnd > lastPageNum) {
			groupEnd = lastPageNum; // �۵��� lastPage���� ũ���ʰ� �ϱ����ؼ�
		}
		
		
		int [] groups = {groupStart,groupEnd};
		
		return groups; // 2�� �̻� return�� �Ұ��� -> array�Ἥ 1���� �������
		
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
	
	
	public int selectCountArticle() { // ����¡ �۾� ����
		
		int total = 0; 
		
		try {
		// 1.2�ܰ�
			Connection conn = DBConfig.getInstance().getConnection();
			
		// 3�ܰ�
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COUNT_ARTICLE);
		
		// 4�ܰ�
			ResultSet rs = psmt.executeQuery();
			
		// 5�ܰ�
			if(rs.next()) {
				total = rs.getInt(1);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return total;
	}
	
	public void insertArticle() {}
	
	public ArticleBean selectArticle(String seq) { // view.jsp�� ���Ǿ� ���� �������� ����
		
		ArticleBean article = new ArticleBean();
		FileBean fb = new FileBean();
		
		try{
			// 1.2 �ܰ�
			Connection conn = DBConfig.getInstance().getConnection();
			
			// 3�ܰ�
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLE);
			psmt.setString(1, seq);
			
			// 4�ܰ�
			ResultSet rs = psmt.executeQuery();
			
			// 5�ܰ�
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
				
				// �߰� �ʵ� [ File���� �� view�ǰ� Ȯ�� -> ��ü�ȿ� ��ü�� �ǵ� Article������ fb�� ���� ] 
				fb.setSeq(rs.getInt(12));
				fb.setParent(rs.getInt(13));
				fb.setOriName(rs.getString(14));
				fb.setNewName(rs.getString(15));
				fb.setDownload(rs.getInt(16));
				fb.setRdate(rs.getString(17));
				
				article.setFb(fb);
				
				
			}
			
			
			// 6�ܰ�
			}catch(Exception e){
				e.printStackTrace();
			}
		return article;
		
	}
	
	public List<ArticleBean> selectArticles(int start) { // �Խù� �������� ����
		
		List<ArticleBean> articles = new ArrayList<>(); 
		
		try{
			// 1,2�ܰ�
				Connection conn = DBConfig.getInstance().getConnection();
			
			// 3�ܰ�
				PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
				psmt.setInt(1, start);
			
			// 4�ܰ�
				ResultSet rs = psmt.executeQuery();
			
			// 5�ܰ�
				while(rs.next()){
					ArticleBean article = new ArticleBean();
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
			// 6�ܰ� 
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		
		return articles;
	}
	
	public FileBean selectFile(String seq) { // Download ���� ��ȸ�ϱ�
		FileBean fb = new FileBean();
		
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
	
	
	
	
	
	public void updateArticle() {}
	
	public void updateArticleHit(String seq) { // hit�� 1�� �ø��� ����
		try{
			// 1.2�ܰ�
			Connection conn = DBConfig.getInstance().getConnection();
			
			// 3�ܰ�
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_ARTICLE_HIT);
			psmt.setString(1, seq);
			
			// 4�ܰ�
			psmt.executeUpdate();
					
			// 6�ܰ�
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void updateFileDownload(String seq) { // �ٿ�ε� Ƚ�� 1�� �ø��� ����
		try{
			// 1,2�ܰ�
			Connection conn = DBConfig.getInstance().getConnection();
			// 3�ܰ�
			PreparedStatement psmt = conn.prepareStatement(Sql.UPDATE_FILE_DOWNLOAD);
			psmt.setString(1, seq);
			// 4�ܰ�
			psmt.executeUpdate();
			// 5�ܰ�			
			// 6�ܰ�
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void deleteArticle() {}
	
	
}
