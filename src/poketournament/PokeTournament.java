/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poketournament;

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
        
        new Pokemon("Florizarre",187,134,135,132); 
        new Pokemon("Tortank",186,135,152,130);
        new Pokemon("Dracaufeu",185,136,130,120);
        
        new ChooseView();
        
    }
}
