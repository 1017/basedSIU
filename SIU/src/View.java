

import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.browser.TitleListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Object;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

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
	
	public void addProgressListener(ProgressListener p)
	{
		b.addProgressListener(p);
	}
	
	public void addTitleListener(TitleListener s) {
		b.addTitleListener(s);
	}
	
	public void setURL(String URL){
        b.setUrl(URL);		
	}

	public void executeJS(String js)
	{
		b.execute(js);		
	}
	
	public Object evaluateJS(String js)
	{
		return b.evaluate(js);
	}

	



}
