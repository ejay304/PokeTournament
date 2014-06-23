package mediator;

import config.Constante;
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
import view.player.PlayerView;

/**
 * Représente le médiateur d'un combat
 */
public class FightMediator extends Observable implements Mediator, Runnable {

	private final Match match; //le match pour lequel le médiateur s'applique
	private final Pokemon pkmn1; //reference sur le premier pokemon
	private final Pokemon pkmn2; //reference sur le deuxième pokemon
	private int pkmn1HP; //la vie courrante du pokemon 1
	private int pkmn2HP; //la vie courrante du pokemon 2
	private final PlayerView player1View; //vue pour le premier joueur
	private final PlayerView player2View; // vue pour le deuxième joueur
	private final Player player1; // le joueur 1
	private final Player player2; // le joueur 2
	private int turn; //le tour actuel

        /**
         * Constructeur
         * @param match le match pour lequel le médiateur s'applique 
         */
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
                //notre premier joueur est un humain, le deuxième une AI
		player1 = new HumanPlayer(this, pkmn1);
		player2 = new AiPlayer(this, pkmn2);

		player1View = new HumanView((HumanPlayer) player1);
		player2View = new AiView((AiPlayer) player2);

		turn = ((pkmn1.getSpeed() >= match.getPkmn1().getSpeed()) ? 0 : 1);

		new Thread(this).start();
	}

        // Retourne le match monitoré par le mediator
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

        /**
         * Gère les attaques des pokemons
         * @param source le pokemon qui attaque
         * @param attack l'attaque lancée
         */
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

        /**
         * Envoie un message à tous les joueurs
         * @param message le message
         */
	private void sendMessageToPlayers(String message) {
		player1.setActionCode(Constante.MESSAGE_CODE);
		player2.setActionCode(Constante.MESSAGE_CODE);
		player1.displayMessage(message);
		player2.displayMessage(message);

	}

        /**
         * Retourne le pokemon ennemi 
         * @param pokemon notre pokemon
         * @return le pokemon ennemi
         */
        @Override
	public Pokemon getEnnemyPokemon(Pokemon pokemon) {
            if(pokemon == pkmn1) 
                return pkmn2;
            return pkmn1;
	}

	@Override
        /**
         * Thread gérant un combat
         */
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
	
        /**
         * Dégâts de statut (poison, brulure)
         * @param source 
         */
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
