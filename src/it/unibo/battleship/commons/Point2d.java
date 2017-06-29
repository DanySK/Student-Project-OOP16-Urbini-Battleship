package it.unibo.battleship.commons;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.Serializable;

/**
 * Represents a 2 dimension point.
 *
 * @author fabio.urbini
 */
@Immutable
public interface Point2d extends Serializable {

  /**
   * Returns the X coordinate (column).
   *
   * @return the x coordinate (column)
   */
  int getX();

  /**
   * Returns the y coordinate (row).
   *
   * @return the y coordinate (row)
   */
  int getY();
}
