package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Text;

import bus.CustomerBUS;
import bus.UserBUS;
import utill.SWTResourceManager;
import utill.ShowMessage;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;

import dto.Config;
import dto.TypeCustomer;

public class ManagerCustomerType {

	protected Shell shlLoiKhchHng;
	private Text text;
	private Table table;
	private Text text_1;
	int current_id = 0;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ManagerCustomerType window = new ManagerCustomerType();
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
		shlLoiKhchHng.open();
		shlLoiKhchHng.layout();
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlLoiKhchHng.getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		shlLoiKhchHng.setLocation(x, y);
		while (!shlLoiKhchHng.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLoiKhchHng = new Shell();
		shlLoiKhchHng.setSize(450, 300);
		shlLoiKhchHng.setText("Loại khách hàng");

		Composite composite = new Composite(shlLoiKhchHng, SWT.NONE);
		composite.setLayout(null);
		composite.setBounds(10, 10, 430, 258);

		Label lblTnLoi = new Label(composite, SWT.NONE);
		lblTnLoi.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblTnLoi.setText("Tên loại");
		lblTnLoi.setBounds(10, 13, 92, 14);

		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text.setBounds(108, 10, 248, 19);

		Label lblHS = new Label(composite, SWT.NONE);
		lblHS.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblHS.setText("Hệ số");
		lblHS.setBounds(10, 39, 59, 14);

		Button btnLu = new Button(composite, SWT.NONE);
		btnLu.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnLu.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = text.getText();
				String surcharge = text_1.getText();
				if(selectIsInstall() || current_id !=0) {
					if(saveCustomerType(0,name,surcharge)) {
						selectTypeCustomer();
					};
				}else {
					ShowMessage.ShowError(shlLoiKhchHng,"Không thêm được ,Quá số lượng loại khách hàng cho phép!", "Lỗi dữ liệu");
				}
			}
		});
		btnLu.setText("Lưu");
		btnLu.setBounds(108, 63, 94, 34);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 103, 410, 145);

		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(29);
		tblclmnStt.setText("STT");

		TableColumn tblclmnLoiKhch = new TableColumn(table, SWT.NONE);
		tblclmnLoiKhch.setWidth(118);
		tblclmnLoiKhch.setText("Loại Khách");

		TableColumn tblclmnHS = new TableColumn(table, SWT.NONE);
		tblclmnHS.setWidth(130);
		tblclmnHS.setText("Hệ số");

		TableColumn tblclmnSa = new TableColumn(table, SWT.NONE);
		tblclmnSa.setWidth(70);
		tblclmnSa.setText("Sửa");

		TableColumn tblclmnXo = new TableColumn(table, SWT.NONE);
		tblclmnXo.setWidth(100);
		tblclmnXo.setText("Xoá");

		Label lblSLoiKhch = new Label(composite, SWT.NONE);
		lblSLoiKhch.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblSLoiKhch.setText("Số loại khách : tối đa 3");
		lblSLoiKhch.setBounds(10, 103, 192, 14);

		text_1 = new Text(composite, SWT.BORDER);
		text_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_1.setBounds(108, 36, 248, 19);

		selectTypeCustomer();

	}
	private boolean saveCustomerType(int id ,String name, String surcharge) {
		if(name.isEmpty()) {
			ShowMessage.ShowError(shlLoiKhchHng,"Vui lòng nhập tên loại khách hàng", "Lỗi dữ liệu");

		}else if(surcharge.isEmpty()) {
			ShowMessage.ShowError(shlLoiKhchHng,"Vui lòng nhập hệ số", "Lỗi dữ liệu");

		}else {
			
			if(CustomerBUS.saveCustomerType( id , name,  surcharge)){
				current_id = 0;
				return true;
			}

		}

		return false;
	}
	Button[] editButtons ;
	Button[] removeButtons ;
	private void selectTypeCustomer() {
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

		ArrayList<TypeCustomer> lsOb  = CustomerBUS.selectTypeCustomer();
		int size = lsOb.size();
		// Create five table editors for color
		editEditors = new TableEditor[size];
		removeEditors = new TableEditor[size];

		// Create five buttons for changing color
		editButtons = new Button[size];
		removeButtons = new Button[size];


		for (int i = 0; i < size; i++) {
			final TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] {(i+1) +"",lsOb.get(i).getNAME(), lsOb.get(i).getSURCHARGE() + "", "Sửa", "Xoá"});

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
			String name = lsOb.get(i).getNAME();
			double surcharge = lsOb.get(i).getSURCHARGE();
			int id = lsOb.get(i).getID();

			editButtons[i].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					text.setText(name);
					text_1.setText(surcharge+ "");
					current_id = id;
				}
			});
			removeButtons[i].addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(deleteCustomerType(id)) {
						ShowMessage.ShowError(shlLoiKhchHng,"Xoá dữ liệu thành công được!", "Lỗi dữ liệu");
						selectTypeCustomer();
					}else {
						ShowMessage.ShowError(shlLoiKhchHng,"Không xoá được!", "Lỗi dữ liệu");
					}

				}
			});
		}

	}
	private boolean selectIsInstall() {

		Config cf = UserBUS.selectConfig();
		int NUM_TYPE_CUSTOMER = cf.getNUM_TYPE_CUSTOMER();
		int NUM_CUSTOMER = cf.getNUM_OF_TYPE_CUSTOMER();

		if(NUM_TYPE_CUSTOMER > NUM_CUSTOMER) {
			return true;
		}
		return false;
	}
	private boolean deleteCustomerType(int id) {
		if( CustomerBUS.deleteCustomerType(id)){
			current_id = 0;
			return true;
		}
		return false;
	}

}
