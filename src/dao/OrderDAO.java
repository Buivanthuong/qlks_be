package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Config;
import dto.Customer;
import dto.Order;
import dto.Room;
import utill.ConnectionUtils;
import utill.DatabaseHelper;

public class OrderDAO {
	public static ArrayList<Room> selectRoom() {
		//connections
		Connection connection;
		ArrayList<Room> lsOb_r = new ArrayList<>();
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("Select ROOM.NAME, NOTE,ROOM.ID,TYPE_ROOM.NAME as TYPE_ROOM_NAME,TYPE_ROOM_ID,PRICE From ROOM , TYPE_ROOM WHERE ROOM.STATUS = 1 and ROOM.TYPE_ROOM_ID = TYPE_ROOM.ID AND ROOM.ID NOT IN (SELECT ROOM_ID FROM qlks_db.ORDER WHERE RECEIPT_ID IS NULL) " ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);
		
			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				Room ob = new Room();
				ob.setPRICE(rs.getInt(6));
				ob.setTYPE_ROOM_ID(rs.getInt(5));
				ob.setTYPE(rs.getString(4));
				ob.setID(rs.getInt(3));
				ob.setNAME(rs.getString(1));
				ob.setNOTE(rs.getString(2));

				lsOb_r.add(ob);

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
		return lsOb_r;
	}

	public static boolean saveOrderRoom(int room_id,int price ,String date, ArrayList<Customer> ls,double total_price) {
		
			Connection connection;
			
			//connections
			try {
				connection = ConnectionUtils.getMyConnection();
				System.out.println("Get connection " + connection);
				System.out.println("Done!");

				// Tạo đối tượng .		 
				String sql = String.format("Insert into qlks_db.ORDER (DATE_ORDER,ROOM_ID,PRICE,STATUS,USER_ID) Values( '%s','%s','%s',1,1)",date,room_id,total_price ) ;
				// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
				 DatabaseHelper.installData(sql, connection);

				int order_id = 0;
				// Tạo đối tượng .		 
				String sql1 = String.format("Select MAX(ID) From qlks_db.ORDER" ) ;
				// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
				ResultSet rss = DatabaseHelper.selectData(sql1, connection);
				// Duyệt trên kết quả trả v�?.
				while (rss.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
					order_id = rss.getInt(1);
				}
				for (Customer customer : ls) {
					String sql2 = String.format("Insert into CUSTOMER_IN_ORDER (ORDER_ID,CUSTOMER_ID) Values( '%s','%s')",order_id,customer.getID() ) ;
					DatabaseHelper.installData(sql2, connection);

				}

				// Close connection
				connection.close();
				return true;

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
		Connection connection;
		Config config = new Config();
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Done!");


			// Tạo đối tượng .		 
			String sql = String.format("Select NUM_TYPE_ROOM,NUM_TYPE_CUSTOMER,NUM_CUSTOMER_IN_ROOM,NUM_SURCHARGE_CUSTOMER,SURCHARGE_CUSTOMER,NUM_SURCHARGE_CUSTOMER_TYPE,HOTEL_NAME,ADDRESS From qlks_db.CONFIG" ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);

			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				config.setNUM_TYPE_ROOM((rs.getInt(1)));
				config.setNUM_TYPE_CUSTOMER(rs.getInt(2));
				config.setNUM_CUSTOMER_IN_ROOM(rs.getInt(3));
				config.setNUM_SURCHARGE_CUSTOMER(rs.getInt(4));
				config.setSURCHARGE_CUSTOMER(rs.getFloat(5));
				config.setNUM_SURCHARGE_CUSTOMER_TYPE(rs.getInt(6));

				config.setHOTEL_NAME(rs.getString(7));
				config.setADDRESS(rs.getString(8));
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

		return config;
	}

	public static ArrayList<Order> selectOrderRoom() {
		//connections
		ArrayList<Order> lsOb_o= new ArrayList<>();
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("SELECT qlks_db.ORDER.ID,ROOM.NAME,qlks_db.ORDER.PRICE,DATEDIFF(NOW(), qlks_db.ORDER.DATE_ORDER) AS NUM_DATE FROM qlks_db.ORDER, qlks_db.ROOM Where qlks_db.ORDER.ROOM_ID = qlks_db.ROOM.ID and qlks_db.ORDER.RECEIPT_ID is null" ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);

			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				Order ob = new Order();
				ob.setAMOUNT((rs.getInt(4) + 1)  * rs.getInt(3));
				ob.setID(rs.getInt(1));
				ob.setROOM_NAME(rs.getString(2));
				ob.setNUM_DATE(rs.getInt(4) + 1);
				ob.setPRICE(rs.getInt(3));

				lsOb_o.add(ob);

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

		return lsOb_o;
	}
	
	public static boolean saveReceiptOrder(int customer_id,int price ,String address, ArrayList<Order> ls) {
		
			Connection connection;

			//connections
			try {
				connection = ConnectionUtils.getMyConnection();
				System.out.println("Get connection " + connection);
				System.out.println("Done!");

				// Tạo đối tượng .		 
				String sql = String.format("Insert into qlks_db.RECEIPT (CUSTOMER_ID,ADDRESS,PRICE,STATUS,USER_ID) Values( '%s','%s','%s',1,1)",customer_id,address,price ) ;
				// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
				DatabaseHelper.installData(sql, connection);

				int receipt_id = 0;
				// Tạo đối tượng .		 
				String sql1 = String.format("Select MAX(ID) From qlks_db.RECEIPT" ) ;
				// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
				ResultSet rss = DatabaseHelper.selectData(sql1, connection);
				// Duyệt trên kết quả trả v�?.
				while (rss.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
					receipt_id = rss.getInt(1);
				}
				for (Order order : ls) {
					String sql2 = String.format("UPDATE qlks_db.ORDER SET RECEIPT_ID='%s' where ID = '%s'",receipt_id,order.getID() ) ;
					DatabaseHelper.installData(sql2, connection);

				}
				// Close connection
				connection.close();
				
				return true;

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
