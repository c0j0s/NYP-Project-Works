package Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
	private String postId,postTitle,postContent,postCategory,tagList,accountId,activityId;
	private java.sql.Timestamp postDate;
	private int postLikes, postDislikes, points,commentCount;
	private char valid, hideId;
	
	public Post(){
		
	}
	
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getPostCategory() {
		return postCategory;
	}
	public void setPostCategory(String postCategory) {
		this.postCategory = postCategory;
	}
	public String getTagList() {
		return tagList;
	}
	public void setTagList(String tagList) {
		this.tagList = tagList;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getPostDate() {
		String date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(postDate);
		return date;
	}
	public void setPostDate(java.sql.Timestamp postDate) {
		this.postDate = postDate;
	}
	public int getPostLikes() {
		return postLikes;
	}
	public void setPostLikes(int postLikes) {
		this.postLikes = postLikes;
	}
	public int getPostDislikes() {
		return postDislikes;
	}
	public void setPostDislikes(int postDislikes) {
		this.postDislikes = postDislikes;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public char getValid() {
		return valid;
	}
	public void setValid(char valid) {
		this.valid = valid;
	}
	public char getHideId() {
		return hideId;
	}
	public void setHideId(char hideId) {
		this.hideId = hideId;
	}
}
