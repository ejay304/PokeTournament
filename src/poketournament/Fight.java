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
            this.pkmnChoisi = pokemon;
            this.pkmnEnnemi = this.match.getPkmn2();
        }else{
            this.pkmnEnnemi = this.match.getPkmn1();
            this.pkmnChoisi = pokemon;            
        }

        this.pkmnChoisiHP = pkmnChoisi.getHp();
        this.pkmnEnnemiHP = pkmnEnnemi.getHp();
        
        pkmnChoisi.setMediator(this);
        pkmnEnnemi.setMediator(this);
    }

    // c.f. : http://www.pokepedia.fr/index.php/Calcul_des_d%C3%A9g%C3%A2ts
    void attack(Pokemon source, Attack attack) {
    	double coeff = (source.getType() == attack.getType()?1.0:1.5);
        if(source == pkmnChoisi){
          pkmnEnnemiHP -= ((50*0.4+2)*source.getAttack()*attack.getPower())/(pkmnEnnemi.getDefense()*50)*coeff;
          if(pkmnEnnemiHP <= 0)
              System.out.println("notify");
        }
        else{
          pkmnChoisiHP -= ((50*0.4+2)*source.getAttack()*attack.getPower())/(pkmnChoisi.getDefense()*50)*coeff;
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
