import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;




public class VisualBlockExtractTool 
{
	ArrayList<VisualBlock> visualBlocks;

	public ArrayList<VisualBlock> extract(View v) 
	{
		visualBlocks = new ArrayList<VisualBlock>();
		extractTextBlocks(v);
		extractImageBlocks(v);
		extractTextBoxBlocks(v);
		extractDropDownBlocks(v);
		extractRadioButtonBlocks(v);
		extractCheckBoxBlocks(v);
		extractButtonBlocks(v);
		sortVisualBlocks();
		return visualBlocks;
	}
	
	private void sortVisualBlocks()
	{
		/*System.out.println("UNSORTED");
		for (int i = 0; i<visualBlocks.size(); i++)
		{
			System.out.println(visualBlocks.get(i));
		}*/
		Collections.sort(visualBlocks);
		System.out.println("SORTED?");
		/*for (int i = 0; i<visualBlocks.size(); i++)
		{
			System.out.println(visualBlocks.get(i));
		}*/
	}

	private void addFormBlock(int topX, int topY, int outerWidth, int outerHeight, String defaultValue, VisualBlockForm.Element e)
	{
		visualBlocks.add(new VisualBlockForm(topX, topY, outerWidth, outerHeight, defaultValue, e));
	}
	
	private void addFormBlock(int topX, int topY, int outerWidth, int outerHeight, String[] defaultValue, VisualBlockForm.Element e)
	{
		visualBlocks.add(new VisualBlockForm(topX, topY, outerWidth, outerHeight, defaultValue, e));
	}
	
	private void extractTextBlocks(View v)
	{
		String js = //top and left > 0
				"var arr = [new Array(), new Array(), new Array(), new Array(), new Array(), new Array(), new Array(), new Array(), new Array()];"
				+ "var i=0;var j=0;"
				+ "$('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea, option').each(function(){ if ($(this).parent().height() != 0) { arr[i][j++] = ($(this).parent().position().left);}});"
				+"i++;j=0;"
				+ "$('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea, option').each(function(){ if ($(this).parent().height() != 0) { arr[i][j++] = ($(this).parent().position().top);}});"
				+"i++;j=0;"
				+ "$('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea, option').each(function(){ if ($(this).parent().height() != 0) { arr[i][j++] = ($(this).parent().outerWidth());}});"
				+"i++;j=0;"
				+ "$('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea, option').each(function(){ if ($(this).parent().height() != 0) { arr[i][j++] = ($(this).parent().outerHeight());}});"
				+"i++;j=0;"
				+ "$('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea, option').each(function(){ if ($(this).parent().height() != 0) { arr[i][j++] = ($(this).parent().css('font-family'));}});"
				+"i++;j=0;"
				+ "$('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea, option').each(function(){ if ($(this).parent().height() != 0) { arr[i][j++] = (this.wholeText);}});"
				+"i++;j=0;"
				+ "$('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea, option').each(function(){ if ($(this).parent().height() != 0) { arr[i][j++] = ($(this).parent().css('font-size'));}});"
				+"i++;j=0;"
				+ "$('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea, option').each(function(){ if ($(this).parent().height() != 0) { arr[i][j++] = ($(this).parent().css('color'));}});"
				+"i++;j=0;"
				+ "$('form').find(\"*\").contents().filter(function () { return this.nodeType == 3 && /\\S/.test(this.nodeValue); }).not('select,:submit,:checked,:selected,:text,textarea, option').each(function(){ if ($(this).parent().height() != 0) { arr[i][j++] = String(($(this).parent().css('font-weight')));}});"
				
				+ "return arr;";

		Object[] unstructuredJSTextBlockInformation = (Object[])v.evaluateJS(js);
		System.out.println(Arrays.deepToString(unstructuredJSTextBlockInformation));
		Object[][] RawJSTextBlockInformation = new Object[unstructuredJSTextBlockInformation.length][((Object[])unstructuredJSTextBlockInformation[0]).length];
		
		for(int i=0;i<unstructuredJSTextBlockInformation.length;i++)
		{			
			RawJSTextBlockInformation[i] = (Object[])unstructuredJSTextBlockInformation[i];
		}

		for(int i=0;i<RawJSTextBlockInformation[0].length;i++)
		{
			visualBlocks.add(new VisualBlockText(((Double)RawJSTextBlockInformation[0][i]).intValue(),((Double)RawJSTextBlockInformation[1][i]).intValue(),
					((Double)RawJSTextBlockInformation[2][i]).intValue(),((Double)RawJSTextBlockInformation[3][i]).intValue(),
					(String)RawJSTextBlockInformation[4][i],(String)RawJSTextBlockInformation[5][i],((String)RawJSTextBlockInformation[6][i]),
					(String)RawJSTextBlockInformation[7][i], (String)RawJSTextBlockInformation[8][i]));
			
			System.out.println(visualBlocks.get(visualBlocks.size()-1));
		}
		System.out.println(RawJSTextBlockInformation[0].length + " text blocks found");
		
	}
	
