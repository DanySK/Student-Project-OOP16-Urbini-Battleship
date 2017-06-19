package it.unibo.battleship.map;

/**
 * Represents the State of a {@link FieldCell}. A field cell can either have the
 * state Water, Missed, Hit or Present.
 * <ul>
 * <li>Water represents an empty field cell.</li>
 * <li>Present represents a field cell occupied by a ship.</li>
 * <li>Hit represents a field ship which was hit.</li>
 * <li>Missed represents a shot which didn't hit anything.</li>
 * </ul>
 *
 * @author fabio.urbini
 */
public enum State {
  WATER,
  HIT,
  MISSED,
  PRESENT
}
