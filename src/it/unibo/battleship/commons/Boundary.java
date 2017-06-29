package it.unibo.battleship.commons;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.io.Serializable;

/**
 * Represents the boundary of a field.
 *
 * @author fabio.urbini
 */
@Immutable
public interface Boundary extends Serializable {

  /**
   * Returns the horizontal bound, which is also the number of columns.
   *
   * @return the Horizontal bound (x)
   */
  int getColumnnsCount();

  /**
   * Returns the vertical bound, which is also the number of rows.
   *
   * @return the Vertical bound (y)
   */
  int getRowsCount();

  /**
   * Returns the size of the boundary.
   * @return the size of the boundary.
   */
  int getSize();
}
