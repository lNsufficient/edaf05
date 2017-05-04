import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class SA {
	static int[][] charVals;
	static String chars;
	static int delta = 4;
	
	public static void main(String[] args) {
		File file = new File("../data/" + args[0] +".txt");
		File blos = new File("../data/BLOSUM62.txt");
		
		chars = "";
		charVals = new int[24][24];
		
		ArrayList<String[]> classesAndDna = new ArrayList<String[]>();
		
		try {
			Scanner scan = new Scanner(blos);
			boolean savedString = false;
			int charsRead = 0;
			while(scan.hasNextLine()) {
				String currentLine = scan.nextLine();
				if (currentLine.charAt(0) == '#') {
					continue;
				} else if (!savedString){
					String[] split = currentLine.split(" ");
					for (int i = 0; i < split.length; i++) {
						if (!split[i].isEmpty()) {
							chars = chars + split[i];
						}
					}
					savedString = true;
					System.out.println(chars);
				} else {
					String[] split = currentLine.split(" ");
					int charPos = 0;
					for (int i = 1; i < split.length; i++) {
						if (!split[i].isEmpty()) {
							charVals[charsRead][charPos++] = (-1)*Integer.parseInt(split[i]);
						} 
					}
					charsRead++;
				}
				
			}
			
			for (int i = 0; i < charVals.length; i++){
				for (int j = 0; j < charVals[0].length; j++) {
					if (charVals[i][j] >= 0 && charVals[i][j] < 10) {
						System.out.print(" " + (charVals[i][j]) + " ");
					} else {
						System.out.print((charVals[i][j]) + " ");
					}
				}
				System.out.print("\n");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		try {
			Scanner scan = new Scanner(file); 
			int arrayIndex = -1;
			while(scan.hasNextLine()) {
				String currentLine = scan.nextLine();
				if(currentLine.charAt(0) == '>') {
					arrayIndex++;
					String[] split = currentLine.split(" ");
					String[] classDna = new String[2];
					String className = split[0].substring(1);
					classDna[0] = className;
					classDna[1] = "";
					classesAndDna.add(classDna);
				}else{
					classesAndDna.get(arrayIndex)[1]+=currentLine;
				}
				
			}
			for (String[] str:classesAndDna){
				System.out.println(str[0] + " " + str[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		for (int i = 0; i < classesAndDna.size(); i++){
			for (int j = 0; j < classesAndDna.size(); j++){
				System.out.println("=================================");
				System.out.println(classesAndDna.get(i)[0] + " and " + classesAndDna.get(j)[0]);
				System.out.println(classesAndDna.get(i)[1] + "\n" + classesAndDna.get(j)[1]);
				String ans = Alignment(classesAndDna.get(i)[1], classesAndDna.get(j)[1]);
				System.out.println(ans);
				System.out.println("=================================");
			}
		}
		
	}
	
	
	private static String Alignment(String X, String Y){
		int m = X.length(), n = Y.length(); 
		
		if (m > n) {
			String tmp = X;
			X = Y;
			Y = tmp;
			m = X.length();
			n = Y.length(); 
		}
		
		int[][] A = new int[m+1][n+1];
		String[][] match = new String[m+1][n+1];
		match[0][0] = "";
		for (int j = 1; j < n + 1; j++) {
			A[0][j] = j*delta;
			match[0][j] = match[0][j-1] + "-";
 		}
		for (int i = 1; i < m + 1; i++) {
			A[i][0] = i*delta;
			match[i][0] = match[i-1][0] + "-";
		}
		
		
		for (int j = 1; j < n + 1; j++) {
			for (int i = 1; i < m + 1; i++) {
				A[i][j] = opt(A, match, X.substring(0, i), Y.substring(0, j));
			}
		}
		
		/*
		for (int j = 0; j < n + 1; j++) {
			for (int i = 0; i < m + 1; i++) {
				if (A[i][j] >= 0 && A[i][j] < 10) {
					System.out.print(" " + (A[i][j]) + " ");
				} else {
					System.out.print((A[i][j]) + " ");
				}
			}
			System.out.print("\n");
		} */
		
		
		return match[m][n];
	}
	
	private static int opt(int[][] A, String[][] match, String X, String Y) {
		int i = X.length();
		int j = Y.length();
		int xi = chars.indexOf(X.charAt(i-1));
		int yi = chars.indexOf(Y.charAt(j-1));
		int alpha = charVals[xi][yi];
		if (alpha + A[i-1][j-1] < delta + A[i-1][j]) {
			if (alpha + A[i-1][j-1] < delta + A[i][j-1]) {
				match[i][j] = match[i-1][j-1] + X.charAt(i-1);
				return alpha + A[i-1][j-1];
			} else {
				match[i][j] = match[i][j-1] + "-";
				return delta + A[i][j-1];
			}	
		} else if (delta + A[i-1][j] < delta + A[i][j-1]){
			match[i][j] =  match[i-1][j] + "-";
			return delta + A[i-1][j];
		} else {
			match[i][j] = match[i][j-1] + "-";
			return delta + A[i][j-1];
		}
		//return Math.min(Math.min(alpha + A[i-1][j-1], delta + A[i-1][j]), delta + A[i][j-1]);
	}
}
