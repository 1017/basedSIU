
public class VisualBlockText extends VisualBlock 
{
	private String text;
	private String styledText;
	private TextStyle textStyle;

	public VisualBlockText(int topX, int topY, int width, int height,
			String font, String text, String fontSize, String color, String weight) 
	{
		super(topX, topY, width, height);
		this.text = text;
		this.styledText = "";
		this.textStyle = TextStyleFactory.getTextStyle(font, fontSize, color, weight);
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
	
	public String getWeight()
	{
		return textStyle.getWeight();
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
