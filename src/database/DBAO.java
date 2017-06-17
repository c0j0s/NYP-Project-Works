package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAO {
	public Connection con;
	
	/**
	 * Default constructor
	 * init connection to database
	 */
	public DBAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://138.75.188.127:3306/ffl","root","password");
		} catch (Exception e) {
			try {
				throw new Exception("Log DBAO: fail to connect to database" + e.getMessage());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
