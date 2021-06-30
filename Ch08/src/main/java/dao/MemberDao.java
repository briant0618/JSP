package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Collection;

import vo.MemberVO;

public class MemberDao {
	
	private static MemberDao instance = new MemberDao();
	
	public static MemberDao getInstance() {
		return instance;
	}
	
	private final String HOST = "jdbc:mysql://13.209.73.49:3306/siopmy";
	private final String USER = "briant";
	private final String PASS = "k2917h06";
	
	
	private MemberDao() {}
	
	public void insertMember(MemberVO vo) { // Bean 만들 필요성 생김 [ Spring에서는 Bean을 VO[value object]라고 부름니다.
		try {
			// 1단계
			Class.forName("com.mysql.jdbc.Driver");
			
			// 2단계
			Connection conn = DriverManager.getConnection(HOST, USER, PASS);
			
			// 3단계
			String sql = "INSERT INTO `Member` VALUES (?,?,?,?,?,NOW())";
			
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUid());
			psmt.setString(2, vo.getName());
			psmt.setString(3, vo.getHp());
			psmt.setString(4, vo.getPos());
			psmt.setInt(5, vo.getDep());
			
			// 4단계
			psmt.executeUpdate();
			
			// 5단계
			// 6단계
			conn.close();
					
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void selectMember() {}
	public void selectMembers() {}
	public void updateMember() {}
	public void deleteMember() {}
	
}
