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
public class Pokemon {

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
    private boolean isIncapacitated;

    public Pokemon(String name, Type type, int hp, int attack, int defense,
            int speed) {
        this.name = name;
        this.hp = hp;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.status = Status.EN_FORME;
        isIncapacitated = false;
        Pokemon.pokemons.add(this);
    }

    public static void init() {

        ArrayList<Attack> skills = new ArrayList<>(4);
        skills.add(Attack.getAttack("Tempête florale"));
        skills.add(Attack.getAttack("Bomb-beurk"));
        skills.add(Attack.getAttack("Séisme"));
        skills.add(Attack.getAttack("Force"));
        new Pokemon("Florizarre", Type.getType("Plante"), 187, 152, 152, 132)
                .setSkillList(skills);

        skills.clear();
        skills.add(Attack.getAttack("Surf"));
        skills.add(Attack.getAttack("Laser-glace"));
        skills.add(Attack.getAttack("Eclate-roc"));
        skills.add(Attack.getAttack("Morsure"));
        new Pokemon("Tortank", Type.getType("Eau"), 186, 137, 157, 130)
                .setSkillList(skills);

        skills.clear();
        skills.add(Attack.getAttack("Lance-flamme"));
        skills.add(Attack.getAttack("Cru-aile"));
        skills.add(Attack.getAttack("Eboulement"));
        skills.add(Attack.getAttack("Dracogriffe"));
        new Pokemon("Dracaufeu", Type.getType("Feu"), 185, 161, 137, 120)
                .setSkillList(skills);

        skills.clear();
        skills.add(Attack.getAttack("Ball'ombre"));
        skills.add(Attack.getAttack("Vibrobscur"));
        skills.add(Attack.getAttack("Psyko"));
        skills.add(Attack.getAttack("Tonnerre"));
        new Pokemon("Ectoplasma", Type.getType("Spectre"), 167, 182, 127, 162)
                .setSkillList(skills);

        skills.clear();
        skills.add(Attack.getAttack("Lame roc"));
        skills.add(Attack.getAttack("Séisme"));
        skills.add(Attack.getAttack("Marto-poing"));
        skills.add(Attack.getAttack("Mégacorne"));
        new Pokemon("Rhinoferos", Type.getType("Roche"), 212, 182, 172, 92)
                .setSkillList(skills);

        skills.clear();
        skills.add(Attack.getAttack("Tonnerre"));
        skills.add(Attack.getAttack("Double-pied"));
        skills.add(Attack.getAttack("Dard-nuée"));
        skills.add(Attack.getAttack("Ball'ombre"));
        new Pokemon("Voltali", Type.getType("Electrique"), 172, 162, 147, 182)
                .setSkillList(skills);

        skills.clear();
        skills.add(Attack.getAttack("Plaquage"));
        skills.add(Attack.getAttack("Morsure"));
        skills.add(Attack.getAttack("Roulade"));
        skills.add(Attack.getAttack("Tacle lourd"));
        new Pokemon("Ronflex", Type.getType("Normal"), 267, 162, 162, 82)
                .setSkillList(skills);

        skills.clear();
        skills.add(Attack.getAttack("Colère"));
        skills.add(Attack.getAttack("Cru-aile"));
        skills.add(Attack.getAttack("Surf"));
        skills.add(Attack.getAttack("Lance-flamme"));
        new Pokemon("Dracolosse", Type.getType("Dragon"), 198, 186, 147, 132)
                .setSkillList(skills);

    }

    public void setMediator(FightMediator fight) {
        this.mediator = fight;
    }

    public String getName() {
        return name;
    }

    public void doAttack(Attack attack) {
        int statusFactor;

        mediator.attack(this, attack);
        if (attack.getStatus() != null) {
            statusFactor = RandomNumberGenerator.getInstance().getRandomNumber(
                    3);
            if (statusFactor == 0) {
                mediator.changeStatus(this, attack.getStatus());
            }

        }
    }

    public int getHp() {
        return hp;
    }

    public Type getType() {
        return type;
    }

    public ArrayList<Attack> getSkillList() {
        return skills;
    }

    public void setSkillList(ArrayList<Attack> skills) {
        this.skills = (ArrayList<Attack>) skills.clone();
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

    public void doStatus() {
        switch (status) {

            case PARALYSE:
            case GELE:
                if (RandomNumberGenerator.getInstance().getRandomNumber(3) == 0) {
                    isIncapacitated = true;
                }

                break;

            case POISON:
            case BRULE:
                if (RandomNumberGenerator.getInstance().getRandomNumber(3) == 0) {
                	
                }
                break;

        }
    }

    public void setStatus(Status newStatus) {
        status = newStatus;
    }

    private void restoreStatus() {
        this.status = Status.EN_FORME;
    }
    
    public boolean isIncapacitated() {
    	return isIncapacitated;
    }
    
    public void setCapacitated() {
    	isIncapacitated = false;
    }
    
    public Status getStatus() {
    	return status;
    }

	@Override
	public String toString() {
		return name;
	}
    
    
}
