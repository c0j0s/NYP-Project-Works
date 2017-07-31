package bean;

import java.util.Date;

public class FamilyGrp {
	private String familyGroupId;
	private String groupName;
	private Date groupCreationDate;
	private String imgUrl;

	public String getFamilyGroupId() {
		return familyGroupId;
	}
	public void setFamilyGroupId(String familyGroupId) {
		this.familyGroupId = familyGroupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Date getGroupCreationDate() {
		return groupCreationDate;
	}
	public void setGroupCreationDate(Date groupCreationDate) {
		this.groupCreationDate = groupCreationDate;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
