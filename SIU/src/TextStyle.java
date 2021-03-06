
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
	

	public void removeMatch(VisualBlock.Quadrants q, VisualBlockForm.Element e) 
	{
		matches[q.ordinal()][e.ordinal()]--;
		
	}
	
	public VisualBlock.Quadrants getMostPopularQuadrant(VisualBlockForm.Element e)
	{
		int quadrant = -1;
		for (int i=0; i<matches.length; i++)
		{
			if (getMatches(VisualBlock.Quadrants.values()[i], e) > quadrant)
				quadrant = i;
		}
		return VisualBlock.Quadrants.values()[0];
	}
	
	public VisualBlock.Quadrants getMostPopularQuadrant()
	{
		int quadrant = -1;
		int numberOfMatches = -1;
		for (int i=(matches.length-1); i>=0; i--)
		{
			if (getMatches(VisualBlock.Quadrants.values()[i]) > numberOfMatches)
			{
				quadrant = i;
				numberOfMatches = getMatches(VisualBlock.Quadrants.values()[i]);
			}
		}
		return VisualBlock.Quadrants.values()[quadrant];
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
	+" Weight: " + this.getWeight()
	+" Above Quad matches: " + this.getMatches(VisualBlock.Quadrants.ABOVE)
	+" Below Quad matches: " + this.getMatches(VisualBlock.Quadrants.BELOW)
	+" Left Quad matches: " + this.getMatches(VisualBlock.Quadrants.LEFT)
	+" Right Quad matches: " + this.getMatches(VisualBlock.Quadrants.RIGHT)
	+" Right Quad matches: " + this.getMatches(VisualBlock.Quadrants.BELOWRIGHT)
	+" Right Quad matches: " + this.getMatches(VisualBlock.Quadrants.BELOWLEFT)/*
	+" \nAbove Left match text input: " + this.getMatches(VisualBlock.Quadrants.ABOVELEFT, VisualBlockForm.Element.TEXTINPUT)
	+" Above match text: " + this.getMatches(VisualBlock.Quadrants.ABOVE, VisualBlockForm.Element.TEXTINPUT)
	+" Left match text input: " + this.getMatches(VisualBlock.Quadrants.LEFT, VisualBlockForm.Element.TEXTINPUT)
	+" Below Left match text input: " + this.getMatches(VisualBlock.Quadrants.BELOWLEFT, VisualBlockForm.Element.TEXTINPUT)
	+" \nAbove Left match drop: " + this.getMatches(VisualBlock.Quadrants.ABOVELEFT, VisualBlockForm.Element.DROPDOWN)
	+" Above match drop: " + this.getMatches(VisualBlock.Quadrants.ABOVE, VisualBlockForm.Element.DROPDOWN)
	+" Left match drop: " + this.getMatches(VisualBlock.Quadrants.LEFT, VisualBlockForm.Element.DROPDOWN)
	+" Below Left match drop: " + this.getMatches(VisualBlock.Quadrants.BELOWLEFT, VisualBlockForm.Element.DROPDOWN);*/
	+" Most popular quadrant: " + this.getMostPopularQuadrant();
	}

}
