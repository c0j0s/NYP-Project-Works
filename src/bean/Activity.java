package bean;

/**
 * try extend MetaValue Class
 * 
 *
 */

public class Activity {
	private String activityId;
	private String activityTitle;
	private String activityDescription;
	private int activityLikes;
	private int activityDislikes;
	private int participantNo;
	private java.sql.Timestamp activityPostDate;
	private java.sql.Timestamp activityEndDate;
	private java.sql.Timestamp activityStartDate;
	private java.sql.Timestamp activityRegistrationPeriod;
	private double activityFee;
	private String activityLocation;
	private String activityCategory;
	private String imgUrl;
	private char valid;
	private String activityDay;
	private int activityRank;
	private String organiserId;

	
	public Activity(){
		
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	public String getActivityDescription() {
		return activityDescription;
	}
	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}
	public int getActivityLikes() {
		return activityLikes;
	}
	public void setActivityLikes(int activityLikes) {
		this.activityLikes = activityLikes;
	}
	public int getActivityDislikes() {
		return activityDislikes;
	}
	public void setActivityDislikes(int activityDislikes) {
		this.activityDislikes = activityDislikes;
	}
	public int getParticipantNo() {
		return participantNo;
	}
	public void setParticipantNo(int participantNo) {
		this.participantNo = participantNo;
	}
	public java.sql.Timestamp getActivityPostDate() {
		return activityPostDate;
	}
	public void setActivityPostDate(java.sql.Timestamp activityPostDate) {
		this.activityPostDate = activityPostDate;
	}
	public java.sql.Timestamp getActivityEndDate() {
		return activityEndDate;
	}
	public void setActivityEndDate(java.sql.Timestamp activityEndDate) {
		this.activityEndDate = activityEndDate;
	}
	public java.sql.Timestamp getActivityStartDate() {
		return activityStartDate;
	}
	public void setActivityStartDate(java.sql.Timestamp activityStartDate) {
		this.activityStartDate = activityStartDate;
	}
	public java.sql.Timestamp getActivityRegistrationPeriod() {
		return activityRegistrationPeriod;
	}
	public void setActivityRegistrationPeriod(java.sql.Timestamp activityRegistrationPeriod) {
		this.activityRegistrationPeriod = activityRegistrationPeriod;
	}
	public double getActivityFee() {
		return activityFee;
	}
	public void setActivityFee(double activityFee) {
		this.activityFee = activityFee;
	}
	public String getActivityLocation() {
		return activityLocation;
	}
	public void setActivityLocation(String activityLocation) {
		this.activityLocation = activityLocation;
	}
	public String getActivityCategory() {
		return activityCategory;
	}
	public void setActivityCategory(String activityCategory) {
		this.activityCategory = activityCategory;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public char getValid() {
		return valid;
	}
	public void setValid(char valid) {
		this.valid = valid;
	}
	public String getActivityDay() {
		return activityDay;
	}
	public void setActivityDay(String activityDay) {
		this.activityDay = activityDay;
	}
	public int getActivityRank() {
		return activityRank;
	}
	public void setActivityRank(int activityRank) {
		this.activityRank = activityRank;
	}
	public String getOrganiserId() {
		return organiserId;
	}
	public void setOrganiserId(String organiserId) {
		this.organiserId = organiserId;
	}

	
	
}
