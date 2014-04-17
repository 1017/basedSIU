import java.util.ArrayList;


public class TextStyleFactory {
	private static ArrayList<TextStyle> textStyles = new ArrayList<TextStyle>();
	
	public static TextStyle getTextStyle(String font, String fontSize, String color, String weight)
	{
		TextStyle textStyle = new TextStyle(font, fontSize, color, weight);
		if (!(textStyles.contains(((Object)textStyle))))
		{
			textStyles.add(textStyle);
		}
			
		return textStyles.get(textStyles.indexOf(textStyle));
	}
	
	public static void printTextStyles() //debug
	{
		for (int i = 0; i<textStyles.size(); i++)
		{
			System.out.println(textStyles.get(i));
		}
	}
	
	public static void clearTextStyles()
	{
		textStyles.clear();
	}
	
	public static ArrayList<TextStyle> getTextStyles()
	{
		return textStyles;
	}
}
