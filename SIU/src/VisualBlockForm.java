
public class VisualBlockForm extends VisualBlock {
	public enum Element {
		TEXTINPUT, PASSWORD, RADIO, CHECKBOX, SUBMITBUTTON, BUTTON, SELECTBOX, TEXTAREA
	}
	Element element;

	public VisualBlockForm(int topX, int topY, int width, int height, int distanceFromFormElementX, int distanceFromFormElementY, Element e) 
	{
		super(topX, topY, width, height, distanceFromFormElementX, distanceFromFormElementY);
		this.element = e;
	}

}
