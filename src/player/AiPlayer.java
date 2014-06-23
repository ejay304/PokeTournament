package player;

import poketournament.Attack;
import mediator.FightMediator;
import poketournament.Pokemon;
import poketournament.RandomNumberGenerator;

/**
 * Represente un joueur AI
 */
public class AiPlayer extends Player {

    public AiPlayer(FightMediator fight, Pokemon chosenPkmn) {
        super(fight, chosenPkmn);
        System.out.println("%AI Player thread started%");

    }

    @Override
    public void selectAttack() {
        this.getPokemon().doAttack(this.getPokemon().getSkillList().get(RandomNumberGenerator.getInstance().getRandomNumber(3)));
    }


    //inutile car cela est fait automatiquement
    @Override
    public void setAttackSelected(Attack attack) {
    }
}
