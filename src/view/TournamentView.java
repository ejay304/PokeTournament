
package view;

import java.awt.*;
import javax.swing.*;

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
    
    private Dimension[] boxes = new Dimension[15];
    
    public TournamentView() {
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);    
       setContentPane(new JLabel(new ImageIcon(getClass().getResource("/ressources/tournament.jpg"))));
       setLayout(null);
       setSize(1227, 641);
       setResizable(false);
       setVisible(true);
       
       this.add(new JPanel(){
           {
               setBackground(Color.red);
               setSize(90, 70);
               setLocation(45, 525);
           }
       
       });
               
               
    }
   
    
}
