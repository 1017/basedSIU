import java.util.ArrayList;


public class SearchInterface 
{
	private String url;
	private ArrayList<VisualBlock> visualBlocks;
	private ArrayList<Attribute> attributes;
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
		vbg.groupFormElements();
	}
	
	private void performInitialLabelling()
	{
		lt = new LabellingTool(visualBlocks, attributes);
		lt.performLabelling();
	}
	
	public String getUrl()
	{
		return url;
	}

	public void understand(View view) {
		attributes = new ArrayList<Attribute>();
		TextStyleFactory.clearTextStyles();
		extractVisualBlocks(view);
		cleanVisualBlocks();
		performInitialLabelling();
		//groupVisualBlocks();
		
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}
}
