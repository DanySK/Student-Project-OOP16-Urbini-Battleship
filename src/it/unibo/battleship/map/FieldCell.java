package it.unibo.battleship.map;

import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.ships.Ship;

/**
 * Represents a field cell which can have
 * different states.
 * @author fabio.urbini
 *
 */
public interface FieldCell {

	void placeShip(Ship s);

	void tryShoot(Shot s);

	boolean isMissed();

	boolean isEmpty();

	/**
	 * Returns {@code true} if a {@link Ship} is present, false otherwise.
	 * @return {@code true} if a {@link Ship} is present
	 */
	boolean isPresent();

}