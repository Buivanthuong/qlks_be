package bus;

import java.util.ArrayList;

import dao.OrderDAO;
import dto.Customer;
import dto.Order;
import dto.Room;

public class OrderBUS {
	public static ArrayList<Room> selectRoom() {
		ArrayList<Room>  lsOb_r = OrderDAO.selectRoom();
		return lsOb_r;
	}
	public static boolean saveOrderRoom(int room_id,int price ,String date, ArrayList<Customer> ls,double total_price) {
		return OrderDAO.saveOrderRoom( room_id, price , date,  ls, total_price);
	}
	public static ArrayList<Order> selectOrderRoom() {
		return OrderDAO.selectOrderRoom();
	}
	public static boolean saveReceiptOrder(int customer_id,int price ,String address, ArrayList<Order> ls) {
		return OrderDAO.saveReceiptOrder(customer_id, price, address, ls);
	}
}