	private void extractImageBlocks(View v) //$('form').find('input[type=image]')
	{
		String js = //top and left > 0
				"var arr = [new Array(), new Array(), new Array(), new Array(), new Array()];"
				+ "var i=0;var j=0;"
				+ "$('form').find(':image').not('input[type=image]').each(function(){ arr[i][j++] = ($(this).position().left);});"
				+"i++;j=0;"
				+ "$('form').find(':image').not('input[type=image]').each(function(){ arr[i][j++] = ($(this).position().top);});"
				+"i++;j=0;"
				+ "$('form').find(':image').not('input[type=image]').each(function(){ arr[i][j++] = ($(this).outerWidth());});"
				+"i++;j=0;"
				+ "$('form').find(':image').not('input[type=image]').each(function(){ arr[i][j++] = $(this).outerHeight();});"
				+"i++;j=0;"
				+ "$('form').find(':image').not('input[type=image]').each(function(){ arr[i][j++] = $(this).val();});"
				+ "return arr;";

		Object[] unstructuredJSTextBlockInformation = (Object[])v.evaluateJS(js);		
		Object[][] RawJSTextBlockInformation = new Object[unstructuredJSTextBlockInformation.length][((Object[])unstructuredJSTextBlockInformation[0]).length];
		
		for(int i=0;i<unstructuredJSTextBlockInformation.length;i++)
		{			
			RawJSTextBlockInformation[i] = (Object[])unstructuredJSTextBlockInformation[i];
		}

		for(int i=0;i<RawJSTextBlockInformation[0].length;i++)
		{
			visualBlocks.add(new VisualBlockImage(((Double)RawJSTextBlockInformation[0][i]).intValue(),((Double)RawJSTextBlockInformation[1][i]).intValue(),
					((Double)RawJSTextBlockInformation[2][i]).intValue(),((Double)RawJSTextBlockInformation[3][i]).intValue(),
					(String)RawJSTextBlockInformation[4][i]));		
		}		
		System.out.println(RawJSTextBlockInformation[0].length + " image blocks found");	
		
	}

	private void extractTextBoxBlocks(View v) ///CONSIDER TEXT AREA
	{
		String js = //top and left > 0
				"var arr = [new Array(), new Array(), new Array(), new Array(), new Array()];"
				+ "var i=0;var j=0;"
				+ "$('form').find(':text,textarea').each(function(){ arr[i][j++] = ($(this).position().left);});"
				+"i++;j=0;"
				+ "$('form').find(':text,textarea').each(function(){ arr[i][j++] = ($(this).position().top);});"
				+"i++;j=0;"
				+ "$('form').find(':text,textarea').each(function(){ arr[i][j++] = ($(this).outerWidth());});"
				+"i++;j=0;"
				+ "$('form').find(':text,textarea').each(function(){ arr[i][j++] = $(this).outerHeight();});"
				+"i++;j=0;"
				+ "$('form').find(':text,textarea').each(function(){ arr[i][j++] = $(this).val()});"
				+ "return arr;";

		Object[] unstructuredJSTextBlockInformation = (Object[])v.evaluateJS(js);
		Object[][] RawJSTextBlockInformation = new Object[unstructuredJSTextBlockInformation.length][((Object[])unstructuredJSTextBlockInformation[0]).length];
		
		for(int i=0;i<unstructuredJSTextBlockInformation.length;i++)
		{			
			RawJSTextBlockInformation[i] = (Object[])unstructuredJSTextBlockInformation[i];
		}

		for(int i=0;i<RawJSTextBlockInformation[0].length;i++)
		{
			addFormBlock(((Double)RawJSTextBlockInformation[0][i]).intValue(),((Double)RawJSTextBlockInformation[1][i]).intValue(),
					((Double)RawJSTextBlockInformation[2][i]).intValue(),((Double)RawJSTextBlockInformation[3][i]).intValue(),
					(String)RawJSTextBlockInformation[4][i], VisualBlockForm.Element.TEXTINPUT);			
		}
		System.out.println(RawJSTextBlockInformation[0].length + " textbox blocks found");
	}
	
