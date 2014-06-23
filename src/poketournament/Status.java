package poketournament;

/**
 * Représente les statuts.
 */
public enum Status {
	EN_FORME, POISON, PARALYSE, GELE, BRULE, ;

	@Override
	public String toString() {
		switch (this) {
		case POISON:
			return "empoisonné";
		case PARALYSE:
			return "paralysé";
		case GELE:
			return "gelé";
		case BRULE:
			return "brûlé";
		default:
			return "en forme";
		}
	}

}
