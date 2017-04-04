import java.util.io.scanner;

public class sUSA{
	
	
	public static void main(String args[]) {
		File file = new File("data/USA-highway-miles.txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}