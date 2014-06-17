package poketournament;

import java.util.HashMap;

public class Attack {

	public static HashMap<String, Attack> attacks = new HashMap<String, Attack>();
	private String name;
	private Integer power;
	private Integer accuracy;
	private Type type;
	private Status status;

	public Attack(String name, Integer power, Integer accuracy, Type type, Status status) {
		this.name = name;
		this.power = power;
		this.accuracy = accuracy;
		this.type = type;
		this.status = status;
		attacks.put(name, this);

	}

	public String getName() {
		return name;
	}

	public Integer getPower() {
		return power;
	}

	public Integer getAccuracy() {
		return accuracy;
	}

	public Type getType() {
		return type;
	}

	public static Attack getAttack(String name) {
		return attacks.get(name);
	}

	public boolean doHit() {
		boolean doHit = true;
		// Une précision de 100 équivaut à une garantie de toucher
		if (accuracy < 100) {

			int hitFactor = RandomNumberGenerator.getInstance()
					.getRandomNumber(100);
			System.out.println("hitfactor:" + hitFactor);
			if (hitFactor > accuracy) {
				doHit = false;
			}
		}
		return doHit;
	}
}
