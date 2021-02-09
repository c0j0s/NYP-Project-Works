package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * 
 * @author cjuns
 *
 */
public class MetaValueDB extends DBAO{
	
	public MetaValueDB(){
		super();
	}

	/**
	 * create post meta values
	 * @param parentId
	 * @param accountId
	 * @param action like|dislike|follow
	 */
	public String addMeta(String id, String accountId, String action){
		
		String stmt = "INSERT INTO "+ schema +".metavalue (`parentId`, `accountId`, `action`)  VALUES (?, ?, ?)";
		String select = "Select Count(*) from "+ schema +".metavalue where `parentId` = ? And action = ?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, id);
			ps.setString(2, accountId);
			ps.setString(3, action);
			PreparedStatement ps1 = con.prepareStatement(select);
			ps1.setString(1, id);
			ps1.setString(2, action);
			System.out.println(ps);
			int status = ps.executeUpdate();
			if(status != 0) {
				ResultSet rs = ps1.executeQuery();
				if(rs.next()) {
					return rs.getString(1);
				}
			}else {
				return "fail";
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "fail";
	}
	
	public String removeMeta(String id, String accountId, String action) {
		
		String stmt = "DELETE FROM "+ schema +".metavalue WHERE `parentId`= ? AND accountId = ? AND action = ?";
				String select = "Select Count(*) from "+ schema +".metavalue where `parentId` = ? And action = ?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, id);
			ps.setString(2, accountId);
			ps.setString(3, action);
			PreparedStatement ps1 = con.prepareStatement(select);
			ps1.setString(1, id);
			ps1.setString(2, action);
			System.out.println(ps);
			int status = ps.executeUpdate();
			if(status != 0) {
				ResultSet rs = ps1.executeQuery();
				if(rs.next()) {
					return rs.getString(1);
				}
			}else {
				return "fail";
			}
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	/**
	 * get post meta value accounts
	 * @param parentId
	 * @param action like|dislike|follow
	 * @return
	 */
	public ArrayList<String> getMetaAccounts(String table, String colName, String id,String action){
		PreparedStatement ps = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			String stmt = "select distinct p."+ colName +",m.accountId,m.action from ffl."+ table +" p join metavalue m on p."+ colName +" = m.parentId where p."+ colName +" = ? AND m.action = ?";
			ps = con.prepareStatement(stmt);
			ps.setString(1, id);
			ps.setString(2, action);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString("accountId"));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	/**
	 * check if the user like the item before
	 * @param itemId
	 * @param accountId
	 * @return String action
	 */
	public String checkLiked(String itemId, String accountId) {
		String stmt = "Select * from ffl.metavalue where parentId = ? and accountId = ? and action != 'follow'";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, itemId);
			ps.setString(2, accountId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getString("action");
			}else {
				return "nil";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "nil";
	}
	
	/**
	 * check if the user follow post before
	 * @param itemId
	 * @param accountId
	 * @return boolean
	 */
	public boolean checkFollowed(String itemId, String accountId) {
		String stmt = "Select * from ffl.metavalue where parentId = ? and accountId = ? and action = 'follow'";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, itemId);
			ps.setString(2, accountId);
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
