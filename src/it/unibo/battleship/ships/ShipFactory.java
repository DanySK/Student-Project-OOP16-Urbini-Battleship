package it.unibo.battleship.ships;

/**
 * Represents a Ship factory. Implement it
 * to decide how to create a ship and when, depending on the size.
 * @author fabio.urbini
 *
 */
public interface ShipFactory {
	Ship createShip(final int size);
}
