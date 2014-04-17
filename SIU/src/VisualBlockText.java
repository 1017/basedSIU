
public class VisualBlockText extends VisualBlock 
{
	private String text;
	private String styledText;
	private TextStyle textStyle;
	private boolean[] matchedInQuadrant = new boolean[VisualBlock.Quadrants.values().length];

	public VisualBlockText(int topX, int topY, int width, int height,
			String font, String text, String fontSize, String color, String weight) 
	{
		super(topX, topY, width, height);
		this.text = text;
		this.styledText = "";
		this.textStyle = TextStyleFactory.getTextStyle(font, fontSize, color, weight);
		
		for (int i = 0; i < VisualBlock.Quadrants.values().length; i++)
			matchedInQuadrant[i] = false;
	}

	public void addStyledText(String t)
	{
		styledText += " "+t;
	}
	
	public void addText(String t)
	{
		text += " "+t;
	}

	public String getText()
	{
		return text;
	}
	
	public TextStyle getTextStyle()
	{
		return textStyle;
	}
	
	public String getWeight()
	{
		return textStyle.getWeight();
	}
	
	public void addTextStyleMatch(VisualBlock.Quadrants q, VisualBlockForm.Element e)
	{
		//boolean hasBeenMatched = matchedInQuadrant[q.ordinal()];
		//if (!hasBeenMatched)
		//{
			matchedInQuadrant[q.ordinal()] = true;
			textStyle.addMatch(q, e);
		//}
	}

	public void removeTextStyleMatch(VisualBlock.Quadrants q, VisualBlockForm.Element e) 
	{
		//matchedInQuadrant[q.ordinal()] = false;
		textStyle.removeMatch(q, e);		
	}
	
	public String toString()
	{
		return "Top X: " + topX
				+"\nTop Y: " + topY
				+"\nWidth: " + width
				+"\nHeight: " + height
				+"\nFont: " + textStyle.getFont()
				+"\nText: " + text
				+"\nFont Size: " + textStyle.getFontSize()
				+"\nColor: " + textStyle.getColor()
				+"\nWeight: " + textStyle.getWeight();
	}

}
