package mvc.librarySystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	public Connection getConnection() throws SQLException {
		//location of db, username , password
		try {
		Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/library_system","root","csir@123");
		return con;
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}

}

