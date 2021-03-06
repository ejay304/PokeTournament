package poketournament;

import java.util.Observable;
import java.util.Observer;

/**
 * Represente un tournoi de pokemons
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
    
    public Pokemon getWinner(){
        return matchFinal[0].getWinner();
    }

    public synchronized Match[] getMatches() {
        System.out.println("tour = " + tour);

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
    public synchronized void update(Observable o, Object arg) {
        System.out.println("Mise a jour etat tournoi recu");

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
            System.out.println("------On passe au tour " +  tour);
            setChanged();
            notifyObservers();
        }
    }
}
