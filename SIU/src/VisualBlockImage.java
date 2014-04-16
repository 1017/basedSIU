
public class VisualBlockImage extends VisualBlock {

	String defaultValue;
	public VisualBlockImage(int topX, int topY, int width, int height, String defaultValue) 
	{
		super(topX, topY, width, height);
		this.defaultValue = defaultValue;
	}
	
	public String getDefaultValue()
	{
		return defaultValue;
	}

	public String toString()
	{
		return "Top X: " + topX
				+"\nTop Y: " + topY
				+"\nWidth: " + width
				+"\nHeight: " + height
				+"\nDefault Value: " + defaultValue;
	}
}
