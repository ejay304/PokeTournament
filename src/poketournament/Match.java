/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poketournament;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.FightView;

/**
 *
 * @author Fabio
 */
public class Match extends Observable implements Runnable, Observer {

    private final Pokemon pkmn1;
    private final Pokemon pkmn2;
    private final Pokemon chosenPkmn;
    private FightView fightView;
    private Pokemon winner;
    private Boolean autoWin;
    private Fight fight;

    public Match(Pokemon pkmn1, Pokemon pkmn2, Boolean autoWin, Pokemon chosenPkmn) {
        this.pkmn1 = pkmn1;
        this.pkmn2 = pkmn2;
        this.chosenPkmn = chosenPkmn;
        this.autoWin = autoWin;
        synchronized(this){
            System.out.println("Autowin = " + autoWin);
            if (autoWin) {
                setWinner(pkmn1);
            }

            new Thread(this).start();
        }
    }

    public void start() {
        fight = new Fight(this, chosenPkmn);
        fight.addObserver(this);
        fightView = new FightView(fight);
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

    @Override
    public void run() {
        System.out.println("Test");
        System.out.println("winner = " + getWinner());
        while (getWinner() == null) {
            synchronized (this) {
                try {
                    System.out.println("Test2");
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Match.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        setChanged();
        notifyObservers();

        if(!autoWin)
         //TODO MESSAGE de fin
         fightView.dispose();
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
