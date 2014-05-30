/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import poketournament.Pokemon;
import poketournament.Tournament;

/**
 *
 * @author Romain Therisod
 */
public class ChooseView extends JFrame {
    
    private final String PATH = "/ressources/";
    private JPanel panel;
    
    public ChooseView(){
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Sélection Pokemon");
        setSize(800, 800);
        
        panel = new JPanel();
        this.add(panel);
        
        for(final Pokemon pokemon : Pokemon.pokemons){
            
            JButton btn = new JButton();
                        
            JPanel pokemonData = new JPanel();
            pokemonData.setLayout(new GridLayout(5,1));
            
            JLabel name = new JLabel(pokemon.getName());
            name.setFont(new Font("Serif", Font.PLAIN, 36));
            
            pokemonData.add(name);
            pokemonData.add(new JLabel("HP : " + pokemon.getHp()));
            pokemonData.add(new JLabel("ATT : " + pokemon.getAttack()));
            pokemonData.add(new JLabel("DEF : " + pokemon.getDefense()));
            pokemonData.add(new JLabel("SPD : " + pokemon.getSpeed()));
                        
            JPanel panelChoose = new JPanel();
            panelChoose.setPreferredSize(new Dimension(300,150));
            panelChoose.setLayout(new GridLayout(1,2));
            System.out.println(pokemon.getName());
            panelChoose.add(new JLabel(new ImageIcon(getClass().getResource(
                            PATH + pokemon.getName() + ".png"))));
            panelChoose.add(pokemonData);            
            btn.add(panelChoose);
            
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    ChooseView.this.dispose();
                    new TournamentView(new Tournament(pokemon));
                }
            });
            
            panel.add(btn);
        }
        setVisible(true);
    }
}