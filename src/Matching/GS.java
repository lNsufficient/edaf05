package Matching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class GS {

	public static void main(String[] args) {

		//Read the names;
		File file = new File(args[0]);
		Scanner scan;
		try {
			scan = new Scanner(file);
			while(scan.hasNextLine()){
				System.out.println(scan.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Test");
		
		LinkedList<Integer> menList = new LinkedList<Integer>();
		
		
	}

}
