package view;

import static config.Constante.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import poketournament.*;

/**
 * TournamentView
 *
 * @since 26.05.2014
 * @author Alain FRESCO
 * @version 1.0
 */
public class TournamentView extends JFrame implements Observer {

    private Tournament tournament;
    private static int i = 0;

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

        for (final Match match : tournament.getMatches()) {

            final Pokemon pkmn1 = match.getPkmn1();
            final Pokemon pkmn2 = match.getPkmn2();
            this.paintMatch(pkmn1, pkmn2, match);

        }
        
        tournament.addObserver(this);

    }

    public void paintMatch(final Pokemon pkmn1, final Pokemon pkmn2, final Match match) {

        this.add(new JPanel() {
            {
                setSize(90, 85);
                setLocation(BOXES[i]);
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
                            match.start();
                        }
                    });
                    this.add(btnCombat);
                }
            }
        });

        i++;

        this.add(new JPanel() {
            {
                setSize(90, 85);
                setLocation(BOXES[i]);
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
                            match.start();
                        }
                    });
                    this.add(btnCombat);
                }
            }
        });
        i++;

        this.add(new JButton());
    }

    @Override
    public void update(Observable o, Object arg) {
        for (final Match match : tournament.getMatches()) {

            final Pokemon pkmn1 = match.getPkmn1();
            final Pokemon pkmn2 = match.getPkmn2();
            this.paintMatch(pkmn1, pkmn2, match);
        }

    }

}
