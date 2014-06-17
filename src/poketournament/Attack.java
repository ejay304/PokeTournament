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
     
     public static void init(){
     
         Attack tempete_florale = new Attack("Tempête florale", 90, 100, Type.getType("Plante"));
        Attack bomb_beurk = new Attack("Bomb-beurk", 90, 100, Type.getType("Poison"));
        Attack seisme = new Attack("Séisme", 100, 95, Type.getType("Sol"));
		Attack force = new Attack("Force", 80, 100, Type.getType("Normal"));
		
		Attack surf = new Attack("Surf", 90, 100, Type.getType("Eau"));
		Attack laser_glace = new Attack("Laser-glace", 90, 100, Type.getType("Glace"));
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
