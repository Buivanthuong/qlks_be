package bus;

import java.util.ArrayList;

import dao.RoomDAO;
import dto.Room;
import dto.TypeRoom;

public class RoomBUS {
	public static ArrayList<Room> selectRoom() {
		return RoomDAO.selectRoom();
	}
	public static ArrayList<Room>selectRoom(int STATUS, String text){
		return RoomDAO.selectRoom( STATUS,  text);
	}
	public static boolean saveRoom(int id ,String name,String note, int id_type) {
		return RoomDAO.saveRoom( id , name, note,  id_type);
	}
	public static ArrayList<TypeRoom> selectTypeRoom() {
		return RoomDAO.selectTypeRoom();
	}
	public static boolean deleteRoom(int id) {
		return RoomDAO.deleteRoom(id);
	}
	public static boolean saveRoomType(int id ,String name, String price)  {
		return RoomDAO.saveRoomType( id , name,  price) ;
	}
	public static boolean deleteRoomType(int id) {
		return RoomDAO.deleteRoomType(id);
	}
	public static ArrayList<Room> selectRoom(int month) {
		return RoomDAO.selectRoom( month);
	}
	public static ArrayList<TypeRoom> selectTyeRoom(int month) {
		return RoomDAO.selectTyeRoom( month);
	}
}
