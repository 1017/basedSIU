

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
	Semaphore s = new Semaphore(0);
	boolean loaded;
	int attempt = 1;
    
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
		loaded = false;
        b.setUrl(URL);
		
	}
	
	public void loadJQuery() //http://stackoverflow.com/questions/5859440/how-to-use-jqueryui-in-swt-browser
	{
		loaded = true; //this is only called after the page has loaded
		
		String js = "function addLibraries()"
				+"{"
				+"var head = document.getElementsByTagName('head')[0];"

				+"var s = document.createElement(\"script\");"
    			+"s.setAttribute('id', 'firebug');"
    			+"s.setAttribute('src', 'https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js');"
    			+"s.setAttribute('type', 'text/javascript');"
    			+"head.appendChild(s);"    
    			+"}"
    			+"addLibraries();";
		b.execute(js);

		//change the title in 100ms so we can check in the text listener if JQuery has loaded yet
		js = "setTimeout(function() { document.title = \"Attempt 1\" }, 100)"; 
		b.execute(js);
				
	}

	public void checkJQuery() {
		if (loaded){ //only need to check this if the page has been fully loaded
			String js = "$('form').find('select,:submit,:checked,:selected,:text,textarea').toArray()";
			try {
				b.execute(js);
				highlightVisualBlocks();
				System.out.println("Works: " + b.evaluate(js));
				extractVisualBlocks();
			} catch (SWTException e) { //if JQuery hasn't been loaded yet, change the title so we can check again in 100ms
				String j = "setTimeout(function() { document.title = \"Attempt "+ ++attempt +"\" }, 100)";	
				System.out.println(j);		
				b.execute(j);			
				System.out.println("No JQ bro" + e);
			}
		}		
	}
	
	private void highlightVisualBlocks()
	{
		String highlightVisualBlocks = 
		"function highlightVisualBlocks()"
		+"{"
		+"$(\"form\").css(\"border\", \"1px solid red\");"
		+"$('form').find('select,:submit,:checked,:selected,:text,textarea').css(\"border\", \"1px solid blue\");"
		+"$('form').find(':radio').wrap('<span></span>').parent().css(\"border\", \"1px solid blue\");"
		+"$('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea').wrap(\"<span></span>\").parent().css(\"border\", \"1px solid yellow\");"
		+"}"
		+"highlightVisualBlocks();";
		
		b.execute(highlightVisualBlocks);
	}
	
	private void extractVisualBlocks()
	{
		String j = 
		//"function test () { "
		 "var arr;"
		+ "arr = $('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea, option').each(function(){ if ($(this).parent().height() != 0) { alert(this.wholeText);}});"
		//+"arr = $('form').find('select,:submit,:checked,:selected,:text,textarea').html().toArray();"
		//+ ".each(function () { arr[$(this).val()] = $(this).css(\"text-size\")}); "
		//+ "return $('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea').css(\"text-size\").toArray(); "
		+ "return arr;";
		//+ "}"
		//+ "test();";
		b.execute(j);
		//System.out.println(((Object[])((Object[])b.evaluate(j))[1]));
		//Object[] o = b.evaluate(j);
		//System.out.println("VB " +b.evaluate(j));
		//System.out.println(Arrays.deepToString(o));
	}


}
