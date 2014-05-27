
package view;

import java.awt.*;
import javax.swing.*;
import poketournament.*;

/**
 * TournamentView
 * 
 * @since   26.05.2014
 * @author  Alain FRESCO
 * @version 1.0
 */
public class TournamentView extends JFrame {

    JButton b1;
    JLabel l1;
    
    private Point[] boxes = new Point[15];
    
    public TournamentView() {
        boxes[0] = new Point(45, 528);
        boxes[1] = new Point(194, 528);
        boxes[2] = new Point(343, 528);
        boxes[3] = new Point(492, 528);
        boxes[4] = new Point(641, 528);
        boxes[5] = new Point(790, 528);
        boxes[6] = new Point(939, 528);
        boxes[7] = new Point(1093, 528);
        boxes[8] = new Point(120, 365);
        boxes[9] = new Point(418, 365);
        boxes[10] = new Point(720, 365);
        boxes[11] = new Point(1020, 365);
        boxes[12] = new Point(272, 195);
        boxes[13] = new Point(872, 195);
        boxes[14] = new Point(568, 25);  
        //JPanel mainPanel = new JPanel();
        
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);    
       setContentPane(new JLabel(new ImageIcon(getClass().getResource("/ressources/tournament.jpg"))));
       setLayout(null);
       setTitle("Etat du tournois");
       setSize(1227, 641);
       setResizable(false);
       setVisible(true);
       //this.add(mainPanel);
       
       int i = 0;
       for(final Point p : boxes){
           
           final Pokemon pokemon = Pokemon.pokemons.get(i);
           this.add(new JPanel(){
                {
                    setSize(90, 70);
                    setLocation(p);
                    setOpaque(false);
                    ImageIcon image = new ImageIcon(new ImageIcon(getClass().getResource(
                            "/ressources/" + pokemon.getName() + ".png")).
                            getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
                    this.add(new JLabel(image));
                }
            });
        i++; 
       }
               
    }
   
    
}
