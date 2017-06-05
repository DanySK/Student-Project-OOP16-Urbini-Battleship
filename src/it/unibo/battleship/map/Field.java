package it.unibo.battleship.map;

import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.shots.Shot;
import it.unibo.battleship.ships.Ship;

/**
 * Represents the field of the battleship game.
 * You can either place a ship or shoot.
 * @author fabio.urbini
 *
 */
public interface Field {
	void updateStateWithShot(Shot shot);

	void placeShip(Ship ship, Point2d point);

	// TODO: move to an utility class
	char[][] getMatrix();

	boolean isShipSunk(Ship s);
}
