package it.unibo.battleship.ships;

import java.io.Serializable;

/**
 * Represents a Ship factory. Implement it to decide how to create a ship and
 * when, depending on the size.
 *
 * @author fabio.urbini
 */
@FunctionalInterface
public interface ShipFactory extends Serializable {
  /**
   * Creates a ship.
   * @param size size of the ship
   * @return a ship
   */
  Ship createShip(final int size);
}
