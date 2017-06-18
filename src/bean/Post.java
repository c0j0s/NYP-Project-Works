package bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Post {
	private String postId,postTitle,postContent,postCategory,tagList,postStatus,accountId,activityId;
	private String postDate;
	private int postLikes, postDislikes,postfollower, points,commentCount;
	private char valid, hideId;
	private ArrayList<String> followerAccounts,likeAccounts,dislikeAccounts;
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	
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
	public String getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
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
		return postDate;
	}
	public void setPostDate() {
		java.util.Date myDate = new java.util.Date();
		Timestamp sqlDate = new java.sql.Timestamp(myDate.getTime());
		this.postDate = formatter.format(sqlDate);
	}
	public void setPostDate(String date) {
		this.postDate = date.substring(0, 19);
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

	public int getPostfollower() {
		return postfollower;
	}

	public void setPostfollower(int postfollower) {
		this.postfollower = postfollower;
	}

	public ArrayList<String> getFollowerAccounts() {
		return followerAccounts;
	}

	public void setFollowerAccounts(ArrayList<String> followerAccounts) {
		this.followerAccounts = followerAccounts;
	}

	public ArrayList<String> getLikeAccounts() {
		return likeAccounts;
	}

	public void setLikeAccounts(ArrayList<String> likeAccounts) {
		this.likeAccounts = likeAccounts;
	}

	public ArrayList<String> getDislikeAccounts() {
		return dislikeAccounts;
	}

	public void setDislikeAccounts(ArrayList<String> dislikeAccounts) {
		this.dislikeAccounts = dislikeAccounts;
	}


}
