package Matching;
import java.util.Stack;

public class Women {

	private int nbr;
	private String name;
	private Stack<Integer> prefs;

	public Women(int nbr, String name){
		this.nbr = nbr;
		this.name = name;
		this.prefs = new Stack<Integer>();
	}
	
	public int getNumber(){
		return nbr;
	}
	
	public String getName(){
		return name;
	}
	
	
}
