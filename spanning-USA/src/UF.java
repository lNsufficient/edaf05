import java.util.ArrayList;
import java.util.LinkedList;

public class UF{
	int[] sets;
	int[] sizes;
	ArrayList<LinkedList> items;
	
	public UF(int n){
		System.out.println(n);
		sets = new int[n];
		for (int i = 0; i < n; i++) {
			sets[i] = i;
		}
		sizes = new int[n];
		for (int i = 0; i < n; i++) {
			sizes[i] = 1;
		}
		
		items = new ArrayList<LinkedList>(n);
		for (int i = 0; i < n; i++) {
			LinkedList<Integer> tmpList = new LinkedList<Integer>();
			tmpList.add(i);
			items.add(i, tmpList);
		}
	}
	
	public int find(int i) {
		return sets[i];
	}
	
	public void union(int x, int y) {
		int small, llong;
		System.out.println(x + " " + y);
		System.out.println(sizes[x] + " " + sizes[y]);
		
		
		if (sizes[x] < sizes[y]) {
			small = x;
			llong = y;
		} else {
			small = y;
			llong = x;
		}
		
		LinkedList<Integer> shortList = items.get(small);
		for (Integer i : shortList) {
			sets[i] = llong;
		}
		
		items.get(llong).addAll(shortList);
		items.set(small, null);
		sizes[llong] += sizes[small];
		sizes[small] = 0;
		
		System.out.println(sizes[x] + " " + sizes[y]);
	}
	
}