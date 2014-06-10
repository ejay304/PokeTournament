/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poketournament;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Fabio
 */
public class Tournament extends Observable implements Observer {

    private final Pokemon chosenPkmn;
    private static final int NB_TOUR = 3;

    private Match[] matchesHuitieme = new Match[8];
    private Match[] matchesQuart = new Match[4];
    private Match[] matchesDemi = new Match[2];
    private Match[] matchFinal = new Match[1];
    private int tour;
    private int cptMatch;

    public Tournament(Pokemon pokemon) {
        this.chosenPkmn = pokemon;
        tour = NB_TOUR;
        quart();
    }

    public Pokemon getPokemon() {
        return chosenPkmn;
    }

    public Match[] getMatches() {

        switch (tour) {
            case 3:
                return matchesQuart;
            case 2:
                return matchesDemi;
            case 1:
                return matchFinal;
            default:
                return null;
        }
    }

    public void quart() {

        boolean autoWin;
        cptMatch = 4;
        for (int i = 0; i < 4; i++) {
            autoWin = true;
            if (Pokemon.pokemons.get(2 * i) == chosenPkmn
                    || Pokemon.pokemons.get(2 * i + 1) == chosenPkmn) {
                autoWin = false;
            }

            matchesQuart[i] = new Match(Pokemon.pokemons.get(2 * i),
                    Pokemon.pokemons.get(2 * i + 1), autoWin, autoWin ? null : chosenPkmn);
            matchesQuart[i].addObserver(this);
        }
    }

    public void demi() {
        boolean autoWin;
        cptMatch = 2;
        for (int i = 0; i < 2; i++) {
            autoWin = true;
            if (matchesQuart[2 * i].getWinner() == chosenPkmn
                    || matchesQuart[2 * i + 1].getWinner() == chosenPkmn) {
                autoWin = false;
            }

            matchesDemi[i] = new Match(matchesQuart[2 * i].getWinner(),
                    matchesQuart[2 * i + 1].getWinner(), autoWin, autoWin ? null : chosenPkmn);
            
            matchesDemi[i].addObserver(this);
        }
    }

    public void finale() {
        boolean autoWin = true;
        cptMatch = 1;
        if (matchesDemi[0].getWinner() == chosenPkmn
                || matchesDemi[1].getWinner() == chosenPkmn) {
            autoWin = false;
        }

        matchFinal[0] = new Match(matchesDemi[0].getWinner(),
                matchesDemi[1].getWinner(), autoWin, autoWin ? null : chosenPkmn);
        matchFinal[0].addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Trolololo");

        if (--cptMatch == 0) {
            tour--;
            switch (tour) {
                case 1:
                    finale();
                    break;
                case 2:
                    demi();
                    break;
            }
            setChanged();
            notifyObservers();
        }
    }
}
