public class Edge implements Comparable<Edge>{
	
	private int cost;
	private int[] towns;
	
	public Edge(int cost, int town1index, int town2index) {
		this.cost = cost;
		towns = new int[2];
		towns[0] = town1index;
		towns[1] = town2index;
	}
	
	public int compareTo(Edge o) {
		return (this.cost - o.cost);
	}
	
	public int getCost() {
		return cost;
	}
	
	public int hasTown(int town) {
		if (towns[0] == town)
			return 0;
		if (towns[1] == town)
			return 1;
		return -1;
	}
	
	public int[] getTowns() {
		return towns;
	}
	
	public String toString(){
		return cost + ": " + towns[0] + "--" + towns[1];
	}
	
}