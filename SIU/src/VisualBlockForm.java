import java.util.Arrays;


public class VisualBlockForm extends VisualBlock {
	public enum Element {
		TEXTINPUT, PASSWORD, RADIO, CHECKBOX, DROPDOWN, SUBMITBUTTON, BUTTON, SELECTBOX, TEXTAREA
	}
	Element element;
	private String[] defaultValue;

	public VisualBlockForm(int topX, int topY, int width, int height, String defaultValue, Element e) 
	{
		super(topX, topY, width, height);
		this.element = e;
		this.defaultValue = new String[1];
		this.defaultValue[0] = defaultValue;
	}
	
	public VisualBlockForm(int topX, int topY, int width, int height, String[] defaultValue, Element e) 
	{
		super(topX, topY, width, height);
		this.element = e;
		this.defaultValue = defaultValue;
	}

	public String toString()
	{
		String output = "Top X: " + topX
				+"\nTop Y: " + topY
				+"\nWidth: " + width
				+"\nHeight: " + height		
				+"\nDefault Value: ";
		if(defaultValue.length > 1) 
			output += Arrays.deepToString(defaultValue);
		else
			output += defaultValue[0];
		output += "\nType: " + element;
		return output;
	}
}
