package it.unibo.battleship.ships;

import it.unibo.battleship.common.GlobalProperties;
import it.unibo.battleship.common.Ruleset;

import java.util.List;
import java.util.Optional;

/**
 * Created by fabio.urbini on 05/06/2017.
 */
public interface Fleet {
	static Fleet getNewFleet(){
	    Fleet fleet = new FleetImpl();

	    for (int i = 0; i < Ruleset.getSubmarinesNumber(); i++) {
	        fleet.addShip(AbstractShip.createShip(GlobalProperties.SUBMARINE_SIZE));
	    }

	    for (int i = 0; i < Ruleset.getCruisersNumber() ; i++) {
	        fleet.addShip(AbstractShip.createShip(GlobalProperties.CRUISER_SIZE));
	    }

	    for (int i = 0; i < Ruleset.getBattleshipsNumber() ; i++) {
	        fleet.addShip(AbstractShip.createShip(GlobalProperties.BATTLESHIP_SIZE));
	    }
	    return fleet;
	}

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
