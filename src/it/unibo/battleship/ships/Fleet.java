package it.unibo.battleship.ships;

import it.unibo.battleship.common.GlobalProperties;

import java.util.List;
import java.util.Optional;

/**
 * Represents a fleet in the battlefield.
 * A fleet is composed of a defined number of ships.
 * @author fabio.urbini
 *
 */
public interface Fleet {

	List<Ship> getAllShips();

	/**
	 * Returns all non placed ships
	 * @return all non placed ships
	 */
	List<Ship> getAllNonPlacedShips();

	/**
	 * Returns all ships of a type
	 * @param shipType {@see GlobalProperties.ShipRules}
	 * @return all ships of a type
	 */
	List<Ship> getAllShipsByType(GlobalProperties.ShipRules shipType);

	/**
	 * Returns the next ship of a type
	 * @param shipType {@see GlobalProperties.ShipRules}
	 * @return the next ship of a type
	 */
	Optional<Ship> getNextShipByType(GlobalProperties.ShipRules shipType);

	/**
	 * Returns the next non placed ship
	 * @return the next non placed ship
	 */
	Optional<Ship> getNextNonPlacedShip();

	void addShip(Ship s);

	void resetFleetPlacement();

	boolean isSunk();

	boolean isReady();
}
