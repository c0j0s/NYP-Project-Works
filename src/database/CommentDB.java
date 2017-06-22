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
	 * create new comment into database
	 * @param com
	 * @return postId
	 */
	public String createComment(Comment com){
		String stmt = "INSERT INTO `"+ schema +"`.`comments` (`commentId`, `commentContent`, `commentDate`,`commentGroup`, `postId`, `AccountId`, `CommentscommentId`)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, common.UID.genCommentId());	
			ps.setString(2, com.getCommentContent());
			ps.setString(3, com.getDate());
			ps.setString(4, com.getCommentGroup());
			ps.setString(6, com.getAccountId());
			
			if(com.getCommentsComId() != null){
				ps.setString(5, null);
				ps.setString(7, com.getCommentsComId());
			}else{
				ps.setString(5, com.getPostId());
				ps.setString(7, null);
			}
			
			int status = ps.executeUpdate();
			ps.close();
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

	/**
	 * To retrieve all comment from database
	 * @param statement
	 * @return ArrayList<Post>
	 */
	public ArrayList<Comment> getComment(String statement){
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		try {
			if(statement == null){
				statement = "SELECT * FROM "+ schema +".commentlist WHERE commentStatus = 'publish' ORDER BY commentDate DESC";
			}
			
			PreparedStatement ps;
			ps = con.prepareStatement(statement);
			
			System.out.println("log CommentDB.java :" + ps);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Comment com = new Comment();
				MetaValueDB meta = new MetaValueDB();
				com.setCommentId(rs.getString("commentId"));
				com.setCommentContent(rs.getString("commentContent"));
				com.setCommentGroup(rs.getString("commentGroup"));
				com.setCommentStatus(rs.getString("commentStatus"));
				com.setPostId(rs.getString("postId"));
				com.setCommentsComId(rs.getString("CommentscommentId"));
				com.setDate(rs.getString("commentDate"));
				com.setAccountId(rs.getString("accountId"));
				
				com.setLikeCount(rs.getInt("likeCount"));
				com.setDislikeCount(rs.getInt("dislikeCount"));	
				com.setCommentCount(rs.getInt("commentCount"));
				
				com.setHideId(rs.getString("hideId").charAt(0));
				
				
//				com.setFollowerAccounts(getMetaAccounts(com.getPostId(),"follow"));
//				com.setLikeAccounts(getMetaAccounts(com.getPostId(),"like"));
//				com.setDislikeAccounts(getMetaAccounts(com.getPostId(),"dislike"));
				
				commentList.add(com);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentList;
	} 
	
	/**
	 * retrieve comments under specific post
	 * @param postId
	 * @param start
	 * @param limit
	 * @return
	 */
	public ArrayList<Comment> getCommentByPostId(String postId, int start, int limit){
		String stmt = "SELECT * FROM "+ schema +".commentlist WHERE postId = '"+ postId +"' AND commentStatus = 'publish' ORDER BY commentDate DESC limit " + start + "," + limit;
		System.out.println("Log getCommentByPostId(): " + stmt);
		return getComment(stmt);
	}
	
	public ArrayList<Comment> getCommentByCommentId(String commentId, int start, int limit){
		String stmt = "select * from (SELECT * FROM "+ schema +".commentlist WHERE commentsCommentId = '"+ commentId +"' AND commentStatus = 'publish' ORDER BY commentDate DESC limit " + start + "," + limit +") m order by m.commentDate asc";
		System.out.println("Log getCommentByPostId(): " + stmt);
		return getComment(stmt);
	}
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
