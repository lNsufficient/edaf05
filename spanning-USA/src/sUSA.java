import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class sUSA{
	
	
	public static void main(String args[]) {
		File file = new File("../data/USA-highway-miles.txt");
		System.out.print("[]");
		Scanner scan;
		try {
			scan = new Scanner(file);
			while(scan.hasNextLine()){
				String currentLine = scan.nextLine();
				String[] split = currentLine.split("--");
				if (split.length>1) {
					String town1 = split[0];
					String[] split1 = split[1].split(" \\[");
					String town2 = split1[0];
					String edge = split1[1];
					edge = edge.substring(0, edge.length()-1);
					//edge = edge.replace("\\]", " ");
					int edgeLength = Integer.parseInt(edge);
					System.out.println("New edge: " + town1 + ":" + town2 + ":" + edgeLength);
					//create object
					//add object to sorted list
				} else {
					String newTown = split[0];
					System.out.print("New town: ");
					System.out.println(newTown);
					//create town
					//Create set containing town inside set of sets.
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}