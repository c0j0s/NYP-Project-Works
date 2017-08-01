package bean;

import java.util.Date;

public class FamilyGrp {
	private String familyGroupId;
	private String groupName;
	private String groupCreationDate;
	private String imgUrl;
	private String grpOwner;
	private String password;
	private String grpMember;

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
	public String getGroupCreationDate() {
		return groupCreationDate;
	}
	public void setGroupCreationDate(String string) {
		this.groupCreationDate = string;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGrpOwner() {
		return grpOwner;
	}
	public void setGrpOwner(String grpOwner) {
		this.grpOwner = grpOwner;
	}
	public String getGrpMember() {
		return grpMember;
	}
	public void setGrpMember(String grpMember) {
		this.grpMember = grpMember;
	}

}
