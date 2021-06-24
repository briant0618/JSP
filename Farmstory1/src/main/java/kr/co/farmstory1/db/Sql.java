package kr.co.farmstory1.db;

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
	
	public static final String SELECT_COUNT_ARTICLE = "SELECT COUNT(*) FROM `JBoard_Article` WHERE `parent`=0 AND `cate` =?"; // all�ϸ� ��۱��� ���Ǽ� ��ۻ��� ��� �߰��� + cate���� �߰�
	
	
	public static final String SELECT_ARTICLE = "SELECT * FROM `JBoard_Article` AS a "
													+ "LEFT JOIN `JBoard_File` AS b "
													+ "ON a.seq = b.parent "
													+ "WHERE a.`seq` =?"; // Select_Articles�� �ٸ����� �򰥸��� �ʰ� �����ϱ�.[view] // Left join���� ���� ���� �۵� join�����ϰ� ������.
	
	
	public static final String SELECT_ARTICLES = "SELECT a.*, b.`nick` FROM `JBoard_Article` AS a "  // Join
													+ "JOIN `JBoard_Member` AS b "
													+ "On a.uid = b.uid "
													+ "WHERE `parent` = 0 AND `cate`=? " // ��۵� ���Ǽ� ��� ������ + category ���е� �ؾߴ�!
													+ "ORDER BY `seq` DESC "
													+ "LIMIT ?,10";
	
	public static final String SELECT_COMMENTS = "SELECT * FROM `JBoard_Article` AS a " // ��ۿ� Nick���� �����ֱ� ���ؼ� join
													+ "JOIN `JBoard_Member` AS b "
													+ "on a.uid = b.uid " 
													+ "WHERE `parent` = ? "
													+ "ORDER BY a.`seq` ASC";
											
	public static final String SELECT_FILE = "SELECT * FROM `JBoard_File` WHERE `seq` =?";
	
	public static final String INSERT_ARTICLE = "INSERT INTO `JBoard_Article` SET "
												   + " `cate` = ?,"
												   + " `title` =?,"
												   + " `content` =?,"
												   + " `file` =?,"
												   + " `uid` =?,"
												   + " `regip` =?,"
												   + " `rdate`= NOW();";
	
	public static final String INSERT_COMMENT = "INSERT INTO `JBoard_Article` SET "
												   + " `parent` =?,"
												   + " `content` =?,"
												   + " `uid` =?,"
												   + " `regip` =?,"
												   + " `rdate` = NOW()";
	
	public static final String SELECT_MAX_SEQ = "SELECT MAX(`seq`) FROM `JBoard_Article`";
	
	public static final String INSERT_FILE = "INSERT INTO `JBoard_File` SET "
													+ " `parent` = ?,"
													+ " `oriName` = ?,"
													+ " `newName` = ?,"
													+ " `rdate` = NOW()";
	
	public static final String UPDATE_ARTICLE = "UPDATE `JBoard_Article` SET "
													+ " `title`=?,"
													+ " `content`=?"
													+ "WHERE `seq`=?";
	public static final String UPDATE_COMMENT = "UPDATE `JBoard_Article` SET "
													+ "`content`=? "
													+ "WHERE `seq`=?";
	
	public static final String UPDATE_ARTICLE_HIT = "UPDATE `JBoard_Article` SET `hit`=`hit`+1 WHERE `seq`=?";
	
	public static final String UPDATE_COMMENT_PLUS = "UPDATE `JBoard_Article` SET `comment`=`comment`+1 WHERE `seq`=?";
	
	public static final String UPDATE_COMMENT_MINUS = "UPDATE `JBoard_Article` SET `comment`=`comment`-1 WHERE `seq`=?";
	
	public static final String UPDATE_FILE_DOWNLOAD = "UPDATE `JBoard_File` SET `download`=`download`+1 WHERE `seq`=?";
	
	public static final String DELETE_ARTICLE = "DELETE FROM `JBoard_Article` WHERE `seq`=?";
																
	
	public static final String DELETE_COMMENT = "DELETE FROM `JBoard_Article` WHERE `seq`=?";
}