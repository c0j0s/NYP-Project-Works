package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Activity;
import bean.FamilyGrp;

public class FamGrpDB extends DBAO{
	public void createFamGrp(FamilyGrp fg) throws Exception{
		try{
			String insertStatement = "Insert into ffl.familygroups (familyGroupId, groupName, groupCreationDate, valid, imgUrl, password, grpOwner)  values(?,?,?,'Y',?,?,?)";
			PreparedStatement prepStmt = con.prepareStatement(insertStatement);
			prepStmt.setString(1, fg.getFamilyGroupId());
			prepStmt.setString(2, fg.getGroupName() );
			prepStmt.setString(3, fg.getGroupCreationDate() );
			prepStmt.setString(4, fg.getImgUrl() );
			prepStmt.setString(5, fg.getPassword() );
			prepStmt.setString(6, fg.getGrpOwner());
			System.out.println(fg.getGrpOwner());
			prepStmt.executeUpdate();
			addMember(fg.getGrpOwner(),fg.getFamilyGroupId());
		}catch(Exception ex){
			throw new Exception("Error:"+ex.getMessage());
		}
	}
	public ArrayList<FamilyGrp> viewFamGrp() {
		ArrayList<FamilyGrp> famGrpList = new ArrayList<FamilyGrp>();
		try {
			String s = "select familyGroupId, groupName, groupCreationDate, imgUrl, password, grpOwner from ffl.familygroups where valid ='Y';";
			PreparedStatement p = con.prepareStatement(s);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
			FamilyGrp fg = new FamilyGrp();
			fg.setFamilyGroupId(rs.getString("familyGroupId"));
			fg.setGroupName(rs.getString("groupName"));
			fg.setGroupCreationDate(rs.getString("groupCreationDate"));
			fg.setImgUrl(rs.getString("imgUrl"));
			fg.setPassword(rs.getString("password"));
			fg.setGrpOwner(rs.getString("grpOwner"));
			famGrpList.add(fg);
			}System.out.println(rs);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return famGrpList;
	}
//	public ArrayList<FamilyGrp> getFamGrpAccurate(String username,String password){
//		ArrayList<FamilyGrp> famGrpList = new ArrayList<FamilyGrp>();
//		try {
//			String stmt ="select * from ffl.familygroups where familyGroupId = ? and password = ?;";
//			PreparedStatement p = con.prepareStatement(stmt);
//			p.setString(1, username);
//			p.setString(2, password);
//			ResultSet rs = p.executeQuery();
//			while(rs.next()) {
//				FamilyGrp fg = new FamilyGrp();
//				fg.setFamilyGroupId(rs.getString("familyGroupId"));
//				fg.setGroupName(rs.getString("groupName"));
//				fg.setGroupCreationDate(rs.getString("groupCreationDate"));
//				fg.setImgUrl(rs.getString("imgUrl"));
//				fg.setPassword(rs.getString("password"));
//				fg.setGrpOwner(rs.getString("grpOwner"));
//				famGrpList.add(fg);
//			}
//
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//		return famGrpList;
//
//	}
	public ArrayList<FamilyGrp> getFamGrpAccurate(String username,String password,String userId){
		ArrayList<FamilyGrp> famGrpList = new ArrayList<FamilyGrp>();
		try {
			String stmt ="select * from ffl.familygroups where familyGroupId = ? and password = ? and valid = 'Y';";
			PreparedStatement p = con.prepareStatement(stmt);
			p.setString(1, username);
			p.setString(2, password);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				FamilyGrp fg = new FamilyGrp();
				fg.setFamilyGroupId(rs.getString("familyGroupId"));
				fg.setGroupName(rs.getString("groupName"));
				fg.setGroupCreationDate(rs.getString("groupCreationDate"));
				fg.setImgUrl(rs.getString("imgUrl"));
				fg.setPassword(rs.getString("password"));
				fg.setGrpOwner(rs.getString("grpOwner"));
				addMember(userId,rs.getString("familyGroupId"));
				
				
				famGrpList.add(fg);
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return famGrpList;

	}
	public ArrayList<FamilyGrp> getFamGrpByUserId(String userId){
		ArrayList<FamilyGrp> famGrpList = new ArrayList<FamilyGrp>();
		try {
			String stmt ="select * from ffl.familygroups f inner join ffl.userfamilygroup u on u.FamilyGroupfamilyGroupId = f.familyGroupId where UseraccountId = ? and u.valid = 'Y';";
			PreparedStatement p = con.prepareStatement(stmt);
			p.setString(1, userId);
	
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				FamilyGrp fg = new FamilyGrp();
				fg.setFamilyGroupId(rs.getString("f.familyGroupId"));
				fg.setGroupName(rs.getString("groupName"));
				fg.setGroupCreationDate(rs.getString("groupCreationDate"));
				fg.setImgUrl(rs.getString("imgUrl"));
				fg.setPassword(rs.getString("password"));
				fg.setGrpOwner(rs.getString("grpOwner"));
				famGrpList.add(fg);
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return famGrpList;

	}
	public ArrayList<FamilyGrp> findMembers(String grpId){
		ArrayList<FamilyGrp> famGrpList = new ArrayList<FamilyGrp>();
		try {
			String stmt ="select * from ffl.familygroups f inner join ffl.userfamilygroup u on u.FamilyGroupfamilyGroupId = f.familyGroupId where f.familyGroupId = ? and u.valid= 'Y';";
			PreparedStatement p = con.prepareStatement(stmt);
			p.setString(1, grpId);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				FamilyGrp fg = new FamilyGrp();
				fg.setFamilyGroupId(rs.getString("f.familyGroupId"));
				fg.setGroupName(rs.getString("groupName"));
				fg.setGroupCreationDate(rs.getString("groupCreationDate"));
				fg.setImgUrl(rs.getString("imgUrl"));
				fg.setPassword(rs.getString("password"));
				fg.setGrpOwner(rs.getString("grpOwner"));
				fg.setGrpMember(rs.getString("userAccountId"));
				System.out.println(rs.getString("f.familyGroupId"));
				famGrpList.add(fg);
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return famGrpList;

	}
	public void addMember(String memberId,String grpId) throws Exception{
		try{
			String insertStatement = "Insert into ffl.userFamilygroup (UseraccountId,FamilyGroupfamilyGroupId,valid) values(?,?,'Y')";
			PreparedStatement prepStmt = con.prepareStatement(insertStatement);
			prepStmt.setString(1, memberId);
			prepStmt.setString(2, grpId );
			prepStmt.executeUpdate();
		}catch(Exception ex){
			throw new Exception("Error:"+ex.getMessage());
		}
	}
	public ArrayList<FamilyGrp> findGrpMembers(String grpId){
		ArrayList<FamilyGrp> famGrpList = new ArrayList<FamilyGrp>();
		try {
			String stmt ="select * from ffl.userfamilygroup ufg inner join ffl.user u on ufg.UserAccountId = u.accountId where FamilyGroupfamilyGroupId = ? and ufg.valid = 'Y';";
			PreparedStatement p = con.prepareStatement(stmt);
			p.setString(1, grpId);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				FamilyGrp fg = new FamilyGrp();
				fg.setFamilyGroupId(rs.getString("ufg.FamilyGroupfamilyGroupId"));
				fg.setAccountId(rs.getString("ufg.UseraccountId"));
				fg.setGivenName(rs.getString("givenName"));
				famGrpList.add(fg);
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return famGrpList;

	}
	
	public void deleteGroup(String grpId) {
		try {
			String stmt ="update ffl.familygroups set valid ='N' where familyGroupId = ?";
			PreparedStatement p = con.prepareStatement(stmt);
			p.setString(1, grpId);
			p.executeUpdate();
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	public void restoreGroup(String grpId) {
		try {
			String stmt ="update ffl.familygroups set valid ='Y' where familyGroupId = ?";
			PreparedStatement p = con.prepareStatement(stmt);
			p.setString(1, grpId);
			p.executeUpdate();
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	public void deleteMember(String grpId , String memberId) {
		try {
			String stmt ="update ffl.userfamilygroups set valid ='N' where FamilyGroupfamilyGroupId = ? and UseraccountId = ?";
			PreparedStatement p = con.prepareStatement(stmt);
			p.setString(1, grpId);
			p.setString(2, memberId);
			p.executeUpdate();
		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	

}
