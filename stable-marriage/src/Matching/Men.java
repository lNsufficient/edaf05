package Matching;

import java.util.LinkedList;

public class Men {
	
	LinkedList<Integer> preferences;
	int nbr;
	String name;
	
	
	public Men(int nbr, String name, LinkedList<Integer> preferences){
		this.nbr = nbr;
		this.name = name;
		this.preferences = preferences;
	}
	
	public int getNumber(){
		return nbr;
	}
	
	public String getName(){
		return name;
	}
	
	public Integer preference(){
		return preferences.poll();
	}
}
