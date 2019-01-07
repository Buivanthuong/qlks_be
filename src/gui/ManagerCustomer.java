package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Text;

import utill.ConnectionUtils;
import utill.DatabaseHelper;
import utill.SWTResourceManager;
import utill.ShowMessage;
import utill.Utill;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import dto.Customer;
import dto.TypeCustomer;

public class ManagerCustomer {

	protected Shell shlQunLKhch;
	private Text text;
	private Table table;
	private Text text_1;
	private Text text_2;

	private Combo combo;
	private int  current_id = 0;
	private int current_type_id = 0;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ManagerCustomer window = new ManagerCustomer();
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
		shlQunLKhch.open();
		shlQunLKhch.layout();
		Monitor primary = display.getPrimaryMonitor();
	    Rectangle bounds = primary.getBounds();
	    Rectangle rect = shlQunLKhch.getBounds();
	    
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    
	    shlQunLKhch.setLocation(x, y);
		while (!shlQunLKhch.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlQunLKhch = new Shell();
		shlQunLKhch.setSize(450, 350);
		shlQunLKhch.setText("Quản lý khách hàng");
		
		Composite composite = new Composite(shlQunLKhch, SWT.NONE);
		composite.setLayout(null);
		composite.setBounds(10, 10, 430, 308);
		
		Label lblTnKhchHng = new Label(composite, SWT.NONE);
		lblTnKhchHng.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblTnKhchHng.setText("Tên khách hàng");
		lblTnKhchHng.setBounds(10, 13, 92, 14);
		
		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text.setBounds(108, 10, 248, 19);
		
		Label lblLoiKhchHng = new Label(composite, SWT.NONE);
		lblLoiKhchHng.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblLoiKhchHng.setText("Loại khách hàng");
		lblLoiKhchHng.setBounds(10, 88, 92, 14);
		
		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		combo.setBounds(108, 84, 148, 22);
		
		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(button.getText().equals("Tạo mới")) {
					button.setText("Cập nhật");
					ManagerCustomerType r = new ManagerCustomerType();
					r.open();
				}else {
					button.setText("Tạo mới");
					selectTypeCustomer();
				}
			}
		});
		button.setText("Tạo mới");
		button.setBounds(262, 79, 94, 28);
		
		Button btnLuKhchHng = new Button(composite, SWT.NONE);
		btnLuKhchHng.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnLuKhchHng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				String name = text.getText();
				String passport = text_1.getText();
				String address = text_2.getText();

				saveCustomer(0, name,passport ,address, current_type_id);
				selectCustomer();
			}
		});
		btnLuKhchHng.setText("Lưu khách hàng");
		btnLuKhchHng.setBounds(108, 112, 125, 34);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 172, 410, 125);
		
		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(29);
		tblclmnStt.setText("STT");
		
		TableColumn tblclmnTnKhchHng = new TableColumn(table, SWT.NONE);
		tblclmnTnKhchHng.setWidth(100);
		tblclmnTnKhchHng.setText("Tên khách hàng");
		
		TableColumn tblclmnCmnd = new TableColumn(table, SWT.NONE);
		tblclmnCmnd.setWidth(52);
		tblclmnCmnd.setText("CMND");
		
		TableColumn tblclmnaCh = new TableColumn(table, SWT.NONE);
		tblclmnaCh.setWidth(90);
		tblclmnaCh.setText("Địa chỉ");
		
		TableColumn tblclmnLoi = new TableColumn(table, SWT.NONE);
		tblclmnLoi.setWidth(61);
		tblclmnLoi.setText("Loại");
		
		TableColumn tblclmnSa = new TableColumn(table, SWT.NONE);
		tblclmnSa.setWidth(39);
		tblclmnSa.setText("Sửa");
		
		TableColumn tblclmnXo = new TableColumn(table, SWT.NONE);
		tblclmnXo.setWidth(37);
		tblclmnXo.setText("Xoá");
		
		Label lblDanhSchKhach = new Label(composite, SWT.NONE);
		lblDanhSchKhach.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblDanhSchKhach.setText("Danh sách khách hàng");
		lblDanhSchKhach.setBounds(10, 152, 141, 14);
		
		Label lblCmnd = new Label(composite, SWT.NONE);
		lblCmnd.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblCmnd.setText("CMND");
		lblCmnd.setBounds(10, 36, 92, 14);
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_1.setBounds(108, 33, 248, 19);
		
		Label lblaCh = new Label(composite, SWT.NONE);
		lblaCh.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblaCh.setText("Địa chỉ");
		lblaCh.setBounds(10, 59, 92, 14);
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_2.setBounds(108, 56, 248, 19);

		selectTypeCustomer();
		selectCustomer();
	}
	private boolean saveCustomer(int id ,String name,String passport,String address, int id_type) {
		if(name.isEmpty()) {
			ShowMessage.ShowError(shlQunLKhch,"Vui lòng nhập tên khách hàng", "Lỗi dữ liệu");

		}else if(id_type == 0) {
			ShowMessage.ShowError(shlQunLKhch,"Vui lòng chọn loại khách hàng", "Lỗi dữ liệu");

		}else {
			Connection connection;

			if(current_id == 0) {
				//connections
				try {
					connection = ConnectionUtils.getMyConnection();
					System.out.println("Get connection " + connection);
					System.out.println("Done!");

					// Tạo đối tượng .		 
					String sql = String.format("Insert into CUSTOMER (NAME, PASSPORT,ADDRESS,TYPE_CUSTOMER_ID,STATUS) Values( '%s','%s','%s','%s',1)",name,passport,address,id_type + "" ) ;

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
					String sql = String.format("UPDATE CUSTOMER set NAME = '%s',PASSPORT ='%s',ADDRESS ='%s',TYPE_CUSTOMER_ID = '%s' WHERE ID = '%s'",name,passport,address,id_type,current_id ) ;

					// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
					int rs = DatabaseHelper.installData(sql, connection);

					if(rs > 0) {
						current_id = 0;
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

		}

		return false;
	}
	Button[] editButtons ;
	Button[] removeButtons ;
	private boolean selectCustomer() {
		table.removeAll();
		if(removeButtons != null) {
			for (Button button : removeButtons) {
				button.dispose();
			}
		}
		if(editButtons != null) {
			for (Button button : editButtons) {
				button.dispose();
			}
		}
		
		TableEditor[] editEditors ;
		TableEditor[] removeEditors;
		
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
			ArrayList<Customer> lsOb = new ArrayList<>();
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

				lsOb.add(ob);
				size ++;

			}
			// Create five table editors for color
			editEditors = new TableEditor[size];
			removeEditors = new TableEditor[size];

			// Create five buttons for changing color
			editButtons = new Button[size];
			removeButtons = new Button[size];


			for (int i = 0; i < size; i++) {
				final TableItem item = new TableItem(table, SWT.NONE);
				item.setText(new String[] {(i + 1)+"",lsOb.get(i).getNAME(),lsOb.get(i).getPASSPORT(),lsOb.get(i).getADDRESS(),lsOb.get(i).getTYPE(), "Sửa", "Xoá"});

				// Create the editor and button
				editEditors[i] = new TableEditor(table);
				editButtons[i] = new Button(table, SWT.PUSH);

				// Set attributes of the button
				editButtons[i].setText("Sửa");
				editButtons[i].computeSize(SWT.DEFAULT, table.getItemHeight());

				// Set attributes of the editor
				editEditors[i].grabHorizontal = true;
				editEditors[i].minimumHeight = editButtons[i].getSize().y;
				editEditors[i].minimumWidth = editButtons[i].getSize().x;

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
				editEditors[i].setEditor(editButtons[i], item, 5);
				removeEditors[i].setEditor(removeButtons[i], item, 6);

			}
			for (int i = 0; i < size; i++) {
				String name = lsOb.get(i).getNAME();
				String passport = lsOb.get(i).getPASSPORT();
				String address = lsOb.get(i).getADDRESS();

				int id = lsOb.get(i).getID();


				editButtons[i].addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						text.setText(name);
						text_1.setText(passport);
						text_2.setText(address);

						current_id = id;
					}
				});
				removeButtons[i].addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if(deleteCustomer(id)) {
							ShowMessage.ShowError(shlQunLKhch,"Xoá dữ liệu thành công!", "Lỗi dữ liệu");
							selectCustomer();
						}else {
							ShowMessage.ShowError(shlQunLKhch,"Không xoá được!", "Lỗi dữ liệu");
						}

					}
				});
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
	ArrayList<TypeCustomer> lsOb;
	private boolean selectTypeCustomer() {
		
		//connections
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("Select NAME, SURCHARGE,ID From TYPE_CUSTOMER WHERE STATUS = 1" ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);
			lsOb = new ArrayList<>();
			int size = 0;

			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				TypeCustomer ob = new TypeCustomer();
				ob.setSURCHARGE(rs.getDouble(2));
				ob.setID(rs.getInt(3));
				ob.setNAME(rs.getString(1));
				lsOb.add(ob);
				size ++;

			}
			String[] strings = new String[size];
			for (int i = 0; i < size; i++) {
				strings[i] = lsOb.get(i).getNAME();	
			}
			combo.setItems(strings);
			combo.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
			    	current_type_id = lsOb.get(combo.getSelectionIndex()).getID();

				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				}
			    );
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
	private boolean deleteCustomer(int id) {

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
				current_id = 0;
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


}
