package mediator;

import poketournament.Attack;
import poketournament.Match;
import poketournament.Pokemon;

/**
 *
 * Interface d'un médiateur de combat entre 2 pokémons
 */
public interface Mediator {
    public void attack(Pokemon source, Attack attack);
    public Pokemon getEnnemyPokemon(Pokemon pokemon);
    public Match getMatch();
    public int getHPForPokemon(Pokemon pokemon);
}
