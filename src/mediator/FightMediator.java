/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediator;

import java.util.Observable;
import poketournament.Attack;
import poketournament.Match;
import poketournament.Pokemon;

/**
 * 
 * @author Fabio
 */
public class FightMediator extends Observable implements Mediator {

	private final Match match;
	private final Pokemon chosenPkmn;
	private final Pokemon pkmnEnnemi;
	private int pkmnChoisiHP;
	private int pkmnEnnemiHP;

	public FightMediator(Match match, Pokemon chosenPkmn) {
		this.match = match;

		if (this.match.getPkmn1() == chosenPkmn) {
			this.chosenPkmn = chosenPkmn;
			this.pkmnEnnemi = this.match.getPkmn2();
		} else {
			this.pkmnEnnemi = this.match.getPkmn1();
			this.chosenPkmn = chosenPkmn;
		}

		pkmnChoisiHP = chosenPkmn.getHp();
		pkmnEnnemiHP = pkmnEnnemi.getHp();

		this.chosenPkmn.setMediator(this);
		this.pkmnEnnemi.setMediator(this);
	}

	public boolean canIPlay(Pokemon pokemon) {
		// if turn...
		// wait
		// notify
		return true;
	}

	public Match getMatch() {
		return match;
	}

	// c.f. : http://www.pokepedia.fr/index.php/Calcul_des_d%C3%A9g%C3%A2ts
	public void attack(Pokemon source, Attack attack) {
		double stab = (source.getType() == attack.getType() ? 1.5 : 1.0);
		double factor = 1;
		if (source == chosenPkmn) {
			System.out.println("/" + source.getName() + " attaque "
					+ pkmnEnnemi.getName() + " avec " + attack.getName());
			factor = pkmnEnnemi.getType().getVulnerabilityFactor(
					attack.getType());
			// Gestion de la précision de l'attaque
			if (attack.doHit()) {
				pkmnEnnemiHP -= ((50 * 0.4 + 2) * source.getAttack() * (attack
						.getPower() * stab))
						/ (pkmnEnnemi.getDefense() * 50)
						* factor;
			} else {
				System.out.println("L'attaque échoue!");
			}
			if (pkmnEnnemiHP <= 0) {
				System.out.println("PKMN ennemi KO !!");
				match.setWinner(chosenPkmn);
				synchronized (match) {
					match.notify();
				}
			}
		} else {
			System.out.println("/" + pkmnEnnemi.getName() + " attaque "
					+ source + " avec " + attack.getName());
			factor = chosenPkmn.getType().getVulnerabilityFactor(
					attack.getType());
			// Gestion de la précision de l'attaque
			if (attack.doHit()) {
				pkmnChoisiHP -= ((50 * 0.4 + 2) * source.getAttack() * (attack
						.getPower() * stab))
						/ (chosenPkmn.getDefense() * 50)
						* factor;
			} else {
				System.out.println("L'attaque échoue!");
			}
			if (pkmnChoisiHP <= 0) {
				System.out.println("T'es KO !! ");
				match.setWinner(chosenPkmn);
				synchronized (match) {
					match.notify();
				}
			}

		}
		System.out.println("stab:" + stab + "effectiveness:" + factor);
	}

	public Pokemon getPokemonCourrant() {
		return chosenPkmn;
	}

	public Pokemon getPokemonEnnemi() {
		return pkmnEnnemi;
	}

}
