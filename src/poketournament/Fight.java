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
public class Fight {
    private final Match match;
    private final Pokemon pkmnChoisi;
    private final Pokemon pkmnEnnemi;
    private int pkmnChoisiHP;
    private int pkmnEnnemiHP;
    
    public Fight(Match match, Pokemon pokemon){
        this.match = match;
        if(this.match.getPkmn1() == pokemon){
            this.pkmnChoisi = this.match.getPkmn1();
            this.pkmnEnnemi = this.match.getPkmn2();
        }else{
            this.pkmnEnnemi = this.match.getPkmn1();
            this.pkmnChoisi = this.match.getPkmn2();            
        }

        this.pkmnChoisiHP = pkmnChoisi.getHp();
        this.pkmnEnnemiHP = pkmnEnnemi.getHp();
        
        pkmnChoisi.setMediator(this);
        pkmnEnnemi.setMediator(this);
    }

    void attack(Pokemon source, Attack attack) {
        if(source == pkmnChoisi){
          pkmnEnnemiHP -= attack.getPower();
          if(pkmnEnnemiHP <= 0)
              System.out.println("notify");
        }
        else{
          pkmnChoisiHP -= attack.getPower();
          if(pkmnChoisiHP <= 0)
              System.out.println("notify");
        }
    }

    public Pokemon getPokemonCourrant() {
        return pkmnChoisi;
    }

    public Pokemon getPokemonEnnemi() {
        return pkmnEnnemi;
    }
    
    
}
