package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MetaValue extends DBAO{
	private String table, identifier;
	
	public MetaValue(String table, String identifier){
		super();
		this.table = table;
		this.identifier = identifier;
	}
		
	/**
	 * create post meta values
	 * @param postId
	 * @param accountId
	 * @param action like|dislike|follow
	 */
	public void addMeta(String postId, String accountId, String action){
		String stmt = "INSERT INTO "+ table +" (`"+ identifier +"`, `accountId`, `postAction`) VALUES ('?', '?', '?')";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, postId);
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
	 * @param postId
	 * @param action like|dislike|follow
	 * @return
	 */
	public int getMetaCounts(String postId,String action){
		int count = 0;
		try {
			String stmt = "SELECT COUNT(*) AS count FROM "+ table +" WHERE "+ identifier +" = '"+ postId +"' AND postAction = '"+ action +"'";
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
	 * @param postId
	 * @param action like|dislike|follow
	 * @return
	 */
	public ArrayList<String> getMetaAccounts(String postId,String action){
		ArrayList<String> list = new ArrayList<String>();
		try {
			String stmt = "SELECT * FROM "+ table +" WHERE "+ identifier +" = '"+ postId +"' AND postAction = '"+ action +"'";
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
