

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

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
			Scanner s;

			while ((line = br.readLine()) != null) 
			{			
				s = new Scanner(line);
				s.useDelimiter(" ");
				SearchInterface searchInterface;
				searchInterface = new SearchInterface(s.next());
				if(s.hasNext()) 
				{
					searchInterface.setDefaultForm(s.nextInt());
					System.out.println(searchInterface.getDefaultForm());
				}
				listOfSearchEngines.add(searchInterface);
				s.close();
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
	
	public SearchInterface getValue(int value) {
		currentValue = value;
		return listOfSearchEngines.get(value);
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
