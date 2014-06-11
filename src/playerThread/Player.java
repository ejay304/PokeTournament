package playerThread;

import java.util.Observable;
import mediator.Mediator;
import poketournament.Attack;
import poketournament.Pokemon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public abstract class Player extends Observable implements Runnable {

    private Pokemon own;
    private Pokemon ennemy;
    private String message;
    private Mediator mediator;

    protected Player(Mediator mediator, Pokemon own, Pokemon ennemy) {
        this.mediator = mediator;
        this.own = own;
        this.ennemy = ennemy;
        new Thread(this).start();
    }

    public Pokemon getPokemon() {
        return own;
    }

    public Mediator getMediator(){
        return mediator;
    }
    public abstract Attack selectAttack();

    public void displayMessage(String message){
        this.message = message;
        setChanged();
        notifyObservers();
    }

    public Pokemon getEnnemy() {
        return ennemy;
    }

}
