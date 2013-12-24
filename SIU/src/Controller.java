

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;

public class Controller {
	View view;
	SearchEngines sEngines;
	Left l = new Left();
	Right r = new Right();
	
	Controller(View view, SearchEngines se){
		this.view = view;
		this.sEngines = se;
		
		view.setURL(sEngines.getCurrentValue());
		view.addKeyListener(l);
		view.addKeyListener(r);
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
