package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import bean.Activity;

public class Point extends DBAO{
	int Points;
	public Point() {
		super();
	}

	public int getPoints(String userId) {
		try {
			String ss = "SELECT points FROM ffl.user where accountId = ?;";
			PreparedStatement ps =con.prepareStatement(ss);
			ps.setString(1,userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Points = rs.getInt("points");}

		}catch(Exception ex) {ex.printStackTrace();}
		return Points;
	}
	public int SetPoints(int currPoint,String userId) {
		try { String ss = "update ffl.user set points = ? where accountId = ?;";
		PreparedStatement ps = con.prepareStatement(ss);
		ps.setInt(1,currPoint);
		ps.setString(2,userId );
		ps.executeUpdate();
		System.out.println(ps);
		System.out.println(currPoint);}
		catch(Exception ex) {
			ex.printStackTrace();
		}


		return currPoint;}


	public int pointsCalc(String userId, int pointAdded) {

		int currPoint = getPoints(userId);
		int value2 = pointAdded;
		int total=currPoint+value2;
		SetPoints(total,userId);
		return total;

	}
	public ArrayList<Activity> getRank() {
		ArrayList<Activity> rankList = new ArrayList<Activity>();
		try {
			String ss = "SELECT * FROM ffl.ranklist where valid ='Y' and status = 'Uploaded' order by actRank desc;"; 
			PreparedStatement ps =con.prepareStatement(ss);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Activity act= new Activity();
				act.setActivityId(rs.getString("activityId"));
				act.setActivityTitle(rs.getString("activityTitle"));
				act.setRankPoints(rs.getInt("actRank"));
				rankList.add(act);
				System.out.println(rs.getString("activityId"));}

		}catch(Exception ex) {ex.printStackTrace();}
		return rankList;
	}
	public ArrayList<Activity> getRankLow() {
		ArrayList<Activity> rankList = new ArrayList<Activity>();
		try {
			String ss = "SELECT * FROM ffl.ranklist where actRank <-5 order by actRank desc;"; 
			PreparedStatement ps =con.prepareStatement(ss);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Activity act= new Activity();
				act.setActivityId(rs.getString("activityId"));
				act.setActivityTitle(rs.getString("activityTitle"));
				act.setValid(rs.getString("valid").charAt(0));
				act.setRankPoints(rs.getInt("actRank"));
				rankList.add(act);
				System.out.println(rs.getString("activityId"));}

		}catch(Exception ex) {ex.printStackTrace();}
		return rankList;
	}
}






