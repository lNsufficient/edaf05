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
						
						//System.out.println("Index: " + index + ", x: " + x + ", y: " + y);
					}	
				} catch (NumberFormatException f) {
					//f.printStackTrace();
					
					////System.out.println("split[0]:" + split[0]);
					////System.out.println("split[1]:" + split[1]);
					////System.out.println("split[2]:" + split[2]);
				}               
                
            }
			
			if (al.isEmpty()){
				al = romeoJuliaMode(file);
			}

			
			ArrayList<double[]> ay = new ArrayList<double[]>(al);
			Collections.sort(ay, comp1);
			
			Collections.sort(al, comp0);
			ArrayList<double[]> ax = al;
			/*
			for (double[] d : ax){
				//System.out.println(d[0]);
			}
			
			for (double[] d : ay){
				//System.out.println(d[1]);
			}
			*/
			

			double dist = cpRec(ax, ay);
			
			System.out.println("Shortest distance is: " + dist);
			
        } catch (FileNotFoundException e) {
	
        }
		
		
    }
	
	private static ArrayList<double[]> romeoJuliaMode(File file) {
		ArrayList<double[]> al = null;
		try {
			al = new ArrayList<double[]>();
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String currentLine = scan.nextLine();
				String[] split = currentLine.split(" ");
				if (split.length < 3) {
					break;
				}
				double[] xy = new double[2];
				xy[0] = Double.parseDouble(split[1]);
				xy[1] = Double.parseDouble(split[2]);
				al.add(xy);
			}
			
		} catch (FileNotFoundException e) {
	
        }
		return al;
	}
	
	private static double cpRec(ArrayList<double[]> Px, ArrayList<double[]> Py) {
		if (Px.size() <= 3) {
			double shortest = Double.MAX_VALUE;
			for (int i = 0; i <= Px.size()-1-1; i++) {
				for (int j = i + 1; j <= Px.size()-1; j++) {
					double d = dist(Px.get(i), Px.get(j));
					if (d < shortest) {
						shortest = d;
					}
				}
			}
			
			return shortest;
		}
		
		int midInd = Px.size()/2;
		ArrayList<double[]> Qx = new ArrayList<double[]>(Px.subList(0, midInd));
		ArrayList<double[]> Rx = new ArrayList<double[]>(Px.subList(midInd, Px.size()));

		double xStar = Qx.get(Qx.size()-1)[0];
		
		ArrayList<double[]> Qy = new ArrayList<double[]>();
		ArrayList<double[]> Ry = new ArrayList<double[]>();
		for (double[] p : Py) {
			if (p[0] < xStar)
				Qy.add(p);
			else if (p[0] == xStar) {
				for (int i = Qx.size()-1; i > 0; i--) {
					if (p == Qx.get(i)) {
						Qy.add(p);
						break;
					}
					if (Qx.get(i)[0] < xStar) {
						Ry.add(p);
						break;
					}
				}
			}
			if (p[0] > xStar) {
				Ry.add(p);
			}
		} 
				
		double dq = cpRec(Qx, Qy);
		double dr = cpRec(Rx, Ry);
		
		double delta = Math.min(dq, dr);
		

		
		/*
		ArrayList<double[]> Sx = new ArrayList<double[]>();
		Sx.add(Qx.get(Qx.size()-1));
		
		for (int i = Qx.size()-2; i >= 0; i--) {
			double[] q = Qx.get(i);
			if ((xStar-q[0]) < delta) {
				Sx.add(0,q);
			} else {
				break;
			}
		}
		
		for (int i = 0; i < Rx.size(); i++) {
			double[] r = Rx.get(i);
			if ((r[0]-xStar) < delta) {
				Sx.add(r);
			} else {
				break;
			}
		}
		*/
		
		ArrayList<double[]> Sy = new ArrayList<double[]>();
		//System.out.println("xStar is: " + xStar + ", delta is: " + delta + "\n Current Px:");
		
		for (double[] p : Px) {
			//System.out.println("x: " + p[0] + ", y: " + p[1]);
		}
		
		//System.out.println("Inside py: ");
		for (double[] p : Py) {

			//System.out.println("x: " + p[0] + ", y: " + p[1]);
			//System.out.println("p[0]: " + p[0]);
			if (Math.abs(p[0]-xStar) < delta) {
				Sy.add(p);
				
			}
		}
		
		double sShortest = Double.MAX_VALUE;
		//System.out.println("Size of Sy: " + Sy.size());
		for (int i = 0; i < Sy.size()-1; i++) {
			for (int j = i+1; j < Sy.size(); j++) {
				//System.out.println("Inuti sShortest");
				double d = dist(Sy.get(i), Sy.get(j));
				if (d < sShortest) {
					sShortest = d;
				}
			}
		}
		
		return Math.min(sShortest, delta);
	}

	private static double dist(double[] p1, double[] p2) {
		return Math.hypot(p1[0]-p2[0], p1[1]-p2[1]);
	}
}
