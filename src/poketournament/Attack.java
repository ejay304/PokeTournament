package poketournament;

import java.util.ArrayList;
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

    public Status getStatus() {
        return status;
    }
}
