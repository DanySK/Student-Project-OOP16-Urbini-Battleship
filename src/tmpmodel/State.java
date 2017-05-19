package tmpmodel;

/**
 * Represents the State of a {@link FieldCell}
 * @author fabio urbini
 *
 */
public enum State {
	WATER,
	MISSED,
	PRESENT;

	public State getStateSeenByOwner() {
		return this;
	}

	public State getStateSeenByEnemy() {
		if (this == PRESENT) {
			return WATER;
		}
		return this;
	}
}