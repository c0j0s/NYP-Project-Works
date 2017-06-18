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
				post.setPostLikes(rs.getInt("postLikes"));
				post.setPostDislikes(rs.getInt("postDislikes"));
				post.setCommentCount(rs.getInt("commentCount"));
				post.setValid(rs.getString("valid").charAt(0));
				post.setHideId(rs.getString("hideId").charAt(0));
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
	
	// getTrendingPost
	
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
}
