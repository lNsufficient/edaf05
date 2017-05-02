import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.util.Scanner;

public class CR {
	
    public static void main(String args[]) {
        String ourFile = "out.txt";
		String theirFile = "../data/closest-pair-out.txt";
        File ourfile = new File(ourFile);
		File theirfile = new File(theirFile);
		Scanner scanOur;
		Scanner scanTheir;


		try {
			double maxPerc = 0.00000000000000000000001;
			System.out.println("When difference is larger than " + maxPerc + "percent:");
			scanOur = new Scanner(ourfile);
			scanTheir = new Scanner(theirfile);
			while(scanOur.hasNextLine() && scanTheir.hasNextLine()){
				String currentLine = scanOur.nextLine();
				String[] split = currentLine.split(" ");
				double dOur = Double.parseDouble(split[2]);
				
				currentLine = scanTheir.nextLine();
				split = currentLine.split(" ");
				double dTheir = Double.parseDouble(split[2]);
				
				double diff = dOur - dTheir;
				double perc = 0;
				if (dOur != 0){
					perc = diff/dOur;
				} else {
					perc = diff/1;
				}
				
				if (perc > maxPerc) {
					System.out.println("Perc: " + perc + ", dOur: " + dOur + ", dTheir: " + dTheir);
				}
			}
		} catch (Exception e){
			
		}
	}
}