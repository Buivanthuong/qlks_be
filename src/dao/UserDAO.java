/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.Config;
import dto.User;
import utill.ConnectionUtils;
import utill.DatabaseHelper;

public class UserDAO {

	public static User LoginUser(String user_name, String password){
		//connections
		System.out.println("Get connection ... ");
		Connection connection;
		User ob = new User();
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			String sql = String.format("Select ID, FULL_NAME from USER Where USER_NAME = '%s'and PASSWORD = '%s' LIMIT 1;",user_name,password ) ;

			ResultSet rs = DatabaseHelper.selectData(sql, connection);
			int _Id  = -1;
			String full_name = "";
			while (rs.next()) {
				_Id = rs.getInt(1);
				full_name = rs.getString(2);
			}
			// Close connection
			connection.close();

			if(_Id >=0) {
				ob.setID(_Id);
				ob.setUSER_NAME(user_name);
				ob.setFULL_NAME(full_name);
				return ob;
			}else {
				return null;
			}
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}

		return ob;
	}
	public static int InsertUser(String name,String full_name, String password){
		int _ID = -1;
		//connections
		System.out.println("Get connection ... ");
		Connection connection;
		//connections
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");
	 
			String sql = String.format("Insert into USER (FULL_NAME, USER_NAME,PASSWORD,STATUS) Values( '%s','%s','%s',1)",full_name,name,password + "" ) ;
			String sql2 = String.format("SELECT * FROM USER WHERE USER_NAME = '%s'",name ) ;

			ResultSet rss = DatabaseHelper.selectData(sql2, connection);
			while(rss.next()) {
				return -1;
			}
			int rs = DatabaseHelper.installData(sql, connection);
			if(rs > 0) {
				return rs;
			}
			// Close connection
			connection.close();

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return _ID;
	}
	public static boolean UpdateUser(int id ,String name,String full_name, String password){
		//connections
		System.out.println("Get connection ... ");
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");
			String sql = String.format("UPDATE USER set FULL_NAME = '%s',USER_NAME ='%s',PASSWORD = '%s' WHERE ID = '%s'",full_name,name,password,id ) ;

			int rs = DatabaseHelper.installData(sql, connection);

			if(rs > 0) {
				return true;
			}
			// Close connection
			connection.close();

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		
		
		return false;
	}
	public static boolean UpdateConfigUser(Config ob){
		//connections
		System.out.println("Get connection ... ");
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");
			String sql = String.format("UPDATE CONFIG set NUM_TYPE_ROOM = '%s',NUM_TYPE_CUSTOMER = '%s',NUM_CUSTOMER_IN_ROOM = '%s',NUM_SURCHARGE_CUSTOMER = '%s',SURCHARGE_CUSTOMER = '%s',NUM_SURCHARGE_CUSTOMER_TYPE = '%s',HOTEL_NAME = '%s',ADDRESS = '%s'",
					ob.getNUM_TYPE_ROOM(),
					ob.getNUM_TYPE_CUSTOMER(),
					ob.getNUM_CUSTOMER_IN_ROOM(),
					ob.getNUM_SURCHARGE_CUSTOMER(),
					ob.getSURCHARGE_CUSTOMER(),
					ob.getNUM_SURCHARGE_CUSTOMER_TYPE(),
					ob.getHOTEL_NAME(),
					ob.getADDRESS()) ;
			int rs = DatabaseHelper.installData(sql, connection);

			if(rs > 0) {
				return true;
			}
			// Close connection
			connection.close();

			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		
		
		return false;
	}
	public static Config selectConfig() {
		//connections
		Config cf = null ;
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("Select HOTEL_NAME, ADDRESS,CONFIG.NUM_TYPE_CUSTOMER ,(Select COUNT(TYPE_CUSTOMER.ID) From TYPE_CUSTOMER WHERE STATUS = 1 ) as NUM_CUSTOMER,CONFIG.NUM_TYPE_ROOM ,(Select COUNT(TYPE_ROOM.ID) From TYPE_ROOM WHERE STATUS = 1 ) as NUM_ROOM From qlks_db.CONFIG" ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);
			cf = new Config();
			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				cf.setADDRESS(rs.getString(2));
				cf.setHOTEL_NAME(rs.getString(1));
				cf.setNUM_TYPE_CUSTOMER(rs.getInt(3));
				cf.setNUM_OF_TYPE_CUSTOMER(rs.getInt(4));
				cf.setNUM_TYPE_ROOM(rs.getInt(3));
				cf.setNUM_OF_TYPE_ROOM(rs.getInt(4));
			}
			// Close connection
			connection.close();
			return cf;
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return null;

	}
	public static boolean selectIsInstall() {

		//connections
		System.out.println("Get connection ... ");
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("Select CONFIG.NUM_TYPE_ROOM ,(Select COUNT(TYPE_ROOM.ID) From TYPE_ROOM WHERE STATUS = 1 ) as NUM_ROOM From CONFIG" ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);
			int NUM_TYPE_ROOM  = 0;
			int NUM_ROOM = 0;
			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				NUM_TYPE_ROOM = rs.getInt(1);
				NUM_ROOM = rs.getInt(2);

			}
			if(NUM_TYPE_ROOM > NUM_ROOM) {
				return true;
			}
			// Close connection
			connection.close();

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}



		return false;
	}
	public static ArrayList<User>  selectUser() {
		ArrayList<User> lsOb = new ArrayList<>();
		//connections
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("Select ID , USER_NAME,FULL_NAME From USER WHERE STATUS = 1" ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);
			

			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				User ob = new User();
				ob.setID(rs.getInt(1));
				ob.setFULL_NAME(rs.getString(3));
				ob.setUSER_NAME(rs.getString(2));

				lsOb.add(ob);
			}

			// Close connection
			connection.close();

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}

		return lsOb;
	}
	public static boolean deleteUser(int id) {

		//connections
		System.out.println("Get connection ... ");
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("UPDATE USER set STATUS = '3' WHERE ID ='%s'",id ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			int rs = DatabaseHelper.installData(sql, connection);

			if(rs > 0) {
				return true;
			}
			// Close connection
			connection.close();

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}

		return false;
	}

	
}
