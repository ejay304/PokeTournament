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

		// Création des types
		Type water = new Type("Eau");
		Type grass = new Type("Plante");
		Type fire = new Type("Feu");
		Type normal = new Type("Normal");
		Type dragon = new Type("Dragon");
		Type electric = new Type("Electrique");
		Type ground = new Type("Sol");
		Type rock = new Type("Roche");
		Type fighting = new Type("Combat");
		Type poison = new Type("Poison");
		Type ghost = new Type("Spectre");
		Type ice = new Type("Glace");
		Type bug = new Type("Insecte");
		Type psy = new Type("Psy");
		Type dark = new Type("Ténèbre");
		Type flying = new Type("Vol");
		Type steel = new Type("Acier");

		// Gestion des résistance, faiblesses et immunités des types entre eux
		water.setResist(water, steel, fire, ice);
		water.setWeakness(grass, electric);
		grass.setResist(water, electric, grass, rock);
		grass.setWeakness(fire, ice, bug, poison, flying);
		fire.setResist(steel, fire, ice, bug, grass);
		fire.setWeakness(water, rock, ground);
		normal.setWeakness(fighting);
		normal.setImmunity(ghost);
		dragon.setResist(water, electric, fire, grass);
		dragon.setWeakness(dragon, ice);
		electric.setResist(steel, electric, flying);
		electric.setWeakness(ground);
		ground.setResist(poison, rock);
		ground.setWeakness(water, grass, ice);
		ground.setImmunity(electric);
		rock.setResist(fire, normal, poison, flying);
		rock.setWeakness(steel, fighting, water, grass, ground);
		fighting.setResist(bug, rock, dark);
		fighting.setWeakness(psy, flying);
		poison.setResist(fighting, bug, grass, poison);
		poison.setWeakness(ground, psy);
		ghost.setResist(bug, poison);
		ghost.setWeakness(ghost, dark);
		ghost.setImmunity(normal, fighting);
		ice.setResist(ice);
		ice.setWeakness(steel, fighting, fire, rock);
		bug.setResist(fighting, grass, ground);
		bug.setWeakness(fire, rock, flying);
		psy.setResist(fighting, psy);
		psy.setWeakness(bug, dark, ghost);
		dark.setResist(ghost, dark);
		dark.setWeakness(fighting, bug);
		dark.setImmunity(psy);
		flying.setResist(fighting, bug, grass);
		flying.setWeakness(electric, ice, rock);
		flying.setImmunity(ground);
		steel.setResist(steel, dragon, ice, bug, normal, grass, psy, rock, ghost, dark, flying);
		steel.setWeakness(fighting, fire, ground);
		steel.setImmunity(poison);

		ArrayList<Attack> skillsFlorizarre = new ArrayList<>(4);
		skillsFlorizarre.add(new Attack("Attack1", 100, 50, water));
		skillsFlorizarre.add(new Attack("Attack2", 100, 50, water));
		skillsFlorizarre.add(new Attack("Attack3", 100, 50, water));
		skillsFlorizarre.add(new Attack("Attack4", 100, 50, water));

		new Pokemon("Florizarre", water, 187, 152, 152, 132)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Tortank", water, 186, 137, 157, 130)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Dracaufeu", water, 185, 161, 137, 120)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Ectoplasma", water, 167, 182, 127, 162)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Rhinoferoce", water, 212, 182, 172, 92)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Voltali", water, 172, 162, 147, 182)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Ronflex", water, 267, 162, 162, 82)
				.setSkillList(skillsFlorizarre);
		new Pokemon("Dracolosse", water, 198, 186, 147, 132)
				.setSkillList(skillsFlorizarre);

		new ChooseView();
		Collections.shuffle(Pokemon.pokemons);

	}
}
