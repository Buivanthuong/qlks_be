package gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Dialog {

	protected Shell shlThngBo;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Dialog window = new Dialog();
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
		shlThngBo.open();
		shlThngBo.layout();
		while (!shlThngBo.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlThngBo = new Shell();
		shlThngBo.setSize(350, 205);
		shlThngBo.setText("Thông báo");
		shlThngBo.setLayout(null);
		
		Label lblNewLabel = new Label(shlThngBo, SWT.WRAP);
		lblNewLabel.setBounds(10, 23, 280, 80);
		lblNewLabel.setText("Thông báo");
		
		Button btnThot = new Button(shlThngBo, SWT.WRAP);
		btnThot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnThot.setBounds(210, 124, 94, 33);
		btnThot.setText("Thoát");
	}
}
