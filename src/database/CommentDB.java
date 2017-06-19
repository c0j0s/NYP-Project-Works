package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Comment;

/**
 * @author cjuns
 *
 */
public class CommentDB extends DBAO{
	
	public CommentDB(){
		super();
	}
	
	/**
	 * create new post into database
	 * @param post
	 * @return postId
	 */
	public String createComment(Comment com){
		String stmt = "INSERT INTO `ffl`.`comments` (`commentId`, `commentContent`, `commentDate`, `commentLikes`, `commentDislikes`, `commentGroup`, `PostpostId`, `CommentscommentId`)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, common.UID.genCommentId());			
			
			if(com.getCommentsComId() != null){
				ps.setString(8, com.getCommentsComId());
			}else{
				ps.setString(8, null);
			}
			
			int status = ps.executeUpdate();
			if(status != 0){
				System.out.println("Log createComment() :" + ps);
				return com.getPostId();
			}else{
				System.out.println("Log createComment() Fail:" + ps);
				return "fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}

//	/**
//	 * To retrieve all post from database
//	 * @param statement
//	 * @return ArrayList<Post>
//	 */
//	public ArrayList<Post> getPost(String statement){
//		ArrayList<Post> postList = new ArrayList<Post>();
//		try {
//			if(statement == null){
//				statement = "SELECT * FROM ffl.post ORDER BY postDate DESC";
//			}
//			PreparedStatement ps;
//			ps = con.prepareStatement(statement);
//			
//			System.out.println("log Forum.java :" + ps);
//			
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()){
//				Post post = new Post();
//				post.setPostId(rs.getString("postId"));
//				post.setPostTitle(rs.getString("postTitle"));
//				post.setPostContent(rs.getString("postContent"));
//				post.setPostCategory(rs.getString("postCategory"));
//				post.setTagList(rs.getString("postCategory"));
//				post.setAccountId(rs.getString("UseraccountId"));
//				post.setActivityId(rs.getString("ActivityactivityId"));
//				post.setPostDate(rs.getString("postDate"));
//				
//				post.setPostLikes(getPostMetaCounts(post.getPostId(),"like"));
//				post.setPostDislikes(getPostMetaCounts(post.getPostId(),"dislike"));
//				post.setPostfollower(getPostMetaCounts(post.getPostId(),"dislike"));
//				
//				post.setCommentCount(rs.getInt("commentCount"));
//				post.setValid(rs.getString("valid").charAt(0));
//				post.setHideId(rs.getString("hideId").charAt(0));
//				
//				post.setFollowerAccounts(getPostMetaAccounts(post.getPostId(),"follow"));
//				post.setLikeAccounts(getPostMetaAccounts(post.getPostId(),"like"));
//				post.setDislikeAccounts(getPostMetaAccounts(post.getPostId(),"dislike"));
//				
//				postList.add(post);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return postList;
//	}
//	
//	/**
//	 * NOT TESTED
//	 * retrieve post for paginations
//	 * @param start 
//	 * @param limit 
//	 * @param category 
//	 * @return ArrayList<Post>
//	 */
//	public ArrayList<Post> getPost(int start, int limit,String category){
//		String stmt = "SELECT * FROM ffl.post WHERE valid = 'Y' AND category = '"+ category +"' AND limit = '" + start + "," + limit + "'";
//		return getPost(stmt);
//	}
//	
//	/**
//	 * retrieve post by post id
//	 * @param postId
//	 * @return ArrayList<Post>
//	 */
//	public ArrayList<Post> getPostById(String postId){
//		String stmt = "SELECT * FROM ffl.post WHERE postId = '"+ postId +"'";
//		return getPost(stmt);
//	}
//	
//	/**
//	 * NOT TESTED
//	 * to sort post by category only
//	 * @param category
//	 * @return ArrayList<Post>
//	 */
//	public ArrayList<Post> getPostByCategory(String category){
//		String stmt = "SELECT * FROM ffl.post WHERE category = '"+ category +"'";
//		return getPost(stmt);
//	}
//	
//	/**
//	 * create post meta values
//	 * @param postId
//	 * @param accountId
//	 * @param action like|dislike|follow
//	 */
//	public void addPostMeta(String postId, String accountId, String action){
//		String stmt = "INSERT INTO `ffl`.`postmeta` (`postId`, `accountId`, `postAction`) VALUES ('?', '?', '?')";
//		try {
//			PreparedStatement ps = con.prepareStatement(stmt);
//			ps.setString(1, postId);
//			ps.setString(2, accountId);
//			ps.setString(3, action);
//			
//			int status = ps.executeUpdate();
//			if(status != 0){
//				System.out.println("Log updatePostMeta(): " + ps);
//			}else{
//				System.out.println("Log updatePostMeta(): fail " + ps);
//			}
//			
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	public void delPostMeta(){}
//	
//	/**
//	 * get post meta value counts
//	 * @param postId
//	 * @param action like|dislike|follow
//	 * @return
//	 */
//	public int getPostMetaCounts(String postId,String action){
//		int count = 0;
//		try {
//			String stmt = "SELECT COUNT(*) AS count FROM `ffl`.`postmeta` WHERE postId = '"+ postId +"' AND postAction = '"+ action +"'";
//			PreparedStatement ps = con.prepareStatement(stmt);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()){
//				count = rs.getInt("count");
//			}
//			
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//		return count;
//	}
//		
//	/**
//	 * get post meta value accounts
//	 * @param postId
//	 * @param action like|dislike|follow
//	 * @return
//	 */
//	public ArrayList<String> getPostMetaAccounts(String postId,String action){
//		ArrayList<String> list = new ArrayList<String>();
//		try {
//			String stmt = "SELECT * FROM `ffl`.`postmeta` WHERE postId = '"+ postId +"' AND postAction = '"+ action +"'";
//			PreparedStatement ps = con.prepareStatement(stmt);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()){
//				list.add(rs.getString("accountId"));
//			}
//			
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//		return list;
//	}
//	
//	public void addCategory(String newCat){
//		try {
//			PreparedStatement ps = con.prepareStatement("INSERT INTO ffl.category(`categoryName`) VALUES (?)");
//			ps.setString(1, newCat);
//			int status = ps.executeUpdate();
//			if(status != 0){
//				System.out.println("Log addCategory(): " + ps);
//			}
//		} catch (SQLException e) {
//			System.out.println("Log addCategory(): " + e.getMessage());
//		}
//	}
//	
//	/**
//	 * 
//	 * @return
//	 */
//	public ArrayList<String> getCategories(){
//		ArrayList<String> list = new ArrayList<String>();
//		try {
//			PreparedStatement ps = con.prepareStatement("SELECT * FROM ffl.category");
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()){
//				list.add(rs.getString("categoryName"));
//			}
//		} catch (SQLException e) {
//			System.out.println("Log getCategory(): " + e.getMessage());
//		}
//		return list;
//	}
}
