package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	public void addMeta(String parentId, String accountId, String action){
		String stmt = "INSERT INTO ffl.metavalue (`parentId`, `accountId`, `action`) VALUES ('?', '?', '?')";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, parentId);
			ps.setString(2, accountId);
			ps.setString(3, action);
			
			int status = ps.executeUpdate();
			if(status != 0){
				System.out.println("Log updatePostMeta(): " + ps);
			}else{
				System.out.println("Log updatePostMeta(): fail " + ps);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
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
			String stmt = "SELECT COUNT(*) AS count FROM ffl.metavalue WHERE "+ colName +" = '"+ parentId +"' AND action = '"+ action +"'";
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
			String stmt = "SELECT * FROM ffl.metavalue WHERE "+ colName +" = '"+ parentId +"' AND action = '"+ action +"'";
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
