package bean;

public class RewardItem {
	private String rewardId,rewardTitle,rewardDescription,imgUrl, itemCreatedOn, accountId;
	private int rewardQuantity, points;
	private char rewardAvailability, valid;
	
	public RewardItem(){
		
	}

	public String getRewardId() {
		return rewardId;
	}

	public void setRewardId(String rewardId) {
		this.rewardId = rewardId;
	}

	public String getRewardTitle() {
		return rewardTitle;
	}

	public void setRewardTitle(String rewardTitle) {
		this.rewardTitle = rewardTitle;
	}

	public String getRewardDescription() {
		return rewardDescription;
	}

	public void setRewardDescription(String rewardDescription) {
		this.rewardDescription = rewardDescription;
	}

    public int getPoints() {
	    return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public char getRewardAvailability() {
		return rewardAvailability;
	}

	public void setRewardAvailability(char rewardAvailability) {
		this.rewardAvailability = rewardAvailability;
	}

	public int getRewardQuantity() {
		return rewardQuantity;
	}

	public void setRewardQuantity(int rewardQuantity) {
		this.rewardQuantity = rewardQuantity;
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

	public String getItemCreatedOn() {
		return itemCreatedOn;
	}

	public void setItemCreatedOn(String itemCreatedOn) {
		this.itemCreatedOn = itemCreatedOn;
	}

	public void setAccountId(String accountId) {
		// TODO Auto-generated method stub
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}
	
}
	
	


