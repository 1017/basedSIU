
public class VisualBlockText extends VisualBlock 
{
	String font;
	int fontSize;
	int color;

	public VisualBlockText(int topX, int topY, int width, int height, int distanceFromFormElementX, int distanceFromFormElementY,
			String font, int fontSize, int color) 
	{
		super(topX, topY, width, height, distanceFromFormElementX, distanceFromFormElementY);
		this.font = font;
		this.fontSize = fontSize;
		this.color = color;
	}

}
