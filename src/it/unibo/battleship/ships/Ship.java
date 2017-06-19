package it.unibo.battleship.ships;

import it.unibo.battleship.commons.Point2d;
import it.unibo.battleship.shots.Shot;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * TODO: javadoc
 *
 * @author fabio.urbini
 */
public interface Ship extends Serializable {

  /**
   * Returns {@code true} if this ship contains a point specified by the
   * parameter.
   *
   * @param point
   *          {@link Point2d}
   * @return {@code true} if this ship contains the specified point
   */
  boolean containsPosition(Point2d point);

  /**
   * Places a ship where specified.
   *
   * @param start
   *          {@link Point2d}
   * @param direction
   *          {@link ShipDirection}
   */
  void place(Point2d start, ShipDirection direction);

  /**
   * Resets the placement of this ship.
   */
  void resetPlacement();

  /**
   * Tries to shoot this ship. Returns {@code true} if the ship was hit,
   * {@code false} otherwise.
   *
   * @param shot
   *          {@link Shot}
   * @return {@code true} if the ship was hit, {@code false} otherwise
   */
  boolean shoot(Shot shot);

  /**
   * Gets all points used by this ship.
   *
   * @return all points used by this ship
   */
  List<Point2d> getAllPositions();

  /**
   * Returns {@code true} if this ship was placed.
   *
   * @return {@code true} if this ship was placed
   */
  boolean isPlaced();

  /**
   * Gets the starting position of this ship.
   *
   * @return the starting position of this ship
   */
  Optional<Point2d> getStartingPosition();

  /**
   * Returns temporary projection points of the ship determined by the point
   * given in the input.
   *
   * @param point
   *          point where the ship will start
   * @return a List of {@link Point2d}
   */
  List<Point2d> getProjectionPoints(Point2d point);

  /**
   * Returns the size of the current ship.
   *
   * @return the size of the current ship
   */
  int getSize();

  /**
   * Returns {@code true} if the ship was sunk.
   *
   * @return {@code true} if the ship was sunk
   */
  boolean isSunk();
}
