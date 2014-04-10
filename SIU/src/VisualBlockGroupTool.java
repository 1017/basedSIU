import java.util.ArrayList;


public class VisualBlockGroupTool {
	
	//grouping class
	//text version of list of VBs
	//list of VBs
	
	ArrayList<VisualBlock> visualBlocks;
	
	public VisualBlockGroupTool(ArrayList<VisualBlock> visualBlocks)
	{
		this.visualBlocks = visualBlocks;
		discardText();
		matchRadioButtonsAndCheckBoxes();
		groupText();
	}
	
	public void matchRadioButtonsAndCheckBoxes()
	{
		//these always have a label - find it!
	}
	
	public void discardText()
	{
		//discard text outside of the range of the form elements
		//determine area where form elements are
		//discard text a "row" away - determine size of row?
		double rowHeight = calculateRowHeight();
		int topFormBoundary; //min topx from list
		int leftFormBoundary; //min topY from list
		int rightFormBoundary; //max topx + width
		int bottomFormBoundary; //max topx + height CREATE VISUAL BLOCK FOR THIS INSTEAD
		
		//for each element in text v of VBs
			//if "exists outside of boundaries"
				//remove from list
	}
	private boolean isIn (VisualBlock v, int leftBoundary, int rightBoundary, int topBoundary, int bottomBoundary)
	{
		return ((v.getTopX() >= leftBoundary) //left edge
				||((v.getTopX() + v.getWidth()) <= rightBoundary) //right edge
				||(v.getTopY() >= topBoundary) //top
				||((v.getTopY() + v.getHeight()) <= bottomBoundary)); //bottom
	}
	private double calculateRowHeight() { //difficult because row height can vary
		//group all elements by how they are aligned
		//choose grouping with most results
		//divide overall height by number of results
		/*VisualBlockGroup g = getLargestGrouping(getGroupings(VisualBlockForm.class));
		return ((double)g.getHeight()) / ((double)g.getNumberOfElements());*/
		return 0.0;
	}

	private Object getGroupings(Class<VisualBlockForm> type) {
		//if (x.getClass().equals(type))
		return null;
	}

	public void groupText()
	{
		//any text not discarded or matched with radio/checkbs
		//take highest-  top left, find how many are aligned vertically, either left or right aligned - take highest
		//removed matched from list
		//continue until all matched
	}
	
	public void groupRows()
	{
		//based on the text that has been grouped
		//take left most group, create rows based off that
		
		//group text groups by text font / size, assume the majority are the labels
	}
	
	public void workOutSpacialDistance()
	{
		
	}
	
	public void extractLabels()
	{
		
	}

}
