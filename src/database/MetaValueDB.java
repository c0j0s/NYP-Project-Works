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
		
	public boolean checkifMetaExist(String colName, String id, String accountId, String action) {
		String stmt = "select count(*) from "+ schema +".metavalue where "+ colName +" = ? AND accountId = ? AND action = ?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, id);
			ps.setString(2, accountId);
			ps.setString(3, action);
			System.out.println("Log checkifMetaExist(): "+ps);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt(1) > 0) {
					return false;
				}else {
					return true;
				}
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * create post meta values
	 * @param parentId
	 * @param accountId
	 * @param action like|dislike|follow
	 */
	public String addMeta(String colName, String id, String accountId, String action){
		
		String stmt = "INSERT INTO "+ schema +".metavalue (`"+ colName +"`, `accountId`, `action`) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, id);
			ps.setString(2, accountId);
			ps.setString(3, action);
			System.out.println(ps);
			int Status = ps.executeUpdate();
			if(Status != 0){
				System.out.println("Log addMeta(): success");
				ps.close();
				return "success";
			}else{
				System.out.println("Log addMeta(): fail");
				return "fail";
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "fail";
	}
	
	public void removeMeta(String colName, String id, String accountId, String action) {
		String stmt = "DELETE FROM "+ schema +".metavalue WHERE "+colName+"= ? AND accountId = ? AND action = ?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, id);
			ps.setString(2, accountId);
			ps.setString(3, action);
			
			int status = ps.executeUpdate();
			if(status != 0){
				System.out.println("Log removeMeta(): success");
			}else{
				System.out.println("Log removeMeta(): fail");
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * get post meta value accounts
	 * @param parentId
	 * @param action like|dislike|follow
	 * @return
	 */
	public static ArrayList<String> getMetaAccounts(String table, String colName, String id,String action){
		ArrayList<String> list = new ArrayList<String>();
		try {
			String stmt = "select p."+ colName +",m.accountId,m.action from ffl."+ table +" p join metavalue m on p."+ colName +" = m."+ colName +" where p."+ colName +" = ? AND action = ?";
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, id);
			ps.setString(2, action);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString("accountId"));
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

}
