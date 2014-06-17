package player;

import config.Constante;
import config.Constante.*;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public abstract class Player extends Observable {

    private Pokemon own;
    private String message;
    private Mediator mediator;
    private int action;


    protected Player(Mediator mediator, Pokemon own) {
        this.mediator = mediator;
        this.own = own;
    }

    public Pokemon getPokemon() {
        return own;
    }
    
    public void setActionCode(int code){
        action = code;
        setChanged();
        notifyObservers();
    }
    public String getMessage(){
        return this.message;
    }

    public Mediator getMediator(){
        return mediator;
    }
    public abstract void selectAttack();

    public void displayMessage(String message){
        this.message = message;
        setChanged();
        notifyObservers();
    }
    
    public abstract void setAttackSelected(Attack attack);

    public int getActionCode() {
        return this.action;
    }

}
