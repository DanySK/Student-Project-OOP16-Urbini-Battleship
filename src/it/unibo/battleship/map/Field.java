package it.unibo.battleship.map;

import it.unibo.battleship.commons.Boundary;
import it.unibo.battleship.commons.Point2d;
import it.unibo.battleship.ships.Ship;
import it.unibo.battleship.ships.ShipDirection;
import it.unibo.battleship.shots.Shot;

import java.io.Serializable;

/**
 * Represents the field of the battleship game. You can either place a ship or
 * shoot. Any field has a Boundary which describes the space it uses and it
 * starts from origin(0,0).
 *
 * @author fabio.urbini
 */
public interface Field extends Serializable {

  /**
   * Places a ship in the field.
   *
   * @param ship
   *          ship to place
   * @param point
   *          position where to place the ship
   * @param direction
   *          direction of the ship
   */
  void placeShip(Ship ship, Point2d point, ShipDirection direction);

  /**
   * Updates the field with a shot.
   *
   * @param shot
   *          any kind of shot {@link Shot}
   */
  void updateStateWithShot(Shot shot);

  /**
   * Returns the boundary of the field.
   *
   * @return the boundary of the field
   */
  Boundary getBoundary();

  /**
   * Returns the field cells matrix.
   *
   * @return the field cells matrix
   */
  FieldCell[][] getFieldCells();
}
