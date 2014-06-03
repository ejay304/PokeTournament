/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poketournament;

import config.Constante;
import java.util.ArrayList;
import java.util.Collections;
import view.ChooseView;

/**
 * 
 * @author Romain Therisod
 */
public class PokeTournament {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		Constante.init();

		ArrayList<Attack> skills = new ArrayList<>(4);
		skills.add(new Attack("Tempête florale", 90, 100, Type.getType("grass")));
		skills.add(new Attack("Bomb-beurk", 90, 100, Type.getType("poison")));
		skills.add(new Attack("Séisme", 100, 95, Type.getType("ground")));
		skills.add(new Attack("Force", 80, 100, Type.getType("normal")));
		new Pokemon("Florizarre", Type.getType("grass"), 187, 152, 152, 132)
				.setSkillList(skills);
		
		skills.clear();
		skills.add(new Attack("Surf", 90, 100, Type.getType("water")));
		skills.add(new Attack("Laser-glace", 90, 100, Type.getType("ice")));
		skills.add(new Attack("Eclate-roc", 70, 100, Type.getType("fighting")));
		skills.add(new Attack("Morsure", 75, 100, Type.getType("dark")));
		new Pokemon("Tortank", Type.getType("water"), 186, 137, 157, 130)
				.setSkillList(skills);
		
		skills.clear();
		skills.add(new Attack("Lance-flamme", 90, 100, Type.getType("fire")));
		skills.add(new Attack("Cru-aile", 85, 100, Type.getType("flying")));
		skills.add(new Attack("Eboulement", 100, 95, Type.getType("rock")));
		skills.add(new Attack("Dracogriffe", 85, 100, Type.getType("dragon")));
		new Pokemon("Dracaufeu", Type.getType("fire"), 185, 161, 137, 120)
				.setSkillList(skills);
		skills.clear();
		skills.add(new Attack("Ball'ombre", 90, 100, Type.getType("ghost")));
		skills.add(new Attack("Vibrobscur", 85, 100, Type.getType("dark")));
		skills.add(new Attack("Psyko", 90, 100, Type.getType("psy")));
		skills.add(new Attack("Tonnerre", 90, 100, Type.getType("electric")));
		new Pokemon("Ectoplasma", Type.getType("ghost"), 167, 182, 127, 162)
				.setSkillList(skills);
		
		skills.clear();
		skills.add(new Attack("Lame roc", 110, 90, Type.getType("rock")));
		skills.add(Attack.getAttack("Séisme"));
		skills.add(new Attack("Marto-poing", 80, 100, Type.getType("fighting")));
		skills.add(new Attack("Mégacorne", 110, 90, Type.getType("bug")));
		new Pokemon("Rhinoferos", Type.getType("rock"), 212, 182, 172, 92)
				.setSkillList(skills);
		
		skills.clear();
		skills.add(Attack.getAttack("Tonnerre"));
		skills.add(new Attack("Double-pied", 80, 100, Type.getType("flying")));
		skills.add(new Attack("Dard-nuée", 75, 100, Type.getType("rock")));
		skills.add(Attack.getAttack("Ball'ombre"));
		new Pokemon("Voltali", Type.getType("electric"), 172, 162, 147, 182)
				.setSkillList(skills);
		
		skills.clear();
		skills.add(new Attack("Plaquage", 90, 100, Type.getType("normal")));
		skills.add(Attack.getAttack("Morsure"));
		skills.add(new Attack("Roulade", 60, 100, Type.getType("rock")));
		skills.add(new Attack("Tacle lourd", 85, 100, Type.getType("steel")));
		new Pokemon("Ronflex", Type.getType("normal"), 267, 162, 162, 82)
				.setSkillList(skills);
		
		skills.clear();
		skills.add(new Attack("Colère", 110, 90, Type.getType("dragon")));
		skills.add(Attack.getAttack("Cru-aile"));
		skills.add(Attack.getAttack("Surf"));
		skills.add(Attack.getAttack("Lance-flamme"));
		new Pokemon("Dracolosse", Type.getType("dragon"), 198, 186, 147, 132)
				.setSkillList(skills);

		new ChooseView();
		Collections.shuffle(Pokemon.pokemons);

	}
}
