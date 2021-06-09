package kr.co.jboard1.db;

public class Sql {
	
	public static final String SELECT_COUNT_EMAIL = "SELECT COUNT(`email`) FROM `JBoard_Member` WHERE `email`=?";
	public static final String SELECT_COUNT_HP    = "SELECT COUNT(`hp`)    FROM `JBoard_Member` WHERE `hp`=?";
	public static final String SELECT_COUNT_NICK  = "SELECT COUNT(`nick`)  FROM `JBoard_Member` WHERE `nick`=?";              
	public static final String SELECT_COUNT_UID   = "SELECT COUNT(`uid`)   FROM `JBoard_Member` WHERE `uid`=?";
	
	public static final String SELECT_MEMBER   = "SELECT * FROM `JBoard_Member` WHERE `uid`=? AND `pass`=PASSWORD(?)";
	public static final String INSERT_MEMBER = "INSERT INTO `JBoard_Member` SET "
												+ "`uid`=?,"
												+ "`pass`=PASSWORD(?),"
												+ "`name`=?,"
												+ "`nick`=?,"
												+ "`email`=?,"
												+ "`hp`=?,"
												+ "`zip`=?,"
												+ "`addr1`=?,"
												+ "`addr2`=?,"
												+ "`regip`=?,"
												+ "`rdate`=NOW()";
	
	public static final String SELECT_TERMS = "SELECT * FROM `JBoard_Terms`";
	
	
	
	
		
}