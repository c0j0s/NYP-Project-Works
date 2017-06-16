package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Bean.Post;

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
	 * @return
	 */
	public ArrayList<Post> getPost(String statement){
		ArrayList<Post> postList = new ArrayList<Post>();
		try {
			if(statement == null){
				statement = "SELECT * FROM ffl.post ORDER BY postDate DESC";
			}
			PreparedStatement ps;
			ps = con.prepareStatement(statement);
			
			System.out.println("log Forum:" + ps);
			
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
				post.setPostDate(java.sql.Timestamp.valueOf(rs.getString("postDate")));
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
	 * to get post for paginations
	 * @param start 
	 * @param limit 
	 * @param category 
	 * @return
	 */
	public ArrayList<Post> getPost(int start, int limit,String category){
		String stmt = "SELECT * FROM ffl.post WHERE valid = 'Y' AND category = '"+ category +"' AND limit = '" + start + "," + limit + "'";
		return getPost(stmt);
	}
	/**
	 * NOT TESTED
	 * to sort post by category only
	 * @param category
	 * @return
	 */
	public ArrayList<Post> getPostByCategory(String category){
		String stmt = "SELECT * FROM ffl.post WHERE category = '"+ category +"'";
		return getPost(stmt);
	}
	
	// getTrendingPost
}
