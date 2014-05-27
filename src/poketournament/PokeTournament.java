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
        
        new Pokemon("Florizarre",187,134,135,132); 
        new Pokemon("Tortank",186,135,152,130);
        new Pokemon("Dracaufeu",185,136,130,120);
        new Pokemon("Ectoplasma",167,117,112,162); 
        new Pokemon("Rhinoferoce",212,182,172,92);
        new Pokemon("Voltali",172,117,112,182);
        new Pokemon("Ronflex",267,162,117,82);
        new Pokemon("Dracolosse",198,186,147,132);
        
        
        new ChooseView();
        Collections.shuffle(Pokemon.pokemons);
        
    }
}
