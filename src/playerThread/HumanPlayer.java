/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerThread;

import java.util.logging.Level;
import java.util.logging.Logger;
import poketournament.Attack;
import mediator.FightMediator;
import poketournament.Pokemon;

/**
 *
 * @author admin
 */
public class HumanPlayer extends Player {

    public HumanPlayer(FightMediator fight, Pokemon chosenPkmn, Pokemon ennemyPkmn) {
        super(fight, chosenPkmn, ennemyPkmn);
        System.out.println("%Human player thread started%");
    }

    @Override
    public Attack selectAttack() {
        throw new UnsupportedOperationException("Display buttons !!"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        while(getMediator().getMatch().getWinner() == null){
            synchronized (getMediator()) {
                if (!getMediator().canIPlay(this.getPokemon())) {
                    try {
                        this.wait();
                    } catch (InterruptedException ex) {}
                } else {
                    //TODO gérer l'input...
                }
            }
        }
        System.out.println("%Human player thread stopped%");
    }

}
