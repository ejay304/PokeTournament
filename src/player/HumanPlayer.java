/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import java.util.logging.Level;
import java.util.logging.Logger;
import mediator.FightMediator;
import poketournament.Attack;
import poketournament.Pokemon;

/**
 *
 * @author admin
 */
public class HumanPlayer extends Player {

    private Attack attackSelected;

    public HumanPlayer(FightMediator fight, Pokemon chosenPkmn, Pokemon ennemyPkmn) {
        super(fight, chosenPkmn, ennemyPkmn);
        System.out.println("%Human player thread started%");
    }

    public void selectAttack() {
        System.out.println("Selection attaque! partie UI");
        try {
            while (attackSelected == null) {
                synchronized(this){
                    this.wait();
                }
            }
            this.getPokemon().doAttack(attackSelected);
        } catch (InterruptedException ex) {
            Logger.getLogger(HumanPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        attackSelected = null;
    }

    @Override
    public void setAttackSelected(Attack attack) {
         attackSelected = attack;
    }

}
