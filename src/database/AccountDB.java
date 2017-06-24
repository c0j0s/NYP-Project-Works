package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account extends DBAO{
	public Account(){
		super();
	}
	public Account getAccount(){
		Account ac = new Account();
		
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
	private void getmobileNo(int int1) {
		// TODO Auto-generated method stub
		
	}
	private void setcountry(String string) {
		// TODO Auto-generated method stub
		
	}
	private void setaddress(String string) {
		// TODO Auto-generated method stub
		
	}
	private void setemail(String string) {
		// TODO Auto-generated method stub
		
	}
	private void setgender(char charAt) {
		// TODO Auto-generated method stub
		
	}
	private void setdob(Date date) {
		// TODO Auto-generated method stub
		
	}
	private void setsurName(String string) {
		// TODO Auto-generated method stub
		
	}
	private void setgivenName(String string) {
		// TODO Auto-generated method stub
		
	}
	private void setAccountId(String string) {
		// TODO Auto-generated method stub
		
	}
}
