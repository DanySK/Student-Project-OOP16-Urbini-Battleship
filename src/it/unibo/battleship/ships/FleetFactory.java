package it.unibo.battleship.ships;

import java.io.Serializable;

/**
 * Represents a Fleet Factory.
 * It can be used to instantiate new fleets.
 *
 * @author fabio.urbini
 */
public interface FleetFactory extends Serializable {
   Fleet createFleet();
}
