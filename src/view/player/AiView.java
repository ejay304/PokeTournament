package view.player;

import java.util.Observable;
import player.AiPlayer;

/**
 * Vue pour un joueur AI durant un combat
 */
public class AiView extends PlayerView{
    public AiView(AiPlayer player){
        super(player);
        player.addObserver(this);
        System.out.println("{----AIView Started----}"); 
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
