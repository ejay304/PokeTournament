package poketournament;

import mediator.FightMediator;
import java.util.ArrayList;

/**
 * Pokemon
 * 
 * @since 27.05.2014
 * @author Alain FRESCO
 * @version 1.0
 */
public class Pokemon{
    
    public static ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
    
    private Type type;
    
    private String name;
    private int hp;
    private int attack;
    private ArrayList<Attack> skills;
    private FightMediator mediator;
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
    
    public static void init(){
    
        ArrayList<Attack> skills = new ArrayList<>(4);
		skills.add(new Attack("Tempête florale", 90, 100, Type.getType("Plante")));
		skills.add(new Attack("Bomb-beurk", 90, 100, Type.getType("Poison")));
		skills.add(new Attack("Séisme", 100, 95, Type.getType("Sol")));
		skills.add(new Attack("Force", 80, 100, Type.getType("Normal")));
		new Pokemon("Florizarre", Type.getType("Plante"), 187, 152, 152, 132)
				.setSkillList(skills);
		
		skills.clear();
		skills.add(new Attack("Surf", 90, 100, Type.getType("Eau")));
		skills.add(new Attack("Laser-glace", 90, 100, Type.getType("Glace")));
		skills.add(new Attack("Eclate-roc", 70, 100, Type.getType("Combat")));
		skills.add(new Attack("Morsure", 75, 100, Type.getType("Ténèbre")));
		new Pokemon("Tortank", Type.getType("Eau"), 186, 137, 157, 130)
				.setSkillList(skills);
		
		skills.clear();
		skills.add(new Attack("Lance-flamme", 90, 100, Type.getType("Feu")));
		skills.add(new Attack("Cru-aile", 85, 100, Type.getType("Vol")));
		skills.add(new Attack("Eboulement", 100, 95, Type.getType("Roche")));
		skills.add(new Attack("Dracogriffe", 85, 100, Type.getType("Dragon")));
		new Pokemon("Dracaufeu", Type.getType("Feu"), 185, 161, 137, 120)
				.setSkillList(skills);
          
		skills.clear();
		skills.add(new Attack("Ball'ombre", 90, 100, Type.getType("Spectre")));
		skills.add(new Attack("Vibrobscur", 85, 100, Type.getType("Ténèbre")));
		skills.add(new Attack("Psyko", 90, 100, Type.getType("Psy")));
		skills.add(new Attack("Tonnerre", 90, 100, Type.getType("Electrique")));
		new Pokemon("Ectoplasma", Type.getType("Spectre"), 167, 182, 127, 162)
				.setSkillList(skills);
		
		skills.clear();
		skills.add(new Attack("Lame roc", 110, 85, Type.getType("Roche")));
		skills.add(Attack.getAttack("Séisme"));
		skills.add(new Attack("Marto-poing", 80, 100, Type.getType("Combat")));
		skills.add(new Attack("Mégacorne", 110, 85, Type.getType("Insecte")));
		new Pokemon("Rhinoferos", Type.getType("Roche"), 212, 182, 172, 92)
				.setSkillList(skills);
		
		skills.clear();
		skills.add(Attack.getAttack("Tonnerre"));
		skills.add(new Attack("Double-pied", 80, 100, Type.getType("Vol")));
		skills.add(new Attack("Dard-nuée", 75, 100, Type.getType("Roche")));
		skills.add(Attack.getAttack("Ball'ombre"));
		new Pokemon("Voltali", Type.getType("Electrique"), 172, 162, 147, 182)
				.setSkillList(skills);
		
		skills.clear();
		skills.add(new Attack("Plaquage", 90, 100, Type.getType("Normal")));
		skills.add(Attack.getAttack("Morsure"));
		skills.add(new Attack("Roulade", 60, 100, Type.getType("Roche")));
		skills.add(new Attack("Tacle lourd", 85, 100, Type.getType("Acier")));
		new Pokemon("Ronflex", Type.getType("Normal"), 267, 162, 162, 82)
				.setSkillList(skills);
		
		skills.clear();
		skills.add(new Attack("Colère", 110, 85, Type.getType("Dragon")));
		skills.add(Attack.getAttack("Cru-aile"));
		skills.add(Attack.getAttack("Surf"));
		skills.add(Attack.getAttack("Lance-flamme"));
		new Pokemon("Dracolosse", Type.getType("Dragon"), 198, 186, 147, 132)
				.setSkillList(skills);
    }
    
    public void setMediator(FightMediator fight){
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
        this.skills = (ArrayList<Attack>)skills.clone();
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
    public void doStatus() {
		switch (status) {
		
		case PARALYSE:
		case GELE:
			
			break;

		case POISON:
		case BRULE:
			break;

		}
	}

	private void restoreStatus() {
		this.status = Status.EN_FORME;
	}
}
 
