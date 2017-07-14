package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import bean.Post;

/**
 * @author cjuns
 *
 */
public class ForumDB extends DBAO{
	private static int maxCount = 0;
	
	public ForumDB(){
		super();
	}
	
	/**
	 * create new post into database
	 * @param post
	 * @return postId
	 */
	public String createPost(Post post){
		String stmt = "INSERT INTO "+ schema +".post (`postId`, `postTitle`, `postDate`, `postContent`, `postCategory`, `points`, `valid`, `hideId`, `tagList`, `UseraccountId`,`ActivityactivityId`) "
				+ "VALUES (?,?,?,?,?,?,'Y',?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			post.setPostId(common.UID.genPostId());
			ps.setString(1, post.getPostId());
			ps.setString(2, post.getPostTitle());
			ps.setString(3, post.getDate());
			ps.setString(4, post.getPostContent());
			ps.setString(5, post.getPostCategory());
			ps.setInt(6, post.getPoints());
			ps.setString(7, String.valueOf(post.getHideId()));
			ps.setString(8, post.getTagList());
			ps.setString(9, post.getAccountId());
			if(post.getActivityId() != null){
				ps.setString(10, post.getActivityId());
			}else{
				ps.setString(10, null);
			}
			System.out.println(ps);
			int status = ps.executeUpdate();
			if(status != 0){
				System.out.println("Log createPost() :" + ps);
				ps.close();
				return post.getPostId();
			}else{
				ps.close();
				return "fail";
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}

	/**
	 * To retrieve all post from database
	 * @param statement
	 * @return ArrayList<Post>
	 */
	public static ArrayList<Post> getPost(String statement){
		maxCount = 0;
		ArrayList<Post> postList = new ArrayList<Post>();
		try {
			PreparedStatement ps;
			if(statement == null){
				statement = "SELECT * FROM "+ schema +".postlist ORDER BY postDate DESC";
			}
	
			ps = con.prepareStatement(statement);
			
			
			System.out.println("log Forum.java :" + ps);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Post post = new Post();
				
				post.setPostId(rs.getString("postId"));
				post.setPostTitle(rs.getString("postTitle"));
				post.setPostContent(rs.getString("postContent"));
				post.setPostCategory(rs.getString("postCategory"));
				post.setTagList(rs.getString("tagList"));
				post.setAccountName(rs.getString("givenName"));
				post.setAccountImgUrl(rs.getString("imgUrl"));
				post.setAccountId(rs.getString("UseraccountId"));
				post.setActivityId(rs.getString("ActivityactivityId"));
				post.setDate(rs.getString("postDate"));
				post.setPostStatus(rs.getString("postStatus"));
				
				post.setLikeCount(rs.getInt("likeCount"));
				post.setDislikeCount(rs.getInt("dislikeCount"));
				post.setFollowerCount(rs.getInt("followCount"));
				
				post.setCommentCount(rs.getInt("commentCount"));
				post.setValid(rs.getString("valid").charAt(0));
				post.setHideId(rs.getString("hideId").charAt(0));
				
//				post.setFollowerAccounts(meta.getMetaAccounts("postId",post.getPostId(),"follow"));
				post.setLikeAccounts(MetaValueDB.getMetaAccounts("post","postId",post.getPostId(),"like"));
				post.setDislikeAccounts(MetaValueDB.getMetaAccounts("post","postId",post.getPostId(),"dislike"));
				
				post.setBestAnswer(rs.getString("bestAnswer"));
				
				postList.add(post);
				maxCount++;
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postList;
	}	
	
	/**
	 * NOT TESTED
	 * retrieve post for paginations
	 * @param start 
	 * @param limit 
	 * @param category 
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> getPost(int start,String category){
		String stmt = "SELECT * FROM "+ schema +".postlist WHERE valid = 'Y' AND postCategory = '"+ category +"' ORDER BY postDate DESC limit " + start + ", 100";
		System.out.println(stmt);
		return getPost(stmt);
	}
		
	/**
	 * retrieve post by post id
	 * @param postId
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> getPostById(String postId){
		new DBAO();
		String stmt = "SELECT * FROM "+ schema +".postlist WHERE postId = '"+ postId +"'";
		return getPost(stmt);
	}
		
	public static int getMaxCount(int currentPage) {
		if(currentPage > 1){
			maxCount = maxCount + (currentPage * 10 - 9);
		}
		System.out.println(maxCount);
		return maxCount;
	}

	public void addCategory(String newCat){
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO "+ schema +".category(`categoryName`) VALUES (?)");
			ps.setString(1, newCat);
			int status = ps.executeUpdate();
			if(status != 0){
				System.out.println("Log addCategory(): " + ps);
			}
		} catch (SQLException e) {
			System.out.println("Log addCategory(): " + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Map<String, String> getCategoryList(){
		Map<String, String> categoryList = new HashMap<String, String>();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+ schema +".category");
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				categoryList.put(rs.getString("categoryId"), rs.getString("categoryName"));
			}
		} catch (SQLException e) {
			System.out.println("Log getCategory(): " + e.getMessage());
		}
		return categoryList;
	}

	public String updatePost(Map<String, String> input, String postId) {
		String startStmt = "Update ffl.post set ";
		String bodyStmt = "";
		for(Entry<String, String> entry : input.entrySet()) {
			 bodyStmt = bodyStmt + entry.getKey() + " = '" + entry.getValue() + "',";
		}
		bodyStmt = bodyStmt.substring(0, (bodyStmt.length()-1));
		String endStmt = " Where postId = ?";
		System.out.println(bodyStmt);
		try {
			String statement = startStmt + bodyStmt + endStmt;
			System.out.println("Log statement: " + statement);
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setString(1, postId);
			
			int status = ps.executeUpdate();
			
			if(status != 0) {
				System.out.println("log updatePost("+ postId +"): (success)" + ps);
				return postId;
			}else {
				ps.close();
				return "fail";
			}
		} catch (SQLException e) {
			System.out.println("log updatePost(): (fail)" + e.getMessage());
			e.printStackTrace();
		} 
		return "fail";
	}

	public void invalidPost(String postId) {
		String statement = "update ffl.post set valid = 'N' where postId = ?";
		try {
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setString(1, postId);

			int status = ps.executeUpdate();
			
			if(status != 0) {
				System.out.println("log invalidPost("+ postId +"): (success)" + ps);
			}
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void pickBestAnswer(String postId, String commentId) {
		// TODO Auto-generated method stub
		System.out.println(postId);
		System.out.println(commentId);
		String stmt = "update ffl.post set bestAnswer = ?, postStatus = 'closed' where postId = ?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, commentId);
			ps.setString(2, postId);
			System.out.println(ps);
			int status = ps.executeUpdate();
			if (status != 0) {
				System.out.println("log pickBestAnswer(): (success)");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
