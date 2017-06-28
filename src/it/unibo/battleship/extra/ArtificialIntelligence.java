package it.unibo.battleship.extra;

import it.unibo.battleship.map.Field;
import it.unibo.battleship.ships.Fleet;
import it.unibo.battleship.shots.Shot;
import java.io.Serializable;

/**
 * Represents an Artificial Intelligence which can be used for creating a new
 * Fleet or for creating new shots. The strategy is decided by implementations.
 *
 * @author fabio.urbini
 */
public interface ArtificialIntelligence extends Serializable {
  Fleet createFleet(); // TODO: make it a functional interface -> FleetFactory?

  Shot createShot(Field field); // TODO: make it a functional interface -> ShotFactory?
}
