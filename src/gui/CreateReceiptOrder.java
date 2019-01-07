package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import bus.CustomerBUS;
import bus.OrderBUS;
import utill.SWTResourceManager;
import utill.ShowMessage;
import utill.Utill;
import dto.Customer;
import dto.Order;

public class CreateReceiptOrder {

	protected Shell shlLpHon;
	private Table table;
	private Text text;
	Combo combo_2;
	Combo combo_1;
	Label lblTngTin;
	int current_customer_id  = 0;
	ArrayList<Order> lsObs = new ArrayList<>();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CreateReceiptOrder window = new CreateReceiptOrder();
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
		shlLpHon.open();
		shlLpHon.layout();
		Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = shlLpHon.getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		shlLpHon.setLocation(x, y);
		while (!shlLpHon.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLpHon = new Shell();
		shlLpHon.setSize(450, 342);
		shlLpHon.setText("Lập hoá đơn thanh toán");

		Composite composite = new Composite(shlLpHon, SWT.NONE);
		composite.setBounds(10, 10, 430, 300);

		Label lblaCh = new Label(composite, SWT.NONE);
		lblaCh.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblaCh.setText("�?ịa chỉ");
		lblaCh.setBounds(10, 41, 116, 14);

		Label lblKhchHng = new Label(composite, SWT.NONE);
		lblKhchHng.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblKhchHng.setText("Khách hàng");
		lblKhchHng.setBounds(10, 10, 116, 14);

		Label lblDanhSchHo = new Label(composite, SWT.NONE);
		lblDanhSchHo.setText("Danh sách hoá đơn");
		lblDanhSchHo.setBounds(10, 104, 159, 14);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 124, 430, 87);

		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(27);
		tableColumn.setText("STT");

		TableColumn tblclmnPhng = new TableColumn(table, SWT.NONE);
		tblclmnPhng.setWidth(100);
		tblclmnPhng.setText("Phòng");

		TableColumn tblclmnNgyThu = new TableColumn(table, SWT.NONE);
		tblclmnNgyThu.setWidth(76);
		tblclmnNgyThu.setText("Ngày thuê");

		TableColumn tblclmnnGi = new TableColumn(table, SWT.NONE);
		tblclmnnGi.setWidth(69);
		tblclmnnGi.setText("Đơn giá");

		TableColumn tblclmnThnhTin = new TableColumn(table, SWT.NONE);
		tblclmnThnhTin.setWidth(100);
		tblclmnThnhTin.setText("Thành tiền");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("Xoá");

		combo_1 = new Combo(composite, SWT.READ_ONLY);
		combo_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		combo_1.setBounds(132, 6, 149, 22);

		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(button.getText().equals("Tạo mới")) {
					button.setText("Cập nhật");
					ManagerCustomer r = new ManagerCustomer();
					r.open();
				}else {
					button.setText("Tạo mới");
					selectCustomer();
				}

			}
		});
		button.setText("Tạo mới");
		button.setBounds(287, 3, 73, 28);

		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int sum = 0;
				for (Order order : lsObs) {
					sum+= order.getPRICE();
				}
				saveReceiptOrder(current_customer_id, sum, text.getText(), lsObs);

			}
		});
		button_1.setText("Lưu hoá đơn");
		button_1.setBounds(111, 252, 104, 38);

		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLpHon.close();
			}
		});
		button_2.setText("Huỷ");
		button_2.setBounds(221, 252, 94, 38);

		Label lblThmHon = new Label(composite, SWT.NONE);
		lblThmHon.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblThmHon.setText("Thêm hoá đơn");
		lblThmHon.setBounds(10, 77, 116, 14);

		combo_2 = new Combo(composite, SWT.READ_ONLY);
		combo_2.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		combo_2.setBounds(132, 73, 149, 22);

		Button button_3 = new Button(composite, SWT.NONE);
		button_3.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		button_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(button_3.getText().equals("Tạo mới")) {
					button_3.setText("Cập nhật");
					CreateOrderRoom r = new CreateOrderRoom();
					r.open();
				}else {
					button_3.setText("Tạo mới");
					selectCustomer();
				}

			}
		});
		button_3.setText("Tạo mới");
		button_3.setBounds(287, 70, 73, 28);

		lblTngTin = new Label(composite, SWT.NONE);
		lblTngTin.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		lblTngTin.setText("Tổng tiền: 0");
		lblTngTin.setBounds(261, 217, 159, 14);

		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text.setBounds(132, 36, 228, 19);
		selectCustomer();
		selectOrderRoom();
	}
	ArrayList<Customer> lsOb_c;
	private void selectCustomer() {

		lsOb_c = CustomerBUS.selectCustomer();
		int size = lsOb_c.size();
		String[] strings = new String[size];
		for (int i = 0; i < size; i++) {
			strings[i] = lsOb_c.get(i).getNAME();	
		}
		combo_1.setItems(strings);
		combo_1.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				current_customer_id = lsOb_c.get(combo_1.getSelectionIndex()).getID();
				text.setText(lsOb_c.get(combo_1.getSelectionIndex()).getADDRESS());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}});

	}
	ArrayList<Order> lsOb_o;
	private void selectOrderRoom() {

		lsOb_o = OrderBUS.selectOrderRoom();
		int size = lsOb_o.size();
		String[] strings = new String[size];
		for (int i = 0; i < size; i++) {
			strings[i] = lsOb_o.get(i).getROOM_NAME();	
		}
		combo_2.setItems(strings);
		combo_2.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				lsObs.add(lsOb_o.get(combo_2.getSelectionIndex()));
				showOrderRoom(lsObs);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}});

	}
	Button[] removeButtons ;
	private void showOrderRoom(ArrayList<Order> lsOb) {
		int size = lsOb.size();

		table.removeAll();
		if(removeButtons != null) {
			for (Button button : removeButtons) {
				button.dispose();
			}
		}

		TableEditor[] removeEditors;
		removeEditors = new TableEditor[size];
		removeButtons = new Button[size];

		int sum = 0;
		for (Order order : lsObs) {
			sum+= order.getPRICE();
		}
		lblTngTin.setText("Tổng tiền: " + Utill.formatCurrency(sum));

		for (int i = 0; i < size; i++) {
			final TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] {(i + 1)+"",lsOb.get(i).getROOM_NAME(),lsOb.get(i).getNUM_DATE() + "",Utill.formatCurrency(lsOb.get(i).getPRICE())+ "",Utill.formatCurrency(lsOb.get(i).getAMOUNT()) + "", "Xoá"});

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
					showOrderRoom(lsObs);
				}
			});
		}


	}
	private void saveReceiptOrder(int customer_id,int price ,String address, ArrayList<Order> ls) {
		if(customer_id == 0) {
			ShowMessage.ShowError(shlLpHon,"Vui lòng chọn khách hàng", "Lỗi dữ liệu");

		}else if(ls.size() == 0) {
			ShowMessage.ShowError(shlLpHon,"Vui lòng chọn hoá đơn", "Lỗi dữ liệu");

		}else {
			
			if(OrderBUS.saveReceiptOrder(customer_id, price, address, ls))
			{
				shlLpHon.close();
			}

		}
	}

}
