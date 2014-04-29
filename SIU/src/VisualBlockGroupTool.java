import java.util.ArrayList;


public class VisualBlockGroupTool {
	
	//grouping class
	//text version of list of VBs
	//list of VBs
	
	ArrayList<VisualBlock> visualBlocks;
	
	public VisualBlockGroupTool(ArrayList<VisualBlock> visualBlocks)
	{
		this.visualBlocks = visualBlocks;
	}
	
	public void groupFormElements()
	{
		//for each form element not in a group
		//if default text which can not be a label? and form element left or up, group
		//else put that element in an attribute class with their default text as a label??????
		for (int i = 0; i < visualBlocks.size(); i++)
		{
			VisualBlock currentBlock = visualBlocks.get(i);
			if ((currentBlock.getClass() == VisualBlockForm.class) &&
					(!currentBlock.isMatchedWithGroup()) &&
					(((VisualBlockForm)currentBlock).getDefaultValue()[0].isEmpty()))
			{
				for (int j = 0; j < VisualBlock.Quadrants.values().length; j++)
				{
					VisualBlock.Quadrants q = VisualBlock.Quadrants.values()[j];
					if ((currentBlock.getNeighbour(q).getClass() == VisualBlockForm.class) &&
						(currentBlock.isMatchedWithGroup()))
					{
						currentBlock.getAttribute().add(currentBlock);
					}
				}
			}
				
		}
	}

}
