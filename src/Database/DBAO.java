package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAO {
	public Connection con;
	
	public DBAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://138.75.188.127:3306/ffl","root","password");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				throw new Exception("Log DBAO: fail to connect to database" + e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println("Log DBAO: fail to connect to database");
		}
	}
}
