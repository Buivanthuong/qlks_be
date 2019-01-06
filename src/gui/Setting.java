package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import bus.UserBUS;
import utill.ConnectionUtils;
import utill.DatabaseHelper;
import utill.SWTResourceManager;
import utill.ShowMessage;
import utill.Utill;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import dto.User;

public class Setting {

	protected Shell shlThitLp;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Table table;
	private Text text_9;
	private Text text_10;
	User us  = new User();
	int current_user_id = 0;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Setting window = new Setting();
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
		shlThitLp.open();
		shlThitLp.layout();
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlThitLp.getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		shlThitLp.setLocation(x, y);
		while (!shlThitLp.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlThitLp = new Shell();
		shlThitLp.setSize(450, 300);
		shlThitLp.setText("Thiết lập");

		TabFolder tabFolder = new TabFolder(shlThitLp, SWT.NONE);
		tabFolder.setBounds(10, 10, 430, 258);

		TabItem tbtmThitLp = new TabItem(tabFolder, SWT.NONE);
		tbtmThitLp.setText("Thông tin quản lý");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmThitLp.setControl(composite);
		composite.setLayout(null);

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblNewLabel.setBounds(10, 10, 153, 14);
		lblNewLabel.setText("Số lượng loại phòng");

		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text.setBounds(169, 7, 135, 19);

		text_1 = new Text(composite, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_1.setBounds(169, 30, 135, 19);

		Label lblSLngLoi = new Label(composite, SWT.NONE);
		lblSLngLoi.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblSLngLoi.setText("Số lượng loại khách");
		lblSLngLoi.setBounds(10, 33, 153, 14);

		text_2 = new Text(composite, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_2.setBounds(169, 55, 135, 19);

		Label lblSLngKhch = new Label(composite, SWT.NONE);
		lblSLngKhch.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblSLngKhch.setText("Số lượng khách trong phòng");
		lblSLngKhch.setBounds(10, 58, 153, 14);

		Label lblPhThuQu = new Label(composite, SWT.NONE);
		lblPhThuQu.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblPhThuQu.setText("Phụ thu quá số khách");
		lblPhThuQu.setBounds(10, 83, 153, 14);

		text_3 = new Text(composite, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_3.setBounds(250, 80, 54, 19);

		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(169, 83, 75, 14);
		lblNewLabel_1.setText("Số khách từ:");

		Label lblThu = new Label(composite, SWT.NONE);
		lblThu.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblThu.setBounds(310, 83, 58, 14);
		lblThu.setText("Thu/ngư�?i");

		text_4 = new Text(composite, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_4.setBounds(368, 80, 32, 19);

		text_5 = new Text(composite, SWT.BORDER);
		text_5.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_5.setBounds(250, 103, 54, 19);

		Label lblHSKhch = new Label(composite, SWT.NONE);
		lblHSKhch.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblHSKhch.setText("Hệ số trên loại khách");
		lblHSKhch.setBounds(10, 106, 153, 14);

		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		label_1.setText("Số khách từ:");
		label_1.setBounds(169, 106, 75, 14);

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(saveConfig() ) {
					shlThitLp.close();
				}
			}
		});
		btnNewButton.setBounds(110, 177, 94, 33);
		btnNewButton.setText("Lưu thay đổi");

		Button btnHu = new Button(composite, SWT.NONE);
		btnHu.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnHu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlThitLp.close();
			}
		});
		btnHu.setBounds(210, 177, 94, 33);
		btnHu.setText("Huỷ");

		Label lblTnKhchSn = new Label(composite, SWT.NONE);
		lblTnKhchSn.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblTnKhchSn.setText("Tên khách sạn");
		lblTnKhchSn.setBounds(10, 131, 153, 14);

		text_9 = new Text(composite, SWT.BORDER);
		text_9.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_9.setBounds(169, 128, 231, 19);

		Label lblaCh = new Label(composite, SWT.NONE);
		lblaCh.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblaCh.setText("�?ịa chỉ");
		lblaCh.setBounds(10, 154, 153, 14);

		text_10 = new Text(composite, SWT.BORDER);
		text_10.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_10.setBounds(169, 151, 231, 19);
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Arial", 10, SWT.NORMAL));
		label.setBounds(396, 85, 14, 14);
		label.setText("%");

		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Thông tin tài khoản");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		composite_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		tbtmNewItem.setControl(composite_1);
		composite_1.setLayout(null);

		Label lblTnTiKhon = new Label(composite_1, SWT.NONE);
		lblTnTiKhon.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblTnTiKhon.setBounds(10, 10, 103, 14);
		lblTnTiKhon.setText("Tên tài khoản");

		text_6 = new Text(composite_1, SWT.BORDER);
		text_6.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_6.setBounds(119, 7, 194, 19);

		Label lblTnngNhp = new Label(composite_1, SWT.NONE);
		lblTnngNhp.setText("Tên đăng nhập");
		lblTnngNhp.setBounds(10, 33, 103, 14);

		text_7 = new Text(composite_1, SWT.BORDER);
		text_7.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_7.setBounds(119, 30, 194, 19);

		Label lblMtKhu = new Label(composite_1, SWT.NONE);
		lblMtKhu.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblMtKhu.setText("Mật khẩu");
		lblMtKhu.setBounds(10, 56, 103, 14);

		text_8 = new Text(composite_1, SWT.BORDER | SWT.PASSWORD);
		text_8.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_8.setBounds(119, 53, 194, 19);

		Button btnLuThmMi = new Button(composite_1, SWT.NONE);
		btnLuThmMi.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnLuThmMi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(saveUser(current_user_id, text_7.getText(), text_6.getText(), text_8.getText())) {
					selectUser();
				}else{
				};
			}
		});
		btnLuThmMi.setBounds(119, 78, 111, 34);
		btnLuThmMi.setText("Lưu/ Thêm mới");

		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		table.setBounds(10, 118, 390, 92);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(31);
		tblclmnStt.setText("STT");

		TableColumn tblclmnTnTiKhon = new TableColumn(table, SWT.NONE);
		tblclmnTnTiKhon.setWidth(100);
		tblclmnTnTiKhon.setText("Tên tài khoản");

		TableColumn tblclmnTnangNhp = new TableColumn(table, SWT.NONE);
		tblclmnTnangNhp.setWidth(130);
		tblclmnTnangNhp.setText("Tên đang nhập");

		TableColumn tblclmnChnhSa = new TableColumn(table, SWT.NONE);
		tblclmnChnhSa.setWidth(59);
		tblclmnChnhSa.setText("Chỉnh sửa");
		
		TableColumn tblclmnXo = new TableColumn(table, SWT.NONE);
		tblclmnXo.setWidth(100);
		tblclmnXo.setText("Xoá");
		selectConfig();

		us = Utill.GetSaveUser();
