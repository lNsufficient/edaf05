package Matching;

public class Person {
	
	int[] prefs;
	int nbr;
	String name;
	int i;
	boolean isMale;
	
	public Person(int nbr, String name, int n, boolean isMale){
		this.nbr = nbr;
		this.name = name;
		this.prefs = new int[n];
		this.isMale = isMale;
		if (isMale){
			this.i = -1;
		} else {
			this.i = prefs.length - 1;
		} 
	}
	
	public Person(int nbr, String name){
		this.nbr = nbr;
		this.name = name;
	}
	
	public int getNumber(){
		return nbr;
	}
	
	public String getName(){
		return name;
	}
	
	public int getNextPref(){
		i++;
		if (i >= prefs.length) {
			return -1;
		} else {
			return prefs[i];
		}
	}
	
	public boolean findPref(int prefNbr) {
		for(int j = i-1; j >= 0; j--){
			if (prefs[j] == prefNbr) {
				i = prefNbr;
				return true;
			}		
		}
		return false;
	}
}
