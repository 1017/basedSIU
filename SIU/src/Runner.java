

public class Runner {
	
	public static void main(String args[])
	{
		View view = new View();
		Controller controller = new Controller(view, new SearchInterfaces("tel8 books.txt"));
		
		view.display();
	}

}
