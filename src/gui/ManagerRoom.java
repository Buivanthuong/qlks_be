package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Text;

import bus.RoomBUS;
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

import dto.Room;
import dto.TypeRoom;

public class ManagerRoom {

	protected Shell shlQunLPhng;
	private Text text;
	private Table table;
	private Combo combo;
	private int  current_id = 0;
	private int current_type_id = 0;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ManagerRoom window = new ManagerRoom();
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
		shlQunLPhng.open();
		shlQunLPhng.layout();
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlQunLPhng.getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		shlQunLPhng.setLocation(x, y);
		while (!shlQunLPhng.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlQunLPhng = new Shell();
		shlQunLPhng.setSize(450, 377);
		shlQunLPhng.setText("Quản lý phòng");

		Composite composite = new Composite(shlQunLPhng, SWT.NONE);
		composite.setBounds(10, 10, 430, 335);
		composite.setLayout(null);

		Label lblTnPhng = new Label(composite, SWT.NONE);
		lblTnPhng.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblTnPhng.setBounds(10, 13, 92, 14);
		lblTnPhng.setText("Tên phòng");

		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text.setBounds(108, 10, 248, 19);

		Label lblLoiPhng = new Label(composite, SWT.NONE);
		lblLoiPhng.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblLoiPhng.setBounds(10, 39, 59, 14);
		lblLoiPhng.setText("Loại phòng");

		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		combo.setBounds(108, 35, 148, 22);

		Button btnToMi = new Button(composite, SWT.NONE);
		btnToMi.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnToMi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnToMi.getText().equals("Tạo mới")) {
					btnToMi.setText("Cập nhật");
					ManagerRoomType r = new ManagerRoomType();
					r.open();
				}else {
					btnToMi.setText("Tạo mới");
					selectTypeRoom();
				}

			}
		});
		btnToMi.setBounds(262, 30, 94, 28);
		btnToMi.setText("Tạo mới");

		Button btnLuPhng = new Button(composite, SWT.NONE);
		btnLuPhng.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnLuPhng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = text.getText();
				String note = text_1.getText();
				saveRoom(0, name, note, current_type_id);
				selectRoom();
			}
		});
		btnLuPhng.setBounds(108, 124, 94, 34);
		btnLuPhng.setText("Lưu phòng");

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		table.setBounds(10, 200, 410, 125);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(31);
		tblclmnStt.setText("STT");

		TableColumn tblclmnPhng = new TableColumn(table, SWT.NONE);
		tblclmnPhng.setWidth(119);
		tblclmnPhng.setText("Phòng");

		TableColumn tblclmnLoi = new TableColumn(table, SWT.NONE);
		tblclmnLoi.setWidth(88);
		tblclmnLoi.setText("Loại");

		TableColumn tblclmnGi = new TableColumn(table, SWT.NONE);
		tblclmnGi.setWidth(69);
		tblclmnGi.setText("Giá");

		TableColumn tblclmnSa = new TableColumn(table, SWT.NONE);
		tblclmnSa.setWidth(51);
		tblclmnSa.setText("Sửa");

		TableColumn tblclmnXo = new TableColumn(table, SWT.NONE);
		tblclmnXo.setWidth(100);
		tblclmnXo.setText("Xoá");

		Label lblDanhSchPhng = new Label(composite, SWT.NONE);
		lblDanhSchPhng.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblDanhSchPhng.setBounds(10, 180, 111, 14);
		lblDanhSchPhng.setText("Danh sách phòng");

		text_1 = new Text(composite, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_1.setBounds(108, 63, 248, 59);

		Label lblGhiCh = new Label(composite, SWT.NONE);
		lblGhiCh.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblGhiCh.setText("Ghi chú");
		lblGhiCh.setBounds(10, 66, 92, 14);

		selectTypeRoom();
		selectRoom();
	}
	private boolean saveRoom(int id ,String name,String note, int id_type) {
		if(name.isEmpty()) {
			ShowMessage.ShowError(shlQunLPhng,"Vui lòng nhập tên phòng", "Lỗi dữ liệu");

		}else if(id_type == 0) {
			ShowMessage.ShowError(shlQunLPhng,"Vui lòng chọn loại phòng", "Lỗi dữ liệu");

		}else {
			if(RoomBUS.saveRoom( current_id , name, note,  id_type)){
				current_id = 0;
				return true;
			}

		}
		return false;

	}

	Button[] editButtons ;
	Button[] removeButtons ;
	private boolean selectRoom() {
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

		ArrayList<Room> lsOb = RoomBUS.selectRoom();
		int size = lsOb.size();
		// Create five table editors for color
		editEditors = new TableEditor[size];
		removeEditors = new TableEditor[size];

		// Create five buttons for changing color
		editButtons = new Button[size];
		removeButtons = new Button[size];


		for (int i = 0; i < size; i++) {
			final TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] {(i + 1)+"",lsOb.get(i).getNAME(),lsOb.get(i).getTYPE(), Utill.formatCurrency(lsOb.get(i).getPRICE()) + "", "Sửa", "Xoá"});

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
			editEditors[i].setEditor(editButtons[i], item, 4);
			removeEditors[i].setEditor(removeButtons[i], item, 5);

		}
		for (int i = 0; i < size; i++) {
			String name = lsOb.get(i).getNAME();
			String note = lsOb.get(i).getNOTE();
			int id = lsOb.get(i).getID();


			editButtons[i].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					text.setText(name);
					text_1.setText(note);
					current_id = id;
				}
			});
			removeButtons[i].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(deleteRoom(id)) {
						ShowMessage.ShowError(shlQunLPhng,"Xoá dữ liệu thành công!", "Lỗi dữ liệu");
						selectRoom();
					}else {
						ShowMessage.ShowError(shlQunLPhng,"Không xoá được!", "Lỗi dữ liệu");
					}

				}
			});
		}

		return false;
	}
	ArrayList<TypeRoom> lsOb;
	private boolean selectTypeRoom() {

		lsOb = RoomBUS.selectTypeRoom();
		int size = lsOb.size();
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
		});
		return false;
	}
	private boolean deleteRoom(int id) {

		if(RoomBUS.deleteRoom(id)){
			current_id = 0;
			return true;
		}

		return false;
	}

}
