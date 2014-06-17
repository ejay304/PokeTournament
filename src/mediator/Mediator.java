/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mediator;

import poketournament.Attack;
import poketournament.Match;
import poketournament.Pokemon;

/**
 *
 * @author admin
 */
public interface Mediator {
    public void attack(Pokemon source, Attack attack);
    public Pokemon getEnnemyPokemon(Pokemon pokemon);
    public Match getMatch();
    public int getHPForPokemon(Pokemon pokemon);
}
