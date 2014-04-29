import java.util.ArrayList;


abstract class VisualBlock implements Comparable<VisualBlock> {
	protected int topX, topY, width, height;
	private int distanceFromFormElementX;
	private int distanceFromFormElementY;
	protected VisualBlock neighbour[] = new VisualBlock[Quadrants.values().length];
	protected int neighbourDistance[] = new int[Quadrants.values().length];
	private boolean matchedWithGroup;
	private Attribute attribute;
	private int neighbourMaxStartingY, neighbourMinStartingY;
	
	enum Quadrants {ABOVE,LEFT,RIGHT,BELOW,ABOVELEFT,ABOVERIGHT,BELOWLEFT,BELOWRIGHT}
	
	public VisualBlock (int topX, int topY, int width, int height)
	{
		this.topX = topX;
		this.topY = topY;
		this.width = width;
		this.height = height;
		this.neighbourMaxStartingY = -1;
		this.neighbourMinStartingY = -1;
		for (int i=0; i<Quadrants.values().length; i++)
		{
			neighbour[i] = null;
			neighbourDistance[i] = Integer.MAX_VALUE;
		}
	}

	public int getTopX() {
		return topX;
	}

	public int getTopY() {
		return topY;
	}
	
	public int getBottomX() {
		return topX+width;
	}

	public int getBottomY() {
		return topY+height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int w) {
		this.width = w;
	}

	public void setHeight(int h) {
		this.height = h;
	}

	public int getArea() {
		return height*width;
	}

	public int getDistanceFromFormElementX() {
		return distanceFromFormElementX;
	}

	public int getDistanceFromFormElementY() {
		return distanceFromFormElementY;
	}

	
	public void setDistanceFromFormElementX(int x) {
		distanceFromFormElementX = x;
	}

	public void setDistanceFromFormElementY(int y) {
		distanceFromFormElementY = y;
	}
	
	///Gets the quadrant v is in relative to this block
	public Quadrants getQuadrant(VisualBlock v)
	{
		int centerHeight = v.getTopY() + (v.getHeight() / 2);
		int xOffset = 20;
		if (centerHeight < topY)
		{
			if (v.getTopX() < (topX - xOffset))
				return Quadrants.ABOVELEFT;
			else if ((v.getTopX() > (topX+width)))
				return Quadrants.ABOVERIGHT;
			else
				return Quadrants.ABOVE;
		}
		else if (centerHeight < (topY + height))
		{
			if (v.getTopX() < topX)
				return Quadrants.LEFT;
			else if ((v.getTopX() > (topX+width)))
				return Quadrants.RIGHT;	
		}
		else
		{
			if (v.getTopX() < topX)
				return Quadrants.BELOWLEFT;
			else if ((v.getTopX() > (topX+width)))
				return Quadrants.BELOWRIGHT;
		}
		return Quadrants.BELOW;
	}
	
	public int[] getCenter()
	{
		int[] center = new int[2];
		center[0] = topX + (width / 2);
		center[1] = topY + (height / 2);
		return center;
	}

	public boolean isIn (VisualBlock v)
	{
		return ((this.topX >= v.getTopX()) //left edge
				||((this.topX + width) <= (v.getTopX() + v.getWidth())) //right edge
				||(this.topY >= v.getTopY()) //top
				||((this.topY + height) <= (v.getTopY() + height))); //bottom
	}

	public boolean isRightAlignedTo (VisualBlock v)
	{
		return ((this.topX >= (v.getTopX() + width - 10))
				&& (this.topX >= (v.getTopX() + width + 10)));
	}
	
	public boolean isLeftAlignedTo (VisualBlock v)
	{
		return ((this.topX >= (v.getTopX() - 10))
				&& (this.topX >= (v.getTopX() + 10)));
	}
	
	public boolean isBeside(VisualBlock v)
	{
		return ((topX+width+10) > v.getTopX())
		&& (topY == v.getTopY());
	}

	public int compareTo(VisualBlock v) //sort blocks such that starts at top left, goes right, then down
	{
		if ((topY+height < v.getTopY()) || 
				(topY < v.getTopY()+v.getHeight() && (topX < v.getTopX())))
			return -1;
		else
			return 1;		
	}

	public VisualBlock getNeighbour(Quadrants quadrant) {
		return neighbour[quadrant.ordinal()];
	}
	
	public int getNeighbourDistance(Quadrants quadrant) {
		return neighbourDistance[quadrant.ordinal()];
	}

	private int distanceAbove(VisualBlock objectBlock)
	{
		return (this.getTopY() - objectBlock.getTopY());		
	}
	private int distanceBelow(VisualBlock objectBlock)
	{
		return (objectBlock.getTopY() - this.getTopY());		
	}
	private int distanceRight(VisualBlock objectBlock)
	{
		return (objectBlock.getTopX() - this.getBottomX());		
	}
	private int distanceLeft(VisualBlock objectBlock)
	{
		return (this.getTopX() - objectBlock.getTopX());		
	}

