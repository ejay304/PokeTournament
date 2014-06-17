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
        setResizable(true);
        setVisible(true);
        setContentPane(new JLabel(new ImageIcon(getClass().getResource(RESSOURCES + "tournament.jpg"))));

        for (final Match match : tournament.getMatches()) {

            final Pokemon pkmn1 = match.getPkmn1();
            final Pokemon pkmn2 = match.getPkmn2();
            this.paintMatch(pkmn1, pkmn2, match);

        }
        tournament.addObserver(this);

    }

    public void paintPokemon(final Pokemon pkmn, final Match match) {

        this.add(new JPanel() {
            {
                setSize(90, 85);
                setLocation(BOXES[i]);
                setOpaque(false);

                ImageIcon image = new ImageIcon(
                        new ImageIcon(getClass().getResource(
                                        RESSOURCES + pkmn.getName() + ".png")).
                        getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));

                if (tournament.getPokemon() != pkmn) {
                    this.add(new JLabel(image)).repaint();
                } else {
                    final JButton btnCombat = new JButton();
                    btnCombat.add(new JLabel(image));

                    btnCombat.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            btnCombat.setEnabled(false);
                            match.start();
                        }
                    });

                    this.add(btnCombat);
                }
            }
        });
        i++;
    }

    public void paintMatch(final Pokemon pkmn1, final Pokemon pkmn2, final Match match) {
        paintPokemon(pkmn1, match);
        paintPokemon(pkmn2, match);
    }

    @Override
    public void update(Observable o, Object arg) {

        System.out.println(i);
        if (i != NB_MATCH) {
            for (final Match match : tournament.getMatches()) {
                System.out.println("print " + match.getPkmn1().getName() + " " + match.getPkmn2().getName());

                final Pokemon pkmn1 = match.getPkmn1();
                final Pokemon pkmn2 = match.getPkmn2();
                this.paintMatch(pkmn1, pkmn2, match);

            }
        } else {
            final Pokemon pkmn = tournament.getMatches()[0].getWinner();
            this.add(new JPanel() {
                {
                    setSize(90, 85);
                    setLocation(BOXES[i]);
                    setOpaque(false);

                    ImageIcon image = new ImageIcon(
                            new ImageIcon(getClass().getResource(
                                            RESSOURCES + pkmn.getName() + ".png")).
                            getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));

                    this.add(new JLabel(image));

                }
            });
        }
        this.revalidate();
        this.repaint();
    }
}
