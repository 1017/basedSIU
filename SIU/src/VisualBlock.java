
abstract class VisualBlock implements Comparable<VisualBlock> {
	protected int topX, topY, width, height;
	private int distanceFromFormElementX;
	private int distanceFromFormElementY;
	
	enum Quadrants {ABOVE,LEFT,RIGHT,BELOW,ABOVELEFT,ABOVERIGHT,BELOWLEFT,BELOWRIGHT }
	
	public VisualBlock (int topX, int topY, int width, int height)
	{
		this.topX = topX;
		this.topY = topY;
		this.width = width;
		this.height = height;
	}
	
	public int getTopX() {
		return topX;
	}

	public int getTopY() {
		return topY;
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
		if (centerHeight < topY)
		{
			if (v.getTopX() < topX)
				return Quadrants.ABOVELEFT;
			else if ((v.getTopX() > (topX+width)))
				return Quadrants.ABOVERIGHT;
			else
				return Quadrants.ABOVE;
		}
		else if (centerHeight > (topY + height))
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
		return ((topX+width+4) > v.getTopX())
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
}
