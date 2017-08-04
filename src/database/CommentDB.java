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
public class CommentDB extends DBAO implements ForumMetaById{
	
	public CommentDB(){
		super();
	}
	
	/**
	 * create new comment into database
	 * @param com
	 * @return postId
	 */
	public String createComment(Comment com){
		
		String stmt = "INSERT INTO `"+ schema +"`.`comments` (`commentId`, `commentContent`, `commentDate`,`commentGroup`, `postId`, `AccountId`, `CommentscommentId`,`hideId`)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, common.UID.genCommentId());	
			ps.setString(2, com.getCommentContent());
			ps.setString(3, com.getDate());
			ps.setString(4, com.getCommentGroup());
			ps.setString(6, com.getAccountId());
			ps.setString(8, ""+com.getHideId());
			
			if(com.getCommentsComId() != null){
				ps.setString(5, null);
				ps.setString(7, com.getCommentsComId());
			}else{
				ps.setString(5, com.getPostId());
				ps.setString(7, null);
			}
			
			int status = ps.executeUpdate();
			if(status != 0){
				System.out.println("Log createComment() :" + ps);
				ps.close();
				return com.getPostId();
			}else{
				System.out.println("Log createComment() Fail:" + ps);
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
	 * To retrieve all comment from database
	 * @param statement
	 * @return ArrayList<Post>
	 */
	public ArrayList<Comment> getComment(String statement){
		
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		try {
			if(statement == null){
				statement = "SELECT * FROM "+ schema +".commentlist WHERE commentStatus = 'publish' and valid = 'Y' ORDER BY commentDate DESC";
			}
			
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setFetchSize(1000);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Comment com = new Comment();
				com.setCommentId(rs.getString("commentId"));
				com.setCommentContent(rs.getString("commentContent"));
				com.setCommentGroup(rs.getString("commentGroup"));
				com.setCommentStatus(rs.getString("commentStatus"));
				com.setPostId(rs.getString("postId"));
				com.setCommentsComId(rs.getString("CommentscommentId"));
				com.setDate(rs.getString("commentDate"));
				com.setAccountId(rs.getString("accountId"));
				com.setAccountName(rs.getString("givenName"));
				com.setAccountImgUrl(rs.getString("imgUrl"));
				
				com.setLikeCount(rs.getInt("likeCount"));
				com.setDislikeCount(rs.getInt("dislikeCount"));	
				com.setCommentCount(rs.getInt("commentCount"));			
				com.setHideId(rs.getString("hideId").charAt(0));				
				commentList.add(com);
			}
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		String stmt = "SELECT * FROM "+ schema +".commentlist WHERE postId = '"+ postId +"' AND commentStatus = 'publish' AND valid = 'Y' ORDER BY bestAnswerFor Desc,commentDate DESC limit " + start + "," + limit;
		return getComment(stmt);
	}
	
	/**
	 * get replies under comment
	 * @param commentId
	 * @param start
	 * @param limit
	 * @return ArrayList<Comment>
	 */
	public ArrayList<Comment> getCommentByCommentId(String commentId, int start, int limit){		
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		try {
			String stmt = "select commentContent,imgUrl from (SELECT * FROM "+ schema +".commentlist WHERE commentsCommentId = '"+ commentId +"' AND commentStatus = 'publish' AND valid = 'Y' ORDER BY commentDate DESC limit " + start + "," + limit +") m order by m.commentDate asc";
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setFetchSize(1000);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Comment com = new Comment();
				com.setCommentContent(rs.getString("commentContent"));
				com.setAccountImgUrl(rs.getString("imgUrl"));
				commentList.add(com);
			}
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentList;
	}

	public String getCommentOwnerbyId(String id) {
		try {
			String statement = "SELECT accountId FROM "+ schema +".commentlist WHERE commentId = ?";
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return rs.getString("accountId");
			}
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public boolean invalidComment(String itemId) {
		String statement = "update ffl.comments set valid = 'N' where commentId = ?";
		try {
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setString(1, itemId);
			int status = ps.executeUpdate();
			
			if(status != 0) {
				System.out.println("log invalidPost("+ itemId +"): (success)" + ps);
				return true;
			}
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public void validComment(String itemId) {
		String statement = "update ffl.comments set valid = 'Y' where commentId = ?";
		try {
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setString(1, itemId);

			int status = ps.executeUpdate();
			
			if(status != 0) {
				System.out.println("log invalidPost("+ itemId +"): (success)" + ps);
			}
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getItemAccountIdByItemId(String itemId) {
		String stmt = "select accountId from ffl.comments where commentId = ?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, itemId);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
