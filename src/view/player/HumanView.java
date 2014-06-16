/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.player;

import config.Constante;
import static config.Constante.*;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import player.HumanPlayer;
import player.Player;
import poketournament.Attack;

/**
 *
 * @author admin
 */
public class HumanView extends PlayerView {

    private JFrame frame;
    private final JPanel panelAttacks;
    private final JPanel panelMessage;
    private final JPanel panelOwn;
    private final JPanel panelEnnemy;
    private final JLabel messageLabel;
    private final JButton btnClose;
    private final JLabel labelEnnemyHP;
    private final JLabel labelOwnHP;

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

        btnClose = new JButton("OK");
        btnClose.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
            }
        });
        labelEnnemyHP = new JLabel(Integer.toString(getPlayer().getEnnemy().getHp()));
        labelOwnHP = new JLabel(Integer.toString(getPlayer().getPokemon().getHp()));
        panelEnnemy = new JPanel() {
            {
                setSize(80, 100);
                setLocation(new Point(0, 200));
                setOpaque(false);

                ImageIcon image = new ImageIcon(
                        new ImageIcon(getClass().getResource(
                                        RESSOURCES + getPlayer().getPokemon().getName() + "-back.png")).
                        getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));

                this.add(new JLabel(image));
                this.add(labelOwnHP);

            }
        };

        panelOwn = new JPanel() {
            {
                setSize(80, 100);
                setOpaque(false);
                setLocation(new Point(400, 0));

                ImageIcon image = new ImageIcon(
                        new ImageIcon(getClass().getResource(
                                        RESSOURCES + getPlayer().getEnnemy().getName() + ".png")).
                        getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
                this.add(new JLabel(image));
                this.add(labelEnnemyHP);
            }

        };

        panelAttacks = new JPanel() {
            {
                setSize(200, 100);
                setOpaque(false);
                setLocation(new Point(100, 200));

                for (final Attack attack : getPlayer().getPokemon().getSkillList()) {
                    JButton btn = new JButton(attack.getName());
                    btn.setSize(20, 200);
                    btn.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent ae) {
                            synchronized (getPlayer()) {
                                getPlayer().setAttackSelected(attack);
                                getPlayer().notify();
                            }
                            HumanView.this.frame.repaint();
                        }
                    });
                    this.add(btn);
                }
            }
        };

        messageLabel = new JLabel();

        panelMessage = new JPanel() {
            {
                setSize(400, 100);
                setOpaque(false);
                setLocation(new Point(20, 150));
                add(messageLabel);
            }
        };

        frame.add(panelEnnemy);
        frame.add(panelOwn);

        frame.add(panelAttacks);
        frame.add(panelMessage);
        panelMessage.setVisible(false);
        panelAttacks.setVisible(false);

        System.out.println("{----HumanView Started----}");

    }

    @Override
    public void update(Observable o, Object arg) {
        //force une pause entre les différents messages
        try {
            Thread.sleep(Constante.TIME_SLEEP_BETWEEN_ACTION);
        } catch (InterruptedException ex) {
        }
        int hpEnnemy = getPlayer().getMediator().getHPForPokemon(getPlayer().getEnnemy());
        int hpOwn = getPlayer().getMediator().getHPForPokemon(getPlayer().getPokemon());

        labelOwnHP.setText(Integer.toString(hpOwn));
        labelEnnemyHP.setText(Integer.toString(hpEnnemy));
        switch (getPlayer().getActionCode()) {
            case SELECT_CODE:
                panelAttacks.setVisible(true);
                panelMessage.setVisible(false);
                break;
            case DEFEAT_CODE:
                messageLabel.setText("Défaite");
                panelMessage.add(btnClose);
                panelAttacks.setVisible(false);
                panelMessage.setVisible(true);
                break;
            case VICTORY_CODE:
                messageLabel.setText("Victoire");
                panelMessage.add(btnClose);
                panelAttacks.setVisible(false);
                panelMessage.setVisible(true);
                break;
            default:
            case MESSAGE_CODE:
                messageLabel.setText(getPlayer().getMessage());
                panelAttacks.setVisible(false);
                panelMessage.setVisible(true);
                break;
        }
    }

    public void close() {
        this.frame.dispose();
    }
}
