import java.util.ArrayList;


public class Attribute {
	String label;
	enum Type {VARCHAR, INTEGER, CURRENCY, BOOLEAN, DATE, FLOAT}
	String operator;
	String operand;
	String defaultValue;
	boolean nullable;
	boolean inDomain; //a value is either in the current domain or it is a constraint on search results
	ArrayList<VisualBlock> visualBlocks;
	VisualBlock.Quadrants labelQuadrant;

	public Attribute(VisualBlockText label, VisualBlockForm vb, VisualBlock.Quadrants q)
	{
		this.labelQuadrant = q;
		visualBlocks = new ArrayList<VisualBlock>();
		label.setAttribute(this);
		vb.setAttribute(this);
		this.label = label.getText();
		visualBlocks.add(vb);
	}
	
	public Attribute(VisualBlockForm vb)
	{
		vb.setAttribute(this);
		this.label = vb.getDefaultValue()[0];
		visualBlocks.add(vb);				
	}
	
	public void add(VisualBlock vb)
	{
		vb.setAttribute(this);
		visualBlocks.add(vb);						
	}
	
	public String toString()
	{
		String output = "LABEL: \"" + label +"\" ";
		output += "located " + labelQuadrant + "  form element " + ((VisualBlockForm)visualBlocks.get(0)).getType() + " with default value ";
		output += ((VisualBlockForm)visualBlocks.get(0)).getDefaultValue()[0] + "\n";
		
		return output;
	}
	
}
