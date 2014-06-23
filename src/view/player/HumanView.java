package view.player;

import config.Constante;
import static config.Constante.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.Observable;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import player.HumanPlayer;
import poketournament.Attack;

/**
 * Vue pour un joueur durant un combat
 */
public class HumanView extends PlayerView {

    private JFrame frame;
    private final JPanel panelAttacks;
    private final JPanel panelOwn;
    private final JPanel panelEnnemy;
    private final JPanel panelMessage;
    private final JButton btnClose;
    private final JLabel labelMessage;
    private final JLabel labelEnnemyHP;
    private final JLabel labelOwnHP;
    private final JLabel labelEnnemyName;
    private final JLabel labelOwnName;

    private final JProgressBar progressBarHealthOwn;
    private final JProgressBar progressBarHealthEnnemy;

    public HumanView(HumanPlayer player) {
        super(player);
        player.addObserver(this);

        frame = new JFrame();
        // Configuration de la JFrame
        //setDefaultCloseOperation(EXIT_ON_CLOSE);    
        frame.setLayout(null);
        frame.setTitle("Combat - Vue player");
        frame.setSize(700, 400);
        frame.setResizable(false);
        frame.setVisible(true);

        btnClose = new JButton("OK");
        btnClose.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        labelEnnemyHP = new JLabel(Integer.toString(player.getMediator().getEnnemyPokemon(getPlayer().getPokemon()).getHp()));
        labelOwnHP = new JLabel(Integer.toString(getPlayer().getPokemon().getHp()));

        labelEnnemyName = new JLabel(player.getMediator().getEnnemyPokemon(getPlayer().getPokemon()).getName());
        labelOwnName = new JLabel(player.getPokemon().getName());

        progressBarHealthOwn = new JProgressBar(0, 100);
        progressBarHealthOwn.setValue(100);
        progressBarHealthOwn.setStringPainted(true);
        progressBarHealthOwn.setForeground(Color.GREEN);

        progressBarHealthEnnemy = new JProgressBar(0, 100);
        progressBarHealthEnnemy.setValue(100);
        progressBarHealthEnnemy.setStringPainted(true);
        progressBarHealthEnnemy.setForeground(Color.GREEN);

        panelAttacks = new JPanel() {
            {
                for (final Attack attack : getPlayer().getPokemon().getSkillList()) {

                    JPanel panelDataAttack = new JPanel();

                    JLabel labelNameAttack = new JLabel(attack.getName());
                    JButton btn = new JButton();

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
                    String typeNom = sansAccent(attack.getType().getName().toLowerCase());
  
                    ImageIcon image = new ImageIcon(
                            new ImageIcon(getClass().getResource(
                                            RESSOURCES_TYPE
                                            + typeNom
                                            + ".png")).getImage());

                    panelDataAttack.add(new JLabel(image));
                    panelDataAttack.add(labelNameAttack);

                    btn.add(panelDataAttack);
                    this.add(btn);
                }
            }
        };

        panelOwn = new JPanel() {
            {
                ImageIcon image = new ImageIcon(
                        new ImageIcon(getClass().getResource(
                                        RESSOURCES + getPlayer().getPokemon().getName() + "-back.png")).
                        getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));

                this.add(new JLabel(image));
                this.add(progressBarHealthOwn);
                this.add(labelOwnHP);
                this.add(labelOwnName);
                this.add(panelAttacks);
            }
        };

        panelEnnemy = new JPanel() {
            {
                ImageIcon image = new ImageIcon(
                        new ImageIcon(getClass().getResource(
                                        RESSOURCES + getPlayer().getMediator().getEnnemyPokemon(getPlayer()
                                                .getPokemon()).getName() + ".png")).
                        getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
                this.add(new JLabel(image));
                this.add(progressBarHealthEnnemy);
                this.add(labelEnnemyHP);
                this.add(labelEnnemyName);
            }
        };

        labelMessage = new JLabel();

        panelMessage = new JPanel() {
            {
                this.add(labelMessage);
            }
        };

        mainPanel.add(panelEnnemy);
        mainPanel.add(panelMessage);
        mainPanel.add(panelOwn);
        frame.setContentPane(mainPanel);

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
        int hpEnnemy = getPlayer().getMediator().getHPForPokemon(getPlayer().getMediator().getEnnemyPokemon(getPlayer().getPokemon()));
        int hpOwn = getPlayer().getMediator().getHPForPokemon(getPlayer().getPokemon());

        progressBarHealthOwn.setValue((int) ((float) hpOwn / (float) getPlayer().getPokemon().getHp() * 100));

        System.out.println("------------------HP " + hpOwn);
        System.out.println("------------------HPF " + getPlayer().getPokemon().getHp());

        if (progressBarHealthOwn.getValue() <= 50) {
            if (progressBarHealthOwn.getValue() <= 20) {
                progressBarHealthOwn.setForeground(Color.RED);
            } else {
                progressBarHealthOwn.setForeground(Color.ORANGE);
            }
        }

        progressBarHealthEnnemy.setValue((int) ((float) hpEnnemy / (float) getPlayer().getMediator().getEnnemyPokemon(getPlayer().getPokemon()).getHp() * 100));

        if (progressBarHealthEnnemy.getValue() <= 50) {
            if (progressBarHealthEnnemy.getValue() <= 20) {
                progressBarHealthEnnemy.setForeground(Color.RED);
            } else {
                progressBarHealthEnnemy.setForeground(Color.ORANGE);
            }
        }

        labelOwnHP.setText(Integer.toString(hpOwn));
        labelEnnemyHP.setText(Integer.toString(hpEnnemy));
        switch (getPlayer().getActionCode()) {
            case SELECT_CODE:
                panelAttacks.setVisible(true);
                panelMessage.setVisible(false);
                break;
            case DEFEAT_CODE:
                labelMessage.setText("Défaite");
                panelMessage.add(btnClose);
                panelAttacks.setVisible(false);
                panelMessage.setVisible(true);
                break;
            case VICTORY_CODE:
                labelMessage.setText("Victoire");
                panelMessage.add(btnClose);
                panelAttacks.setVisible(false);
                panelMessage.setVisible(true);
                break;
            default:
            case MESSAGE_CODE:
                labelMessage.setText(getPlayer().getMessage());
                panelAttacks.setVisible(false);
                panelMessage.setVisible(true);
                break;
        }
    }

    private static String sansAccent(String s) {

        String strTemp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(strTemp).replaceAll("");
    }

    public void close() {
        this.frame.dispose();
    }
}
