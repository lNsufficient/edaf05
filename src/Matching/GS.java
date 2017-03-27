package Matching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class GS {

	public static void main(String[] args) {

		//Read the names;
		File file = new File("data\\" + args[0] + ".txt");
		Scanner scan;
		try {
			scan = new Scanner(file);
			while(scan.hasNextLine()){
				String currentLine = scan.nextLine();
				if (currentLine.length() > 0) {
					if (currentLine.charAt(0) == '#'){
						System.out.println("h");
					} else {
						System.out.println(currentLine);
					}
				}
					
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		LinkedList<Integer> menList = new LinkedList<Integer>();
		
		
	}

}
