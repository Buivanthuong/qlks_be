package utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

	// Connect MySQL.
	public static Connection getMySQLConnection() throws SQLException,
	ClassNotFoundException {
		String hostName = "127.0.0.1";

		String dbName = "qlksl";
		String userName = "root";
		String password = "10171961";

		return getMySQLConnection(hostName, dbName, userName, password);
	}

	public static Connection getMySQLConnection(String hostName, String dbName,
			String userName, String password) throws SQLException,
	ClassNotFoundException {
		// Create Class Driver DB MySQL
		Class.forName("com.mysql.jdbc.Driver");

		// URL Connection
		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

		Connection conn = DriverManager.getConnection(connectionURL, userName,
				password);
		return conn;
	}
	public static int installData(String sql,Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		// Thực thi câu lệnh.
	      // executeUpdate(String) sử dụng cho các loại lệnh Insert,Update,Delete.
	      int rowCount = statement.executeUpdate(sql);
	 
	      // In ra số dòng được trèn vào bởi câu lệnh trên.
	      System.out.println("Row Count affected = " + rowCount);
	      
	      return rowCount;
	}
	public static ResultSet selectData(String sql,Connection connection) throws SQLException {
		// Tạo đối tượng Statement.
	      Statement statement = connection.createStatement();
	 	 
	      // Thực thi câu lệnh SQL trả về đối tượng ResultSet.
	      ResultSet rs = statement.executeQuery(sql);
	      
		  return rs;

	}
}