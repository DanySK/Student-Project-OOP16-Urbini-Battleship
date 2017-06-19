package it.unibo.battleship.ships;

import java.io.Serializable;

/**
 * Represents a Ship factory. Implement it
 * to decide how to create a ship and when, depending on the size.
 * @author fabio.urbini
 *
 */
public interface ShipFactory extends Serializable {
	Ship createShip(final int size);
}
