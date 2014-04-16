import java.util.ArrayList;


public class LabellingTool {
	ArrayList<VisualBlock> visualBlocks;
	
	public LabellingTool(ArrayList<VisualBlock> visualBlocks) {
		this.visualBlocks = visualBlocks;
	}
	
	public void performLabelling()
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
		TextStyleFactory.printTextStyles();
	}
	
	

}
