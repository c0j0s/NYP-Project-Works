package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAO {
	protected Connection con;
	final private String schurl = "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12181123";
	final private String schpasswd = "mysql";
	final private String url = "jdbc:mysql://138.75.188.127:3306/ffl";
	final private String passwd = "password";
	
	/**
	 * Default constructor
	 * init connection to database
	 */
	public DBAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//con = DriverManager.getConnection(schurl,"root",schpasswd);
			con = DriverManager.getConnection(url,"root",passwd);
		} catch (Exception e) {
			try {
				System.out.println("Log DBAO: fail to connect to database" + e.getMessage());
			} catch (Exception ex) {
				System.out.println("Log DBAO: fail to connect to database" + e.getMessage());
			}
		}
	}
}
