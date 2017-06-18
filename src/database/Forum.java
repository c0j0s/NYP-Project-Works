package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Post;

/**
 * @author cjuns
 *
 */
public class Forum extends DBAO{

	public Forum(){
		super();
	}
	
	/**
	 * create new post into database
	 * @param post
	 * @return postId
	 */
	public String createPost(Post post){
		String stmt = "INSERT INTO ffl.post (`postId`, `postTitle`, `postDate`, `postContent`, `postLikes`, `postDislikes`, `postCategory`, `points`, `valid`, `commentCount`, `hideId`, `tagList`, `UseraccountId`,`ActivityactivityId`) "
				+ "VALUES (?,?,?,?,'0','0',?,?,'Y','0',?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			post.setPostId(common.UID.genPostId());
			ps.setString(1, post.getPostId());
			ps.setString(2, post.getPostTitle());
			ps.setString(3, post.getPostDate());
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
				return post.getPostId();
			}else{
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
	public ArrayList<Post> getPost(String statement){
		ArrayList<Post> postList = new ArrayList<Post>();
		try {
			if(statement == null){
				statement = "SELECT * FROM ffl.post ORDER BY postDate DESC";
			}
			PreparedStatement ps;
			ps = con.prepareStatement(statement);
			
			System.out.println("log Forum.java :" + ps);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Post post = new Post();
				post.setPostId(rs.getString("postId"));
				post.setPostTitle(rs.getString("postTitle"));
				post.setPostContent(rs.getString("postContent"));
				post.setPostCategory(rs.getString("postCategory"));
				post.setTagList(rs.getString("postCategory"));
				post.setAccountId(rs.getString("UseraccountId"));
				post.setActivityId(rs.getString("ActivityactivityId"));
				post.setPostDate(rs.getString("postDate"));
				
				post.setPostLikes(getPostMetaCounts(post.getPostId(),"like"));
				post.setPostDislikes(getPostMetaCounts(post.getPostId(),"dislike"));
				post.setPostfollower(getPostMetaCounts(post.getPostId(),"dislike"));
				
				post.setCommentCount(rs.getInt("commentCount"));
				post.setValid(rs.getString("valid").charAt(0));
				post.setHideId(rs.getString("hideId").charAt(0));
				
				post.setFollowerAccounts(getPostMetaAccounts(post.getPostId(),"follow"));
				post.setLikeAccounts(getPostMetaAccounts(post.getPostId(),"like"));
				post.setDislikeAccounts(getPostMetaAccounts(post.getPostId(),"dislike"));
				
				postList.add(post);
			}
		} catch (SQLException e) {
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
	public ArrayList<Post> getPost(int start, int limit,String category){
		String stmt = "SELECT * FROM ffl.post WHERE valid = 'Y' AND category = '"+ category +"' AND limit = '" + start + "," + limit + "'";
		return getPost(stmt);
	}
	
	/**
	 * retrieve post by post id
	 * @param postId
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> getPostById(String postId){
		String stmt = "SELECT * FROM ffl.post WHERE postId = '"+ postId +"'";
		return getPost(stmt);
	}
	
	/**
	 * NOT TESTED
	 * to sort post by category only
	 * @param category
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> getPostByCategory(String category){
		String stmt = "SELECT * FROM ffl.post WHERE category = '"+ category +"'";
		return getPost(stmt);
	}
	
	/**
	 * create post meta values
	 * @param postId
	 * @param accountId
	 * @param action like|dislike|follow
	 */
	public void addPostMeta(String postId, String accountId, String action){
		String stmt = "INSERT INTO `ffl`.`postmeta` (`postId`, `accountId`, `postAction`) VALUES ('?', '?', '?')";
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
	public int getPostMetaCounts(String postId,String action){
		int count = 0;
		try {
			String stmt = "SELECT COUNT(*) AS count FROM `ffl`.`postmeta` WHERE postId = '"+ postId +"' AND postAction = '"+ action +"'";
			PreparedStatement ps = con.prepareStatement(stmt);
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
	public ArrayList<String> getPostMetaAccounts(String postId,String action){
		ArrayList<String> list = new ArrayList<String>();
		try {
			String stmt = "SELECT * FROM `ffl`.`postmeta` WHERE postId = '"+ postId +"' AND postAction = '"+ action +"'";
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
	
	public void addCategory(String newCat){
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO ffl.category(`categoryName`) VALUES (?)");
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
	public ArrayList<String> getCategories(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM ffl.category");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString("categoryName"));
			}
		} catch (SQLException e) {
			System.out.println("Log getCategory(): " + e.getMessage());
		}
		return list;
	}
}
