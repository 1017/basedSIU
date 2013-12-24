

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class SearchEngines {
	
	ArrayList<String> listOfSearchEngines;
	int currentValue = 0;
	String fileName;

	public SearchEngines(String file) {
		fileName = file;
		listOfSearchEngines = new ArrayList<String>();
		BufferedReader br;
		String line;
		try {
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) 
			{			
				listOfSearchEngines.add(line);
			}
			br.close();
			}
		catch (Exception e)
		{
			System.out.println("Something done fucked up: " + e);
		}
	}

	public String getCurrentValue() {
		return listOfSearchEngines.get(currentValue);
	}
	
	public String getNextValue() {
		currentValue++;
		if (currentValue == listOfSearchEngines.size())
			currentValue = 0;
		return getCurrentValue();
	}
	
	public String getPreviousValue() {
		currentValue--;
		if (currentValue < 0)
			currentValue = listOfSearchEngines.size() - 1;
		return getCurrentValue();
	}

}
