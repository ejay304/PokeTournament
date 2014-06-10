/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poketournament;

import java.util.Observable;

/**
 *
 * @author Fabio
 */
public class Fight extends Observable {

    private final Match match;
    private final Pokemon chosenPkmn;
    private final Pokemon pkmnEnnemi;
    private int pkmnChoisiHP;
    private int pkmnEnnemiHP;

    public Fight(Match match, Pokemon chosenPkmn) {
        this.match = match;

        if (this.match.getPkmn1() == chosenPkmn) {
            this.chosenPkmn = chosenPkmn;
            this.pkmnEnnemi = this.match.getPkmn2();
        } else {
            this.pkmnEnnemi = this.match.getPkmn1();
            this.chosenPkmn = chosenPkmn;
        }

        pkmnChoisiHP = chosenPkmn.getHp();
        pkmnEnnemiHP = pkmnEnnemi.getHp();

        chosenPkmn.setMediator(this);
        pkmnEnnemi.setMediator(this);
    }

    // c.f. : http://www.pokepedia.fr/index.php/Calcul_des_d%C3%A9g%C3%A2ts
    void attack(Pokemon source, Attack attack) {
        double stab = (source.getType() == attack.getType() ? 1.0 : 1.5);
        double factor = source.getType().getVulnerabilityFactor(attack.getType());
        if (source == chosenPkmn) {
            pkmnEnnemiHP -= ((50 * 0.4 + 2) * source.getAttack() * (attack.getPower() * stab)) / (pkmnEnnemi.getDefense() * 50) * factor;
            if (pkmnEnnemiHP <= 0) {
                System.out.println("PKMN ennemi KO !!");
                match.setWinner(chosenPkmn);
                synchronized(match){
                    match.notify();
                }
            }
        } else {
            pkmnChoisiHP -= ((50 * 0.4 + 2) * source.getAttack() * (attack.getPower() * stab)) / (chosenPkmn.getDefense() * 50) * factor;
            if (pkmnChoisiHP <= 0) {
                System.out.println("T'es KO !! ");
                match.setWinner(chosenPkmn);
                synchronized(match){
                    match.notify();
                }
            }

        }
    }

    public Pokemon getPokemonCourrant() {
        return chosenPkmn;
    }

    public Pokemon getPokemonEnnemi() {
        return pkmnEnnemi;
    }

}
