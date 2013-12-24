

import org.eclipse.swt.events.KeyListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class View {

	Display display = new Display();
    Shell shell = new Shell(display);
	Browser b = new Browser(shell, SWT.WEBKIT);
    
	View(){
		
	}
	
	public void display() {
		b.setSize(shell.getSize());
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	public void addKeyListener(KeyListener k){
		b.addKeyListener(k);
	}
	
	public void setURL(String URL){
        b.setUrl(URL);
	}
}
