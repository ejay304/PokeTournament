/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

import java.util.logging.Level;
import java.util.logging.Logger;
import poketournament.Attack;
import mediator.FightMediator;
import poketournament.Pokemon;
import poketournament.RandomNumberGenerator;

/**
 *
 * @author admin
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


    @Override
    public void setAttackSelected(Attack attack) {
    }
}
