/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poketournament;

import config.Constante;
import java.util.Collections;
import view.ChooseView;
import view.TournamentView;

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
        
        new Pokemon("Florizarre",187,152,152,132); 
        new Pokemon("Tortank",186,137,157,130);
        new Pokemon("Dracaufeu",185,161,137,120);
        new Pokemon("Ectoplasma",167,182,127,162); 
        new Pokemon("Rhinoferoce",212,182,172,92);
        new Pokemon("Voltali",172,162,147,182);
        new Pokemon("Ronflex",267,162,162,82);
        new Pokemon("Dracolosse",198,186,147,132);
        
        
        new ChooseView();
        Collections.shuffle(Pokemon.pokemons);
        
    }
}
