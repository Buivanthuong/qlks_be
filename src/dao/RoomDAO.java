package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dto.Room;
import dto.TypeRoom;
import utill.ConnectionUtils;
import utill.DatabaseHelper;

public class RoomDAO {
	public static boolean selectRoom(int STATUS, String text) {
		
		ArrayList<Room> lsOb = new ArrayList<>();

		//connections
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");
			String where = "";
			if(STATUS == 3) {
				where = " and ROOM.NAME like '%"+text+"%'";
			}
			// Tạo đối tượng .		 
			String sql = ("Select ROOM.NAME, NOTE,ROOM.ID,TYPE_ROOM.NAME as TYPE_ROOM_NAME,TYPE_ROOM_ID,PRICE, (Select 2 from db_qlks.ORDER where ROOM.ID  = db_qlks.ORDER.ROOM_ID  and RECEIPT_ID IS NULL GROUP BY ROOM.ID  )  AS STATUS  From ROOM , TYPE_ROOM WHERE ROOM.STATUS = 1 and ROOM.TYPE_ROOM_ID = TYPE_ROOM.ID" + where ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);
			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				if(STATUS == 0 || STATUS ==3) {

					Room ob = new Room();
					ob.setPRICE(rs.getInt(6));
					ob.setTYPE_ROOM_ID(rs.getInt(5));
					ob.setTYPE(rs.getString(4));
					ob.setID(rs.getInt(3));
					ob.setNAME(rs.getString(1));
					ob.setNOTE(rs.getString(2));
					ob.setSTATUS_ROOM(rs.getInt(7));
					lsOb.add(ob);
					
				}
				if(STATUS == 1 && rs.getInt(7) == 0) {
					Room ob = new Room();
					ob.setPRICE(rs.getInt(6));
					ob.setTYPE_ROOM_ID(rs.getInt(5));
					ob.setTYPE(rs.getString(4));
					ob.setID(rs.getInt(3));
					ob.setNAME(rs.getString(1));
					ob.setNOTE(rs.getString(2));
					ob.setSTATUS_ROOM(rs.getInt(7));
					lsOb.add(ob);
				}
				if(STATUS == 2 && rs.getInt(7) == 2) {
					Room ob = new Room();
					ob.setPRICE(rs.getInt(6));
					ob.setTYPE_ROOM_ID(rs.getInt(5));
					ob.setTYPE(rs.getString(4));
					ob.setID(rs.getInt(3));
					ob.setNAME(rs.getString(1));
					ob.setNOTE(rs.getString(2));
					ob.setSTATUS_ROOM(rs.getInt(7));
					lsOb.add(ob);
				}
			}


