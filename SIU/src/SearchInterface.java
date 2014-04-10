import java.util.ArrayList;


public class SearchInterface 
{
	private String url;
	private ArrayList<VisualBlock> visualBlocks;
	private VisualBlockExtractTool vbe;
	private VisualBlockGroupTool vbg;
	
	public SearchInterface(String u)
	{
		url = u;
	}
	
	public void extractVisualBlocks(View v)
	{
		vbe = new VisualBlockExtractTool();
		visualBlocks = vbe.extract(v);
	}
	
	public void groupVisualBlocks()
	{
		vbg = new VisualBlockGroupTool(visualBlocks);
	}
	
	public String getUrl()
	{
		return url;
	}
}
