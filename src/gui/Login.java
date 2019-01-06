package gui;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import bus.UserBUS;
import utill.ConnectionUtils;
import utill.DatabaseHelper;
import utill.SWTResourceManager;
import utill.ShowMessage;
import utill.Utill;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;

import dto.User;
public class Login {

	protected Shell shell;
	private Text text;
	private Text text_1;
	static Login window;
	public Shell shell_ds;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			window = new Login();
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
		shell.open();
		shell.layout();
		Monitor primary = display.getPrimaryMonitor();
	    Rectangle bounds = primary.getBounds();
	    Rectangle rect = shell.getBounds();
	    
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    
	    shell.setLocation(x, y);
	    
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Đăng nhập");
		
		Label lblTnTiKhon = new Label(shell, SWT.NONE);
		lblTnTiKhon.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblTnTiKhon.setBounds(63, 49, 107, 14);
		lblTnTiKhon.setText("Tên tài khoản");
		
		text = new Text(shell, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text.setBounds(176, 46, 169, 19);
		
		Label lblMtKhu = new Label(shell, SWT.NONE);
		lblMtKhu.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		lblMtKhu.setText("Mật khẩu");
		lblMtKhu.setBounds(63, 78, 107, 14);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		text_1.setBounds(176, 78, 169, 19);
		
		Button btnGhiNhTi = new Button(shell, SWT.CHECK);
		btnGhiNhTi.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnGhiNhTi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				Utill.SaveLogin(btnGhiNhTi.getSelection());
			}
		});
		btnGhiNhTi.setSelection(Utill.GetSaveLogin());
		btnGhiNhTi.setBounds(176, 103, 219, 18);
		btnGhiNhTi.setText("Ghi nhớ tài khoản đăng nhập");
		
		Button btnngNhp = new Button(shell, SWT.NONE);
		btnngNhp.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnngNhp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String user_name = text.getText();
				String password = text_1.getText();
			
				if(checkLogin(user_name, password)) {
					Dashboard ds = new Dashboard();
					ds.shell = shell;
					ds.createContents();
					ds.open();
				};
				
			}

			});

		btnngNhp.setBounds(95, 143, 115, 40);
		btnngNhp.setText("Đăng nhập");
		
		Button btnThot = new Button(shell, SWT.NONE);
		btnThot.setFont(SWTResourceManager.getFont("Arial", 11, SWT.NORMAL));
		btnThot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnThot.setText("Thoát");
		btnThot.setBounds(230, 143, 115, 40);

		checkSaveLogin();
			
	}
	private boolean checkSaveLogin() {
		if(Utill.GetSaveLogin()) {
			Dashboard ds = new Dashboard();
			ds.createContents();
			ds.open();
			shell.close();
		}
		return false;
	}
	private boolean checkLogin(String user_name, String password) {
		if(user_name.isEmpty()) {
			ShowMessage.ShowError(shell,"Vui lòng nhập tên tài khoản", "Lỗi dữ liệu");

		}else if(password.isEmpty()) {
			ShowMessage.ShowError(shell,"Vui lòng nhập mật khẩu", "Lỗi dữ liệu");

		}else if(!UserBUS.LoginUser(user_name, password)) {
			ShowMessage.ShowError(shell,"Tài khoản hoặc mật khẩu không chính xác!", "Lỗi dữ liệu");

		}else {
			return true;
		}
		
		ShowMessage.ShowError(shell,"Lỗi hệ thống không thể lưu dữ liệu", "Lỗi hệ thống");

		return false;
	}
}
