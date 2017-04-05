import java.util.Scanner;
import java.util.Collections;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class sUSA{
	
	
	public static void main(String args[]) {
		File file = new File("../data/USA-highway-miles.txt");
//		File file = new File("../data/tinyEWG-alpha.txt");
		Scanner scan;
		ArrayList<Edge> edges = new ArrayList<Edge>();
		ArrayList<String> towns = new ArrayList<String>();

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
					//System.out.println("New edge: " + town1 + ":" + town2 + ":" + edgeLength);
					
					//create object
					int town1index = towns.indexOf(town1);
					int town2index = towns.indexOf(town2);
					Edge tmpEdge = new Edge(edgeLength, town1index, town2index);
					
					if (town1index == -1 || town2index == -1) {
						System.out.println("COULD NOT FIND TOWN");
					}

					if (town1index == 0 || town2index == 0) {
						//System.out.println("FOUND A ZERO");
					}

					//add object to sorted list
					edges.add(tmpEdge);
				} else {
					String newTown = split[0];
                    char lastChar = newTown.charAt(newTown.length()-1);
                    if (lastChar == ' ') {
    					newTown = newTown.substring(0, newTown.length()-1);
                    }
					System.out.print("New town:");
					System.out.print(newTown+":");
					towns.add(newTown);
					System.out.println(towns.indexOf(newTown));
					
					//create town
					//Create set containing town inside set of sets.
				}
			}
			
			Collections.sort(edges);
			//System.out.println(edges);
			
			UF uf = new UF(towns.size());
			int totalLength = 0;
			
			for (Edge tmpEdge : edges){
				int[] uv = tmpEdge.getTowns();
				int l0 = uf.find(uv[0]);
				int l1 = uf.find(uv[1]);
				if (l0 != l1) {
                    System.out.println("Connecting:" + uv[0] + ":" + uv[1]);
					totalLength+=tmpEdge.getCost();
					uf.union(l0,l1);
				//	System.out.println("Differents stuff");
				} else {
//					System.out.println("Sames stuff");
				}
			}
			
			System.out.println("\n \n \n TOTAL LENGTH: " + totalLength);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
