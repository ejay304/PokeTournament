package poketournament;

import java.util.LinkedList;

public class Type {
	
	public static LinkedList<Type> types = new LinkedList<Type>();
	
	private String name;
	private LinkedList<Type> weakness;
	private LinkedList<Type> resist;
	private LinkedList<Type> immunity;
	
	public Type(String name) {
		this.name = name;
		types.add(this);
		weakness = new LinkedList<Type> ();
		resist = new LinkedList<Type> ();
		immunity = new LinkedList<Type> ();
	}
	
	public void setWeakness(Type ... t) {
		for(Type type : t) {
			weakness.add(type);
		}
	}
	
	public void setResist(Type ... t) {
		for(Type type : t) {
			resist.add(type);
		}
	}
	
	public void setImmunity(Type ... t) {
		for(Type type : t) {
			immunity.add(type);
		}
	}
}
