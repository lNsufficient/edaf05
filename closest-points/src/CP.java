import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.lang.Math;

public class CP {
    public static void main(String args[]) {
        String fileName = "../data/"+args[0]+".txt";
        File file = new File(fileName);
		Scanner scan;
		ArrayList<double[]> al = new ArrayList<double[]>();
		Comparator comp1 = new Comparator<double[]>()
		{
			@Override
			public int compare(double[] xy1, double[] xy2)
			{
				Double y1 = xy1[1];
				Double y2 = xy2[1];
				return y1.compareTo(y2);
			}
		};
		
		Comparator comp0 = new Comparator<double[]>()
		{
			@Override
			public int compare(double[] xy1, double[] xy2)
			{
				Double y1 = xy1[0];
				Double y2 = xy2[0];
				return y1.compareTo(y2);
			}
		};
		try {
			scan = new Scanner(file);
			while(scan.hasNextLine()){
				String currentLine = scan.nextLine();
				String[] split = currentLine.split(" ");
				try {
					double[] info = new double[3];
					int success = 0;
					for (String str : split) {
						if (!str.isEmpty()) {
							info[success++] = Double.parseDouble(str);
						}
					}
					if (success >= 2) {
						double index = info[0];
						double x = info[1];
						double y = info[2];
						double[] xy = new double[2];
						xy[0] = info[1];
						xy[1] = info[2];
						al.add(xy);				
						
						System.out.println("Index: " + index + ", x: " + x + ", y: " + y);
					}	
				} catch (NumberFormatException f) {
					//f.printStackTrace();
					
					//System.out.println("split[0]:" + split[0]);
					//System.out.println("split[1]:" + split[1]);
					//System.out.println("split[2]:" + split[2]);
				}               
                
            }
			
			ArrayList<double[]> ay = new ArrayList<double[]>(al);
			Collections.sort(ay, comp1);
			
			Collections.sort(al, comp0);
			ArrayList<double[]> ax = al;
			for (double[] d : ax){
				System.out.println(d[0]);
			}
			
			for (double[] d : ay){
				System.out.println(d[1]);
			}
			
        } catch (FileNotFoundException e) {
	
        }
		
		
    }
	
	private double cp-rec(ArrayList<double[]> Px, ArrayList<double[]> Py) {
		if (Px.length() <= 3) {
			double shortest = Double.MAX_VALUE;
			for (int i = 0; i <= Px.length()-1-1; i++) {
				for (int j = i + 1; j <= Px.lengthj()-1; j++) {
					double d = dist(Px(i), Px(j));
					if (d < shortest) {
						shortest = d;
					}
				}
			}
			
			return shortest;
		}
		
		int midInd = Px.length()/2;
		ArrayList<double[]> Qx = new ArrayList<double[]>(Px.subList(0, midInd));
		ArrayList<double[]> Rx = new ArrayList<double[]>(Px.subList(midInd, Px.length()));
		ArrayList<double[]> Qy = new ArrayList<double[]>(Py.subList(0, midInd));
		ArrayList<double[]> Ry = new ArrayList<double[]>(Py.subList(midInd, Py.length()));
		
		double dq = cp-rec(Qx, Qy);
		double dr = cp-rec(Rx, Ry);
		
		double delta = Math.min(dq, dr);
		
		double xStar = Qx.get(Qx.length()-1);
		
		ArrayList<double[]> Sx = new ArrayList<double[]>();
		Sx.add(Qx.get(Qx.length()-1));
		
		for (int i = Qx.length()-2; i >= 0; i--) {
			double[] q = Qx.get(i);
			if (Math.abs(q[0]-xStar) < delta) {
				Sx.add(q);
			} else {
				break;
			}
		}
		
		for (int i = 0; i < Rx.length(); i++) {
			double[] r = Rx.get(i);
			if (Math.abs(r[0]-xStar) < delta) {
				Sx.add(r);
			} else {
				break;
			}
		}
		
		
	}

	private double dist(double[] p1, double[] p2) {
		return Math.hypot(p1[0]-p2[0], p1[1]-p2[1]);
	}
}
