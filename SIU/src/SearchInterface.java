import java.util.ArrayList;


public class SearchInterface 
{
	private String url;
	private ArrayList<VisualBlock> visualBlocks;
	private VisualBlockCleanTool vbc;
	private VisualBlockExtractTool vbe;
	private VisualBlockGroupTool vbg;
	private LabellingTool lt;
	
	public SearchInterface(String u)
	{
		url = u;
	}
	
	private void cleanVisualBlocks()
	{
		vbc = new VisualBlockCleanTool(visualBlocks);
		vbc.clean();
	}
	
	private void extractVisualBlocks(View v)
	{
		vbe = new VisualBlockExtractTool();
		visualBlocks = vbe.extract(v);
	}
	
	private void groupVisualBlocks()
	{
		vbg = new VisualBlockGroupTool(visualBlocks);
	}
	
	private void performInitialLabelling()
	{
		lt = new LabellingTool(visualBlocks);
		lt.performLabelling();
	}
	
	public String getUrl()
	{
		return url;
	}

	public void understand(View view) {
		TextStyleFactory.clearTextStyles();
		extractVisualBlocks(view);
		cleanVisualBlocks();
		performInitialLabelling();
		
	}
}
