/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.player;

import static config.Constante.*;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import playerThread.HumanPlayer;
import poketournament.Attack;

/**
 *
 * @author admin
 */
public class HumanView extends PlayerView {

    private JFrame frame;

    public HumanView(HumanPlayer player) {
        super(player);
        player.addObserver(this);

        frame = new JFrame();
        // Configuration de la JFrame
        //setDefaultCloseOperation(EXIT_ON_CLOSE);    
        frame.setLayout(null);
        frame.setTitle("Combat - Vue player");
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setVisible(true);

        frame.add(new JPanel() {
            {
                setSize(90, 85);
                setLocation(new Point(0, 200));
                setOpaque(false);

                ImageIcon image = new ImageIcon(
                        new ImageIcon(getClass().getResource(
                                        RESSOURCES + getPlayer().getPokemon().getName() + "-back.png")).
                        getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));

                this.add(new JLabel(image));
            }
        });

        frame.add(new JPanel() {
            {
                setSize(90, 85);
                setOpaque(false);
                setLocation(new Point(400, 0));

                ImageIcon image = new ImageIcon(
                        new ImageIcon(getClass().getResource(
                                        RESSOURCES + getPlayer().getEnnemy().getName() + ".png")).
                        getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
                this.add(new JLabel(image));
            }

        });

        frame.add(new JPanel() {
            {
                setSize(200, 100);
                setOpaque(false);
                setLocation(new Point(100, 200));

                for (final Attack attack : getPlayer().getPokemon().getSkillList()) {
                    JButton btn = new JButton(attack.getName());
                    btn.setSize(20, 200);
                    btn.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent ae) {
                            getPlayer().getPokemon().doAttack(attack);
                            HumanView.this.frame.repaint();
                        }
                    });
                    this.add(btn);
                }
            }
        }
        );
        
        System.out.println("{----HumanView Started----}");

    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("{HumanView - mise a jour a faire...}");
    }

    public void close() {
        this.frame.dispose();
    }
}
