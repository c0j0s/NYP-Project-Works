package database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.RewardItem;
public class RewardItemDB extends DBAO{
	
	public RewardItemDB(){
		super();
	}
	public ArrayList<RewardItem> getRewardItem(String statement){
		ArrayList<RewardItem> rewardList = new ArrayList<RewardItem>();
		try {
			if(statement == null){
				statement = "SELECT * FROM "+ schema +".rewarditem ORDER BY rewardId ";
			}
			PreparedStatement ps;
			ps = con.prepareStatement(statement);
			
			System.out.println("log Redemption.java :" + ps);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				RewardItem rew = new RewardItem();
				rew.setRewardId(rs.getString("rewardId"));
				rew.setRewardTitle(rs.getString("rewardTitle"));
				rew.setRewardDescription(rs.getString("rewardDescription"));
				rew.setPoints(rs.getInt("points"));
				rew.setRewardQuantity(rs.getInt("rewardQuantity"));
				rew.setValid(rs.getString("valid").charAt(0));
				rew.setItemCreatedOn(rs.getString("itemCreatedOn"));
				rew.setImgUrl(rs.getString("imgUrl"));
				System.out.println("record retrieve");
				rewardList.add(rew);
				System.out.println(rew);		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rewardList;
	}
	public String createRewardItem(RewardItem rew){
		String stmt = "INSERT INTO "+ schema +".rewarditem (`rewardId`,`rewardTitle`, `rewardDescription`, `points`,`rewardQuantity`, `imgUrl`, `valid`) "
				+ "VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			rew.setRewardId(common.UID.genRewardId());
			System.out.println(rew.getRewardId());
			ps.setString(1, rew.getRewardId());
			ps.setString(2, rew.getRewardTitle());
			ps.setString(3, rew.getRewardDescription());
		    ps.setInt(4, rew.getPoints());
			ps.setInt(5, rew.getRewardQuantity());
			ps.setString(6, rew.getImgUrl());
			ps.setString(7, Character.toString(rew.getValid()));
			
			
			System.out.println(rew);
			int status = ps.executeUpdate();
			
			if(status != 0){
				System.out.println("Log CreateReward() :" + rew);
				return rew.getRewardId();
			}else{
				return "fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
	public String createReward(RewardItem rew){
		String stmt = "INSERT INTO "+ schema +".rewarditem (`rewardId`,`rewardTitle`, `rewardDescription`, `points`,`rewardQuantity`, `imgUrl`, `valid`) "
		+  "VALUES (?,?,?,?,?,?,'Y')";
		try
		{
			PreparedStatement ps = con.prepareStatement(stmt);
			rew.setRewardId(common.UID.genRewardId());
			System.out.println(rew.getRewardId());
			ps.setString(1, rew.getRewardId());
			ps.setString(2, rew.getRewardTitle());
			ps.setString(3, rew.getRewardDescription());
		    ps.setInt(4, rew.getPoints());
			ps.setInt(5, rew.getRewardQuantity());
			ps.setString(6, rew.getImgUrl());
			
			int status = ps.executeUpdate();
			
			if(status!=0)
			{
				System.out.println("Recorded Added");
				return rew.getRewardId();
			}
		}catch (Exception ex)
		{
			
			
		}
		return "fail";
	}
	
	public String claimReward(String RewardItemId, String AccountId, String Points){
		String stmt = "INSERT INTO "+ schema +".rewarditem (`rewardItemId`,`AccountId`, `points`) "
		+  "VALUES (?,?,?)";
		try
		{
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, RewardItemId);
		    ps.setString(2, AccountId);
			ps.setString(3, Points);
			
			int status = ps.executeUpdate();
			
			if(status!=0)
			{
				System.out.println("Recorded Added");
				return "";
			}
		}catch (Exception ex)
		{
			
			
		}
		return "fail";
	}
	public ArrayList<RewardItem> getRewardItemById(String rewardId){
		String stmt = "SELECT * FROM "+ schema +".rewarditem WHERE rewardId = '"+ rewardId +"'";
		return getRewardItem(stmt);
	}
	
}


	

