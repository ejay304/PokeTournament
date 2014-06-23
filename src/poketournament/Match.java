package poketournament;

import mediator.FightMediator;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represente un match dans un tournoi
 */
public class Match extends Observable implements Runnable, Observer {

    private final Pokemon pkmn1;
    private final Pokemon pkmn2;
    private final Pokemon chosenPkmn;

    private Pokemon winner;
    private Boolean autoWin;
    private FightMediator fight;

    public Match(Pokemon pkmn1, Pokemon pkmn2, Boolean autoWin,
            Pokemon chosenPkmn) {
        this.pkmn1 = pkmn1;
        this.pkmn2 = pkmn2;
        pkmn1.setStatus(Status.EN_FORME);
        pkmn2.setStatus(Status.EN_FORME);
        this.chosenPkmn = chosenPkmn;
        this.autoWin = autoWin;
        synchronized (this) {
            if (autoWin) {
                if (RandomNumberGenerator.getInstance().getRandomBoolean()) {
                    setWinner(pkmn1);
                } else {
                    setWinner(pkmn2);
                }

            }

            new Thread(this).start();
        }
    }

    public void start() {
        fight = new FightMediator(this);
        fight.addObserver(this);
    }

    public Pokemon getWinner() {
        return winner;
    }

    public void setWinner(Pokemon pkmn) {
        this.winner = pkmn;
    }

    public Pokemon getPkmn1() {
        return pkmn1;
    }

    public Pokemon getPkmn2() {
        return pkmn2;
    }
    
    public Pokemon getChosenPokemon() {
        return chosenPkmn;
    }


    @Override
    public void run() {
        while (getWinner() == null) {
            synchronized (this) {
                try {
                    System.out.println("Attente de resultat pour le match "
                            + pkmn1.getName() + " vs " + pkmn2.getName());
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Match.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
            }
        }
        System.out.println("[" + pkmn1.getName() + " vs " + pkmn2.getName()
                + ": winner = " + getWinner().getName() + "]");

        setChanged();
        notifyObservers();
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); // To
        // change
        // body
        // of
        // generated
        // methods,
        // choose
        // Tools
        // |
        // Templates.
    }
}