	public int getDistanceFrom(VisualBlock objectBlock, Quadrants quadrant) {
		if (quadrant == Quadrants.ABOVE)
			return distanceAbove(objectBlock);
		else if (quadrant == Quadrants.BELOW)
			return distanceBelow(objectBlock);
		else if (quadrant == Quadrants.LEFT)
			return distanceLeft(objectBlock);
		else if (quadrant == Quadrants.RIGHT)
			return distanceRight(objectBlock);
		else if (quadrant == Quadrants.ABOVELEFT)
			return (int)(Math.sqrt(Math.pow(distanceAbove(objectBlock),2)+(Math.pow(distanceLeft(objectBlock),2))));
		else if (quadrant == Quadrants.ABOVERIGHT)
			return (int)(Math.sqrt(Math.pow(distanceAbove(objectBlock),2)+(Math.pow(distanceRight(objectBlock),2))));			
		else if (quadrant == Quadrants.BELOWLEFT)
			return (int)(Math.sqrt(Math.pow(distanceBelow(objectBlock),2)+(Math.pow(distanceLeft(objectBlock),2))));				
		else if (quadrant == Quadrants.BELOWRIGHT)
			return (int)(Math.sqrt(Math.pow(distanceBelow(objectBlock),2)+(Math.pow(distanceRight(objectBlock),2))));
		else
			return Integer.MAX_VALUE;
	}

	public void updateNeighbour(VisualBlock objectBlock, Quadrants quadrant, int distance) {
		int index = quadrant.ordinal();
		
		if ((neighbour[index]!=null) && (neighbour[index].getClass() == VisualBlockText.class))
			((VisualBlockText)neighbour[index]).removeTextStyleMatch(quadrant, ((VisualBlockForm)this).element);
		
		if ((objectBlock.getClass() == VisualBlockText.class) &&
				(this.getClass() == VisualBlockForm.class))
		{
			((VisualBlockText)objectBlock).addTextStyleMatch(quadrant, ((VisualBlockForm)this).element);			
		}

		int centerHeight = objectBlock.getTopY() + (objectBlock.getHeight() / 2);
		
		if (centerHeight < getTopY()) //if above
		{
			if (neighbourMinStartingY == -1)
			{
				neighbourMinStartingY = index;
				neighbour[index] = objectBlock;
				neighbourDistance[index] = distance;
			}
			else if (objectBlock.getBottomY() >= neighbour[neighbourMinStartingY].getTopY())
			{	
				
				if (objectBlock.getTopY() > neighbour[neighbourMinStartingY].getBottomY()) //if old min is now invalid
				{					
					neighbour[neighbourMinStartingY] = null;
					neighbourMinStartingY = index;
				}
				else if (objectBlock.getBottomY() > neighbour[neighbourMinStartingY].getBottomY()) //if min should be updated
				{
					neighbourMinStartingY = index;					
				}
				neighbour[index] = objectBlock;
				neighbourDistance[index] = distance;
			}
		}
		else if (centerHeight > getBottomY()) //if below
		{
			if (neighbourMaxStartingY == -1)
			{
				neighbourMaxStartingY = index;	
				neighbour[index] = objectBlock;
				neighbourDistance[index] = distance;
			}
			else if (objectBlock.getTopY() <= neighbour[neighbourMaxStartingY].getBottomY())
			{
					
				if (objectBlock.getBottomY() < neighbour[neighbourMaxStartingY].getTopY()) //if old max now invalid
				{					
					neighbour[neighbourMaxStartingY] = null;
					neighbourMaxStartingY = index;
				}
				else if (objectBlock.getBottomY() < neighbour[neighbourMaxStartingY].getBottomY()) //if old max should be updated
				{
					neighbourMaxStartingY = index;					
				}
				neighbour[index] = objectBlock;
				neighbourDistance[index] = distance;
				
			}
				
		}
		else
		{		
			neighbour[index] = objectBlock;
			neighbourDistance[index] = distance;
			
		}
		
		
		
	}

	public boolean isMatchedWithGroup() {
		return matchedWithGroup;
	}

	public void setMatchedWithGroup(boolean matchedWithGroup) {
		this.matchedWithGroup = matchedWithGroup;
	}

	public void printQuadrants() {
		System.out.println("Printing for " + ((VisualBlockForm)this).element);
		for (int i = 0; i< Quadrants.values().length; i++)
		{
			System.out.println("Quad: " + Quadrants.values()[i] + " element: " + neighbour[i]);
		}
		
	}

	public void setAttribute(Attribute a) {
		attribute = a;
	}
	public Attribute getAttribute() {
		return attribute;
	}
	
	//merge this with visual block vb from quadrant q, updating boundaries and neighbours
	public void merge(VisualBlock vb, Quadrants q)
	{
		neighbour[q.ordinal()] = vb.getNeighbour(q);
		this.topX = Math.min(this.topX, vb.getTopX());
		this.topY = Math.min(this.topY, vb.getTopY());
		this.width = Math.max(this.width, vb.getWidth());
		this.height = Math.max(this.height, vb.getHeight());
	}
}
