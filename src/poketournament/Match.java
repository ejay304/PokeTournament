/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package poketournament;

/**
 *
 * @author Fabio
 */
public class Match {
    private final Pokemon pkmn1;
    private final Pokemon pkmn2;
    private Pokemon winner;
    private Boolean autoWin;
    
    public Match(Pokemon pkmn1, Pokemon pkmn2, Boolean autoWin){
        this.pkmn1 = pkmn1;
        this.pkmn2 = pkmn2;

        this.autoWin = autoWin;
    }
    
    public Pokemon getWinner(){
        if(autoWin)
            // TODO randomize
            return pkmn1;
        return winner;
    }
    
    public void setWinner(Pokemon pkmn){
        this.winner = pkmn;
    }
    
    public Pokemon getPkmn1(){
        return pkmn1;
    }
    
    public Pokemon getPkmn2(){
        return pkmn2;
    }
}
