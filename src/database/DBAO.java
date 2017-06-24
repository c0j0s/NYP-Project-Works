package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Account;

public class DBAO {
	public Connection con;
	final private String schurl = "jdbc:mysql://localhost:3306/ffl";
	final private String schpasswd = "mysql";
	final private String url = "jdbc:mysql://138.75.188.127:3306/ffl";
	final private String passwd = "mysql";
	
	/**
	 * Default constructor
	 * init connection to database
	 */
	public DBAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(schurl,"root",schpasswd);
		} catch (Exception e) {
			try {
				System.out.println("Log DBAO: fail to connect to database" + e.getMessage());
			} catch (Exception ex) {
				System.out.println("Log DBAO: fail to connect to database" + e.getMessage());
			}
		}
	}

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
}
