package it.unibo.battleship.ships;

import it.unibo.battleship.common.GlobalProperties;

import java.util.List;
import java.util.Optional;

/**
 * Created by fabio.urbini on 05/06/2017.
 */
public interface Fleet {

	List<Ship> getAllShips();

	List<Ship> getAllNonPlacedShips();

	List<Ship> getAllShipsByType(GlobalProperties.ShipRules shipType);

	Optional<Ship> getNextShipByType(GlobalProperties.ShipRules shipType);

	Optional<Ship> getNextNonPlacedShip();

	void addShip(Ship s);

	void resetFleetPlacement();

	boolean isSunk();

	boolean isReady();
}
