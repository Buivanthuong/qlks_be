package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import dto.Customer;
import utill.ConnectionUtils;
import utill.DatabaseHelper;
import utill.ShowMessage;

public class CustomerDAO {
	private boolean selectCustomer() {
		//connections
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("Select CUSTOMER.NAME, PASSPORT,ADDRESS ,CUSTOMER.ID,TYPE_CUSTOMER.NAME as TYPE_CUSTOMER_NAME,TYPE_CUSTOMER_ID,SURCHARGE From CUSTOMER , TYPE_CUSTOMER WHERE CUSTOMER.STATUS = 1 and CUSTOMER.TYPE_CUSTOMER_ID = TYPE_CUSTOMER.ID" ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);
			lsOb_c = new ArrayList<>();
			int size = 0;

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
				size ++;

			}
			String[] strings = new String[size];
			for (int i = 0; i < size; i++) {
				strings[i] = lsOb_c.get(i).getNAME();	
			}
			combo.setItems(strings);
			combo.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					int sum = 0;
					for (Customer cm : lsObs) {
						if(cm.getID() == lsOb_c.get(combo.getSelectionIndex()).getID()) {
							sum = 1;
						};
					}if(sum == 0 && lsObs.size() < config.getNUM_CUSTOMER_IN_ROOM()) {
						lsObs.add(lsOb_c.get(combo.getSelectionIndex()));
					}else {
						ShowMessage.ShowError(shlLpPhiuThu,"Quá số khách tối đa trong 1 phòng !", "Lỗi dữ liệu");
					}
					showCustomer(lsObs);
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
	Button[] removeButtons ;

}
