package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDB extends DBAO{
	
	/**
	 * this is where ur database connection method is, not javabean
	 */
	
	public AccountDB(){
		super();
	}
	public AccountDB getAccount(){
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
		return ac;
		
	}
	void getmobileNo(int int1) {
		// TODO Auto-generated method stub
		
	}
	void setcountry(String string) {
		// TODO Auto-generated method stub
		
	}
	void setaddress(String string) {
		// TODO Auto-generated method stub
		
	}
	void setemail(String string) {
		// TODO Auto-generated method stub
		
	}
	void setgender(char charAt) {
		// TODO Auto-generated method stub
		
	}
	void setdob(Date date) {
		// TODO Auto-generated method stub
		
	}
	void setsurName(String string) {
		// TODO Auto-generated method stub
		
	}
	void setgivenName(String string) {
		// TODO Auto-generated method stub
		
	}
	void setAccountId(String string) {
		// TODO Auto-generated method stub
		
	}
	public AccountDB isMember() {
		// TODO Auto-generated method stub
		return null;
	}
}
