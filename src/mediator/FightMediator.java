/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediator;

import java.util.Observable;
import player.AiPlayer;
import player.HumanPlayer;
import player.Player;
import poketournament.Attack;
import poketournament.Match;
import poketournament.Pokemon;
import poketournament.RandomNumberGenerator;
import poketournament.Status;
import view.player.AiView;
import view.player.HumanView;
import config.Constante;

/**
 * 
 * @author Fabio
 */
public class FightMediator extends Observable implements Mediator, Runnable {

	private final Match match;
	private final Pokemon pkmn1;
	private final Pokemon pkmn2;
	private int pkmn1HP;
	private int pkmn2HP;
	private final HumanView humanView;
	private final AiView aiView;
	private final Player player1;
	private final Player player2;
	private int turn;

	public FightMediator(Match match) {
		this.match = match;

		this.pkmn1 = match.getChosenPokemon();
                
                if(match.getPkmn1() == this.pkmn1)
                    this.pkmn2 = match.getPkmn2();
                else
                    this.pkmn2 = match.getPkmn1();
                
		pkmn1HP = pkmn1.getHp();
		pkmn2HP = pkmn2.getHp();

		this.pkmn1.setMediator(this);
		this.pkmn2.setMediator(this);

		player1 = new HumanPlayer(this, pkmn1);
		player2 = new AiPlayer(this, pkmn2);

		humanView = new HumanView((HumanPlayer) player1);
		aiView = new AiView((AiPlayer) player2);

		turn = ((pkmn1.getSpeed() >= match.getPkmn1().getSpeed()) ? 0 : 1);

		new Thread(this).start();
	}

	public Match getMatch() {
		return match;
	}

	/**
	 * Change le statut du pokemon cible
	 * 
	 * @param source
	 *            La source du statut
	 * @param newStatus
	 *            Le nouveau statut
	 */
	public void changeStatus(Pokemon source, Status newStatus) {
		if (source.equals(pkmn1)) {
			pkmn2.setStatus(newStatus);
		} else {
			pkmn1.setStatus(newStatus);
		}
	}

	// c.f. : http://www.pokepedia.fr/index.php/Calcul_des_d%C3%A9g%C3%A2ts
	public void attack(Pokemon source, Attack attack) {
		double stab = (source.getType() == attack.getType() ? 1.5 : 1.0);
		double factor = 1;

		// N'attaque pas si paralysé ou gelé (1/4 de chance par tour sous statut)
		if (!source.isIncapacitated()) {
			// Si la source est le pokemon choisi
			if (source == pkmn1) {
				sendMessageToPlayers(source.getName() + " attaque "
						+ pkmn2.getName() + " avec " + attack.getName());

				factor = pkmn2.getType().getVulnerabilityFactor(
						attack.getType());
				// Gestion de la précision de l'attaque
				if (attack.doHit()) {
					pkmn2HP -= ((50 * 0.4 + 2) * source.getAttack() * (attack
							.getPower() * stab))
							/ (pkmn2.getDefense() * 50) * factor;
					if(attack.getStatus() != null) {
			    		if(RandomNumberGenerator.getInstance().getRandomNumber(3) == 0) {
			    			pkmn2.setStatus(attack.getStatus());
			    			sendMessageToPlayers(pkmn2.toString() + " est " + attack.getStatus().toString());
			    		}
			    	}
				} else {
					sendMessageToPlayers("L'attaque a échoue!");

				}
				if (pkmn2HP <= 0) {
					pkmn2HP = 0;
					sendMessageToPlayers(pkmn2.toString() + " KO !!");

					match.setWinner(pkmn1);
				}
			} else {
				sendMessageToPlayers(pkmn2.toString() + " attaque "
						+ pkmn1.toString() + " avec " + attack.getName());

				factor = pkmn1.getType().getVulnerabilityFactor(
						attack.getType());
				// Gestion de la précision de l'attaque
				if (attack.doHit()) {
					pkmn1HP -= ((50 * 0.4 + 2) * source.getAttack() * (attack
							.getPower() * stab))
							/ (pkmn1.getDefense() * 50) * factor;
					if(attack.getStatus() != null) {
			    		if(RandomNumberGenerator.getInstance().getRandomNumber(3) == 0) {
			    			pkmn1.setStatus(attack.getStatus());
			    			sendMessageToPlayers(pkmn1.toString() + " est " + attack.getStatus().toString());
			    		}
			    	}
				} else {
					sendMessageToPlayers("L'attaque échoue!");
				}
				if (pkmn1HP <= 0) {
					pkmn1HP = 0;
					sendMessageToPlayers(pkmn1.toString() + " est KO !! ");
					match.setWinner(pkmn2);
				}
			}
			System.out.println("stab:" + stab + " effectiveness:" + factor);
		} else {
			sendMessageToPlayers(source.toString() + " est incapable d'attaquer car il est " + source.getStatus().toString() + ".");
			source.setCapacitated();
		}
	}

	private void sendMessageToPlayers(String message) {
		player1.setActionCode(Constante.MESSAGE_CODE);
		player2.setActionCode(Constante.MESSAGE_CODE);
		player1.displayMessage(message);
		player2.displayMessage(message);

	}

	public Pokemon getEnnemyPokemon(Pokemon pokemon) {
            if(pokemon == pkmn1) 
                return pkmn2;
            return pkmn1;
	}

	@Override
	public void run() {
		while (this.match.getWinner() == null) {

			if (this.turn == 0) {
				player1.setActionCode(Constante.SELECT_CODE);
				player1.selectAttack();
				turn = 1;
				player2.setActionCode(Constante.MESSAGE_CODE);
				
			} else {

				player2.setActionCode(Constante.SELECT_CODE);
				player2.selectAttack();
				turn = 0;
				player1.setActionCode(Constante.MESSAGE_CODE);
			}
			// Fin du tour, gestion des statuts
			pkmn2.doStatus();
			pkmn1.doStatus();
			
		}
		// Envoi du résultat aux joueurs
		if (match.getWinner() == player1.getPokemon()) {
			player1.setActionCode(Constante.VICTORY_CODE);
			player2.setActionCode(Constante.DEFEAT_CODE);
		} else {
			player1.setActionCode(Constante.DEFEAT_CODE);
			player2.setActionCode(Constante.VICTORY_CODE);
		}

		synchronized (match) {
			match.notify();
		}
	}
	
	public void statusDamage(Pokemon source) {
		if(source.equals(pkmn1)) {
			pkmn1HP -= (source.getHp()/8);
		} else {
			pkmn2HP -= (source.getHp()/8);
		}
	}

	@Override
	public int getHPForPokemon(Pokemon pokemon) {
		if (this.pkmn1 == pokemon) {
			return pkmn1HP;
		} else {
			return pkmn2HP;
		}
	}

}