			// Close connection
			connection.close();

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}



		return false;
	}
	
	public static boolean saveRoom(int id ,String name,String note, int id_type) {
		
			Connection connection;

			if(id == 0) {
				//connections
				try {
					connection = ConnectionUtils.getMyConnection();
					System.out.println("Get connection " + connection);
					System.out.println("Done!");

					// Tạo đối tượng .		 
					String sql = String.format("Insert into ROOM (NAME, NOTE,TYPE_ROOM_ID,STATUS) Values( '%s','%s','%s',1)",name,note,id_type + "" ) ;

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
				}
			}else {
				try {
					connection = ConnectionUtils.getMyConnection();
					System.out.println("Get connection " + connection);
					System.out.println("Done!");
					// Tạo đối tượng .		 
					String sql = String.format("UPDATE ROOM set NAME = '%s',NOTE ='%s',TYPE_ROOM_ID = '%s' WHERE ID = '%s'",name,note,id_type,id ) ;

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
				}
			}

		

		return false;
	}

	public static ArrayList<Room> selectRoom() {
		ArrayList<Room> lsOb = new ArrayList<>();
		//connections
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("Select ROOM.NAME, NOTE,ROOM.ID,TYPE_ROOM.NAME as TYPE_ROOM_NAME,TYPE_ROOM_ID,PRICE From ROOM , TYPE_ROOM WHERE ROOM.STATUS = 1 and ROOM.TYPE_ROOM_ID = TYPE_ROOM.ID" ) ;

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
		}



		return lsOb;
	}
	
	public static ArrayList<TypeRoom>  selectTypeRoom() {
		ArrayList<TypeRoom>lsOb = new ArrayList<>();
		//connections
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("Select NAME, PRICE,ID From TYPE_ROOM WHERE STATUS = 1" ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);
			lsOb = new ArrayList<>();

			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				TypeRoom ob = new TypeRoom();
				ob.setPRICE(rs.getInt(2));
				ob.setID(rs.getInt(3));
				ob.setNAME(rs.getString(1));
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
		}



		return lsOb;
	}
	
	public static boolean deleteRoom(int id) {

		//connections
		System.out.println("Get connection ... ");
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("UPDATE ROOM set STATUS = '3' WHERE ID ='%s'",id ) ;

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
		}



		return false;
	}

	public static boolean saveRoomType(int id ,String name, String price) {
		
			Connection connection;

			if(id == 0) {
				//connections
				try {
					connection = ConnectionUtils.getMyConnection();
					System.out.println("Get connection " + connection);
					System.out.println("Done!");

					// Tạo đối tượng .		 
					String sql = String.format("Insert into TYPE_ROOM (NAME, PRICE,STATUS) Values( '%s','%s',1)",name,price ) ;

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
				}
			}else {
				try {
					connection = ConnectionUtils.getMyConnection();
					System.out.println("Get connection " + connection);
					System.out.println("Done!");
					// Tạo đối tượng .		 
					String sql = String.format("UPDATE TYPE_ROOM set NAME = '%s',PRICE ='%s' WHERE ID = '%s'",name,price,id ) ;

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
				}
			}


		return false;
	}

	public static boolean deleteRoomType(int id) {
		//connections
		System.out.println("Get connection ... ");
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("UPDATE TYPE_ROOM set STATUS = '3' WHERE ID ='%s'",id ) ;

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
		}

		return false;
	}

	public static ArrayList<Room> selectRoom(int month) {
		ArrayList<Room>lsb2 = new ArrayList<>();
		//connections
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("SELECT db_qlks.ROOM.ID ,db_qlks.ROOM.NAME , SUM(db_qlks.ORDER.PRICE) as PRICE\n" + 
					"	FROM db_qlks.ROOM , db_qlks.ORDER" + 
					" Where " + 
					"	 db_qlks.ROOM.ID = db_qlks.ORDER.ROOM_ID" + 
					"    and db_qlks.ORDER.RECEIPT_ID is not null" + 
					"    and  MONTH(db_qlks.ORDER.DATE_ORDER) = %s" + 
					" group by db_qlks.ROOM.ID,db_qlks.ROOM.NAME" + 
					" " ,month) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);
			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				Room ob = new Room();
				ob.setPRICE(rs.getInt(3));
				ob.setID(rs.getInt(1));
				ob.setNAME(rs.getString(2));
				ob.setPRICE(rs.getInt(3));
				lsb2.add(ob);
			}

			// Close connection
			connection.close();

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}



		return lsb2;
	}
	
	public static ArrayList<TypeRoom> selectTyeRoom(int month) {
		ArrayList<TypeRoom> lsb = new ArrayList<>();
		//connections
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("SELECT db_qlks.TYPE_ROOM.ID ,db_qlks.TYPE_ROOM.NAME , SUM(db_qlks.ORDER.PRICE) as PRICE FROM db_qlks.TYPE_ROOM, db_qlks.ORDER, db_qlks.ROOM\n" + 
					"Where" + 
					"	db_qlks.ROOM.ID = db_qlks.ORDER.ROOM_ID" + 
					"    and" + 
					"    db_qlks.TYPE_ROOM.ID = db_qlks.ROOM.TYPE_ROOM_ID" +
					"    and MONTH(db_qlks.ORDER.DATE_ORDER) = %s"+
					" group by TYPE_ROOM.ID;", month) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);

			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				TypeRoom ob = new TypeRoom();
				ob.setPRICE(rs.getInt(3));
				ob.setID(rs.getInt(1));
				ob.setNAME(rs.getString(2));
				ob.setPRICE(rs.getInt(3));
				lsb.add(ob);
			}
			
			// Close connection
			connection.close();

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}



		return lsb;
	}

}
