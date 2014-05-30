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
        matchesQuart[0] = new Match(Pokemon.pokemons.get(0), 
                                    Pokemon.pokemons.get(1));
        matchesQuart[1] = new Match(Pokemon.pokemons.get(2), 
                                    Pokemon.pokemons.get(3));
        matchesQuart[2] = new Match(Pokemon.pokemons.get(4), 
                                    Pokemon.pokemons.get(5));
        matchesQuart[3] = new Match(Pokemon.pokemons.get(6), 
                                    Pokemon.pokemons.get(7));
    }
    
    public void demi(){
        matchesDemi[0]  = new Match(matchesQuart[0].getWinner(), matchesQuart[1].getWinner());
        matchesDemi[1]  = new Match(matchesQuart[2].getWinner(), matchesQuart[3].getWinner());
    }
    
    public void finale(){
        matchesDemi[0]  = new Match(matchesDemi[0].getWinner(), matchesDemi[1].getWinner());
    }
}
