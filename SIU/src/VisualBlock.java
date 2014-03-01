
abstract class VisualBlock {
	public int topX, topY, width, height;
	private int distanceFromFormElementX;
	private int distanceFromFormElementY;
	
	public VisualBlock (int topX, int topY, int width, int height, int distanceFromFormElementX, int distanceFromFormElementY)
	{
		this.topX = topX;
		this.topY = topY;
		this.width = width;
		this.height = height;
		this.distanceFromFormElementX = distanceFromFormElementX;
		this.distanceFromFormElementY = distanceFromFormElementY;
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
}
