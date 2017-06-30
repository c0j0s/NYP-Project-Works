package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Account;

public class AccountDB extends DBAO{
	
	/**
	 * this is where ur database connection method is, not javabean
	 */
	
	public AccountDB(){
		super();
	}
	
	public Account isMember(String userId, String userPw) {
		// TODO Auto-generated method stub
		Account ac = null;
		try{
			String selectStatement = "SELECT * FROM ffl.user where email = ? and password = ?";
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1, userId);
			prepStmt.setString(2, userPw);
			
			ResultSet rs = prepStmt.executeQuery();
			if(rs.next()){
				ac = new Account();
				ac.setAccountId(rs.getString("accountId"));
				ac.setGivenName(rs.getString("givenName"));
				ac.setSurName(rs.getString("surName"));
				ac.setDob(rs.getDate("DOB"));
				ac.setGender(rs.getString("gender").charAt(0));
				ac.setEmail(rs.getString("email"));
				ac.setAddress(rs.getString("address"));
				ac.setMobileno(rs.getInt("mobileNo"));
				ac.setPoints(rs.getInt("points"));
				ac.setCreditLevel(rs.getInt("creditLevel"));
			}
		}catch(Exception ex){
			System.out.println("Error: "+ex.getMessage());
		}
		return ac;
	}
	
}