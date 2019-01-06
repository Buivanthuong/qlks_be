package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import dto.Room;
import utill.ConnectionUtils;
import utill.DatabaseHelper;

public class OrderDAO {
	private boolean selectRoom() {
		//connections
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("Select ROOM.NAME, NOTE,ROOM.ID,TYPE_ROOM.NAME as TYPE_ROOM_NAME,TYPE_ROOM_ID,PRICE From ROOM , TYPE_ROOM WHERE ROOM.STATUS = 1 and ROOM.TYPE_ROOM_ID = TYPE_ROOM.ID AND ROOM.ID NOT IN (SELECT ROOM_ID FROM db_qlks.ORDER WHERE RECEIPT_ID IS NULL) " ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);
			lsOb_r = new ArrayList<>();
			int size = 0;

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
				size ++;

			}
			String[] strings = new String[size];
			for (int i = 0; i < size; i++) {
				strings[i] = lsOb_r.get(i).getNAME();	
			}
			combo_2.setItems(strings);
			combo_2.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					current_room_id = lsOb_r.get(combo_2.getSelectionIndex()).getID();
					price= lsOb_r.get(combo_2.getSelectionIndex()).getPRICE();
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
				}});

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
}
