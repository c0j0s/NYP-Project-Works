package bean;

public class ActReg extends BankDetails {
private String registrationId;
private java.sql.Timestamp registrationDate;
private double registrationAmtPaid;
private int participantNo;
private String userAccountId;
private String ActivityactivityId;
public ActReg(){
	
}

public String getRegistrationId() {
	return registrationId;
}

public void setRegistrationId(String registrationId) {
	this.registrationId = registrationId;
}

public java.sql.Timestamp getRegistrationDate() {
	return registrationDate;
}

public void setRegistrationDate(java.sql.Timestamp registrationDate) {
	this.registrationDate = registrationDate;
}

public double getRegistrationAmtPaid() {
	return registrationAmtPaid;
}

public void setRegistrationAmtPaid(double registrationAmtPaid) {
	this.registrationAmtPaid = registrationAmtPaid;
}

public int getParticipantNo() {
	return participantNo;
}

public void setParticipantNo(int participantNo) {
	this.participantNo = participantNo;
}

public String getUserAccountId() {
	return userAccountId;
}

public void setUserAccountId(String userAccountId) {
	this.userAccountId = userAccountId;
}

public String getActivityactivityId() {
	return ActivityactivityId;
}

public void setActivityactivityId(String activityactivityId) {
	ActivityactivityId = activityactivityId;
}


}