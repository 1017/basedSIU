

import org.eclipse.swt.SWT;
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
	SearchEngines sEngines;
	Left l = new Left();
	Right r = new Right();
	ProgressListener p = new ProgressL();
	TitleListener t = new TitleL();
	
	Controller(View view, SearchEngines se){
		this.view = view;
		this.sEngines = se;
		
		view.addKeyListener(l);
		view.addKeyListener(r);
		view.addProgressListener(p);
		view.addTitleListener(t);
		view.setURL(sEngines.getCurrentValue());
	}
	
	class TitleL implements TitleListener {

		@Override
		public void changed(TitleEvent event) {
			System.out.println("TitleEvent");
			view.checkJQuery();
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
			view.loadJQuery();
			
		}
		
	}
	

	class Left implements KeyListener {
	
		public void keyPressed(KeyEvent e) {
			if (e.equals(SWT.CAPS_LOCK))
			{
				view.setURL(sEngines.getPreviousValue());
			}
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.keyCode == SWT.ARROW_LEFT)
			{
				view.setURL(sEngines.getPreviousValue());
			}
			
		}
		
	}
	class Right implements KeyListener {
	
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.keyCode == SWT.ARROW_RIGHT)
			{
				view.setURL(sEngines.getNextValue());				
			}
			
		}
		
	}
}
