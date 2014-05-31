
package view;

import static config.Constante.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import poketournament.*;

/**
 * TournamentView
 * 
 * @since   26.05.2014
 * @author  Alain FRESCO
 * @version 1.0
 */
public class FightView extends JFrame {
    
    private final Fight fight;
    
    public FightView(final Fight fight) {
        
       this.fight = fight;
        
       // Configuration de la JFrame
       setDefaultCloseOperation(EXIT_ON_CLOSE);    
       setLayout(null);
       setTitle("Combat");
       setSize(500, 400);
       setResizable(false);
       setVisible(true);
                  
           
           this.add(new JPanel(){
                {
                    setSize(90, 85);
                    setLocation(new Point(0, 200));
                    setOpaque(false);
                    
                    ImageIcon image = new ImageIcon(
                        new ImageIcon(getClass().getResource(
                            RESSOURCES + fight.getPokemonCourrant().getName() + "-back.png")).
                            getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
                  
                   
                        this.add(new JLabel(image));
                }
            });
           
           this.add(new JPanel(){
                {
                    setSize(90, 85);
                    setOpaque(false);
                    setLocation(new Point(400, 0));
                    
                    ImageIcon image = new ImageIcon(
                        new ImageIcon(getClass().getResource(
                            RESSOURCES + fight.getPokemonEnnemi().getName() + ".png")).
                            getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
                        this.add(new JLabel(image));
                }
                
            });
           
           this.add(new JPanel(){
               {
                   setSize(200, 100);
                   setOpaque(false);
                   setLocation(new Point(100, 200));
                   
                   for(final Attack attack : fight.getPokemonCourrant().getSkillList()){
                       JButton btn = new JButton(attack.getName());
                       btn.setSize(20, 200);
                       btn.addActionListener(new ActionListener() {


                           public void actionPerformed(ActionEvent ae) {
                               fight.getPokemonCourrant().doAttack(attack);
                               FightView.this.repaint();
                           }
                       });
                       this.add(btn);
                   }
               }
           }
           );
    }
}
