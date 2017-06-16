package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Bean.Post;

public class Forum extends DBAO{

	public Forum(){
		super();
	}
	public ArrayList<Post> getPost(){
		ArrayList<Post> postList = new ArrayList<Post>();
		try {
			String stmt = "SELECT * FROM ffl.post WHERE valid = 'Y'";
			PreparedStatement ps;
			ps = con.prepareStatement(stmt);
			
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return postList;
	}
}
