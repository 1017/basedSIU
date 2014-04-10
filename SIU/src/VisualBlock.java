
abstract class VisualBlock {
	protected int topX, topY, width, height;
	private int distanceFromFormElementX;
	private int distanceFromFormElementY;
	
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
}
