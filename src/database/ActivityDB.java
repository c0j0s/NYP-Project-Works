package database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.Activity;
public class ActivityDB extends DBAO{
	
	public ActivityDB(){
		super();
	}
	public ArrayList<Activity> getActivity(String statement){
		ArrayList<Activity> activityList = new ArrayList<Activity>();
		try {
			if(statement == null){
				statement = "SELECT * FROM "+ schema +".activity ORDER BY activityId DESC";
			}
			PreparedStatement ps;
			ps = con.prepareStatement(statement);
			
			System.out.println("log Activity.java :" + ps);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Activity act = new Activity();
				act.setActivityId(rs.getString("activityId"));
				act.setActivityTitle(rs.getString("activityTitle"));
				act.setActivityDescription(rs.getString("activityDescription"));
				act.setActivityLikes(rs.getInt("activityLikes"));
				act.setActivityDislikes(rs.getInt("activityDislikes"));
				act.setParticipantNo(rs.getInt("participantNo"));
				act.setActivityPostDate(java.sql.Timestamp.valueOf(rs.getString("activityPostDate")));
				act.setActivityStartDate(java.sql.Timestamp.valueOf(rs.getString("activityStartDate")));
				act.setActivityEndDate(java.sql.Timestamp.valueOf(rs.getString("activityEndDate")));
				act.setActivityRegistrationPeriod(java.sql.Timestamp.valueOf(rs.getString("activityRegistrationPeriod")));
				act.setActivityFee(rs.getDouble("activityFee"));
				act.setActivityCategory(rs.getString("activityCategory"));
				act.setActivityLocation(rs.getString("activityLocation"));
				act.setImgUrl(rs.getString("imgUrl"));
				act.setValid(rs.getString("valid").charAt(0));
				act.setActivityDay(rs.getString("activityDays"));
				act.setOrganiserId(rs.getString("accountId"));
				System.out.println("record retrieve");
				activityList.add(act);
				System.out.println(act);		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activityList;
	}

	
}
