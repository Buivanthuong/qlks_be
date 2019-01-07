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
import bus.UserBUS;
import utill.SWTResourceManager;
import utill.ShowMessage;
import utill.Utill;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import dto.Config;
import dto.TypeRoom;

public class ManagerRoomType {

	protected Shell shlLoiPhng;
	private Text text;
	private Table table;
	private Text text_1;
	private int current_id = 0;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ManagerRoomType window = new ManagerRoomType();
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
		shlLoiPhng.open();
		shlLoiPhng.layout();
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlLoiPhng.getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		shlLoiPhng.setLocation(x, y);
		while (!shlLoiPhng.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLoiPhng = new Shell();
		shlLoiPhng.setSize(450, 300);
		shlLoiPhng.setText("Loại phòng");

		Composite composite = new Composite(shlLoiPhng, SWT.NONE);
		composite.setBounds(10, 10, 430, 258);
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 103, 410, 145);

		TableColumn tblclmnTnLoi = new TableColumn(table, SWT.NONE);
		tblclmnTnLoi.setWidth(100);
		tblclmnTnLoi.setText("Tên loại");

		TableColumn tblclmnnGi = new TableColumn(table, SWT.NONE);
		tblclmnnGi.setWidth(100);
		tblclmnnGi.setText("Đơn giá");

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Chỉnh sửa");

		TableColumn tblclmnXo = new TableColumn(table, SWT.NONE);
		tblclmnXo.setWidth(100);
		tblclmnXo.setText("Xoá");

		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		label.setText("Tên loại");
		label.setBounds(10, 13, 92, 14);

		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text.setBounds(108, 10, 248, 19);

		Label lblnGi = new Label(composite, SWT.NONE);
		lblnGi.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblnGi.setText("Đơn giá");
		lblnGi.setBounds(10, 39, 59, 14);

		Label lblSLoiPhng = new Label(composite, SWT.NONE);
		lblSLoiPhng.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblSLoiPhng.setBounds(10, 103, 192, 14);

		text_1 = new Text(composite, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_1.setBounds(108, 34, 248, 19);

		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = text.getText();
				String price = text_1.getText();
				if(selectIsInstall() || current_id !=0) {
					if(saveRoomType(0,name,price)) {
						selectTypeRoom();
					};
				}else {
					ShowMessage.ShowError(shlLoiPhng,"Không thêm được ,Quá số lượng loại phòng cho phép!", "Lỗi dữ liệu");
				}

			}
		});
		button.setText("Lưu");
		button.setBounds(108, 63, 94, 34);

		selectTypeRoom();

	}
	private boolean saveRoomType(int id ,String name, String price) {
		if(name.isEmpty()) {
			ShowMessage.ShowError(shlLoiPhng,"Vui lòng nhập tên loại phòng", "Lỗi dữ liệu");

		}else if(price.isEmpty()) {
			ShowMessage.ShowError(shlLoiPhng,"Vui lòng nhập đơn giá", "Lỗi dữ liệu");

		}else {

			if(RoomBUS.saveRoomType( current_id , name,  price)){
				current_id = 0;
				return true;
			}
		}
		return false;
	}

	Button[] editButtons ;
	Button[] removeButtons ;
	private boolean selectTypeRoom() {
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

		ArrayList<TypeRoom> lsOb  = RoomBUS.selectTypeRoom();
		int size = lsOb.size();
		// Create five table editors for color
		editEditors = new TableEditor[size];
		removeEditors = new TableEditor[size];

		// Create five buttons for changing color
		editButtons = new Button[size];
		removeButtons = new Button[size];


		for (int i = 0; i < size; i++) {
			final TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] {lsOb.get(i).getNAME(), Utill.formatCurrency(lsOb.get(i).getPRICE()) + "", "Sửa", "Xoá"});

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
			editEditors[i].setEditor(editButtons[i], item, 2);
			removeEditors[i].setEditor(removeButtons[i], item, 3);

		}
		for (int i = 0; i < size; i++) {
			String name = lsOb.get(i).getNAME();
			int price = lsOb.get(i).getPRICE();
			int id = lsOb.get(i).getID();


			editButtons[i].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					text.setText(name);
					text_1.setText(price+ "");
					current_id = id;
				}
			});
			removeButtons[i].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(deleteRoomType(id)) {
						ShowMessage.ShowError(shlLoiPhng,"Xoá dữ liệu thành công được!", "Lỗi dữ liệu");
						selectTypeRoom();
					}else {
						ShowMessage.ShowError(shlLoiPhng,"Không xoá được!", "Lỗi dữ liệu");
					}

				}
			});
		}

		return false;
	}
	private boolean selectIsInstall() {
		Config cf = UserBUS.selectConfig();
		int NUM_TYPE_ROOM = cf.getNUM_TYPE_ROOM();
		int NUM_ROOM = cf.getNUM_OF_TYPE_ROOM();

		if(NUM_TYPE_ROOM > NUM_ROOM) {
			return true;
		}
		return false;
	}
	private boolean deleteRoomType(int id) {

		if( RoomBUS.deleteRoomType(id)){
			current_id = 0;
			return true;
		}
		return false;
	}
}
