/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.player;

import java.util.Observer;
import player.Player;

/**
 *
 * @author admin
 */
public abstract class PlayerView implements Observer {
    private Player player;
    protected PlayerView(Player player){
        this.player = player;
    }
    
    public Player getPlayer(){
        return this.player;
    }
}
