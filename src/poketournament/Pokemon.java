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
}
