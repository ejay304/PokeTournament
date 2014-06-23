package poketournament;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Représente une attaque de pokémon.
 * 
 */
public class Attack {

	public static HashMap<String, Attack> attacks = new HashMap<String, Attack>();
	private String name;
	private Integer power;
	private Integer accuracy;
	private Type type;
	private Status status;

	/**
	 * Constructeur d'une attaque.
	 * 
	 * @param name
	 *            nom de l'attaque
	 * @param power
	 *            puissance de l'attaque
	 * @param accuracy
	 *            précision de l'attaque (100 = touche à tout les coups)
	 * @param type
	 *            le type de l'attaque
	 * @param status
	 *            le statut que l'attaque peut engendrer (peut-être null)
	 */
	public Attack(String name, Integer power, Integer accuracy, Type type,
			Status status) {
		this.name = name;
		this.power = power;
		this.accuracy = accuracy;
		this.type = type;
		this.status = status;
		attacks.put(name, this);
	}

	/**
	 * Initialisation des attaques par pokémon.
	 */
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

	/**
	 * @return le nom de l'attaque
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return la puissance de l'attaque
	 */
	public Integer getPower() {
		return power;
	}

	/**
	 * @return la précision de l'attaque
	 */
	public Integer getAccuracy() {
		return accuracy;
	}

	/**
	 * @return le type de l'attaque
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Récupère une attaque existante.
	 * 
	 * @param name
	 *            le nom de l'attaque
	 * @return l'attaque ayant le nom spécifié
	 */
	public static Attack getAttack(String name) {
		return attacks.get(name);
	}

	/**
	 * Permet de savoir si l'attaque touche en fonction de la précision.
	 * @return si l'attaque touche
	 */
	public boolean doHit() {
		boolean doHit = true;
		// Une précision de 100 équivaut à une garantie de toucher
		if (accuracy < 100) {

			int hitFactor = RandomNumberGenerator.getInstance()
					.getRandomNumber(100);
			if (hitFactor > accuracy) {
				doHit = false;
			}
		}
		return doHit;
	}

	/**
	 * @return le statut que l'attaque peut engendrer
	 */
	public Status getStatus() {
		return status;
	}
}
