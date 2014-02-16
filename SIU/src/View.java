

import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressListener;
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
	
	public void addProgressListener(ProgressListener p)
	{
		b.addProgressListener(p);
	}
	
	public void setURL(String URL){
        b.setUrl(URL);
	}
	
	public void highlightVisualBlocks() //http://stackoverflow.com/questions/5859440/how-to-use-jqueryui-in-swt-browser
	{
		String js = "function addLibraries()"
				+"{"
				+"var head = document.getElementsByTagName('head')[0];"

				+"var s = document.createElement(\"script\");"
    			+"s.setAttribute('id', 'firebug');"
    			+"s.setAttribute('src', 'https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js');"
    			+"s.setAttribute('type', 'text/javascript');"
    			+"head.appendChild(s);"    

    			/*+"s = document.createElement(\"script\");"       
    			+"s.setAttribute('src', 'https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/jquery-ui.min.js');"
    			+"s.setAttribute('type', 'text/javascript');"
    			+"head.appendChild(s);"

    			+"s = document.createElement(\"link\");"     
    			+"s.setAttribute('href', 'http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.12/themes/base/jquery-ui.css');"
    			+"s.setAttribute('type', 'text/css');"
    			+"s.setAttribute('media', 'all');"
    			+"s.setAttribute('rel', 'stylesheet');"
    			+"head.appendChild(s);"

    			+"s = document.createElement(\"link\");"     
    			+"s.setAttribute('href', 'http://static.jquery.com/ui/css/demo-docs-theme/ui.theme.css');"
    			+"s.setAttribute('type', 'text/css');"
    			+"s.setAttribute('media', 'all');"
    			+"s.setAttribute('rel', 'stylesheet');"
    			+"head.appendChild(s);"*/
    			+"}"
    			+"addLibraries();";

		String highlightVisualBlocks = "function isTextNode(){"
			 
				+"return( this.nodeType === 3 );"
			 
				+"}"
				+ ""
				+ "function highlightVisualBlocks()"
				+"{"
				+"$(\"form\").css(\"border\", \"3px solid red\");"
				/*+"$(\"form\").children().css(\"border\", \"3px solid green\");"
				+"$(\"form\").children().children().css(\"border\", \"3px solid blue\");"
				+"$(\"form\").find(\"input\").css(\"border\", \"3px solid blue\");"*/
				+"$('form').find('select,:submit,:checked,:selected,:text,textarea').css(\"border\", \"3px solid blue\");"
				//+"$('form').find('*').css(\"border\", \"3px solid green\");"
				//+"$('*').contents.filter(isTextNode).css(\"border\", \"3px solid yellow\");"
				/*+"$( 'form' )"
				  +".find('*')"
				    +".filter(function() {"
				     +" return this.nodeType === 3;"
				    +"}).wrap(\"<p></p>\");"*/
				+"$('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea').wrap(\"<x></x>\");"
						+"$( 'form' ).find('x').css(\"border\", \"3px solid yellow\");"
				//.each(function (index, value) { alert(index + \" : \" + value.nodeValue)});"
				//+"$(\"body\").css(\"background-color\", \"green\");"
				//+"alert(\"JavaScript, called from Java\");"
				+"}"
				+"setTimeout(highlightVisualBlocks, 500);";

		b.execute(js);
		b.execute(highlightVisualBlocks);
				
	}
}
