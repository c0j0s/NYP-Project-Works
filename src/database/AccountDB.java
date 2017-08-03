package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Account;
import bean.FamilyGrp;

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
			String selectStatement = "SELECT * FROM ffl.userinfo where email = ? and password = ? and valid = 'Y'";
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
				ac.setImgUrl(rs.getString("imgUrl"));
				ac.setRole(rs.getString("role"));
				ac.setActivityOrganisedCount(rs.getInt("activityOrganisedCount"));
				ac.setActivityParticipatedCount(rs.getInt("activityParticipatedCount"));
				ac.setClaimCount(rs.getInt("claimCount"));
				ac.setFamilyGroupCount(rs.getInt("familyGroup"));
				ac.setBestAnswerCount(rs.getInt("bestAnswerCount"));
				ac.setCommentCounts(rs.getInt("commentCounts"));
				ac.setPostsCounts(rs.getInt("postCounts"));
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return ac;
	}
	public void regMember(Account ac, String pw){
		try{
			String insertStatement = "Insert into ffl.user (givenName, surName, dob, gender, email, address, mobileno, password, accountId, imgUrl)";
			insertStatement = insertStatement+ " values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement prepStmt = con.prepareStatement(insertStatement);
			prepStmt.setString(1, ac.getGivenName());
			prepStmt.setString(2, ac.getSurName());
			prepStmt.setDate(3, (Date) ac.getDob());
			prepStmt.setString(4, Character.toString(ac.getGender()));
			prepStmt.setString(5, ac.getEmail());
			prepStmt.setString(6, ac.getAddress());
			prepStmt.setInt(7, ac.getMobileno());
			prepStmt.setString(8, pw);
			prepStmt.setString(9, ac.getAccountId());
			prepStmt.setString(10, ac.getImgUrl());
			int status = prepStmt.executeUpdate();
			
			if(status!=0){
				System.out.println("Recorded Added");
			}
			
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void updateMember(Account ac){
		try{
			String updateStatement = "update ffl.user set givenName = ?, surName = ?, dob = ?, gender = ?, email = ?,"
					+ "address = ?, mobileno = ?, password = ?, imgUrl = ? where accountId = ?";
			PreparedStatement prepStmt=con.prepareStatement(updateStatement);
			prepStmt.setString(1, ac.getGivenName());
			prepStmt.setString(2, ac.getSurName());
			prepStmt.setDate(3, (Date) ac.getDob());
			prepStmt.setString(4, Character.toString(ac.getGender()));
			prepStmt.setString(5, ac.getEmail());
			prepStmt.setString(6, ac.getAddress());;
			prepStmt.setInt(7, ac.getMobileno());
			prepStmt.setString(8, ac.getPassword());
			prepStmt.setString(9, ac.getImgUrl());
			prepStmt.setString(10, ac.getAccountId());
			int status = prepStmt.executeUpdate();
			if(status != 0) {
				System.out.println("update successful");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void resetPw(String pw, String email){
		try{
			String updateStatement = "update ffl.user set password = ? where email = ?";
			PreparedStatement prepStmt = con.prepareStatement(updateStatement);
			prepStmt.setString(1, pw);
			prepStmt.setString(2, email);
			int status = prepStmt.executeUpdate();System.out.println(prepStmt);
			if(status != 0) {
				System.out.println("update successful");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public ArrayList<Account> getReportedAccount(){
		ArrayList<Account> reportList = new ArrayList<Account>();
		try{
			String ss = "select * from ffl.reported where type = 'account';";
			PreparedStatement prepStmt = con.prepareStatement(ss);
			ResultSet rs = prepStmt.executeQuery(); System.out.println(prepStmt);
			while(rs.next()){
				Account ac =  new Account();
				ac.setAccountId(rs.getString("itemId"));
				//ac.setGivenName(rs.getString("givenName")+" "+rs.getString("surName"));
				ac.setStatus(rs.getString("status"));
				reportList.add(ac);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			}
		return reportList;
	}
	public void invalidateAccount(String accId){
		try{
			String updateStatement = "update ffl.user set valid = 'N' where accountId = ?;";
			PreparedStatement prepStmt = con.prepareStatement(updateStatement);
			
			prepStmt.setString(1, accId);
			int status = prepStmt.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void updateReportStatus(String status) {
		try{
			String updateStatement = "update ffl.reported set status=?;";
			PreparedStatement prepStmt = con.prepareStatement(updateStatement);
			prepStmt.setString(1, status);
			prepStmt.executeUpdate();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
}