package poketournament;

import config.Constante;
import java.util.ArrayList;
import java.util.Collections;
import view.ChooseView;

/**
 * Programme principal déclarant les pokemons dans le tournoi
 */
public class PokeTournament {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Constante.init();
        Type.init();

        ArrayList<Attack> skills = new ArrayList<>(4);
        skills.add(new Attack("Tempête florale", 90, 100, Type
                .getType("Plante"), Status.POISON));
        skills.add(new Attack("Bomb-beurk", 90, 100, Type.getType("Poison"),
                Status.POISON));
        skills.add(new Attack("Séisme", 100, 95, Type.getType("Sol"), null));
        skills.add(new Attack("Force", 80, 100, Type.getType("Normal"), null));
        new Pokemon("Florizarre", Type.getType("Plante"), 187, 152, 152, 132)
                .setSkillList(skills);

        skills.clear();
        skills.add(new Attack("Surf", 90, 100, Type.getType("Eau"), null));
        skills.add(new Attack("Laser-glace", 90, 100, Type.getType("Glace"),
                Status.GELE));
        skills.add(new Attack("Eclate-roc", 70, 100, Type.getType("Combat"),
                null));
        skills.add(new Attack("Morsure", 75, 100, Type.getType("Ténèbre"), null));
        new Pokemon("Tortank", Type.getType("Eau"), 186, 137, 157, 130)
                .setSkillList(skills);

        skills.clear();
        skills.add(new Attack("Lance-flamme", 90, 100, Type.getType("Feu"),
                Status.BRULE));
        skills.add(new Attack("Cru-aile", 85, 100, Type.getType("Vol"), null));
        skills.add(new Attack("Eboulement", 100, 95, Type.getType("Roche"), null));
        skills.add(new Attack("Dracogriffe", 85, 100, Type.getType("Dragon"), null));
        new Pokemon("Dracaufeu", Type.getType("Feu"), 185, 161, 137, 120)
                .setSkillList(skills);

        skills.clear();
        skills.add(new Attack("Ball'ombre", 90, 100, Type.getType("Spectre"), null));
        skills.add(new Attack("Vibrobscur", 85, 100, Type.getType("Ténèbre"), null));
        skills.add(new Attack("Psyko", 90, 100, Type.getType("Psy"), null));
        skills.add(new Attack("Tonnerre", 90, 100, Type.getType("Electrique"), Status.PARALYSE));
        new Pokemon("Ectoplasma", Type.getType("Spectre"), 167, 182, 127, 162)
                .setSkillList(skills);

        skills.clear();
        skills.add(new Attack("Lame roc", 110, 85, Type.getType("Roche"), null));
        skills.add(Attack.getAttack("Séisme"));
        skills.add(new Attack("Marto-poing", 80, 100, Type.getType("Combat"), Status.PARALYSE));
        skills.add(new Attack("Mégacorne", 110, 85, Type.getType("Insecte"), null));
        new Pokemon("Rhinoferos", Type.getType("Roche"), 212, 182, 172, 92)
                .setSkillList(skills);

        skills.clear();
        skills.add(Attack.getAttack("Tonnerre"));
        skills.add(new Attack("Double-pied", 80, 100, Type.getType("Combat"), null));
        skills.add(new Attack("Dard-nuée", 75, 100, Type.getType("Insecte"), null));
        skills.add(Attack.getAttack("Ball'ombre"));
        new Pokemon("Voltali", Type.getType("Electrique"), 172, 162, 147, 182)
                .setSkillList(skills);

        skills.clear();
        skills.add(new Attack("Plaquage", 90, 100, Type.getType("Normal"), Status.PARALYSE));
        skills.add(Attack.getAttack("Morsure"));
        skills.add(new Attack("Roulade", 60, 100, Type.getType("Roche"), null));
        skills.add(new Attack("Tacle lourd", 85, 100, Type.getType("Acier"), null));
        new Pokemon("Ronflex", Type.getType("Normal"), 267, 162, 162, 82)
                .setSkillList(skills);

        skills.clear();
        skills.add(new Attack("Colère", 110, 85, Type.getType("Dragon"), null));
        skills.add(Attack.getAttack("Cru-aile"));
        skills.add(Attack.getAttack("Surf"));
        skills.add(Attack.getAttack("Lance-flamme"));
        new Pokemon("Dracolosse", Type.getType("Dragon"), 198, 186, 147, 132)
                .setSkillList(skills);

        new ChooseView();
        Collections.shuffle(Pokemon.pokemons);

    }
}
