package bean;

public class Comment {
	private String commentId, commentContent, commentGroup, postId, commentsComId;
	private java.sql.Timestamp commentDate;
	private int commentLikes, commentDislikes;
	
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

	public java.sql.Timestamp getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(java.sql.Timestamp commentDate) {
		this.commentDate = commentDate;
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
