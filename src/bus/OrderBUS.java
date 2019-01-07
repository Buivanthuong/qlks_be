package bus;

import java.util.ArrayList;

import dao.OrderDAO;
import dto.Room;

public class OrderBUS {
	public static ArrayList<Room> selectRoom() {
		ArrayList<Room>  lsOb_r = OrderDAO.selectRoom();
		
		return lsOb_r;
	}
}
