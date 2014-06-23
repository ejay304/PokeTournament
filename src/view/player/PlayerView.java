package view.player;

import java.util.Observer;
import player.Player;

/**
 * Represente une vue de joueur dans un combat
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
