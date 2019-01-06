package gui;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import utill.ConnectionUtils;
import utill.DatabaseHelper;
import utill.SWTResourceManager;
import utill.ShowMessage;
import utill.Utill;
import dto.Config;
import dto.Customer;
import dto.Room;
import dto.TypeCustomer;

public class CreateOrderRoom {

	protected Shell shlLpPhiuThu;
	private Table table;
	Combo combo_2;
	Combo combo ;
	Label lblNewLabel;
	int current_room_id;
	int price;
	Config config ;
	int current_customer_id;
	private ArrayList<Customer> lsObs = new ArrayList();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CreateOrderRoom window = new CreateOrderRoom();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlLpPhiuThu.open();
		shlLpPhiuThu.layout();
		Monitor primary = display.getPrimaryMonitor();
	    Rectangle bounds = primary.getBounds();
	    Rectangle rect = shlLpPhiuThu.getBounds();
	    
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    
	    shlLpPhiuThu.setLocation(x, y);
		
		while (!shlLpPhiuThu.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLpPhiuThu = new Shell();
		shlLpPhiuThu.setSize(450, 300);
		shlLpPhiuThu.setText("Lập phiếu thuê phòng");
		shlLpPhiuThu.setLayout(null);

		Composite composite = new Composite(shlLpPhiuThu, SWT.NONE);
		composite.setBounds(10, 10, 430, 258);

		Label lblNgyBtu = new Label(composite, SWT.NONE);
		lblNgyBtu.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblNgyBtu.setText("Ngày bắt đầu thuê");
		lblNgyBtu.setBounds(10, 41, 116, 14);

		Label lblThmKhchHng = new Label(composite, SWT.NONE);
		lblThmKhchHng.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblThmKhchHng.setText("Phòng");
		lblThmKhchHng.setBounds(10, 10, 116, 14);

		Label lblDanhSchKhch = new Label(composite, SWT.NONE);
		lblDanhSchKhch.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblDanhSchKhch.setText("Danh sách khách hàng");
		lblDanhSchKhch.setBounds(10, 104, 159, 14);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		table.setBounds(10, 124, 430, 87);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(27);
		tblclmnStt.setText("STT");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Tên khách hàng");

		TableColumn tblclmnLoi = new TableColumn(table, SWT.NONE);
		tblclmnLoi.setWidth(76);
		tblclmnLoi.setText("Loại");

		TableColumn tblclmnCmnd = new TableColumn(table, SWT.NONE);
		tblclmnCmnd.setWidth(69);
		tblclmnCmnd.setText("CMND");

		TableColumn tblclmnaCh = new TableColumn(table, SWT.NONE);
		tblclmnaCh.setWidth(100);
		tblclmnaCh.setText("�?ịa chỉ");

		TableColumn tblclmnXo = new TableColumn(table, SWT.NONE);
		tblclmnXo.setWidth(100);
		tblclmnXo.setText("Xoá");

		combo_2 = new Combo(composite, SWT.READ_ONLY);
		combo_2.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		combo_2.setBounds(132, 6, 149, 25);

		Button btnToMi = new Button(composite, SWT.NONE);
		btnToMi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnToMi.getText().equals("Tạo mới")) {
					btnToMi.setText("Cập nhật");
					ManagerRoom r = new ManagerRoom();
					r.open();
				}else {
					btnToMi.setText("Tạo mới");
					selectRoom();
				}
				
			}
		});
		btnToMi.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnToMi.setBounds(287, 3, 73, 28);
		btnToMi.setText("Tạo mới");

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				saveOrderRoom(current_room_id, price, lblNewLabel.getText(), lsObs);
			}
		});
		btnNewButton.setBounds(111, 217, 104, 32);
		btnNewButton.setText("Lưu hoá đơn");

		Button btnHu = new Button(composite, SWT.NONE);
		btnHu.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnHu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLpPhiuThu.close();
			}
		});
		btnHu.setBounds(221, 217, 94, 32);
		btnHu.setText("Huỷ");

		Label lblPhiuThuPhng = new Label(composite, SWT.NONE);
		lblPhiuThuPhng.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblPhiuThuPhng.setText("Thêm khách hàng");
		lblPhiuThuPhng.setBounds(10, 77, 116, 14);

		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		combo.setBounds(132, 73, 149, 25);

		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(button.getText().equals("Tạo mới")) {
					button.setText("Cập nhật");
					ManagerCustomer customer = new ManagerCustomer();
					customer.open();
				}else {
					button.setText("Tạo mới");
					selectCustomer();
				}
				
			}
		});
		button.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		button.setText("Tạo mới");
		button.setBounds(287, 70, 73, 28);
		
		lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblNewLabel.setBounds(222, 40, 138, 14);
		
		Button btnChnNgy = new Button(composite, SWT.NONE);
		btnChnNgy.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnChnNgy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final Shell dialog = new Shell (shlLpPhiuThu, SWT.DIALOG_TRIM);
				dialog.setLayout (new GridLayout (3, false));
				Display display = Display.getDefault();
				Monitor primary = display.getPrimaryMonitor();
			    Rectangle bounds = primary.getBounds();
			    Rectangle rect = shlLpPhiuThu.getBounds();
			    
			    int x = bounds.x + (bounds.width - rect.width) / 2;
			    int y = bounds.y + (bounds.height - rect.height) / 2;
			    
			    dialog.setLocation(x, y);
				
				final DateTime calendar = new DateTime (dialog, SWT.CALENDAR | SWT.BORDER);
				final DateTime date = new DateTime (dialog, SWT.DATE | SWT.SHORT);
				final DateTime time = new DateTime (dialog, SWT.TIME | SWT.SHORT);

				new Label (dialog, SWT.NONE);
				new Label (dialog, SWT.NONE);
				Button ok = new Button (dialog, SWT.PUSH);
				ok.setText ("OK");
				ok.setLayoutData(new GridData (SWT.FILL, SWT.CENTER, false, false));
				ok.addSelectionListener (new SelectionAdapter () {
					public void widgetSelected (SelectionEvent e) {
						System.out.println ("Calendar date selected (MM/DD/YYYY) = " + (calendar.getMonth () + 1) + "/" + calendar.getDay () + "/" + calendar.getYear ());
						lblNewLabel.setText( calendar.getYear () + "-" +((calendar.getMonth () + 1) <10?"0" +(calendar.getMonth () + 1): (calendar.getMonth () + 1)) + "-" + (calendar.getDay ()<10?"0"+calendar.getDay ():calendar.getDay ()) + " " + (time.getHours ()<10?"0"+time.getHours ():time.getHours ()) + ":" + (time.getMinutes ()<10?"0"+time.getMinutes ():time.getMinutes ())+ ":00" );

						dialog.close ();
					}
				});
				dialog.setDefaultButton (ok);
				dialog.pack ();
				dialog.open ();
			}
		});
		btnChnNgy.setBounds(132, 33, 94, 28);
		btnChnNgy.setText("Chọn ngày");

		selectRoom();
		selectCustomer();
		config = selectConfig();
	}
	ArrayList<Room> lsOb_r;
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
	ArrayList<Customer> lsOb_c;
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

	private boolean showCustomer(ArrayList<Customer> lsOb) {
		int size = lsOb.size();
		if(removeButtons != null) {
			for (Button button : removeButtons) {
				button.dispose();
			}
		}
		table.removeAll();
		TableEditor[] removeEditors;

		removeEditors = new TableEditor[size];
		removeButtons = new Button[size];


		for (int i = 0; i < size; i++) {
			final TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] {(i + 1)+"",lsOb.get(i).getNAME(),lsOb.get(i).getTYPE(),lsOb.get(i).getPASSPORT(),lsOb.get(i).getADDRESS(), "Xoá"});

			// Create the editor and button
			removeEditors[i] = new TableEditor(table);
			removeButtons[i] = new Button(table, SWT.PUSH);

			// Set attributes of the button
			removeButtons[i].setText("Xoá");
			removeButtons[i].computeSize(SWT.DEFAULT, table.getItemHeight());

			// Set attributes of the editor
			removeEditors[i].grabHorizontal = true;
			removeEditors[i].minimumHeight = removeButtons[i].getSize().y;
			removeEditors[i].minimumWidth = removeButtons[i].getSize().x;

			// Set the editor for the first column in the row
			removeEditors[i].setEditor(removeButtons[i], item, 5);

		}
		for (int i = 0; i < size; i++) {

			int index = i;

			removeButtons[i].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					lsObs.remove(index);
					for (Button button : removeButtons) {
						button.dispose();
					}
					showCustomer(lsObs);

				}
			});
		}


		return false;
	}
	private boolean saveOrderRoom(int room_id,int price ,String date, ArrayList<Customer> ls) {
		
		if(room_id == 0) {
			ShowMessage.ShowError(shlLpPhiuThu,"Vui lòng ch�?n phòng", "Lỗi dữ liệu");

		}else if(ls.size() == 0) {
			ShowMessage.ShowError(shlLpPhiuThu,"Vui lòng ch�?n khách hàng", "Lỗi dữ liệu");

		}else if(date.isEmpty()) {
			ShowMessage.ShowError(shlLpPhiuThu,"Vui lòng ch�?n ngày", "Lỗi dữ liệu");

		}else {
			int total_price = price;
			if(ls.size() > config.getNUM_SURCHARGE_CUSTOMER()) {
				total_price+=( price *config.getSURCHARGE_CUSTOMER()/100);
			}
			ArrayList<TypeCustomer> tcs = new ArrayList();
			for (Customer customer : ls) {
				int is_has = 0;
				for (TypeCustomer t : tcs) {
					if(customer.getTYPE_CUSTOMER_ID() == t.getID()) {
						is_has = 1;
						t.setNUMBER(t.getNUMBER() + 1);
						break;
					}
				}
				if(is_has == 0) {
					TypeCustomer tc = new TypeCustomer();
					tc.setID(customer.getID());
					tc.setNUMBER(1);
					tc.setSURCHARGE(customer.getSURCHARGE());
					tcs.add(tc);
				}
			}
			
			for (TypeCustomer type_CUSTOMER : tcs) {
				if(type_CUSTOMER.getNUMBER() > config.getNUM_SURCHARGE_CUSTOMER_TYPE()) {
					total_price+=(total_price * type_CUSTOMER.getSURCHARGE());
				}
			}
			Connection connection;
			
			
			//connections
			try {
				connection = ConnectionUtils.getMyConnection();
				System.out.println("Get connection " + connection);
				System.out.println("Done!");

				// Tạo đối tượng .		 
				String sql = String.format("Insert into db_qlks.ORDER (DATE_ORDER,ROOM_ID,PRICE,STATUS,USER_ID) Values( '%s','%s','%s',1,1)",date,room_id,total_price ) ;
				// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
				int rs = DatabaseHelper.installData(sql, connection);

				int order_id = 0;
				// Tạo đối tượng .		 
				String sql1 = String.format("Select MAX(ID) From db_qlks.ORDER" ) ;
				// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
				ResultSet rss = DatabaseHelper.selectData(sql1, connection);
				// Duyệt trên kết quả trả v�?.
				while (rss.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
					order_id = rss.getInt(1);
				}
				for (Customer customer : ls) {
					String sql2 = String.format("Insert into CUSTOMER_IN_ORDER (ORDER_ID,CUSTOMER_ID) Values( '%s','%s')",order_id,customer.getID() ) ;
					int rs2 = DatabaseHelper.installData(sql2, connection);

				}
				// Close connection
				connection.close();
				shlLpPhiuThu.close();

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
	private Config selectConfig() {
		//connections
		Connection connection;
		Config config = new Config();
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Done!");


			// Tạo đối tượng .		 
			String sql = String.format("Select NUM_TYPE_ROOM,NUM_TYPE_CUSTOMER,NUM_CUSTOMER_IN_ROOM,NUM_SURCHARGE_CUSTOMER,SURCHARGE_CUSTOMER,NUM_SURCHARGE_CUSTOMER_TYPE,HOTEL_NAME,ADDRESS From db_qlks.CONFIG" ) ;

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
		}



		return config;
	}
}
