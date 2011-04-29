package tracker;


import java.util.*;

public class Tracker<T> {

	protected Collection<T> members;
	
	public Tracker() {
		members = new ArrayList<T>();}
	
	public void add(T s) { members.add(s); }
	public void remove(T s) { members.remove(s); }
	
	public ArrayList<T> getMembers() { 
		
		ArrayList<T> result = new ArrayList<T>();
		
		for (T m : members) result.add(m);
		
		return result;
		
	}	
		
}
