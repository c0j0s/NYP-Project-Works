package bean;

import java.util.Date;

public class Account {
	private String givenName;
	private String surName;
	private Date dob;
	private char gender;
	private String email;
	private String address;
	private int mobileno;
	private String accountId;
	private int points;
	private int creditLevel;
	private String password;
	private String imgUrl;
	private int postsCounts;
	private int commentCounts;
	private int bestAnswerCount;
	private int activityOrganisedCount;
	private int activityParticipatedCount;
	private int claimCount;
	private int familyGroupCount;
	private String status;
	private String role;
	
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMobileno() {
		return mobileno;
	}
	public void setMobileno(int mobileno) {
		this.mobileno = mobileno;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getCreditLevel() {
		return creditLevel;
	}
	public void setCreditLevel(int creditLevel) {
		this.creditLevel = creditLevel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public int getPostsCounts() {
		return postsCounts;
	}
	public void setPostsCounts(int postsCounts) {
		this.postsCounts = postsCounts;
	}
	public int getCommentCounts() {
		return commentCounts;
	}
	public void setCommentCounts(int commentCounts) {
		this.commentCounts = commentCounts;
	}
	public int getBestAnswerCount() {
		return bestAnswerCount;
	}
	public void setBestAnswerCount(int bestAnswerCount) {
		this.bestAnswerCount = bestAnswerCount;
	}
	public int getActivityOrganisedCount() {
		return activityOrganisedCount;
	}
	public void setActivityOrganisedCount(int activityOrganisedCount) {
		this.activityOrganisedCount = activityOrganisedCount;
	}
	public int getActivityParticipatedCount() {
		return activityParticipatedCount;
	}
	public void setActivityParticipatedCount(int activityParticipatedCount) {
		this.activityParticipatedCount = activityParticipatedCount;
	}
	public int getClaimCount() {
		return claimCount;
	}
	public void setClaimCount(int claimCount) {
		this.claimCount = claimCount;
	}
	public int getFamilyGroupCount() {
		return familyGroupCount;
	}
	public void setFamilyGroupCount(int familyGroupCount) {
		this.familyGroupCount = familyGroupCount;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 *  resources for account
	 */
	public String getAnonymousPic() {
		return "https://firebasestorage.googleapis.com/v0/b/famforlife-accc8.appspot.com/o/%252Fuser%252Fdefault%252F%2Fanonymous.png?alt=media&token=41c7c380-029e-4f20-86cd-ade552ad50c4";
	}

}
