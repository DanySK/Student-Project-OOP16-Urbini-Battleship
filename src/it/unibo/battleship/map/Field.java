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
	void updateStateWithShot(Shot shot);

	void placeShip(Ship ship, Point2d point);

	// TODO: move to an utility class
	char[][] getMatrix();

	char[][] getViewByOwner();

	char[][] getViewByEnemy();

	Boundary getBoundary();
}
