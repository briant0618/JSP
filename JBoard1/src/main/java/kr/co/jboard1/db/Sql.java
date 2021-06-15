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
	
	public static final String SELECT_COUNT_ARTICLE = "SELECT COUNT(*) FROM `JBoard_Article`";
	
	
	public static final String SELECT_ARTICLE = "SELECT * FROM `JBoard_Article` AS a "
													+ "LEFT JOIN `JBoard_File` AS b "
													+ "ON a.seq = b.parent "
													+ "WHERE a.`seq` =?"; // Select_Articles와 다른것임 헷갈리지 않게 주의하기.[view] // Left join으로 파일 없는 글도 join가능하게 설정함.
	
	
	public static final String SELECT_ARTICLES = "SELECT a.*, b.`nick` FROM `JBoard_Article` AS a "  // Join
													+ "JOIN `JBoard_Member` AS b "
													+ "On a.uid = b.uid "
													+ "ORDER BY `seq` DESC "
													+ "LIMIT ?,10";
	public static final String SELECT_FILE = "SELECT * FROM `JBoard_File` WHERE `seq` =?";
	
	public static final String INSERT_ARTICLE = "INSERT INTO `JBoard_Article` SET "
												   + " `title` =?,"
												   + " `content` =?,"
												   + " `file` =?,"
												   + " `uid` =?,"
												   + " `regip` =?,"
												   + " `rdate`= NOW();";
	
	public static final String SELECT_MAX_SEQ = "SELECT MAX(`seq`) FROM `JBoard_Article`";
	
	public static final String INSERT_FILE = "INSERT INTO `JBoard_File` SET "
												+ " `parent` = ?,"
												+ " `oriName` = ?,"
												+ " `newName` = ?,"
												+ " `rdate` = NOW()" ;
	
	public static final String UPDATE_ARTICLE_HIT = "UPDATE `JBoard_Article` SET `hit`=`hit`+1 WHERE `seq`=?";
	
	public static final String UPDATE_FILE_DOWNLOAD = "UPDATE `JBoard_File` SET `download`=`download`+1 WHERE `seq`=?";
}