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
	
	
	public int selectCountArticle(String cate) { // ����¡ �۾� ����
		
		int total = 0; 
		
		try {
		// 1.2�ܰ�
			Connection conn = DBConfig.getInstance().getConnection();
			
		// 3�ܰ�
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COUNT_ARTICLE);
			psmt.setString(1, cate);
		
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
	
	public int insertArticle(ArticleVO vo) {
		

		try{
			// 1,2 �ܰ�
			Connection conn = DBConfig.getInstance().getConnection();
			// 3 �ܰ�
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_ARTICLE);
			
			psmt.setString(1, vo.getCate());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getContent());
			psmt.setInt(4, vo.getFile());
			psmt.setString(5, vo.getUid());
			psmt.setString(6, vo.getRegip()); // Mapping ��
			
			// 4 �ܰ�
			psmt.executeUpdate();
			
			
			
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return selectMaxSeq();
	}

	public void insertComment(ArticleVO comment) {
		
		try{
			// 1,2 �ܰ�
			Connection conn = DBConfig.getInstance().getConnection();
			// 3 �ܰ�
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_COMMENT);
			psmt.setInt(1, comment.getParent());
			psmt.setString(2, comment.getContent());
			psmt.setString(3, comment.getUid());
			psmt.setString(4, comment.getRegip());
			// 4 �ܰ�
			psmt.executeUpdate();
			// 5 �ܰ�
			// 6 �ܰ�
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void insertFile(int seq, String fname, String newName) {
		try {
			
			// 1, 2�ܰ�
			Connection conn = DBConfig.getInstance().getConnection();
			// 3�ܰ�
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_FILE);
			psmt.setInt(1, seq);
			psmt.setString(2, fname);
			psmt.setString(3, newName);
			
			// 4�ܰ�
			psmt.executeUpdate();
			// 5�ܰ�
			// 6�ܰ�
			conn.close();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int selectMaxSeq() { // ���� ÷�θ� ���� �ۿ��� ��ȣ ��������
		
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
	
	
	public List<ArticleVO> selectLatests() { // index�� �ֽ� board�� ���� �۾�
		
		List<ArticleVO> latests = new ArrayList<ArticleVO>();
		
				
		try {
			
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_LATESTS); 
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) { // 15���� ���� list�� ����������.
				
				ArticleVO article = new ArticleVO();
				article.setSeq(rs.getInt(1));
				article.setCate(rs.getString(4));
				article.setTitle(rs.getString(5));
				article.setRdate(rs.getString(11).substring(2, 10));
				
				latests.add(article); // latests list�� 15���� article�� �ݺ��ȴ�.
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return latests;
	}
	
	
	public ArticleVO selectArticle(String seq) { // view.jsp�� ���Ǿ� ���� �������� ����
		
		ArticleVO article = new ArticleVO();
		FileVO fb = new FileVO();
		
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
	
	public List<ArticleVO> selectArticles(String cate,int start) { // �Խù� �������� ����
		
		List<ArticleVO> articles = new ArrayList<>(); 
		
		try{
			// 1,2�ܰ�
				Connection conn = DBConfig.getInstance().getConnection();
			
			// 3�ܰ�
				PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_ARTICLES);
				psmt.setString(1, cate);
				psmt.setInt(2, start);
			
			// 4�ܰ�
				ResultSet rs = psmt.executeQuery();
			
			// 5�ܰ�
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
			// 6�ܰ� 
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		
		return articles;
	}
	
	
	public List<ArticleVO> selectComments(String parent) { // ��� �������� ����
		
		List<ArticleVO> articles = new ArrayList<>(); 
		
		try{
			// 1,2�ܰ�
				Connection conn = DBConfig.getInstance().getConnection();
			
			// 3�ܰ�
				PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COMMENTS);
				psmt.setString(1, parent);
			
			// 4�ܰ�
				ResultSet rs = psmt.executeQuery();
			
			// 5�ܰ�
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
			// 6�ܰ� 
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		
		return articles;
	}
	
	
	public FileVO selectFile(String seq) { // Download ���� ��ȸ�ϱ�
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
	
	public void updateCommentCount(String seq,int type) { // ��ۼ� +1 �ø���� -1�� ������ ���� ����
		try{
			PreparedStatement psmt = null;
			
			// 1.2�ܰ�
			Connection conn = DBConfig.getInstance().getConnection();
			
			// 3�ܰ�
			if(type == 1) {
				psmt = conn.prepareStatement(Sql.UPDATE_COMMENT_PLUS);
			}else{
				psmt = conn.prepareStatement(Sql.UPDATE_COMMENT_MINUS);
			}
			
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
	
	
	public void deleteArticle(String seq, String parent) {
		
		try{
			// 1,2�ܰ�
			Connection conn = DBConfig.getInstance().getConnection();
			// 3�ܰ�
			PreparedStatement psmt = conn.prepareStatement(Sql.DELETE_ARTICLE);
			psmt.setString(1, seq);
			psmt.setString(2, parent);
			// 4�ܰ�
			psmt.executeUpdate();
			// 5�ܰ�			
			// 6�ܰ�
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
