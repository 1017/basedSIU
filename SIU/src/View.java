

import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.browser.TitleListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
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
    final List urlList = new List(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
    Browser b = new Browser(shell, SWT.WEBKIT);
    final List labelList = new List(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
	View(){
		initialiseUI();
		
	}
	
	public void display() {
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	public void initialiseUI()
	{
		RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL);
        rowLayout.marginTop = 0;
        rowLayout.marginBottom = 0;
        rowLayout.marginLeft = 0;
        rowLayout.marginRight = 0;
        rowLayout.spacing = 0;
        shell.setLayout(rowLayout);
        
        
        urlList.setLayoutData(new RowData(shell.getSize().x-50, 100));
    	
    	b.setLayoutData(new RowData(shell.getSize().x-50,500));
        
    	
        labelList.setLayoutData(new RowData(shell.getSize().x-50, 140));
		//b.setSize(shell.getSize());
        
        
	}
	
	public void addSelectionListenerToURLList(SelectionListener sl)
	{
		urlList.addSelectionListener(sl);
	}
	
	public List getURLList()
	{
		return urlList;
	}

	public void addToURLList(String s)
	{
		urlList.add(s);
	}
	
	public void addToLabelList(String s)
	{
		labelList.add(s);
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

	public void clearLabelList() {
		labelList.removeAll();
		
	}
	
	public void clearURLList() {
		urlList.removeAll();
		
	}

	



}
