package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
			Points = rs.getInt("points");

		}catch(Exception ex) {ex.printStackTrace();}
		return Points;
	}
	public int SetPoints(int currPoint,String userId) {
		try { String ss = "update ffl.user set points = ? where accountId = ?;";
		PreparedStatement ps = con.prepareStatement(ss);
		ps.setInt(1,currPoint);
		ps.setString(2,userId );}
		catch(Exception ex) {ex.printStackTrace();}
		return currPoint;}
	
	
		public int pointsCalc(String userId, int pointAdded) {
			
			int currPoint = getPoints(userId);
			int value2 = pointAdded;
			int total=currPoint+value2;
			SetPoints(total,userId);
			return total;
			
		}
		}
