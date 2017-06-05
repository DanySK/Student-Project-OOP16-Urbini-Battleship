package it.unibo.battleship.ships;

import com.google.common.base.Objects;
import it.unibo.battleship.common.GlobalProperties;
import it.unibo.battleship.common.GlobalProperties.ShipRules;
import it.unibo.battleship.common.Ruleset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a fleet in the battlefield.
 * A fleet is composed of a defined number of ships.
 * @author fabio.urbini
 *
 */
public class FleetImpl {
    private final List<AbstractShip> ships;

    public FleetImpl() {
        this.ships = new ArrayList<>();
    }

    public List<AbstractShip> getAllShips() {
    	return Collections.unmodifiableList(this.ships);
    }

    public List<AbstractShip> getAllNonPlacedShips() {
        List<AbstractShip> nonPlacedShips = this.getAllShips()
                .stream()
                .filter(ship -> !ship.isPlaced()).collect(Collectors.toList());

        return Collections.unmodifiableList(nonPlacedShips);
    }

    public List<AbstractShip> getAllShipsByType(ShipRules shipType) {
        List<AbstractShip> ships = this.ships.stream().filter(ship -> ship.toString().equals(shipType.toString())).filter(ship -> !ship.isPlaced()).collect(Collectors.toList());

        return Collections.unmodifiableList(ships);
    }

    public Optional<AbstractShip> getNextShipByType(ShipRules shipType) {
        Optional<AbstractShip> ship = Optional.empty();

        if (!getAllShipsByType(shipType).isEmpty()) {
            ship = Optional.of(getAllShipsByType(shipType).get(0));
        }

        return ship;
    }

    public Optional<AbstractShip> getNextNonPlacedShip() {
        Optional<AbstractShip> ship = Optional.empty();

        if (!getAllNonPlacedShips().isEmpty()) {
            ship = Optional.of(getAllNonPlacedShips().get(0));
        }

        return ship;
    }

    public void addShip(AbstractShip s) {
        this.ships.add(s);
    }

    public void resetFleetPlacement () {
        this.getAllShips().forEach(AbstractShip::resetPlacement);
    }

    public boolean isSunk() {
        for (AbstractShip ship : this.ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public boolean isReady() {
        for (final AbstractShip s : this.ships ) {
            if (!s.isPlaced()) {
                return false;
            }
        }
        return true;
    }
    
    public static FleetImpl getNewFleet(){
        FleetImpl fleet = new FleetImpl();
        
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
    
    private static void createShips(final FleetImpl fleet, final int ShipsNumber) {
    	
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FleetImpl that = (FleetImpl) o;

        return Objects.equal(this.ships, that.ships);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ships);
    }
}
