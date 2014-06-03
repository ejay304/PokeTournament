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
public class Tournament {
    private final Pokemon pkmnChoisi;
    
    private Match[] matchesHuitieme = new Match[8];
    private Match[] matchesQuart = new Match[4];
    private Match[] matchesDemi = new Match[2];
    private Match[] matchFinal;
    private int tour;
    
    public Tournament(Pokemon pokemon){
        this.pkmnChoisi = pokemon;
        tour = 0;
        quart();
    }

    public Pokemon getPokemon() {
        return pkmnChoisi;
    }
    
    public Match[] getMatches(){
        if (tour==0){
            return matchesQuart;
        }else if(tour==1){
            return matchesDemi;
        }else{
            return matchFinal;
        }
    }  

    public void quart(){
        
        boolean autoWin = true;
        for(int i = 0 ; i < 4; i++){
            if(Pokemon.pokemons.get(2 * i) == pkmnChoisi 
               || Pokemon.pokemons.get(2 * i + 1) == pkmnChoisi)
                autoWin =false;
                
            matchesQuart[i] = new Match(Pokemon.pokemons.get(2 * i), 
                                        Pokemon.pokemons.get(2 * i + 1), autoWin);
       }
    }
    
    public void demi(){
        boolean autoWin = true;
        
        for(int i = 0 ; i < 2; i++){
            if(matchesQuart[2 * i].getWinner() == pkmnChoisi 
               || matchesQuart[2 * i + 1].getWinner() == pkmnChoisi)
                autoWin =false;
                
            matchesDemi[i] = new Match(matchesQuart[2 * i].getWinner(),
                                        matchesQuart[2 * i + 1].getWinner(), autoWin);
       }
    }
    
    public void finale(){
        boolean autoWin = true;
        
        if(matchesDemi[0].getWinner() == pkmnChoisi 
               || matchesDemi[1].getWinner() == pkmnChoisi)
                autoWin =false;
        
        matchFinal[0]  =  new Match(matchesDemi[0].getWinner(),
                                        matchesDemi[1].getWinner(), autoWin);
    }
}
