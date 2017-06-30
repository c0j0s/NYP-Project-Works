package database;
import com.mysql.jdbc.PreparedStatement;

import bean.RewardItem;
public class RewardItemDB extends DBAO{
	
	public RewardItemDB(){
		super();
	}
	public ArrayList<RewardItem> getRewardItem(String statement){
		ArrayList<RewardItem> activityList = new ArrayList<RewardItem>();
		try {
			if(statement == null){
				statement = "SELECT * FROM ffl.rewarditem ORDER BY rewardId DESC";
			}
			PreparedStatement ps;
			ps = con.prepareStatement(statement);
			
			System.out.println("log Redemption.java :" + ps);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				RewardItem act = new RewardItem();
				act.setrewardID(rs.getString("rewardID"));
				act.setrewardTitle(rs.getString("rewardTitle"));
				act.setrewardDescription(rs.getInt("rewardDescription"));
				act.setpoints(rs.getInt("points"));
				act.setrewardAvailability(rs.getInt("rewardAvailability"));
				act.setrewardQuantity(java.sql.Timestamp.valueOf(rs.getString("rewardQuantity")));
				act.setvalid(java.sql.Timestamp.valueOf(rs.getString("valid")));
				act.setitemCreateOn(java.sql.Timestamp.valueOf(rs.getString("itemCreateOn")));
				act.setImgUrl(rs.getString("imgUrl"));
				act.setValid(rs.getString("valid").charAt(0));
				System.out.println("record retrieve");
				activityList.add(act);
				System.out.println(act);		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activityList;
	}

	
}
