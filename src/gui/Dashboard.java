package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import bus.RoomBUS;
import bus.UserBUS;

import org.eclipse.swt.widgets.Button;

import dto.Config;
import dto.Room;
import dto.User;
import utill.SWTResourceManager;
import utill.Utill;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Monitor;

public class Dashboard {

	public static Shell shlQunLKhch;
	private Text text;
	Composite composite_4;
	Label lblaCh ;
	Label lblNewLabel;
	public Shell shell;
	Label lblNhnVinBi ;
	int STATUS = 0;//0 all/1 null//2 have//3 search
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Dashboard window = new Dashboard();
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
		shell.close();
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
		shlQunLKhch.setSize(1024, 600);
		shlQunLKhch.setText("Quản lý khách sạn");
		shlQunLKhch.setLayout(null);

		Composite composite = new Composite(shlQunLKhch, SWT.BORDER);
		composite.setBounds(10, 10, 1004, 106);

		lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Arial", 20, SWT.BOLD));
		lblNewLabel.setBounds(10, 10, 471, 30);
		lblNewLabel.setText("Khách sạn ABC");

		lblaCh = new Label(composite, SWT.NONE);
		lblaCh.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblaCh.setBounds(10, 46, 471, 20);
		lblaCh.setText("Địa chỉ: 123 Tô hiến thành, Quận 1, TP.HCM");

		lblNhnVinBi = new Label(composite, SWT.NONE);
		lblNhnVinBi.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblNhnVinBi.setBounds(10, 66, 276, 20);
		lblNhnVinBi.setText("Nhân viên: Bùi văn thương");

