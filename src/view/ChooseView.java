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
            pokemonData.setLayout(new GridLayout(3,1));
            
            JLabel name = new JLabel(pokemon.getName());
            name.setFont(new Font("Serif", Font.PLAIN, 30));
            
            JLabel type = new JLabel(pokemon.getType().toString());
            type.setFont(new Font("Serif", Font.PLAIN, 20));
                        
            JPanel pokemonSkills = new JPanel();
            pokemonSkills.setLayout(new GridLayout(2,2));
            pokemonSkills.add(new JLabel("HP : " + pokemon.getHp()));
            pokemonSkills.add(new JLabel("ATT : " + pokemon.getAttack()));
            pokemonSkills.add(new JLabel("DEF : " + pokemon.getDefense()));
            pokemonSkills.add(new JLabel("SPD : " + pokemon.getSpeed()));
            
            pokemonData.add(name);
            pokemonData.add(type);
            pokemonData.add(pokemonSkills);
                        
            JPanel panelChoose = new JPanel();
            panelChoose.setPreferredSize(new Dimension(330,150));
            panelChoose.setLayout(new GridLayout(1,2));
            System.out.println(pokemon.getName());
            panelChoose.add(new JLabel(new ImageIcon(getClass().getResource(
                            PATH + pokemon.getName() + ".png"))));
            panelChoose.add(pokemonData);            
            btn.add(panelChoose);
            
            btn.addActionListener(new ActionListener() {
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