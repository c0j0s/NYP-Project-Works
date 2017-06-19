package bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Comment {
	private String commentId, commentContent, commentGroup, postId, commentsComId;
	private String commentDate;
	private int commentLikes, commentDislikes;
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	
	public Comment() {
		super();
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentGroup() {
		return commentGroup;
	}

	public void setCommentGroup(String commentGroup) {
		this.commentGroup = commentGroup;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getCommentsComId() {
		return commentsComId;
	}

	public void setCommentsComId(String commentsComId) {
		this.commentsComId = commentsComId;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate() {
		java.util.Date myDate = new java.util.Date();
		Timestamp sqlDate = new java.sql.Timestamp(myDate.getTime());
		this.commentDate = formatter.format(sqlDate);
	}
	
	public void setCommentDate(String date) {
		this.commentDate = date.substring(0, 19);
	}

	public int getCommentLikes() {
		return commentLikes;
	}

	public void setCommentLikes(int commentLikes) {
		this.commentLikes = commentLikes;
	}

	public int getCommentDislikes() {
		return commentDislikes;
	}

	public void setCommentDislikes(int commentDislikes) {
		this.commentDislikes = commentDislikes;
	}
	
	
}