	private void extractDropDownBlocks(View v)
	{
		String js = //top and left > 0
				"var arr = [new Array(), new Array(), new Array(), new Array(), new Array()];"
				+ "var i=0;var j=0;"
				+ "$('form').find('select').each(function(){ arr[i][j++] = ($(this).position().left);});"
				+"i++;j=0;"
				+ "$('form').find('select').each(function(){ arr[i][j++] = ($(this).position().top);});"
				+"i++;j=0;"
				+ "$('form').find('select').each(function(){ arr[i][j++] = ($(this).outerWidth());});"
				+"i++;j=0;"
				+ "$('form').find('select').each(function(){ arr[i][j++] = $(this).outerHeight();});"
				+"i++;j=0;var k=0;"
				+ "$('form').find('select').each(function(){ children = new Array();k=0; $(this).children('option').each(function(i, e){if (e.innerText != '') children[k++] =  e.innerText; }); arr[i][j++] = children});"
				+ "return arr;";

		Object[] unstructuredJSTextBlockInformation = (Object[])v.evaluateJS(js);		
		Object[][] RawJSTextBlockInformation = new Object[unstructuredJSTextBlockInformation.length][((Object[])unstructuredJSTextBlockInformation[0]).length];
		String[][] defaultValue = new String[((Object[])unstructuredJSTextBlockInformation[0]).length][];
		for(int i=0;i<unstructuredJSTextBlockInformation.length;i++)
		{		
			if (i == unstructuredJSTextBlockInformation.length-1) //text array will be in the final place
			{
				Object[] unstructuredDefaultValues = (Object[])unstructuredJSTextBlockInformation[i];
				for(int j=0; j<unstructuredDefaultValues.length;j++)
				{
					Object[] unstructuredDefaultValue = (Object[])unstructuredDefaultValues[j];
					defaultValue[j] = new String[unstructuredDefaultValue.length];
					for(int k=0; k<unstructuredDefaultValue.length;k++)
					{
						defaultValue[j][k] = ((String)unstructuredDefaultValue[k]).replaceAll("\r\n", " ").trim(); 
					}
				}
			}
			else
			{
				RawJSTextBlockInformation[i] = (Object[])unstructuredJSTextBlockInformation[i];				
			}
		}
		
		for(int i=0;i<RawJSTextBlockInformation[0].length;i++)
		{
			addFormBlock(((Double)RawJSTextBlockInformation[0][i]).intValue(),((Double)RawJSTextBlockInformation[1][i]).intValue(),
					((Double)RawJSTextBlockInformation[2][i]).intValue(),((Double)RawJSTextBlockInformation[3][i]).intValue(),
					defaultValue[i], VisualBlockForm.Element.DROPDOWN);	
			//System.out.println(visualBlocks.get(visualBlocks.size()-1));	
		}
		System.out.println(RawJSTextBlockInformation[0].length + " dropdown blocks found");	
	}
	private void extractRadioButtonBlocks(View v)
	{
		String js = //top and left > 0
				"var arr = [new Array(), new Array(), new Array(), new Array(), new Array()];"
				+ "var i=0;var j=0;"
				+ "$('form').find(':radio').each(function(){ arr[i][j++] = ($(this).position().left);});"
				+"i++;j=0;"
				+ "$('form').find(':radio').each(function(){ arr[i][j++] = ($(this).position().top);});"
				+"i++;j=0;"
				+ "$('form').find(':radio').each(function(){ arr[i][j++] = ($(this).outerWidth());});"
				+"i++;j=0;"
				+ "$('form').find(':radio').each(function(){ arr[i][j++] = $(this).outerHeight();});"
				+"i++;j=0;"
				+ "$('form').find(':radio').each(function(){ if ($(this).is(':checked')) {arr[i][j++] = 'selected'} else {arr[i][j++] = ''}});"
				+ "return arr;";

		Object[] unstructuredJSTextBlockInformation = (Object[])v.evaluateJS(js);		
		Object[][] RawJSTextBlockInformation = new Object[unstructuredJSTextBlockInformation.length][((Object[])unstructuredJSTextBlockInformation[0]).length];
		
		for(int i=0;i<unstructuredJSTextBlockInformation.length;i++)
		{			
			RawJSTextBlockInformation[i] = (Object[])unstructuredJSTextBlockInformation[i];
		}
		
		for(int i=0;i<RawJSTextBlockInformation[0].length;i++)
		{
			addFormBlock(((Double)RawJSTextBlockInformation[0][i]).intValue(),((Double)RawJSTextBlockInformation[1][i]).intValue(),
					((Double)RawJSTextBlockInformation[2][i]).intValue(),((Double)RawJSTextBlockInformation[3][i]).intValue(),
					(String)RawJSTextBlockInformation[4][i], VisualBlockForm.Element.RADIO);		
		}
		System.out.println(RawJSTextBlockInformation[0].length + " radio button blocks found");
	}
	
