package it.unibo.battleship.extra;

import it.unibo.battleship.map.Field;
import it.unibo.battleship.ships.Fleet;
import it.unibo.battleship.shots.Shot;

/**
 * Represents an Artificial Intelligence which
 * can be used for creating a new Fleet or for
 * creating new shots.
 * The strategy is decided by implementations.
 * @author fabio.urbini
 */
public interface ArtificialIntelligence {

    Fleet createFleet();
    Shot createShot(Field field);

}
