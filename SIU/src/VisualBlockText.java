
public class VisualBlockText extends VisualBlock 
{
	String font;
	String text;
	String fontSize;
	String color;
	String weight;
	String styledText;

	public VisualBlockText(int topX, int topY, int width, int height,
			String font, String text, String fontSize, String color, String weight) 
	{
		super(topX, topY, width, height);
		this.font = font;
		this.text = text;
		this.fontSize = fontSize;
		this.color = color;
		this.styledText = "";
		
		try { 
			int weightInt = Integer.valueOf(weight);
			if (weightInt == 400)
				this.weight = "normal";
			else if (weightInt < 400)
				this.weight = "light";
			else if (weightInt > 400)
				this.weight = "bold";
	    } catch(NumberFormatException e) { 
			this.weight = weight;
	    }
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
		return weight;
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
				+"\nColor: " + color
				+"\nWeight: " + weight;
	}

}