	private void extractCheckBoxBlocks(View v)
	{
		String js = //top and left > 0
				"var arr = [new Array(), new Array(), new Array(), new Array(), new Array()];"
				+ "var i=0;var j=0;"
				+ "$('form').find(':checkbox').each(function(){ arr[i][j++] = ($(this).position().left);});"
				+"i++;j=0;"
				+ "$('form').find(':checkbox').each(function(){ arr[i][j++] = ($(this).position().top);});"
				+"i++;j=0;"
				+ "$('form').find(':checkbox').each(function(){ arr[i][j++] = ($(this).outerWidth());});"
				+"i++;j=0;"
				+ "$('form').find(':checkbox').each(function(){ arr[i][j++] = $(this).outerHeight();});"
				+"i++;j=0;"
				+ "$('form').find(':checkbox').each(function(){ if ($(this).is(':checked')) {arr[i][j++] = 'selected'} else {arr[i][j++] = ''}});"
				+ "return arr;";

		Object[] unstructuredJSTextBlockInformation = (Object[])v.evaluateJS(js);		
		Object[][] RawJSTextBlockInformation = new Object[unstructuredJSTextBlockInformation.length][((Object[])unstructuredJSTextBlockInformation[0]).length];
		
		for(int i=0;i<unstructuredJSTextBlockInformation.length;i++)
		{			
			RawJSTextBlockInformation[i] = (Object[])unstructuredJSTextBlockInformation[i];
		}

		for(int i=0;i<RawJSTextBlockInformation[0].length;i++)
		{
			addFormBlock(((Double)RawJSTextBlockInformation[0][i]).intValue(),((Double)RawJSTextBlockInformation[1][i]).intValue(),
					((Double)RawJSTextBlockInformation[2][i]).intValue(),((Double)RawJSTextBlockInformation[3][i]).intValue(),
					(String)RawJSTextBlockInformation[4][i], VisualBlockForm.Element.CHECKBOX);		
		}		
		System.out.println(RawJSTextBlockInformation[0].length + " checkbox blocks found");
	}
	
	private void extractButtonBlocks(View v)
	{
		String js = //top and left > 0
				"var arr = [new Array(), new Array(), new Array(), new Array(), new Array()];"
				+ "var i=0;var j=0;"
				+ "$('form').find(':submit,input[type=image]').each(function(){ arr[i][j++] = ($(this).position().left);});"
				+"i++;j=0;"
				+ "$('form').find(':submit,input[type=image]').each(function(){ arr[i][j++] = ($(this).position().top);});"
				+"i++;j=0;"
				+ "$('form').find(':submit,input[type=image]').each(function(){ arr[i][j++] = ($(this).outerWidth());});"
				+"i++;j=0;"
				+ "$('form').find(':submit,input[type=image]').each(function(){ arr[i][j++] = $(this).outerHeight();});"
				+"i++;j=0;"
				+ "$('form').find(':submit,input[type=image]').each(function(){ arr[i][j++] = $(this).val();});"
				/*+"i++;j=0;" //necessary to
				+ "$('form').find(':select').each(function(){ if () {arr[i][j++] = 'submit';}else{j++;};});"*/
				+ "return arr;";

		Object[] unstructuredJSTextBlockInformation = (Object[])v.evaluateJS(js);		
		Object[][] RawJSTextBlockInformation = new Object[unstructuredJSTextBlockInformation.length][((Object[])unstructuredJSTextBlockInformation[0]).length];
		
		for(int i=0;i<unstructuredJSTextBlockInformation.length;i++)
		{			
			RawJSTextBlockInformation[i] = (Object[])unstructuredJSTextBlockInformation[i];
		}

		for(int i=0;i<RawJSTextBlockInformation[0].length;i++)
		{
			addFormBlock(((Double)RawJSTextBlockInformation[0][i]).intValue(),((Double)RawJSTextBlockInformation[1][i]).intValue(),
					((Double)RawJSTextBlockInformation[2][i]).intValue(),((Double)RawJSTextBlockInformation[3][i]).intValue(),
					(String)RawJSTextBlockInformation[4][i], VisualBlockForm.Element.BUTTON);
			//System.out.println(visualBlocks.get(visualBlocks.size()-1));			
		}		
		System.out.println(RawJSTextBlockInformation[0].length + " button blocks found");		
	}

}
