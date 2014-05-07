

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
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.List;

public class Controller {
	View view;
	SearchInterfaces sEngines;
	Left l = new Left();
	Right r = new Right();
	ProgressListener p = new ProgressL();
	TitleListener t = new TitleL();
	SelectionL sl = new SelectionL();
	boolean pageLoaded = false;
	int attempt = 1;
	int jQueryTimeout = 100;
	
	Controller(View view, SearchInterfaces se){
		this.view = view;
		this.sEngines = se;
		
		addSEstoList();
		view.addKeyListener(l);
		view.addKeyListener(r);
		view.addProgressListener(p);
		view.addTitleListener(t);
		sl.setURLList(view.getURLList());
		view.addSelectionListenerToURLList(sl);
		view.setURL(sEngines.getCurrentValue().getUrl());
	}
	
	private void addSEstoList()
	{
			for (int i = 0; i < sEngines.listOfSearchEngines.size(); i++)
			{
				view.addToURLList(sEngines.listOfSearchEngines.get(i).getUrl());
			}
	}
	

	private void displayAttributes()
	{
		view.clearLabelList();
		for (int i = 0; i<sEngines.getCurrentValue().getAttributes().size(); i++)
		{
			view.addToLabelList(sEngines.getCurrentValue().getAttributes().get(i).toString());
		}
	}
	
	
	private void loadJQuery()
	{
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
		view.executeJS(js);

		//change the title in 100ms so we can check in the text listener if JQuery has loaded yet
		js = "setTimeout(function() { document.title = \"Attempt 1\" }, "+jQueryTimeout+")"; 
		view.executeJS(js);
	}
	
	private void highlightVisualBlocks()
	{
		/*int formNum = sEngines.getCurrentValue().getDefaultForm();
		String highlightVisualBlocks = 
		"function highlightVisualBlocks()"
		+"{"
		+"$(\"form:nth-child("+formNum+")\").css(\"border\", \"1px solid red\");"
		+"$('form:nth-child("+formNum+")').find('select,:submit,:checked,:selected,:text,textarea').css(\"border\", \"1px solid blue\");"
		+"$('form:nth-child("+formNum+")').find('input[type=image]').css(\"border\", \"1px solid green\");"
		+"$('form:nth-child("+formNum+")').find(':checkbox,:radio').wrap('<span></span>').parent().css(\"border\", \"1px solid blue\");"
		+"$('form:nth-child("+formNum+")').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea').wrap(\"<span></span>\").parent().css(\"border\", \"1px solid yellow\");"
		+"}"
		+"highlightVisualBlocks();";*/
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
	

	class TitleL implements TitleListener {

		@Override
		public void changed(TitleEvent event) { //check if JQuery library has been loaded from URL, if not, check again
			System.out.println("TitleEvent");
			if (pageLoaded && (attempt<200)){ //only need to check this if the page has been fully loaded
				String js = "$('form').find('select,:submit,:checked,:selected,:text,textarea').toArray()";
				try {
					view.executeJS(js);
					highlightVisualBlocks();
					System.out.println("Works: " + view.evaluateJS(js));
					//extractVisualBlocks();
					sEngines.getCurrentValue().understand(view);
					displayAttributes();
				} catch (SWTException e) { //if JQuery hasn't been loaded yet, change the title so we can check again in 100ms
					String j = "setTimeout(function() { document.title = \"Attempt "+ ++attempt +"\" }, "+jQueryTimeout+")";	
					System.out.println(j);		
					view.executeJS(j);			
					System.out.println("No JQ bro" + e);
				}
			}	
			else if (attempt >= 200)
				view.addToLabelList("ERROR: JQUERY COULD NOT BE LOADED ON THIS PAGE");
		}
		
		
	}
	
	class SelectionL implements SelectionListener
 {
		List urlList;
		
		public void setURLList(List list)
		{
			urlList = list;
		}

        public void widgetSelected(SelectionEvent event) {
          /*int[] selections = urlList.getSelectionIndices();
          String outText = "";
          for (int i = 0; i < selections.length; i++)
            outText += selections[i] + " ";
          System.out.println("You selected: " + outText);*/
        	

			view.setURL(sEngines.getValue(urlList.getFocusIndex()).getUrl());
			pageLoaded=false;
			attempt = 1;
        }

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
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
