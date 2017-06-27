package database;

import java.sql.Connection;
import java.sql.DriverManager;
<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
=======
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
>>>>>>> 655e75eaae0af6cae4b8651ef13ba5bafb6b8806

import bean.Account;

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
<<<<<<< HEAD

	public Account isMember(String userId, String userPw) {
AccountDB ac = new AccountDB();
		
		String statement = "SELECT * FROM ffl.user where accountId = ?";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(statement);
			ResultSet rs = ps.executeQuery();
			
			ac.setAccountId(rs.getString("accountId"));
			ac.setgivenName(rs.getString("givenName"));
			ac.setsurName(rs.getString("surName"));
			ac.setdob(rs.getDate("DOB"));
			ac.setgender(rs.getString("gender").charAt(0));
			ac.setemail(rs.getString("email"));
			ac.setaddress(rs.getString("address"));
			ac.setcountry(rs.getString("country"));
			ac.getmobileNo(rs.getInt("mobileNo"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return ac;
=======
	
>>>>>>> 655e75eaae0af6cae4b8651ef13ba5bafb6b8806
}
