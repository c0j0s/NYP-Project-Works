package common;
import database.Point;

public class pointManagement {
	public int pointsCalc(String userId, int pointAdded) {
		Point p = new Point();
		int currPoint = p.getPoints(userId);
		int value2 = pointAdded;
		int total=currPoint+value2;
		p.SetPoints(total,userId);
		return total;
		
	}

}
