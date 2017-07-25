package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Result;

public class SearchDB extends DBAO {

	public ArrayList<Result> searchSpecific(String table, String keyWord, String servletPath) {
		ArrayList<Result> list = new ArrayList<Result>();
		ArrayList<String> colList = getTableColumns(table);
		String stmt = "select * FROM ffl." + table + " where "+ table +"Title like ? or ";
		if(table.equals("activity")) {
			stmt = stmt + ""+ table +"Description like ?";
		}else {
			stmt = stmt + ""+ table +"Content like ?";
		}
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, "%"+keyWord+"%");
			ps.setString(2, "%"+keyWord+"%");
			System.out.println("Log searchSpecific(): " + ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Result r = new Result();
				String paramName = null;
				
				for(String colName:colList) {
					if (colName.contains(table+"Id")) {
						paramName = colName;
						r.setItemId(rs.getString(colName));
						System.out.println("Log searchSpecific(): r.setItemId() = " + rs.getString(colName));
					}else if (colName.contains(table+"Title")) {
						r.setTitle(rs.getString(colName));
						System.out.println("Log searchSpecific(): r.setTitle() = " + rs.getString(colName));
					}else if (colName.contains(table+"Content") || colName.contains("Description")) {
						String content = rs.getString(colName);
						if(content.contains("<hr>")) {
							content = content.substring(0, content.indexOf("<hr>"));
						}
						content = content.concat("...");
						r.setContent(content);
						System.out.println("Log searchSpecific(): r.setContent() = " + content);
					}else if (colName.contains("imgUrl")) {
						r.setImgUrl(rs.getString(colName));
						System.out.println("Log searchSpecific(): r.setImgUrl() = " + rs.getString(colName));
					}else if (colName.toLowerCase().contains("postdate")) {
						r.setCreatedOn(rs.getString(colName));
						System.out.println("Log searchSpecific(): r.setCreatedOn() = " + rs.getString(colName));
					}
				}
				
				r.setType(table);
				System.out.println("Log searchSpecific(): r.setUrl() = " + servletPath+"?" + paramName +"=" +r.getItemId());
				r.setUrl(servletPath+"?" + paramName +"=" +r.getItemId());
				System.out.println("=================================================================================");
				list.add(r);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Log searchSpecific(): list.size() " + list.size());
		return list;
	}

	public ArrayList<Result> searchAll(String keyWord) {
		ArrayList<Result> combineList = new ArrayList<Result>();
		String tables[] = {"post","activity"};
		String servletPaths[] = {"Post","ActFull"};
		for(int i = 0; i < tables.length; i++) {
			System.out.println("Log searchAll(): " + tables[i] + servletPaths[i]);
			combineList.addAll(searchSpecific(tables[i], keyWord, servletPaths[i]));
		}
		return combineList;
	}

	public ArrayList<String> getTableColumns(String table){
		String stmt = "SHOW COLUMNS FROM "+table;
		ArrayList<String> list = new ArrayList<String>();
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
				System.out.println("log getTableColumns(): colName = " + rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
}
