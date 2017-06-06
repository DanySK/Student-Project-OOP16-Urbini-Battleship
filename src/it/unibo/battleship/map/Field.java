package it.unibo.battleship.map;

import it.unibo.battleship.common.Boundary;
import it.unibo.battleship.common.Point2d;
import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.shots.Shot;

/**
 * Represents the field of the battleship game.
 * You can either place a ship or shoot.
 * @author fabio.urbini
 *
 */
public interface Field {

	/**
	 * Updates the field with a shot
	 * @param shot any kind of shot {@link Shot}
	 */
	void updateStateWithShot(Shot shot);

	/**
	 * Places a ship in the field
	 * @param ship ship to place
	 * @param point position where to place the ship
	 */
	void placeShip(Ship ship, Point2d point);

	// TODO: move to an utility class
	char[][] getMatrix();

	/**
	 * Returns a representation of the field
	 * viewied by the owner of the field
	 * @return
	 */
	char[][] getViewByOwner();

	/**
	 * Returns a representation of the field
	 * viewied by the enemy
	 * @return a representation of the field
	 * seen by the enemy
	 */
	char[][] getViewByEnemy();

	Boundary getBoundary();
}
