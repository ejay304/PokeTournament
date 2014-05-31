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
    
    private Type type;
    
    private String name;
    private int hp;
    private int attack;
    private ArrayList<Attack> skills;
    private Fight mediator;
    private int defense;
    private int speed;
    private Status status;

    public Pokemon(String name, Type type, int hp, int attack, int defense, int speed) {
        this.name = name;
        this.hp = hp;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.status = Status.EN_FORME;
        Pokemon.pokemons.add(this);
    }
    
    public void setMediator(Fight fight){
        this.mediator = fight;
    }

    public String getName() {
        return name;
    }
    
    public void doAttack(Attack attack){
        mediator.attack(this, attack);
    }

    public int getHp() {
        return hp;
    }
    
    public Type getType(){
        return type;
    }

    public ArrayList<Attack> getSkillList() {
        return skills;
    }
    
    public void setSkillList(ArrayList<Attack> skills){
        this.skills = skills;
    }
    
    public int getAttack(){
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }
 
}
