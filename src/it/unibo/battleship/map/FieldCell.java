package it.unibo.battleship.map;

import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.ships.Ship;

import java.util.Optional;

/**
 * Represents a field cell which can have
 * different states.
 * @author fabio.urbini
 *
 */
public interface FieldCell {

	void placeShip(Ship s);

	void shoot(Shot s);

	boolean isMissed();

	boolean isEmpty();

	/**
	 * Returns {@code true} if a {@link Ship} is present, false otherwise.
	 * @return {@code true} if a {@link Ship} is present
	 */
	boolean isPresent();

	/**
	 * Returns {@code true} if a {@link Ship} is hit.
	 * @return
	 */
	boolean isHit();

	/**
	 * Returns an Optional of Ship
	 * @return an Optional of Ship
	 */
	Optional<Ship> getShip();

}