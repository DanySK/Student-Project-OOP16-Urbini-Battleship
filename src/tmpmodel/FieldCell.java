package tmpmodel;

public interface FieldCell {

	// tryPlaceShip -> restituire eccezione
	void placeShip(AbstractShip s);

	void tryShoot(Shot s);

	boolean isMissed();

	boolean isEmpty();

	/**
	 * Returns {@code true} if a {@link Ship} is present...
	 * @return {@code true} if a {@link Ship} is present
	 */
	boolean isPresent();

}