		text = new Text(composite, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text.setMessage("Tìm kiếm theo tên phòng ...");
		text.setBounds(487, 41, 407, 25);

		Button btnTmKim = new Button(composite, SWT.NONE);
		btnTmKim.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				STATUS = 3;
				selectRoom();
			}
		});
		btnTmKim.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 11, SWT.NORMAL));
		btnTmKim.setBounds(900, 39, 94, 30);
		btnTmKim.setText("Tìm kiếm");

		Composite composite_1 = new Composite(shlQunLKhch, SWT.BORDER);
		composite_1.setBounds(10, 122, 185, 446);

		Button btnNewButton = new Button(composite_1, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				CreateOrderRoom cr = new CreateOrderRoom();
				cr.open();
			}
		});
		btnNewButton.setBounds(10, 10, 165, 39);
		btnNewButton.setText("Lập phiếu thuê phòng");

		Button btnLpPhiuHo = new Button(composite_1, SWT.NONE);
		btnLpPhiuHo.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnLpPhiuHo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CreateReceiptOrder cr = new CreateReceiptOrder();
				cr.open();
			}
		});
		btnLpPhiuHo.setText("Lập hoá đơn thanh toán");
		btnLpPhiuHo.setBounds(10, 55, 165, 39);

		Button btnToDanhMc = new Button(composite_1, SWT.NONE);
		btnToDanhMc.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnToDanhMc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ManagerRoom romms = new ManagerRoom();
				romms.open();

			}
		});
		btnToDanhMc.setText("Tạo danh mục phòng");
		btnToDanhMc.setBounds(10, 100, 165, 39);

		Button btnLpBoCo = new Button(composite_1, SWT.NONE);
		btnLpBoCo.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnLpBoCo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Report rp=new Report();
				rp.open();
			}
		});
		btnLpBoCo.setText("Lập báo cáo tháng");
		btnLpBoCo.setBounds(10, 145, 165, 39);

		Button btnDanhSchKhch = new Button(composite_1, SWT.NONE);
		btnDanhSchKhch.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnDanhSchKhch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ManagerCustomer mc= new ManagerCustomer();
				mc.open();
			}
		});
		btnDanhSchKhch.setText("Danh sách khách hàng");
		btnDanhSchKhch.setBounds(10, 190, 165, 39);

		Button btnThitLp = new Button(composite_1, SWT.NONE);
		btnThitLp.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnThitLp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Setting st = new Setting();
				st.open();
			}
		});
		btnThitLp.setText("Thiết lập");
		btnThitLp.setBounds(10, 235, 165, 39);

		Button btnThot = new Button(composite_1, SWT.NONE);
		btnThot.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnThot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Utill.SaveLogin(false);
				Utill.SaveUser(new User());
				Login login = new Login();
				login.shell_ds = shlQunLKhch;
				shell = shlQunLKhch;
				login.open();
			}
		});
		btnThot.setText("Đăng xuất");
		btnThot.setBounds(10, 280, 165, 39);

		Button button = new Button(composite_1, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shlQunLKhch.close();
			}
		});
		button.setText("Thoát");
		button.setBounds(10, 325, 165, 39);

		Composite composite_2 = new Composite(shlQunLKhch, SWT.BORDER);
		composite_2.setBounds(201, 122, 813, 64);

		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(10, 10, 109, 14);
		lblNewLabel_1.setText("Danh sách phòng");
		Button btnNewButton_2 = new Button(composite_2, SWT.NONE);
		Button btnNewButton_3 = new Button(composite_2, SWT.NONE);
		Button btnNewButton_1 = new Button(composite_2, SWT.BOLD);
		btnNewButton_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				STATUS = 0;
				btnNewButton_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
				btnNewButton_2.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NONE));
				btnNewButton_3.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NONE));

				selectRoom();
			}
		});
		btnNewButton_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnNewButton_1.setBounds(20, 30, 94, 28);
		btnNewButton_1.setText("Tất cả");

		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				STATUS = 1;
				btnNewButton_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NONE));
				btnNewButton_2.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
				btnNewButton_3.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NONE));
				selectRoom();
			}
		});
		btnNewButton_2.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnNewButton_2.setBounds(120, 30, 94, 28);
		btnNewButton_2.setText("Trống");

		btnNewButton_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				STATUS = 2;
				btnNewButton_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NONE));
				btnNewButton_2.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NONE));
				btnNewButton_3.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
				selectRoom();
			}
		});
		btnNewButton_3.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnNewButton_3.setBounds(220, 30, 94, 28);
		btnNewButton_3.setText("Có khách");

		Label lblThiGianam = new Label(composite_2, SWT.NONE);
		lblThiGianam.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		lblThiGianam.setBounds(615, 10, 186, 14);
		lblThiGianam.setText("Thời gian: 12:20AM");

		Composite composite_3 = new Composite(shlQunLKhch, SWT.BORDER);
		composite_3.setBounds(201, 192, 813, 376);

		ScrolledComposite scrolledComposite = new ScrolledComposite(composite_3, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(10, 10, 791, 354);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		composite_4 = new Composite(scrolledComposite, SWT.NONE);
		scrolledComposite.setContent(composite_4);
		scrolledComposite.setMinSize(composite_4.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		selectRoom();


		Config cf =UserBUS.selectConfig();
		if( cf != null){
			lblaCh.setText("Địa chỉ: "+cf.getADDRESS());
			lblNewLabel.setText(""+ cf.getHOTEL_NAME());
			lblNhnVinBi.setText("Nhân viên: "+  Utill.GetSaveUser().getFULL_NAME());
		}

		Thread updateThread = new Thread() {
			public void run() {
				while (true) {

					try {


						Display.getDefault().syncExec(new Runnable() {
							@Override
							public void run() {
								if(shell == shlQunLKhch) {
									shlQunLKhch.close();
								}else {
									selectRoom();
									lblThiGianam.setText("Thời gian:" + new SimpleDateFormat(" HH:mm:ss dd/MM/YYYY").format(new Date()));
								}
							}
						});

						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		// background thread
		updateThread.setDaemon(true);
		updateThread.start();
	}
	private void selectRoom() {
		for (Control control : composite_4.getChildren()) {
			control.dispose();
		}
		String tx = text.getText();
		ArrayList<Room> lsOb = RoomBUS.selectRoom( STATUS, tx );
		int size = 0;
		int row = -1;
		for (Room ob : lsOb) {
			if(STATUS == 0 || STATUS ==3) {
				if(size % 5 == 0) {
					row ++;
					size = 0;
				}
				Button btnNewButton_4 = new Button(composite_4, SWT.NONE);
				btnNewButton_4.setBounds(10 +(150 * size), 10 + (95 * row) , 145, 86);
				btnNewButton_4.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 11, SWT.NORMAL));
				btnNewButton_4.setText("Phòng : "+ob.getNAME()+"\n"+ob.getTYPE()+"\nĐơn giá: "+ob.getPRICE()+"đ\nTrạng thái: "+(ob.getSTATUS_ROOM() == 2?"Có khách":"Trống"));
				if(ob.getSTATUS_ROOM() == 2) {
					btnNewButton_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				size ++;
			}
			if(STATUS == 1 && ob.getSTATUS_ROOM() == 0) {
				if(size % 5 == 0) {
					row ++;
					size = 0;
				}
				Button btnNewButton_4 = new Button(composite_4, SWT.NONE);
				btnNewButton_4.setBounds(10 +(150 * size), 10 + (95 * row) , 145, 86);
				btnNewButton_4.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 11, SWT.NORMAL));
				btnNewButton_4.setText("Phòng : "+ob.getNAME()+"\n"+ob.getTYPE()+"\nĐơn giá: "+ob.getPRICE()+"đ\nTrạng thái: "+(ob.getSTATUS_ROOM() == 2?"Có khách":"Trống"));
				if(ob.getSTATUS_ROOM() == 2) {
					btnNewButton_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				size ++;
			}
			if(STATUS == 2 && ob.getSTATUS_ROOM() == 2) {
				if(size % 5 == 0) {
					row ++;
					size = 0;
				}
				Button btnNewButton_4 = new Button(composite_4, SWT.NONE);
				btnNewButton_4.setBounds(10 +(150 * size), 10 + (95 * row) , 145, 86);
				btnNewButton_4.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 11, SWT.NORMAL));
				btnNewButton_4.setText("Phòng : "+ob.getNAME()+"\n"+ob.getTYPE()+"\nĐơn giá: "+ob.getPRICE()+"đ\nTrạng thái: "+(ob.getSTATUS_ROOM() == 2?"Có khách":"Trống"));
				if(ob.getSTATUS_ROOM() == 2) {
					btnNewButton_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				size ++;
			}
		}
	
		
	}

}
