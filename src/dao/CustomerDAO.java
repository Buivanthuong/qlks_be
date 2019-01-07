package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Customer;
import dto.TypeCustomer;
import utill.ConnectionUtils;
import utill.DatabaseHelper;

public class CustomerDAO {
	public static ArrayList<Customer> selectCustomer() {
		//connections
		Connection connection;
		ArrayList<Customer>lsOb_c = new ArrayList<>();
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("Select CUSTOMER.NAME, PASSPORT,ADDRESS ,CUSTOMER.ID,TYPE_CUSTOMER.NAME as TYPE_CUSTOMER_NAME,TYPE_CUSTOMER_ID,SURCHARGE From CUSTOMER , TYPE_CUSTOMER WHERE CUSTOMER.STATUS = 1 and CUSTOMER.TYPE_CUSTOMER_ID = TYPE_CUSTOMER.ID" ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);
			

			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				Customer ob = new Customer();
				ob.setSURCHARGE(rs.getInt(7));
				ob.setTYPE_CUSTOMER_ID(rs.getInt(6));
				ob.setTYPE(rs.getString(5));
				ob.setID(rs.getInt(4));
				ob.setNAME(rs.getString(1));
				ob.setPASSPORT(rs.getString(2));
				ob.setADDRESS(rs.getString(3));

				lsOb_c.add(ob);

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



		return lsOb_c;
	}
	public static boolean saveCustomer(int id ,String name,String passport,String address, int id_type) {
		
			Connection connection;

			if(id == 0) {
				//connections
				try {
					connection = ConnectionUtils.getMyConnection();
					System.out.println("Get connection " + connection);
					System.out.println("Done!");

					// Tạo đối tượng .		 
					String sql = String.format("Insert into CUSTOMER (NAME, PASSPORT,ADDRESS,TYPE_CUSTOMER_ID,STATUS) Values( '%s','%s','%s','%s',1)",name,passport,address,id_type + "" ) ;

					// Thực thi câu lệnh SQL trả  đối tượng ResultSet.
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
			}else {
				try {
					connection = ConnectionUtils.getMyConnection();
					System.out.println("Get connection " + connection);
					System.out.println("Done!");
					// Tạo đối tượng .		 
					String sql = String.format("UPDATE CUSTOMER set NAME = '%s',PASSPORT ='%s',ADDRESS ='%s',TYPE_CUSTOMER_ID = '%s' WHERE ID = '%s'",name,passport,address,id_type,id ) ;

					// Thực thi câu lệnh SQL trả đối tượng ResultSet.
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
			}

		return false;
	}
	public static ArrayList<TypeCustomer> selectTypeCustomer() {
		
		//connections
		Connection connection;
		ArrayList<TypeCustomer>lsOb = new ArrayList<>();
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("Select NAME, SURCHARGE,ID From TYPE_CUSTOMER WHERE STATUS = 1" ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);

			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				TypeCustomer ob = new TypeCustomer();
				ob.setSURCHARGE(rs.getDouble(2));
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
		}catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}

		return lsOb;
	}
	public static boolean deleteCustomer(int id) {

		//connections
		System.out.println("Get connection ... ");
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("UPDATE CUSTOMER set STATUS = '3' WHERE ID ='%s'",id ) ;

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

	public static boolean saveCustomerType(int id ,String name, String surcharge) {
		
			Connection connection;

			if(id == 0) {
				//connections
				try {
					connection = ConnectionUtils.getMyConnection();
					System.out.println("Get connection " + connection);
					System.out.println("Done!");

					// Tạo đối tượng .		 
					String sql = String.format("Insert into TYPE_CUSTOMER (NAME, SURCHARGE,STATUS) Values( '%s','%s',1)",name,surcharge ) ;

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
			}else {
				try {
					connection = ConnectionUtils.getMyConnection();
					System.out.println("Get connection " + connection);
					System.out.println("Done!");
					// Tạo đối tượng .		 
					String sql = String.format("UPDATE TYPE_CUSTOMER set NAME = '%s',SURCHARGE ='%s' WHERE ID = '%s'",name,surcharge,id ) ;

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
			}

		

		return false;
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
			String sql = String.format("Select CONFIG.NUM_TYPE_CUSTOMER ,(Select COUNT(TYPE_CUSTOMER.ID) From TYPE_CUSTOMER WHERE STATUS = 1 ) as NUM_CUSTOMER From CONFIG" ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);
			int NUM_TYPE_CUSTOMER  = 0;
			int NUM_CUSTOMER = 0;
			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				NUM_TYPE_CUSTOMER = rs.getInt(1);
				NUM_CUSTOMER = rs.getInt(2);

			}
			if(NUM_TYPE_CUSTOMER > NUM_CUSTOMER) {
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
	public static boolean deleteCustomerType(int id) {

		//connections
		System.out.println("Get connection ... ");
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("UPDATE TYPE_CUSTOMER set STATUS = '3' WHERE ID ='%s'",id ) ;

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
