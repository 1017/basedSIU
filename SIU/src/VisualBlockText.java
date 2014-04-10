
public class VisualBlockText extends VisualBlock 
{
	String font;
	String text;
	String fontSize;
	String color;

	public VisualBlockText(int topX, int topY, int width, int height,
			String font, String text, String fontSize, String color) 
	{
		super(topX, topY, width, height);
		this.font = font;
		this.text = text;
		this.fontSize = fontSize;
		this.color = color;
	}
	
	public String toString()
	{
		return "Top X: " + topX
				+"\nTop Y: " + topY
				+"\nWidth: " + width
				+"\nHeight: " + height
				+"\nFont: " + font
				+"\nText: " + text
				+"\nFont Size: " + fontSize
				+"\nColor: " + color;
	}

}
