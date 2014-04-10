

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.browser.StatusTextEvent;
import org.eclipse.swt.browser.StatusTextListener;
import org.eclipse.swt.browser.TitleEvent;
import org.eclipse.swt.browser.TitleListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;

public class Controller {
	View view;
	SearchInterfaces sEngines;
	Left l = new Left();
	Right r = new Right();
	ProgressListener p = new ProgressL();
	TitleListener t = new TitleL();
	boolean pageLoaded = false;
	int attempt = 1;
	
	Controller(View view, SearchInterfaces se){
		this.view = view;
		this.sEngines = se;
		
		view.addKeyListener(l);
		view.addKeyListener(r);
		view.addProgressListener(p);
		view.addTitleListener(t);
		view.setURL(sEngines.getCurrentValue().getUrl());
	}
	
	private void loadJQuery()
	{
		String js = "function addLibraries()"  //http://stackoverflow.com/questions/5859440/how-to-use-jqueryui-in-swt-browser
				+"{"
				+"var head = document.getElementsByTagName('head')[0];"

				+"var s = document.createElement(\"script\");"
    			+"s.setAttribute('id', 'firebug');"
    			+"s.setAttribute('src', 'https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js');"
    			+"s.setAttribute('type', 'text/javascript');"
    			+"head.appendChild(s);"    
    			+"}"
    			+"addLibraries();";
		view.executeJS(js);

		//change the title in 100ms so we can check in the text listener if JQuery has loaded yet
		js = "setTimeout(function() { document.title = \"Attempt 1\" }, 100)"; 
		view.executeJS(js);
	}
	
	private void highlightVisualBlocks()
	{
		String highlightVisualBlocks = 
		"function highlightVisualBlocks()"
		+"{"
		+"$(\"form\").css(\"border\", \"1px solid red\");"
		+"$('form').find('select,:submit,:checked,:selected,:text,textarea').css(\"border\", \"1px solid blue\");"
		+"$('form').find('input[type=image]').css(\"border\", \"1px solid green\");"
		+"$('form').find(':checkbox,:radio').wrap('<span></span>').parent().css(\"border\", \"1px solid blue\");"
		+"$('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea').wrap(\"<span></span>\").parent().css(\"border\", \"1px solid yellow\");"
		+"}"
		+"highlightVisualBlocks();";
		
		view.executeJS(highlightVisualBlocks);
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
		view.executeJS(j);
		//System.out.println(((Object[])((Object[])b.evaluate(j))[1]));
		//Object[] o = b.evaluate(j);
		//System.out.println("VB " +b.evaluate(j));
		//System.out.println(Arrays.deepToString(o));
	}
	
	class TitleL implements TitleListener {

		@Override
		public void changed(TitleEvent event) {
			System.out.println("TitleEvent");
			if (pageLoaded && (attempt<200)){ //only need to check this if the page has been fully loaded
				String js = "$('form').find('select,:submit,:checked,:selected,:text,textarea').toArray()";
				try {
					view.executeJS(js);
					highlightVisualBlocks();
					System.out.println("Works: " + view.evaluateJS(js));
					//extractVisualBlocks();
					sEngines.getCurrentValue().extractVisualBlocks(view);
				} catch (SWTException e) { //if JQuery hasn't been loaded yet, change the title so we can check again in 100ms
					String j = "setTimeout(function() { document.title = \"Attempt "+ ++attempt +"\" }, 100)";	
					System.out.println(j);		
					view.executeJS(j);			
					System.out.println("No JQ bro" + e);
				}
			}	
		}
		
		
	}
	
	class ProgressL implements ProgressListener {

		@Override
		public void changed(ProgressEvent event) {
			System.out.println("Current loading: " + event.current);
			
		}

		@Override
		public void completed(ProgressEvent event) {
			System.out.println("Browser Completed Loading");
			pageLoaded = true;
			loadJQuery();
			
		}
		
	}
	

	class Left implements KeyListener {
	
		public void keyPressed(KeyEvent e) {
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.keyCode == SWT.ARROW_LEFT)
			{
				view.setURL(sEngines.getPreviousValue().getUrl());
				pageLoaded=false;
				attempt = 1;
			}
			
		}
		
	}
	class Right implements KeyListener {
	
		public void keyPressed(KeyEvent e) {
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.keyCode == SWT.ARROW_RIGHT)
			{
				view.setURL(sEngines.getNextValue().getUrl());	
				pageLoaded=false;	
				attempt = 1;		
			}
			
		}
		
	}
}
