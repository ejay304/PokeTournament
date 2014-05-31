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
 * @since 26.05.2014
 * @author Alain FRESCO
 * @version 1.0
 */
public class TournamentView extends JFrame {

    private Tournament tournament;

    public TournamentView(final Tournament tournament) {

        this.tournament = tournament;

        // Configuration de la JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Etat du tournois");
        setSize(1227, 641);
        setResizable(false);
        setVisible(true);
        setContentPane(new JLabel(new ImageIcon(getClass().getResource(RESSOURCES + "tournament.jpg"))));

        int i = 0;
        for (final Match match : tournament.getMatches()) {
            final Point point = BOXES[i];

            final Pokemon pkmn1 = match.getPkmn1();
            final Pokemon pkmn2 = match.getPkmn2();
            this.add(new JPanel() {
                {
                    setSize(90, 85);
                    setLocation(point);
                    setOpaque(false);

                    ImageIcon image = new ImageIcon(
                            new ImageIcon(getClass().getResource(
                                            RESSOURCES + pkmn1.getName() + ".png")).
                            getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));

                    if (tournament.getPokemon() != pkmn1) {
                        this.add(new JLabel(image));
                    } else {
                        final JButton btnCombat = new JButton();
                        btnCombat.add(new JLabel(image));
                        btnCombat.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                btnCombat.setEnabled(false);
                                //TODO: Recuperer le pokemon ennemi...
                                Fight fight = new Fight(match, tournament.getPokemon());
                                new FightView(fight);
                            }
                        });
                        this.add(btnCombat);
                    }
                }
            });
            
            i++;
            final Point point2 = BOXES[i];

            this.add(new JPanel() {
                {
                    setSize(90, 85);
                    setLocation(point2);
                    setOpaque(false);

                    ImageIcon image = new ImageIcon(
                            new ImageIcon(getClass().getResource(
                                            RESSOURCES + pkmn2.getName() + ".png")).
                            getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));

                    if (tournament.getPokemon() != pkmn2) {
                        this.add(new JLabel(image));
                    } else {
                        final JButton btnCombat = new JButton();
                        btnCombat.add(new JLabel(image));
                        btnCombat.addActionListener(new ActionListener() {
                            
                            public void actionPerformed(ActionEvent e) {
                                btnCombat.setEnabled(false);
                                //TODO: Recuperer le pokemon ennemi...
                                Fight fight = new Fight(match, tournament.getPokemon());
                                new FightView(fight);
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
