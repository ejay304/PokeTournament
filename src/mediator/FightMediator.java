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
	private final Pokemon chosenPkmn;
	private final Pokemon ennemyPkmn;
	private int chosenPkmnHP;
	private int ennemyPkmnHP;
	private final HumanView humanView;
	private final AiView aiView;
	private final Player player1;
	private final Player player2;
	private int turn;

	public FightMediator(Match match, Pokemon chosenPkmn) {
		this.match = match;

		if (this.match.getPkmn1() == chosenPkmn) {
			this.chosenPkmn = chosenPkmn;
			this.ennemyPkmn = this.match.getPkmn2();
		} else {
			this.ennemyPkmn = this.match.getPkmn1();
			this.chosenPkmn = chosenPkmn;
		}

		chosenPkmnHP = chosenPkmn.getHp();
		ennemyPkmnHP = ennemyPkmn.getHp();

		this.chosenPkmn.setMediator(this);
		this.ennemyPkmn.setMediator(this);

		player1 = new HumanPlayer(this, chosenPkmn, ennemyPkmn);
		player2 = new AiPlayer(this, ennemyPkmn, chosenPkmn);

		humanView = new HumanView((HumanPlayer) player1);
		aiView = new AiView((AiPlayer) player2);

		turn = ((chosenPkmn.getSpeed() >= match.getPkmn1().getSpeed()) ? 0 : 1);

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
		if (source.equals(chosenPkmn)) {
			ennemyPkmn.setStatus(newStatus);
		} else {
			chosenPkmn.setStatus(newStatus);
		}
	}

	// c.f. : http://www.pokepedia.fr/index.php/Calcul_des_d%C3%A9g%C3%A2ts
	public void attack(Pokemon source, Attack attack) {
		double stab = (source.getType() == attack.getType() ? 1.5 : 1.0);
		double factor = 1;

		// N'attaque pas si paralysé ou gelé (1/4 de chance par tour sous statut)
		if (!source.isIncapacitated()) {
			// Si la source est le pokemon choisi
			if (source == chosenPkmn) {
				sendMessageToPlayers(source.getName() + " attaque "
						+ ennemyPkmn.getName() + " avec " + attack.getName());

				factor = ennemyPkmn.getType().getVulnerabilityFactor(
						attack.getType());
				// Gestion de la précision de l'attaque
				if (attack.doHit()) {
					ennemyPkmnHP -= ((50 * 0.4 + 2) * source.getAttack() * (attack
							.getPower() * stab))
							/ (ennemyPkmn.getDefense() * 50) * factor;
				} else {
					sendMessageToPlayers("L'attaque a échoue!");

				}
				if (ennemyPkmnHP <= 0) {
					ennemyPkmnHP = 0;
					sendMessageToPlayers(ennemyPkmn.getName() + " KO !!");

					match.setWinner(chosenPkmn);
				}
			} else {
				sendMessageToPlayers(ennemyPkmn.getName() + " attaque "
						+ chosenPkmn.getName() + " avec " + attack.getName());

				factor = chosenPkmn.getType().getVulnerabilityFactor(
						attack.getType());
				// Gestion de la précision de l'attaque
				if (attack.doHit()) {
					chosenPkmnHP -= ((50 * 0.4 + 2) * source.getAttack() * (attack
							.getPower() * stab))
							/ (chosenPkmn.getDefense() * 50) * factor;
				} else {
					sendMessageToPlayers("L'attaque échoue!");
				}
				if (chosenPkmnHP <= 0) {
					chosenPkmnHP = 0;
					sendMessageToPlayers(chosenPkmn.getName() + " est KO !! ");
					match.setWinner(ennemyPkmn);
				}
			}
			System.out.println("stab:" + stab + " effectiveness:" + factor);
		} else {
			sendMessageToPlayers(source.toString() + " est incapable d'attaquer!");
			source.setCapacitated();
		}
	}

	private void sendMessageToPlayers(String message) {
		player1.setActionCode(Constante.MESSAGE_CODE);
		player2.setActionCode(Constante.MESSAGE_CODE);
		player1.displayMessage(message);
		player2.displayMessage(message);

	}

	public Pokemon getPokemonCourrant() {
		return chosenPkmn;
	}

	public Pokemon getPokemonEnnemi() {
		return ennemyPkmn;
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

	@Override
	public int getHPForPokemon(Pokemon pokemon) {
		if (this.chosenPkmn == pokemon) {
			return chosenPkmnHP;
		} else {
			return ennemyPkmnHP;
		}
	}

}
