package bean;

import java.util.ArrayList;

public class MetaValue {

	private String date;
	private int likeCount, dislikeCount, followerCount;
	private ArrayList<String> followerAccounts,likeAccounts,dislikeAccounts;
	
	
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getDislikeCount() {
		return dislikeCount;
	}
	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}
	public int getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
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
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date.substring(0,19);
	}
	

}
