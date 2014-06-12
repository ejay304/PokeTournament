package poketournament;

import java.util.Random;

/**
 * Générateur de nombre aléatoire.
 * 
 * @author gaetanchevalley
 * 
 */
public class RandomNumberGenerator {

	private Random randomNumberGenerator;
	private static RandomNumberGenerator instance;

	private RandomNumberGenerator() {
		randomNumberGenerator = new Random();
	}

	/**
	 * Récupère une instance unique du générateur.
	 * 
	 * @return l'instance du générateur
	 */
	public static RandomNumberGenerator getInstance() {
		if (instance == null) {
			instance = new RandomNumberGenerator();
		}

		return instance;
	}

	/**
	 * Récupère le nombre aléatoire généré entre 0 et max.
	 * 
	 * @param max
	 *            valeur maximale de l'aléatorisation
	 * @return la valeur aléatoire générée
	 */
	public int getRandomNumber(int max) {
		return randomNumberGenerator.nextInt(max);
	}
}
