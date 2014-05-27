package poketournament;

import java.util.ArrayList;

/**
 * Pokemon
 * 
 * @since   27.05.2014
 * @author  Alain FRESCO
 * @version 1.0
 */
public class Pokemon{
    
    public static ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
    
    private Object[] moveset = new Object[4];
    private Object type;
    
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private int speed;

    public Pokemon(String name, int hp, int attack, int defense, int speed) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        
        Pokemon.pokemons.add(this);
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }
 
}
