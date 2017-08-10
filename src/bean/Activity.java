package bean;

import java.util.ArrayList;

/**
 * try extend MetaValue Class
 * 
 *
 */

public class Activity extends MetaValue{
	private String commentCount;
	private String activityId;
	private String activityTitle;
	private String activityDescription;

	private int participantNo;
	private String activityPostDate;
	private String activityEndDate;
	private String activityStartDate;
	private String activityRegistrationEnd;
	private double activityFee;
	private String activityLocation;
	private String activityCategory;
	private String imgurl;
	private char valid;
	private ArrayList<String> activityDay;
	private int activityRank;
	private String organiserId;
	private String activityTime;
	private int activityPostCount;
	private String accountId;
	private int points;
	private int rankPoints;
	private String status;

	
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

	public int getParticipantNo() {
		return participantNo;
	}
	public void setParticipantNo(int participantNo) {
		this.participantNo = participantNo;
	}
	public String getActivityPostDate() {
		return activityPostDate;
	}
	public void setActivityPostDate(String string) {
		this.activityPostDate = string;
	}
	public String getActivityEndDate() {
		return activityEndDate;
	}
	public void setActivityEndDate(String string) {
		this.activityEndDate = string;
	}
	public String getActivityStartDate() {
		return activityStartDate;
	}
	public void setActivityStartDate(String string) {
		this.activityStartDate = string;
	}
	public String getActivityRegistrationEnd() {
		return activityRegistrationEnd;
	}
	public void setActivityRegistrationEnd(String string) {
		this.activityRegistrationEnd = string;
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
		return imgurl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgurl = imgUrl;
	}
	public char getValid() {
		return valid;
	}
	public void setValid(char valid) {
		this.valid = valid;
	}
	public ArrayList<String> getActivityDay() {
		return activityDay;
	}
	public void setActivityDay(ArrayList<String> arrayList) {
		this.activityDay = arrayList;
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
	public String getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}
	public int getActivityPostCount() {
		return activityPostCount;
	}
	public void setActivityPostCount(int activityPostCount) {
		this.activityPostCount = activityPostCount;
	}
	public String getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
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
	public void setPoints(int i) {
		this.points =i;
	}
	public int getRankPoints() {
		return rankPoints;
	}
	public void setRankPoints(int rankPoints) {
		this.rankPoints = rankPoints;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
