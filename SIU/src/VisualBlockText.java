
public class VisualBlockText extends VisualBlock 
{
	private String text;
	private String styledText;
	private TextStyle textStyle;
	private int[] matchedInQuadrant = new int[VisualBlock.Quadrants.values().length];

	public VisualBlockText(int topX, int topY, int width, int height,
			String font, String text, String fontSize, String color, String weight) 
	{
		super(topX, topY, width, height);
		this.text = text.trim();
		this.styledText = "";
		this.textStyle = TextStyleFactory.getTextStyle(font, fontSize, color, weight);
		
		for (int i = 0; i < VisualBlock.Quadrants.values().length; i++)
			matchedInQuadrant[i] = 0;
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
		matchedInQuadrant[q.ordinal()]++;
		if (matchedInQuadrant[q.ordinal()] == 1)
		{
			textStyle.addMatch(q, e);
		}
	}

	public void removeTextStyleMatch(VisualBlock.Quadrants q, VisualBlockForm.Element e) 
	{
		matchedInQuadrant[q.ordinal()]--;
		if (matchedInQuadrant[q.ordinal()] == 0)
			textStyle.removeMatch(q, e);		
	}
	
	public boolean isFoundIn(VisualBlock.Quadrants q)
	{
		System.out.println("Most popular quad: " + q + " matched? " + (matchedInQuadrant[q.ordinal()] > 0));
		return (matchedInQuadrant[q.ordinal()] > 0);
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
