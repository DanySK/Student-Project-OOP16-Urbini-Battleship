package it.unibo.battleship.map;

/**
 * Represents the State of a {@link FieldCell}.
 * A field cell can either be Water, Missed or Present.
 * Water represents an empty field cell.
 * Present represents a field cell occupied by a ship.
 * @author fabio.urbini
 *
 */
public enum State {
	WATER,
	MISSED,
	PRESENT
}