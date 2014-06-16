/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view.player;

import java.util.Observable;
import java.util.Observer;
import player.AiPlayer;

/**
 *
 * @author admin
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
