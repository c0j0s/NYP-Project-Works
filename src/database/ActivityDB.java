package database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import bean.Activity;
import bean.Post;

public class ActivityDB extends DBAO{

	public ActivityDB(){
		super();
	}
	public ArrayList<Activity> getActivity(String statement){
		ArrayList<Activity> activityList = new ArrayList<Activity>();
		try {
			if(statement == null){
				statement = "SELECT * FROM "+ schema +".activitylist ORDER BY activityId ";
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
				act.setParticipantNo(rs.getInt("participantNo"));
				act.setActivityPostDate(rs.getString("activityPostDate"));
				act.setActivityStartDate(rs.getString("activityStartDate"));
				act.setActivityEndDate(rs.getString("activityEndDate"));
				act.setActivityRegistrationEnd(rs.getString("activityRegistrationEnd"));
				act.setActivityFee(rs.getDouble("activityFee"));
				act.setActivityCategory(rs.getString("activityCategory"));
				act.setActivityLocation(rs.getString("activityLocation"));
				act.setImgUrl(rs.getString("imgurl"));
				act.setActivityTime(rs.getString("activityTime"));
				act.setValid(rs.getString("valid").charAt(0));
				act.setActivityDay(rs.getString("activityDays"));

				act.setAccountId(rs.getString("accountId"));
				act.setLikeCount(rs.getInt("likeCount"));
				act.setDislikeCount(rs.getInt("dislikeCount"));
				act.setCommentCount(rs.getString("postCount"));

				act.setAccountName(rs.getString("givenName"));
				act.setAccountId(rs.getString("accountId")); 
				act.setLikeCount(rs.getInt("likeCount"));
				act.setDislikeCount(rs.getInt("dislikeCount"));
				//act.setCommentCount(rs.getString("commentCount")); you dun have this in your view yet

				System.out.println(rs.getString("imgUrl"));
				act.setImgUrl(rs.getString("imgUrl"));
				act.setOrganiserId(rs.getString("accountId"));
				System.out.println("record retrieve");
				activityList.add(act);
				System.out.println(act);		
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activityList;
	}
	public String createActivity(Activity act){
		String stmt = "INSERT INTO "+ schema +".activity (`activityId`, `activityTitle`, `activityDescription`, `participantNo`, `activityPostDate`, `activityStartDate`, `activityEndDate`, `activityDays`,`activityTime`, `activityRegistrationEnd`, `activityFee`, `activityCategory`,`imgUrl`,`activityLocation`,`accountId`) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			act.setActivityId(common.UID.genActivityId());
			System.out.println(act.getActivityId());
			ps.setString(1, act.getActivityId());
			ps.setString(2, act.getActivityTitle());
			ps.setString(3, act.getActivityDescription());
			ps.setInt(4, act.getParticipantNo());
			ps.setString(5, act.getActivityPostDate());
			ps.setString(6, act.getActivityStartDate());
			ps.setString(7, act.getActivityEndDate());
			ps.setString(8, act.getActivityDay());
			ps.setString(9, act.getActivityTime());
			ps.setString(10, act.getActivityRegistrationEnd());
			ps.setDouble(11, act.getActivityFee());
			ps.setString(12, act.getActivityCategory());
			ps.setString(13, act.getImgUrl());
			ps.setString(14, act.getActivityLocation());
			ps.setString(15, act.getAccountId());

			System.out.println(ps);
			int status = ps.executeUpdate();
			
			if(status != 0){
				System.out.println("Log createActivity() :" + ps);
				return act.getActivityId();
			}else{
				return "fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
	public String editActivity(Activity act){
		String stmt ="update "+ schema +".activity set activityTitle =?,activityDescription = ?,participantNo = ? ,activityCategory = ?,imgUrl= ? where activityId =? ";
		String stmt2 ="update "+ schema +".activity set activityTitle =?,activityDescription = ?,participantNo = ?,activityCategory = ? where activityId =? ";
		try {
			PreparedStatement ps;
			if(!act.getImgUrl().equals("")) {
			ps=con.prepareStatement(stmt);
			ps.setString(5, act.getImgUrl());
			ps.setString(6, act.getActivityId());
			} 
			else {
				ps=con.prepareStatement(stmt2);
				ps.setString(5, act.getActivityId());
			}

			ps.setString(1, act.getActivityTitle());
			ps.setString(2, act.getActivityDescription());
			ps.setInt(3, act.getParticipantNo());
			ps.setString(4, act.getActivityCategory());
			

			System.out.println(ps);
			int status = ps.executeUpdate();
			
			if(status != 0){
				System.out.println("Log createActivity() :" + ps);
				return act.getActivityId();
			}else{
				return "fail";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
	public ArrayList<Activity> getActivityById(String activityId){
		String stmt = "SELECT * FROM "+ schema +".activitylist WHERE activityId = '"+ activityId +"'";
		return getActivity(stmt);
	}
	public String deleteActivity(Activity act){
		String stmt ="delete from "+ schema +".activity where activityId=?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, act.getActivityId());

			System.out.println(ps);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
}

