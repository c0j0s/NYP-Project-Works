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
	public String addMeta(String colName, String id, String accountId, String action){
		
		String stmt = "INSERT INTO "+ schema +".metavalue (`"+ colName +"`, `accountId`, `action`) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, id);
			ps.setString(2, accountId);
			ps.setString(3, action);
			System.out.println(ps);
			int Status = ps.executeUpdate();
			ps.close();
			if(Status != 0){
				System.out.println("Log addMeta(): success");
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * unused methods
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public void delPostMeta(){}
	
	/**
	 * get post meta value counts
	 * @param parentId
	 * @param action like|dislike|follow
	 * @return
	 */
	public int getMetaCounts(String colName, String parentId,String action){
		int count = 0;
		try {
			String stmt = "SELECT COUNT(*) AS count FROM "+ schema +".metavalue WHERE "+ colName +" = '"+ parentId +"' AND action = '"+ action +"'";
			PreparedStatement ps = con.prepareStatement(stmt);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				count = rs.getInt("count");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return count;
	}
		
	/**
	 * get post meta value accounts
	 * @param parentId
	 * @param action like|dislike|follow
	 * @return
	 */
	public ArrayList<String> getMetaAccounts(String colName, String parentId,String action){
		ArrayList<String> list = new ArrayList<String>();
		try {
			String stmt = "SELECT * FROM "+ schema +".metavalue WHERE "+ colName +" = '"+ parentId +"' AND action = '"+ action +"'";
			PreparedStatement ps = con.prepareStatement(stmt);
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
