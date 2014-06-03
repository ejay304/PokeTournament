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
		weakness = new LinkedList<Type>();
		resist = new LinkedList<Type>();
		immunity = new LinkedList<Type>();
          types.put(name, this);
	}
     
     public static void init(){
     
         // Création des types
		Type water = new Type("Eau");
		Type grass = new Type("Plante");
		Type fire = new Type("Feu");
		Type normal = new Type("Normal");
		Type dragon = new Type("Dragon");
		Type electric = new Type("Electrique");
		Type ground = new Type("Sol");
		Type rock = new Type("Roche");
		Type fighting = new Type("Combat");
		Type poison = new Type("Poison");
		Type ghost = new Type("Spectre");
		Type ice = new Type("Glace");
		Type bug = new Type("Insecte");
		Type psy = new Type("Psy");
		Type dark = new Type("Ténèbre");
		Type flying = new Type("Vol");
		Type steel = new Type("Acier");

		// Gestion des résistance, faiblesses et immunités des types entre eux
		water.setResist(water, steel, fire, ice);
		water.setWeakness(grass, electric);
		grass.setResist(water, electric, grass, rock);
		grass.setWeakness(fire, ice, bug, poison, flying);
		fire.setResist(steel, fire, ice, bug, grass);
		fire.setWeakness(water, rock, ground);
		normal.setWeakness(fighting);
		normal.setImmunity(ghost);
		dragon.setResist(water, electric, fire, grass);
		dragon.setWeakness(dragon, ice);
		electric.setResist(steel, electric, flying);
		electric.setWeakness(ground);
		ground.setResist(poison, rock);
		ground.setWeakness(water, grass, ice);
		ground.setImmunity(electric);
		rock.setResist(fire, normal, poison, flying);
		rock.setWeakness(steel, fighting, water, grass, ground);
		fighting.setResist(bug, rock, dark);
		fighting.setWeakness(psy, flying);
		poison.setResist(fighting, bug, grass, poison);
		poison.setWeakness(ground, psy);
		ghost.setResist(bug, poison);
		ghost.setWeakness(ghost, dark);
		ghost.setImmunity(normal, fighting);
		ice.setResist(ice);
		ice.setWeakness(steel, fighting, fire, rock);
		bug.setResist(fighting, grass, ground);
		bug.setWeakness(fire, rock, flying);
		psy.setResist(fighting, psy);
		psy.setWeakness(bug, dark, ghost);
		dark.setResist(ghost, dark);
		dark.setWeakness(fighting, bug);
		dark.setImmunity(psy);
		flying.setResist(fighting, bug, grass);
		flying.setWeakness(electric, ice, rock);
		flying.setImmunity(ground);
		steel.setResist(steel, dragon, ice, bug, normal, grass, psy, rock, ghost, dark, flying);
		steel.setWeakness(fighting, fire, ground);
		steel.setImmunity(poison);
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

	/**
	 * Récupère la hashmap des types.
	 * 
	 * @return la hashmap contenant les types
	 */
	public static HashMap<String, Type> getTypes() {
		return types;
	}
	
	public boolean isResistant(Type type) {
		return resist.contains(type);
	}
	
	public boolean isWeak(Type type) {
		return weakness.contains(type);
	}
	
	public boolean isImmune(Type type) {
		return immunity.contains(type);
	}
	
	public double getVulnerabilityFactor(Type type) {
		double factor = 1;
		if(isImmune(type)) {
			factor = 0;
		} else if(isResistant(type)) {
			factor = 0.5;
		} else if(isWeak(type)) {
			factor = 2;
		}
		return factor;
		
	}
}
