package bean;

public class Post extends MetaValue{
	private String postId,postTitle,postContent,postCategory,tagList,postStatus,accountId,activityId,bestAnswer;
	private int points,commentCount,hitLevel;
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

	public String getBestAnswer() {
		return bestAnswer;
	}

	public void setBestAnswer(String bestAnswer) {
		this.bestAnswer = bestAnswer;
	}

	public int getHitLevel() {
		return hitLevel;
	}

	public void setHitLevel(int hitLevel) {
		this.hitLevel = hitLevel;
	}

}
