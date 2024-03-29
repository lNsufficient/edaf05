package Matching;

public class Person {
	
	int[] prefs;
	int nbr;
	String name;
	int i;
	boolean isMale;
	int scanIndex;
	
	public Person(int nbr, String name, int n, boolean isMale){
		this.nbr = nbr;
		this.name = name;
		this.prefs = new int[n];
		this.scanIndex = 0;
		this.isMale = isMale;
		if (isMale){
			this.i = -1;
		} else {
			this.i = prefs.length;
		} 
	}
	
	
	public Person(int nbr, String name){
		this.nbr = nbr;
		this.name = name;
	}
	
	public boolean addPreference(int pref) {
		if (scanIndex == prefs.length) {
			return false;
		}
		prefs[scanIndex] = pref;
		scanIndex++;
		return true;
		
	}
	
	public int getNumber(){
		return nbr;
	}
	
	public String getName(){
		return name;
	}
	
	public int getNextPref(){
		if (!isMale) {
			System.out.println("Calling for male function for female.");
		}
		i++;
		if (i >= prefs.length) {
			return -1;
		} else {
			return prefs[i];
		}
	}
	
	public int findPref(int prefNbr) {
		int oldi = -1;
		for(int j = i-1; j >= 0; j--){
			if (prefs[j] == prefNbr) {
				oldi = i;
				i = j;
				if (oldi == prefs.length) {
					return -2;
				}
				return prefs[oldi];
			}		
		}
		return oldi;
	}
	
	public int geti() {
		return i;
	}
	
	public int getMatch() {
		return prefs[i];
	}
}
