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
	private String AccountId;
	private int points;
	private int creditLevel;
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
		return AccountId;
	}
	public void setAccountId(String accountId) {
		AccountId = accountId;
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
	public Account isMember(String userId, String userPw) {
		return null ;
		// TODO Auto-generated method stub
		
	}
	
}
