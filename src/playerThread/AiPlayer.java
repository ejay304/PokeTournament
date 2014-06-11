/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package playerThread;

import poketournament.Attack;
import mediator.FightMediator;
import poketournament.Pokemon;

/**
 *
 * @author admin
 */
public class AiPlayer extends Player{
    
    public AiPlayer(FightMediator fight, Pokemon chosenPkmn, Pokemon ennemyPkmn) {
        super(fight, chosenPkmn, ennemyPkmn);
        System.out.println("%AI Player thread started%");
        
    }

    @Override
    public Attack selectAttack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        System.out.println("%AIPlayer thread - should be waiting turn here%");
    }
}
