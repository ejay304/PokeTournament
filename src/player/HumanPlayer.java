package player;

import java.util.logging.Level;
import java.util.logging.Logger;
import mediator.FightMediator;
import poketournament.Attack;
import poketournament.Pokemon;

/**
 * Represente un joueur humain
 */
public class HumanPlayer extends Player {

    private Attack attackSelected;

    public HumanPlayer(FightMediator fight, Pokemon chosenPkmn) {
        super(fight, chosenPkmn);
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
