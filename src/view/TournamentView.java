
package view;

import static config.Constante.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    private final Pokemon pkmnChoisi;
    
    public TournamentView(final Pokemon pkmnChoisi) {
        
       this.pkmnChoisi = pkmnChoisi;
        
       // Configuration de la JFrame
       setDefaultCloseOperation(EXIT_ON_CLOSE);    
       setLayout(null);
       setTitle("Etat du tournois");
       setSize(1227, 641);
       setResizable(false);
       setVisible(true);
       setContentPane(new JLabel(new ImageIcon(getClass().getResource(RESSOURCES + "tournament.jpg"))));
      

       int i = 0;
       for(final Pokemon pkmnCourant : Pokemon.pokemons){
           
           final Point point = BOXES[i];
           
           this.add(new JPanel(){
                {
                    setSize(90, 85);
                    setLocation(point);
                    setOpaque(false);
                    
                    ImageIcon image = new ImageIcon(
                        new ImageIcon(getClass().getResource(
                            RESSOURCES + pkmnCourant.getName() + ".png")).
                            getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
                  
                    if(pkmnChoisi != pkmnCourant){
                        this.add(new JLabel(image));
                    }
                    else{
                        final JButton btnCombat = new JButton();
                        btnCombat.add(new JLabel(image));
                        btnCombat.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                btnCombat.setEnabled(false);
                            }
                        });
                        this.add(btnCombat);
                    }
                }
            });
           
           this.add(new JButton());
        i++; 
       }
               
    }
   
    
}
