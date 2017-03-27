package Matching;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class GS {

	public static void main(String[] args) {

		//Read the names;
		File file = new File("data\\" + args[0] + ".txt");
		Scanner scan;
		int n = 0;
		ArrayList<Person> personList = new ArrayList<Person>();
		try {
			scan = new Scanner(file);
			while(scan.hasNextLine()){ //Skips lines starting with # and reads the line "n=?"
				String currentLine = scan.nextLine();
				if (currentLine.length() > 0) {
					if (currentLine.charAt(0) != '#'){
						n = Character.getNumericValue(currentLine.charAt(2));
						break;
					}
				}
			}
			for (int i = 0; i < 2*n; i++){//reads the lines which corresponds each name to a number
				String currentLine = scan.nextLine();
				String[] split = currentLine.split(" ");
				int nbr = Integer.parseInt(split[0]);
				String name = split[1];
				personList.add(new Person(nbr, name));
			}
			for (Person person:personList){
				System.out.println(person.getNumber() + " " + person.getName());
			}
			scan.nextLine();
			
			for (int i = 0; i < 2*n; i++){//reads the lines containing preferences
				String pref = scan.nextLine();
				String[] split = pref.split(":");
				System.out.print(split[0]);
				System.out.println(split[1]);
				Scanner scan2 = new Scanner(split[1]);
				while(scan2.hasNext()){
					System.out.println(scan2.nextInt());
				}
				scan2.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		LinkedList<Integer> menList = new LinkedList<Integer>();
		
		
	}

}
