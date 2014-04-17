import java.util.ArrayList;
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
	
	private boolean isBetterLabelMatch(VisualBlockText vbText, int newIndex, int numberOfCurrentMatches, int currentIndex)
	{
		int numberOfMatches = vbText.getTextStyle().getMatches(Quadrants.values()[newIndex], this.element);
		//int numberOfMatches = vbText.getTextStyle().getMatches(Quadrants.values()[newIndex]);
		/*if (this.element == Element.DROPDOWN)
		{
			System.out.println("#matches" + numberOfMatches + "dist" + neighbourDistance[newIndex] + "text " + vbText.getText());
		}*/
		return ((numberOfMatches > numberOfCurrentMatches) ||
				((numberOfMatches == numberOfCurrentMatches) &&
						(neighbourDistance[newIndex] < neighbourDistance[currentIndex]))
						);
	}
	
	public boolean matchWithText(ArrayList<VisualBlock> visualBlocks)
	{
		VisualBlockText textToMatch = null;
		int numberOfCurrentMatches = 0;
		int currentIndex = -1;
		VisualBlock.Quadrants q = null;
		for (int i = 0; i<neighbour.length; i++)
		{
			if ((neighbour[i] != null) &&
					(neighbour[i].getClass() == VisualBlockText.class) &&
					(!neighbour[i].isMatchedWithGroup()) &&
					((textToMatch == null) || (isBetterLabelMatch((VisualBlockText)neighbour[i], i, numberOfCurrentMatches, currentIndex))))
			{
				numberOfCurrentMatches = ((VisualBlockText)neighbour[i]).getTextStyle().getMatches(Quadrants.values()[i], this.element);
				currentIndex = i;
				textToMatch = ((VisualBlockText)neighbour[i]);
				q = Quadrants.values()[i];
			}
		}
		if (textToMatch != null)
		{
			System.out.println("Matching: " + textToMatch.getText() + " from quadrant " + q.toString() + " with " + this.element.toString());
			textToMatch.setMatchedWithGroup(true);
			visualBlocks.remove(textToMatch);
			this.setMatchedWithGroup(true);
			return true;
		}
		return false;
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
