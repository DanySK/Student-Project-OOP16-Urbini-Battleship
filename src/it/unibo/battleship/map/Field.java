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
     * Places a ship in the field
     * @param ship ship to place
     * @param point position where to place the ship
     */
    void placeShip(Ship ship, Point2d point);

    /**
     * Updates the field with a shot
     * @param shot any kind of shot {@link Shot}
     */
    void updateStateWithShot(Shot shot);

    /**
     * Returns a copy of the boundary
     * @return a copy of the boundary
     */
    Boundary getBoundary();

    /**
     * Returns a copy of the field cells
     * @return a copy of the field cells
     */
    FieldCell[][] getFieldCells();
}

