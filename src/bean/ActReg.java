package bean;

public class ActReg extends BankDetails {
private String registrationId;
private double registrationAmtPaid;
private int participantNo;
private String userAccountId;
private String cashOrBank;
private String ActivityactivityId;
public ActReg(){
	
}

public String getRegistrationId() {
	return registrationId;
}

public void setRegistrationId(String registrationId) {
	this.registrationId = registrationId;
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

public String getCashOrBank() {
	return cashOrBank;
}

public void setCashOrBank(String cashOrBank) {
	this.cashOrBank = cashOrBank;
}


}