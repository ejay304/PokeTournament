package poketournament;

import java.util.HashMap;

public class Attack {

    public HashMap<String, Attack> attacks = new HashMap<String, Attack>();
    private String name;
    private Integer power;
    private Integer accuracy;
    private Type type;

    public Attack(String name, Integer power, Integer accuracy, Type type) {
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.type = type;
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

}
