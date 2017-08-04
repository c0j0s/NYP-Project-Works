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
	public ArrayList<Activity> getActivity(String statement, int start){
		ArrayList<Activity> activityList = new ArrayList<Activity>();
		try {
			if(statement == null){
				statement = "SELECT * FROM ffl.activitylist where valid='Y' and status = 'Uploaded' ORDER BY activityPostDate desc limit "+start+",5;";
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
				act.setLikeCount(rs.getInt("likeCount"));
				act.setDislikeCount(rs.getInt("dislikeCount"));
				act.setCommentCount(rs.getString("postCount"));
				act.setAccountName(rs.getString("givenName"));
				act.setRankPoints(rs.getInt("likeCount")*rs.getInt("likeCount")-rs.getInt("dislikeCount"));	
				act.setOrganiserId(rs.getString("accountId"));
				act.setStatus(rs.getString("status"));
				
				activityList.add(act);
		
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activityList;
	}
	public ArrayList<Activity> getActivityDraft(String statement, int start,String userId){
		ArrayList<Activity> activityList = new ArrayList<Activity>();
		try {
			if(statement == null){
				statement = "SELECT * FROM ffl.activitylist where valid='Y' and status= 'Draft' and accountId = ? ORDER BY activityPostDate desc limit "+start+",5;";
			}
			PreparedStatement ps;
			ps = con.prepareStatement(statement);
			ps.setString(1, userId);

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
				act.setLikeCount(rs.getInt("likeCount"));
				act.setDislikeCount(rs.getInt("dislikeCount"));
				act.setCommentCount(rs.getString("postCount"));
				act.setAccountName(rs.getString("givenName"));
				act.setRankPoints(rs.getInt("likeCount")*rs.getInt("likeCount")-rs.getInt("dislikeCount"));	
				act.setOrganiserId(rs.getString("accountId"));
				act.setStatus(rs.getString("status"));
				
				activityList.add(act);
		
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activityList;
	}
	public String createActivity(Activity act){
		String stmt = "INSERT INTO "+ schema +".activity (`activityId`, `activityTitle`, `activityDescription`, `participantNo`, `activityPostDate`, `activityStartDate`, `activityEndDate`, `activityDays`,`activityTime`, `activityRegistrationEnd`, `activityFee`, `activityCategory`,`imgUrl`,`activityLocation`,`accountId`,'status') "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Draft')";
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

		try {
			PreparedStatement ps;
		
			ps=con.prepareStatement(stmt);
			ps.setString(5, act.getImgUrl());
			ps.setString(6, act.getActivityId());
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
		String stmt = "SELECT * FROM "+ schema +".activitylist WHERE status = 'Uploaded' and activityId = '"+ activityId +"'";
		return getActivity(stmt,0);
	}
	public String deleteActivity(String activityId){
		String stmt ="update "+ schema +".activity set valid='N' where activityId=?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, activityId);

			System.out.println(ps);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
	public String uploadActivity(String activityId){
		String stmt ="update "+ schema +".activity set status = 'Uploaded' where activityId=?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, activityId);

			System.out.println(ps);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
	public String restoreActivity(String activityId){
		String stmt ="update "+ schema +".activity set valid='Y' where activityId=?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, activityId);

			System.out.println(ps);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}
	public int getTotalActivityCount() {
		String stmt = "select count(*) from ffl.activityList where valid ='Y';";
		try {PreparedStatement ps = con.prepareStatement(stmt);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getInt(1);
		}
			
		}catch(Exception ex) {ex.printStackTrace();}
		// TODO Auto-generated method stub
		return 0;
	}
	public void editParNo(String activityId, int count) {
		String stmt = "select participantNo from ffl.activity where activityId = ? and valid='Y'";
		try {PreparedStatement ps =con.prepareStatement(stmt);
		ps.setString(1, activityId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int i =rs.getInt("participantNo");
			int v = i-count;
			String stmt2 ="update "+ schema +".activity set participantNo = ? where activityId=?";
			PreparedStatement ps2 = con.prepareStatement(stmt2);
			ps2.setInt(1, v);
			ps2.setString(2, activityId);
			ps2.executeUpdate();
			
		}
			
		}catch(Exception ex) {ex.printStackTrace();}
		
	}
}

