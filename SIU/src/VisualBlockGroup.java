import java.util.ArrayList;


public class VisualBlockGroup {
	private ArrayList<VisualBlock> visualBlocks;
	private int topX,topY,height,width;
	
	public VisualBlockGroup()
	{
		visualBlocks = new ArrayList<VisualBlock>();
	}
	
	public void addVisualBlock(VisualBlock vb)
	{
		visualBlocks.add(vb);
		if (visualBlocks.size() == 1)
		{
			this.topX = vb.getTopX();
			this.topY = vb.getTopY();
			this.height = vb.getHeight();
			this.width = vb.getWidth();
		}
		else
		{
			updateSize(vb);
		}
	}

	private void updateSize(VisualBlock vb) {
		if (vb.getTopX() < topX)
			topX = vb.getTopX();
		if (vb.getTopY() < topY)
			topY = vb.getTopY();
		if ((topX + width) < (vb.getTopX()+vb.getWidth()))
			width = vb.getTopX() + vb.getWidth() - topX;
		if ((topY + height) < (vb.getTopY()+vb.getHeight()))
			width = vb.getTopY() + vb.getHeight() - topY;
	}
}
