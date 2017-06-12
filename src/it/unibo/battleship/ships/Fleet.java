package it.unibo.battleship.ships;

import it.unibo.battleship.commons.GlobalProperties;

import java.util.List;
import java.util.Optional;

/**
 * Represents a fleet in the battlefield.
 * A fleet is composed of a defined number of ships.
 * @author fabio.urbini
 *
 */
public interface Fleet {
    void addShip(Ship s);

    void resetFleetPlacement();

    /**
     * Returns all non placed ships
     * @return all non placed ships
     */
    List<Ship> getAllNonPlacedShips();

    /**
     * Returns a list of all ships
     * @return a list of all ships
     */
    List<Ship> getAllShips();

    /**
     * Returns all ships of a type
     * @param shipType {@see GlobalProperties.ShipRules}
     * @return all ships of a type
     */
    List<Ship> getAllShipsByType(GlobalProperties.ShipRules shipType);

    /**
     * Returns the next non placed ship
     * @return the next non placed ship
     */
    Optional<Ship> getNextNonPlacedShip();

    /**
     * Returns the next ship of a type
     * @param shipType {@see GlobalProperties.ShipRules}
     * @return the next ship of a type
     */
    Optional<Ship> getNextShipByType(GlobalProperties.ShipRules shipType);

    /**
     * Returns {@code true} if all the ships of the current fleet
     * were placed and the game can start. {@code false} otherwise
     * @return
     */
    boolean isReady();

    /**
     * Returns true if the fleet is sunk. So
     * all the ships it contains were sunk.
     * @return
     */
    boolean isSunk();
}

