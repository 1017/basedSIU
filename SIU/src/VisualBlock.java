
abstract class VisualBlock {
	public int topX, topY, width, height;
	
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
		return 0;
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
}
