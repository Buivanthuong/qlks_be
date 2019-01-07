package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import bus.RoomBUS;
import utill.PrintPDF;
import utill.SWTResourceManager;
import utill.Utill;
import dto.Room;
import dto.TypeRoom;

public class Report {

	protected Shell shlLpBoCo;
	private Table table;
	private Table table_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Report window = new Report();
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
		shlLpBoCo.open();
		shlLpBoCo.layout();
		Monitor primary = display.getPrimaryMonitor();
	    Rectangle bounds = primary.getBounds();
	    Rectangle rect = shlLpBoCo.getBounds();
	    
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    
	    shlLpBoCo.setLocation(x, y);
		while (!shlLpBoCo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLpBoCo = new Shell();
		shlLpBoCo.setSize(450, 300);
		shlLpBoCo.setText("Lập báo cáo");
		
		TabFolder tabFolder = new TabFolder(shlLpBoCo, SWT.NONE);
		tabFolder.setBounds(10, 10, 430, 258);
		
		TabItem tbtmLpBoCo = new TabItem(tabFolder, SWT.NONE);
		tbtmLpBoCo.setText("Doanh thu theo loại phòng");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmLpBoCo.setControl(composite);
		composite.setLayout(null);
		
		Label lblThng = new Label(composite, SWT.NONE);
		lblThng.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblThng.setBounds(10, 10, 59, 14);
		lblThng.setText("Tháng");
		
		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		combo.setItems(new String[] {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"});
		combo.setBounds(75, 6, 185, 22);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblNewLabel.setBounds(10, 30, 216, 14);
		lblNewLabel.setText("Danh sách báo cáo theo loại phòng");
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		table.setBounds(10, 50, 390, 131);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnStt = new TableColumn(table, SWT.NONE);
		tblclmnStt.setWidth(31);
		tblclmnStt.setText("STT");
		
		TableColumn tblclmnLoiPhng = new TableColumn(table, SWT.NONE);
		tblclmnLoiPhng.setWidth(100);
		tblclmnLoiPhng.setText("Loại phòng");
		
		TableColumn tblclmnDoanhThu = new TableColumn(table, SWT.NONE);
		tblclmnDoanhThu.setWidth(100);
		tblclmnDoanhThu.setText("Doanh thu");
		
		TableColumn tblclmnTL = new TableColumn(table, SWT.NONE);
		tblclmnTL.setWidth(100);
		tblclmnTL.setText("Tỷ lệ %");
		
		Button btnInPdf = new Button(composite, SWT.NONE);
		btnInPdf.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnInPdf.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				printPFDType();
			}
		});
		btnInPdf.setBounds(106, 187, 94, 28);
		btnInPdf.setText("In PDF");
		
		Button btnng = new Button(composite, SWT.NONE);
		btnng.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnng.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLpBoCo.close();
			}
		});
		btnng.setBounds(206, 187, 94, 28);
		btnng.setText("Đóng");
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Mật độ sử dụng phòng");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_1);
		composite_1.setLayout(null);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		label.setText("Tháng");
		label.setBounds(10, 4, 59, 14);
		
		Combo combo_1 = new Combo(composite_1, SWT.READ_ONLY);
		combo_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		combo_1.setItems(new String[] {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"});
		combo_1.setBounds(75, 0, 185, 22);
		
		Label lblDanhSchBo = new Label(composite_1, SWT.NONE);
		lblDanhSchBo.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblDanhSchBo.setText("Danh sách báo cáo theo mức độ sử dụng phòng");
		lblDanhSchBo.setBounds(10, 24, 279, 14);
		
		table_1 = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(10, 44, 390, 139);
		
		TableColumn tblclmnStt_1 = new TableColumn(table_1, SWT.NONE);
		tblclmnStt_1.setWidth(38);
		tblclmnStt_1.setText("STT");
		
		TableColumn tblclmnTnPhng = new TableColumn(table_1, SWT.NONE);
		tblclmnTnPhng.setWidth(100);
		tblclmnTnPhng.setText("Tên phòng");
		
		TableColumn tblclmnDoanhThu_1 = new TableColumn(table_1, SWT.NONE);
		tblclmnDoanhThu_1.setWidth(100);
		tblclmnDoanhThu_1.setText("Doanh thu");
		
		TableColumn tblclmnTL_1 = new TableColumn(table_1, SWT.NONE);
		tblclmnTL_1.setWidth(100);
		tblclmnTL_1.setText("Tỷ lệ");
		
		Button button = new Button(composite_1, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				printPFDRoom();
			}
		});
		button.setText("In PDF");
		button.setBounds(106, 189, 94, 28);
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlLpBoCo.close();
			}
		});
		button_1.setText("Đóng");
		button_1.setBounds(206, 189, 94, 28);
		combo.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				selectTyeRoom(combo.getSelectionIndex() + 1);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}});
		combo_1.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				selectRoom(combo_1.getSelectionIndex() + 1);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}});
	}
	protected void printPFDType() {
		// TODO Auto-generated method stub
		PrintPDF.PrintFilePDF("Doanh thu theo loại phòng","Danh sách báo cáo",shlLpBoCo, lsb,new ArrayList<Room>());
	}
	protected void printPFDRoom() {
		// TODO Auto-generated method stub
		PrintPDF.PrintFilePDF("Doanh thu theo loại phòng","Danh sách báo cáo",shlLpBoCo, new ArrayList<TypeRoom>(),lsb2);
	}
	ArrayList<Room> lsb2;
	private boolean selectRoom(int month) {
		table_1.removeAll();
		lsb2 = RoomBUS.selectRoom(month);
		int size = lsb2.size();
		double sum = 0;
		for ( Room room : lsb2) {
			sum += room.getPRICE();
		}
		
		for (int i = 0; i < size; i++) {
			final TableItem item = new TableItem(table_1, SWT.NONE);
			item.setText(new String[] {(i + 1)+"",lsb2.get(i).getNAME() ,Utill.formatCurrency(lsb2.get(i).getPRICE()) + "",String.format("%10.1f",(lsb2.get(i).getPRICE()/sum  * 100)) + "%" });

		}
		return false;
	}
	ArrayList<TypeRoom> lsb;
	private boolean selectTyeRoom(int month) {
		
		table.removeAll();
		lsb = RoomBUS.selectTyeRoom(month);
		int size = lsb.size();
		double sum = 0;
		for ( TypeRoom room : lsb) {
			sum += room.getPRICE();
		}
		for (int i = 0; i < size; i++) {
			final TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] {(i + 1)+"",lsb.get(i).getNAME() ,Utill.formatCurrency(lsb.get(i).getPRICE()) + "",String.format("%10.1f",(lsb.get(i).getPRICE()/sum  * 100)) + "%" });
		}
		
		return false;
	}
}
