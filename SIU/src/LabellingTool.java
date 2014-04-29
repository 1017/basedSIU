import java.util.ArrayList;


public class LabellingTool {
	ArrayList<VisualBlock> visualBlocks;
	ArrayList<Attribute> attributes;
	
	public LabellingTool(ArrayList<VisualBlock> visualBlocks, ArrayList<Attribute> attributes) {
		this.visualBlocks = visualBlocks;
		this.attributes = attributes;
	}
	
	public void performLabelling()
	{
		getNearestQuadrantForAllFormElements();
		TextStyleFactory.printTextStyles();
		matchCheckboxes();
		matchRadioButtons();
		matchTextBoxes();
		//matchDropdowns();
	}
	
	public void getNearestQuadrantForAllFormElements()
	{
		for (int i = 0; i<visualBlocks.size(); i++)
		{
			if (visualBlocks.get(i).getClass() == VisualBlockForm.class)
			{
				for (int j = 0; j<visualBlocks.size(); j++)
				{
					VisualBlock subjectBlock = visualBlocks.get(i);
					VisualBlock objectBlock = visualBlocks.get(j);
					VisualBlock.Quadrants quadrant = subjectBlock.getQuadrant(objectBlock);
					int distance = subjectBlock.getDistanceFrom(objectBlock, quadrant);
					
					if ((subjectBlock != objectBlock) &&
							(distance < subjectBlock.getNeighbourDistance(quadrant)))
					{
						subjectBlock.updateNeighbour(objectBlock, quadrant, distance);
					}
				}
			}
		}
		
		System.out.println("Just finished first pass labelling ..");		
	}

	public void matchCheckboxes()
	{
		int i = 0;
		while (i<visualBlocks.size())
		{
			if ((visualBlocks.get(i).getClass() == VisualBlockForm.class)
					&& (((VisualBlockForm)visualBlocks.get(i)).element == VisualBlockForm.Element.CHECKBOX))
			{		
				VisualBlockForm currentFormElement = (VisualBlockForm)visualBlocks.get(i);
				if (currentFormElement.matchBoxesWithText(visualBlocks, attributes)) //THESE SHOULDNT BE ATTRS.. COMBINE LIKE DD HERE..
				{
					i = visualBlocks.indexOf(currentFormElement);
					visualBlocks.remove(currentFormElement);	
					i--;
				}
			}
			i++;
		}
	}
	public void matchRadioButtons()
	{
		int i = 0;
		while (i<visualBlocks.size())
		{
			if ((visualBlocks.get(i).getClass() == VisualBlockForm.class)
					&& (((VisualBlockForm)visualBlocks.get(i)).element == VisualBlockForm.Element.RADIO))
			{		
				VisualBlockForm currentFormElement = (VisualBlockForm)visualBlocks.get(i);
				if (((VisualBlockForm)visualBlocks.get(i)).matchBoxesWithText(visualBlocks, attributes)) //THESE SHOULDNT BE ATTRS.. COMBINE LIKE DD HERE..
				{
					i = visualBlocks.indexOf(currentFormElement);
					visualBlocks.remove(currentFormElement);	
					i--;
				}
			}
			i++;
		}
	}
	public void matchTextBoxes()
	{
		int i = 0;
		while (i<visualBlocks.size())
		{
			if ((visualBlocks.get(i).getClass() == VisualBlockForm.class)
					&& ((((VisualBlockForm)visualBlocks.get(i)).element == VisualBlockForm.Element.TEXTINPUT) ||
					((VisualBlockForm)visualBlocks.get(i)).element == VisualBlockForm.Element.DROPDOWN))
			{		
				VisualBlockForm currentFormElement = (VisualBlockForm)visualBlocks.get(i);
				if (((VisualBlockForm)visualBlocks.get(i)).matchWithText(visualBlocks, attributes) && (i<visualBlocks.size()))
				{
					i = visualBlocks.indexOf(currentFormElement);
					visualBlocks.remove(currentFormElement);	
					i--;
				}
			}
			i++;
		}
	}
	/*public void matchDropdowns()
	{
		int i = 0;
		while (i<visualBlocks.size())
		{
			if ((visualBlocks.get(i).getClass() == VisualBlockForm.class)
					&& (((VisualBlockForm)visualBlocks.get(i)).element == VisualBlockForm.Element.DROPDOWN))
			{
				if (((VisualBlockForm)visualBlocks.get(i)).matchWithText(visualBlocks))
				{
					visualBlocks.remove(i);		
					i--;
				}
			}
			i++;
		}
	}*/
	

}
