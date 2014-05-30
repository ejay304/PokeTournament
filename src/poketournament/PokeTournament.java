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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Constante.init();
        
        Type eau = new Type("eau");
        ArrayList<Attack> skillsFlorizarre = new ArrayList<>(4);
        skillsFlorizarre.add(new Attack("Attack1", 100, 50, eau));
        skillsFlorizarre.add(new Attack("Attack2", 100, 50, eau));
        skillsFlorizarre.add(new Attack("Attack3", 100, 50, eau));
        skillsFlorizarre.add(new Attack("Attack4", 100, 50, eau));
        
        new Pokemon("Florizarre",187,152,152,132).setSkillList(skillsFlorizarre); 
        new Pokemon("Tortank",186,137,157,130).setSkillList(skillsFlorizarre);
        new Pokemon("Dracaufeu",185,161,137,120).setSkillList(skillsFlorizarre);
        new Pokemon("Ectoplasma",167,182,127,162).setSkillList(skillsFlorizarre);
        new Pokemon("Rhinoferoce",212,182,172,92).setSkillList(skillsFlorizarre);
        new Pokemon("Voltali",172,162,147,182).setSkillList(skillsFlorizarre);
        new Pokemon("Ronflex",267,162,162,82).setSkillList(skillsFlorizarre);
        new Pokemon("Dracolosse",198,186,147,132).setSkillList(skillsFlorizarre);
        
        new ChooseView();
        Collections.shuffle(Pokemon.pokemons);
        
    }
}
