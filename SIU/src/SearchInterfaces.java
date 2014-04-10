

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class SearchInterfaces {
	
	ArrayList<SearchInterface> listOfSearchEngines;
	int currentValue = 0;
	String fileName;

	public SearchInterfaces(String file) {
		fileName = file;
		listOfSearchEngines = new ArrayList<SearchInterface>();
		BufferedReader br;
		String line;
		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) 
			{			
				listOfSearchEngines.add(new SearchInterface(line));
			}
			br.close();
			}
		catch (Exception e)
		{
			System.out.println("Something done fucked up: " + e);
		}
	}

	public SearchInterface getCurrentValue() {
		return listOfSearchEngines.get(currentValue);
	}
	
	public SearchInterface getNextValue() {
		currentValue++;
		if (currentValue == listOfSearchEngines.size())
			currentValue = 0;
		return getCurrentValue();
	}
	
	public SearchInterface getPreviousValue() {
		currentValue--;
		if (currentValue < 0)
			currentValue = listOfSearchEngines.size() - 1;
		return getCurrentValue();
	}

}
