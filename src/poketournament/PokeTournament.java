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

		ArrayList<Attack> skillsFlorizarre = new ArrayList<>(4);
		skillsFlorizarre.add(new Attack("Attack1", 100, 50, Type.getType("grass")));
		skillsFlorizarre.add(new Attack("Attack2", 100, 50, Type.getType("grass")));
		skillsFlorizarre.add(new Attack("Attack3", 100, 50, Type.getType("grass")));
		skillsFlorizarre.add(new Attack("Attack4", 100, 50, Type.getType("grass")));

		new Pokemon("Florizarre", Type.getType("grass"), 187, 152, 152, 132)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Tortank", Type.getType("water"), 186, 137, 157, 130)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Dracaufeu", Type.getType("fire"), 185, 161, 137, 120)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Ectoplasma", Type.getType("ghost"), 167, 182, 127, 162)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Rhinoferoce", Type.getType("rock"), 212, 182, 172, 92)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Voltali", Type.getType("electric"), 172, 162, 147, 182)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Ronflex", Type.getType("normal"), 267, 162, 162, 82)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Dracolosse", Type.getType("dragon"), 198, 186, 147, 132)
				.setSkillList(skillsFlorizarre);

		new ChooseView();
		Collections.shuffle(Pokemon.pokemons);

	}
}
