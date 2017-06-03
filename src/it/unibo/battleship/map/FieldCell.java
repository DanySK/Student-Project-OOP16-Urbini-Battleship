package it.unibo.battleship.map;

import it.unibo.battleship.common.Shot;
import it.unibo.battleship.ships.AbstractShip;

/**
 * Represents a field cell which can have
 * different states.
 * @author fabio
 *
 */
public interface FieldCell {

	void placeShip(AbstractShip s);

	void tryShoot(Shot s);

	boolean isMissed();

	boolean isEmpty();

	/**
	 * Returns {@code true} if a {@link AbstractShip} is present, false otherwise.
	 * @return {@code true} if a {@link AbstractShip} is present
	 */
	boolean isPresent();

}