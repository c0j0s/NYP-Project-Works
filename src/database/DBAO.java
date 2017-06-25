package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DBAO {
	protected static Connection con;
	//final protected String schema = "famforlife";
	final protected static String schema = "ffl";
	
	final private String schurl = "jdbc:mysql://db4free.net:3307/famforlife";
	final private String schpasswd = "ffl@db";
	final private String url = "jdbc:mysql://138.75.188.127:3306/ffl";
	final private String passwd = "password";
	
	/**
	 * Default constructor
	 * init connection to database
	 */
	public DBAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//con = DriverManager.getConnection(schurl,"fflmysqldatabase",schpasswd);
			con = DriverManager.getConnection(url,"root",passwd);
			con.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("Log DBAO: fail to connect to database" + e.getMessage());
		} 
	}
	
	public static String getDateTime(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		java.util.Date myDate = new java.util.Date();
		Timestamp sqlDate = new java.sql.Timestamp(myDate.getTime());
		return formatter.format(sqlDate).substring(0, 19);
	}
	
}
