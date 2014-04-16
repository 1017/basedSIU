
public class TextStyle {

	
	private String font;
	private String fontSize;
	private String color;
	private String weight;
	private int matches[][] = new int [VisualBlock.Quadrants.values().length][VisualBlockForm.Element.values().length];

	public TextStyle(String font, String fontSize, String color, String weight)
	{
		this.font = font;
		this.fontSize = fontSize;
		this.color = color;
		
		
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
	
	public void addMatch(VisualBlock.Quadrants q, VisualBlockForm.Element e)
	{
		matches[q.ordinal()][e.ordinal()]++;
	}
	
	public int getMatches(VisualBlock.Quadrants q, VisualBlockForm.Element e)
	{
		return matches[q.ordinal()][e.ordinal()];
	}
	
	public int getMatches(VisualBlock.Quadrants q)
	{
		int total = 0;
		for (int i=0; i<matches[0].length; i++)
		{
			total += matches[q.ordinal()][i];
		}
		return total;
	}
	
	
	public int getMatches(VisualBlockForm.Element e)
	{
		int total = 0;
		for (int i=0; i<matches.length; i++)
		{
			total += matches[i][e.ordinal()];
		}
		return total;
	}
	
	
	@Override
    public boolean equals(Object o)
    {
		TextStyle t = (TextStyle)o;
		return ((this.weight.equals(t.getWeight())) &&
				(this.font.equals(t.getFont())) &&
				(this.fontSize.equals(t.getFontSize())) &&
				(this.color.equals(t.getColor())));		
    }

	public String getWeight()
	{
		return weight;
	}
	
	public String getFont() 
	{
		return font;
	}

	public String getFontSize() 
	{
		return fontSize;
	}

	public String getColor() 
	{
		return color;
	}
	
	public String toString()
	{
	return "\nFont: " + this.getFont()
	+" Font Size: " + this.getFontSize()
	+" Color: " + this.getColor()
	+" Weight: " + this.getWeight();
	}
}
