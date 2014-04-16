import java.util.ArrayList;


public class VisualBlockCleanTool {
	ArrayList<VisualBlock> visualBlocks;
	
	public VisualBlockCleanTool(ArrayList<VisualBlock> visualBlocks) {
		this.visualBlocks = visualBlocks;
	}

	public void clean() {
		mergeText();
		
	}
	
	private void mergeText() { //if text is 0 distance, it is the same, store the sentence and make note of which are of non normal font weight
		//need to return font weight
		//need to order VBs
		int i = 0;
		while (i<(visualBlocks.size()-1))
		{
			VisualBlock firstBlock = visualBlocks.get(i);
			VisualBlock secondBlock = visualBlocks.get(i+1);
			if ((firstBlock.getClass() == VisualBlockText.class) && 
					(secondBlock.getClass() == VisualBlockText.class) 
					&& (firstBlock.isBeside(secondBlock)))
				{
					System.out.println("About to merge: " + ((VisualBlockText)firstBlock).getText() + " with " + ((VisualBlockText)secondBlock).getText());
					if (((VisualBlockText)firstBlock).getWeight() != "normal")
					{
						((VisualBlockText)firstBlock).addStyledText(((VisualBlockText)firstBlock).getText());
					}
					if (((VisualBlockText)secondBlock).getWeight() != "normal")
					{
						((VisualBlockText)secondBlock).addStyledText(((VisualBlockText)secondBlock).getText());
					}
					((VisualBlockText)firstBlock).addText(((VisualBlockText)secondBlock).getText());
					//System.out.print("Old second block end: " +(secondBlock.getTopX() + secondBlock.getWidth() ));
					firstBlock.setWidth(secondBlock.getTopX() + secondBlock.getWidth() - firstBlock.getTopX());
					//System.out.println("New first block end: " + (firstBlock.getTopX() + firstBlock.getWidth() ));
					
					visualBlocks.remove(secondBlock);
				}
				else
					i++;
		}
	}

}
