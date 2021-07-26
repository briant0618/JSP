package kr.co.jboard2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


import kr.co.jboard2.db.DBConfig;
import kr.co.jboard2.db.Sql;
import kr.co.jboard2.vo.MemberVO;
import kr.co.jboard2.vo.TermsVO;


public class MemberDao {
	private static MemberDao instance = new MemberDao();
	private MemberDao() {
		
	}
	public static MemberDao getInstance() {
		return instance;
		
	}
	
	public void insertMember(MemberVO mv) { // 회원가입!
		
		try{
			
			Connection conn = DBConfig.getInstance().getConnection();
			PreparedStatement psmt = conn.prepareStatement(Sql.INSERT_MEMBER);
			
			psmt.setString(1, mv.getUid());
			psmt.setString(2, mv.getPass());
			psmt.setString(3, mv.getName());
			psmt.setString(4, mv.getNick());
			psmt.setString(5, mv.getEmail());
			psmt.setString(6, mv.getHp());
			psmt.setString(7, mv.getZip());
			psmt.setString(8, mv.getAddr1());
			psmt.setString(9, mv.getAddr2());
			psmt.setString(10, mv.getRegip());
			// 4단계
	        psmt.executeUpdate();
			// 5단계
			// 6단계
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public MemberVO selectMember(String uid, String pass) {
		
		MemberVO mv = null;
		
		try{
			//1,2단계
			Connection conn = DBConfig.getInstance().getConnection();
			
			// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_MEMBER);
			psmt.setString(1, uid);
			psmt.setString(2, pass);
			
			// 4단계
			ResultSet rs = psmt.executeQuery();
			
			// 5단계
			if(rs.next()){
				// 회원일 경우
				mv = new MemberVO();
				
				mv.setUid(rs.getString(1));
				mv.setPass(rs.getString(2));
				mv.setName(rs.getString(3));
				mv.setNick(rs.getString(4));
				mv.setEmail(rs.getString(5));
				mv.setHp(rs.getString(6));
				mv.setGrade(rs.getInt(7));
				mv.setZip(rs.getString(8));
				mv.setAddr1(rs.getString(9));
				mv.setAddr2(rs.getString(10));
				mv.setRegip(rs.getString(11));
				mv.setRdate(rs.getString(12));
			}
			
			// 6단계
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return mv;
	}
	
	
	public TermsVO selectTerms() { // 약관 내용 Select-Query
		
		TermsVO tv = new TermsVO();	
		
		try{
			// 1단계
			Class.forName("com.mysql.jdbc.Driver");
			// 2단계
			Connection conn = DBConfig.getInstance().getConnection();
			// 3단계
			Statement stmt = conn.createStatement();
			// 4단계
			ResultSet rs = stmt.executeQuery(Sql.SELECT_TERMS);
			
			// 5단계
			if(rs.next()){
				tv.setTerms(rs.getString(1));
				tv.setPrivacy(rs.getString(2));
			}
			// 6단계
			conn.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return tv;
	}
	
	public int selectMemberCount(String uid) {
		
		int count = -1;

		
		try{
			// 1단계
			Connection conn = DBConfig.getInstance().getConnection();		
			
			// 3단계
			PreparedStatement psmt = conn.prepareStatement(Sql.SELECT_COUNT_UID);
			psmt.setString(1, uid);



			// 4단계
			ResultSet rs = psmt.executeQuery();
			// 5단계
			if(rs.next()){
				count = rs.getInt(1);
			}
			
			// 6단계
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
		
	}
	
	public void selectMembers() {}
	
	public void updateMember() {}
	public void deleteMember() {}
	
	
}
