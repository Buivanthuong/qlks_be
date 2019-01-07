package utill;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

public class ShowMessage {
public static void ShowError(Shell parent ,String error, String title) {
	// create a jframe
		Shell dialog = new Shell(parent, SWT.DIALOG_TRIM
	        | SWT.APPLICATION_MODAL);
		dialog.setSize(330, 210);
		dialog.setText("Thông báo");
		dialog.setLayout(null);
		
		Label lblNewLabel = new Label(dialog, SWT.WRAP);
		lblNewLabel.setBounds(10, 25, 280, 103);
		lblNewLabel.setText(error);
		
		Button btnThot = new Button(dialog, SWT.NONE);
		btnThot.setBounds(196, 134, 94, 33);
		btnThot.setText("Đóng");
		btnThot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {dialog.close();
			}
		});
	    dialog.open();
	    Display display = parent.getDisplay();
	    
	    Monitor primary = display.getPrimaryMonitor();
		Rectangle bounds = primary.getBounds();
		Rectangle rect = dialog.getBounds();

		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;

		dialog.setLocation(x, y);
	    
	    
	    while (!dialog.isDisposed()) {
	      if (!display.readAndDispatch())
	        display.sleep();
	    }
}
}
