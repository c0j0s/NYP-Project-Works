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
				
				
//				com.setFollowerAccounts(getMetaAccounts(com.getPostId(),"follow"));
				com.setLikeAccounts(MetaValueDB.getMetaAccounts("post","postId",com.getPostId(),"like"));
				com.setDislikeAccounts(MetaValueDB.getMetaAccounts("post","postId",com.getPostId(),"dislike"));
			
				com.setCommentComList(getCommentByCommentId(com.getCommentId(),0,5));
				
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
		String stmt = "SELECT * FROM "+ schema +".commentlist WHERE postId = '"+ postId +"' AND commentStatus = 'publish' ORDER BY bestAnswerFor Desc,commentDate DESC limit " + start + "," + limit;
		return getComment(stmt);
	}
	
	public ArrayList<Comment> getCommentByCommentId(String commentId, int start, int limit){
		String stmt = "select * from (SELECT * FROM "+ schema +".commentlist WHERE commentsCommentId = '"+ commentId +"' AND commentStatus = 'publish' ORDER BY commentDate DESC limit " + start + "," + limit +") m order by m.commentDate asc";
		return getComment(stmt);
	}

}