//		text_6.setText(us.getFULL_NAME());
//		text_7.setText(us.getUSER_NAME());

		selectUser();
	}
	private boolean selectConfig() {
		//connections
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("Select NUM_TYPE_ROOM,NUM_TYPE_CUSTOMER,NUM_CUSTOMER_IN_ROOM,NUM_SURCHARGE_CUSTOMER,SURCHARGE_CUSTOMER,NUM_SURCHARGE_CUSTOMER_TYPE,HOTEL_NAME,ADDRESS From db_qlks.CONFIG" ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);

			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				text.setText(rs.getString(1));
				text_1.setText(rs.getString(2));
				text_2.setText(rs.getString(3));
				text_3.setText(rs.getString(4));
				text_4.setText(rs.getString(5));
				text_5.setText(rs.getString(6));

				text_9.setText(rs.getString(7));
				text_10.setText(rs.getString(8));

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
	Button[] editButtons ;
	Button[] removeButtons ;
	private boolean selectUser() {
		table.removeAll();
		TableEditor[] editEditors ;
		TableEditor[] removeEditors;
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
		//connections
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("Select ID , USER_NAME,FULL_NAME From USER WHERE STATUS = 1" ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			ResultSet rs = DatabaseHelper.selectData(sql, connection);
			ArrayList<User> lsOb = new ArrayList<>();
			int size = 0;

			// Duyệt trên kết quả trả v�?.
			while (rs.next()) {// Di chuyển con tr�? xuống bản ghi kế tiếp.
				User ob = new User();
				ob.setID(rs.getInt(1));
				ob.setFULL_NAME(rs.getString(3));
				ob.setUSER_NAME(rs.getString(2));

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
				item.setText(new String[] {(i + 1)+"",lsOb.get(i).getFULL_NAME(),lsOb.get(i).getUSER_NAME(), "Sửa", "Xoá"});

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
				editEditors[i].setEditor(editButtons[i], item, 3);
				removeEditors[i].setEditor(removeButtons[i], item, 4);

			}
			for (int i = 0; i < size; i++) {
				String name = lsOb.get(i).getFULL_NAME();
				String user = lsOb.get(i).getUSER_NAME();
				int id = lsOb.get(i).getID();


				editButtons[i].addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						text_6.setText(name);
						text_7.setText(user);
						text_8.setText("");
						current_user_id = id;
					}
				});
				removeButtons[i].addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						if(deleteUser(id)) {
							ShowMessage.ShowError(shlThitLp,"Xoá dữ liệu thành công!", "Lỗi dữ liệu");
							selectUser();
						}else {
							ShowMessage.ShowError(shlThitLp,"Không xoá được!", "Lỗi dữ liệu");
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
	private boolean deleteUser(int id) {

		//connections
		System.out.println("Get connection ... ");
		Connection connection;
		try {
			connection = ConnectionUtils.getMyConnection();
			System.out.println("Get connection " + connection);
			System.out.println("Done!");

			// Tạo đối tượng .		 
			String sql = String.format("UPDATE USER set STATUS = '3' WHERE ID ='%s' AND ID != %s",id,us.getID() ) ;

			// Thực thi câu lệnh SQL trả v�? đối tượng ResultSet.
			int rs = DatabaseHelper.installData(sql, connection);

			if(rs > 0) {
				current_user_id = 0;
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
	private boolean saveUser(int id ,String name,String full_name, String password) {
		if(name.isEmpty()) {
			ShowMessage.ShowError(shlThitLp,"Vui lòng nhập tên đăng nhập", "Lỗi dữ liệu");

		}else if(full_name.isEmpty()) {
			ShowMessage.ShowError(shlThitLp,"Vui lòng nhập tên", "Lỗi dữ liệu");

		}else if(password.isEmpty()) {
			ShowMessage.ShowError(shlThitLp,"Vui lòng nhập mật khẩu", "Lỗi dữ liệu");

		}else {

			if(current_user_id == 0) {
				if(!UserBUS.InsertUser(name, full_name, password)) {
					ShowMessage.ShowError(shlThitLp,"Không thêm được,  tên đăng nhập đã tồn tại!", "Lỗi dữ liệu");
				}
			}else {
				if(UserBUS.UpdateUser(id, name, full_name, password)) {
					current_user_id = 0;
					return true;
				};
			}
		}

		return false;
	}
	private boolean saveConfig() {
		if(text.getText().isEmpty()) {
			ShowMessage.ShowError(shlThitLp,"Vui lòng nhập số lượng loại phòng", "Lỗi dữ liệu");

		}else if(text_1.getText().isEmpty()) {
			ShowMessage.ShowError(shlThitLp,"Vui lòng nhập số lượng loại khách", "Lỗi dữ liệu");

		}else if(text_2.getText().isEmpty()) {
			ShowMessage.ShowError(shlThitLp,"Vui lòng nhập số lượng khách trong phòng", "Lỗi dữ liệu");

		}else if(text_3.getText().isEmpty()) {
			ShowMessage.ShowError(shlThitLp,"Vui lòng nhập số khách phụ thu", "Lỗi dữ liệu");

		}else if(text_4.getText().isEmpty()) {
			ShowMessage.ShowError(shlThitLp,"Vui lòng nhập Hệ số phụ thu", "Lỗi dữ liệu");

		}else if(text_5.getText().isEmpty()) {
			ShowMessage.ShowError(shlThitLp,"Vui lòng nhập số khách nước ngoài", "Lỗi dữ liệu");

		}else if(text_9.getText().isEmpty()) {
			ShowMessage.ShowError(shlThitLp,"Vui lòng nhập tên khách sạn", "Lỗi dữ liệu");

		}else if(text_10.getText().isEmpty()) {
			ShowMessage.ShowError(shlThitLp,"Vui lòng nhập địa chỉ", "Lỗi dữ liệu");
		}else {
			if( UserBUS.InsertConfigUser(text.getText(),text_1.getText(),text_2.getText(),text_3.getText(),text_4.getText(),text_5.getText(),text_9.getText(),text_10.getText())){
				current_user_id = 0;
			} ;
		}
		return false;
	}

}
