package tmpmodel;

/**
 * Represents a field cell which can have
 * different states.
 * @author fabio
 *
 */
public interface FieldCell {

	// TODO: tryPlaceShip -> restituire eccezione
	void placeShip(AbstractShip s);

	void tryShoot(Shot s);

	boolean isMissed();

	boolean isEmpty();

	/**
	 * Returns {@code true} if a {@link Ship} is present, false otherwise.
	 * @return {@code true} if a {@link Ship} is present
	 */
	boolean isPresent();

}