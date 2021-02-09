package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import bean.Result;

public class AdminDB extends DBAO {
	
	public AdminDB() {
		super();
	}
	
	public String reportItem(String itemId,String accountId, String type, String reasons) throws MySQLIntegrityConstraintViolationException {
		String stmt = "Insert into ffl.reported (itemId,accountId,type,reasons) values(?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, itemId);
			ps.setString(2, accountId);
			ps.setString(3, type);
			ps.setString(4, reasons);
			int status = ps.executeUpdate();
			System.out.println(ps);
			if(status != 0) {
				System.out.println("log reportItem(): (success) " + ps);
				return "Successfully reported!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail to report " + type;
		}
		return "Servive unavailable";
	}
	
	public ArrayList<Result> getReportList(String get) {
		ArrayList<Result> list = new ArrayList<Result>();
		String stmt = "";
		if (get.equals("reported")) {
			stmt = "Select * from ffl.reportlist";
		} else if (get.equals("post")) {
			stmt = "Select * from ffl.reportlist where type = 'post' ";
		} else if (get.equals("comment")){
			stmt = "Select * from ffl.reportlist where type = 'comment' ";
		}
		
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Result r = new Result();
				r.setItemId(rs.getString("itemId"));
				r.setTitle(rs.getString("title"));
				r.setImgUrl(rs.getString("itemImgUrl"));
				Map<String,String> m = new HashMap<String,String>();
				r.setType(rs.getString("type"));
				m.put("status", rs.getString("status"));
				m.put("reportCount", rs.getString("reportCount"));
				m.put("itemCreatedOn", rs.getString("itemCreatedOn"));
				m.put("reasons", rs.getString("reasons"));
				r.setMetadata(m);
				list.add(r);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void updateReportStatus(String action, String itemId) {
		String stmt = "update ffl.reported set status = ? where itemId = ?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, action);
			ps.setString(2, itemId);
			
			int status = ps.executeUpdate();
			if (status != 0) {
				System.out.println("log update report status:" + ps);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
