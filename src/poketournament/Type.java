package poketournament;

import java.util.HashMap;
import java.util.LinkedList;

public class Type {

	public static HashMap<String, Type> types = new HashMap<String, Type>();

	private String name;
	private LinkedList<Type> weakness;
	private LinkedList<Type> resist;
	private LinkedList<Type> immunity;

	public Type(String name) {
		this.name = name;
		types.put(name, this);
		weakness = new LinkedList<Type>();
		resist = new LinkedList<Type>();
		immunity = new LinkedList<Type>();
	}

	/**
	 * Spécifie les types auquelles le type est faible
	 * 
	 * @param t
	 *            les types auquelles le type est faible
	 */
	public void setWeakness(Type... t) {
		for (Type type : t) {
			weakness.add(type);
		}
	}

	/**
	 * Spécifie les types auquelles le type est résistant
	 * 
	 * @param t
	 *            les types auquelles le type est résistant
	 */
	public void setResist(Type... t) {
		for (Type type : t) {
			resist.add(type);
		}
	}

	/**
	 * Spécifie les types auquelles le type est immunisé
	 * 
	 * @param t
	 *            les types auquelles le type est immunisé
	 */
	public void setImmunity(Type... t) {
		for (Type type : t) {
			immunity.add(type);
		}
	}

	/**
	 * Récupère le type spécifier par la clé.
	 * 
	 * @param name
	 *            le nom du type : la clé utiliser pour le référencer
	 * @return le type si il existe, null autrement
	 */
	public static Type getType(String name) {
		return types.get(name);
	}
}
