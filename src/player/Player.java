package player;

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
public abstract class Player extends Observable {

    private final Pokemon own; //le pokemon du joueur
    private String message;//le message courant a afficher
    private final Mediator mediator; //le médiateur pour ce joueur
    private int action;//l'action courante


    protected Player(Mediator mediator, Pokemon own) {
        this.mediator = mediator;
        this.own = own;
    }

    public Pokemon getPokemon() {
        return own;
    }
    
    /**
     * modifie l'action courrante
     * @param code 
     */
    public void setActionCode(int code){
        action = code;
        setChanged();
        notifyObservers();
    }
    /**
     * Retourne le message courant
     * @return le message
     */
    public String getMessage(){
        return this.message;
    }

    /**
     * Retourne le médiateur
     * @return le médiateur
     */
    public Mediator getMediator(){
        return mediator;
    }
    /**
     * Selectione une attaque
     */
    public abstract void selectAttack();

    /**
     * Affiche un message
     * @param message 
     */
    public void displayMessage(String message){
        this.message = message;
        setChanged();
        notifyObservers();
    }
    
    /**
     * Met à jour l'attaque séléctionnée
     * @param attack l'attaque
     */
    public abstract void setAttackSelected(Attack attack);

    /**
     * Retourne l'attaque courante
     * @return 
     */
    public int getActionCode() {
        return this.action;
    }

}
