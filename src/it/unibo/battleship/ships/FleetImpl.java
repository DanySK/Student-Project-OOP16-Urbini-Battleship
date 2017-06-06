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
public class FleetImpl implements Fleet {
    private final List<Ship> ships;

    public FleetImpl() {
        this.ships = new ArrayList<>();
    }
    public static Fleet getNewFleet(){
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
    @Override
    public List<Ship> getAllShips() {
    	return Collections.unmodifiableList(this.ships);
    }

    @Override
    public List<Ship> getAllNonPlacedShips() {
        List<Ship> nonPlacedShips = this.getAllShips()
                .stream()
                .filter(ship -> !ship.isPlaced()).collect(Collectors.toList());

        return Collections.unmodifiableList(nonPlacedShips);
    }

    @Override
    public List<Ship> getAllShipsByType(ShipRules shipType) {
        List<Ship> ships = this.ships.stream().filter(ship -> ship.toString().equals(shipType.toString())).filter(ship -> !ship.isPlaced()).collect(Collectors.toList());

        return Collections.unmodifiableList(ships);
    }

    @Override
    public Optional<Ship> getNextShipByType(ShipRules shipType) {
        Optional<Ship> ship = Optional.empty();

        if (!getAllShipsByType(shipType).isEmpty()) {
            ship = Optional.of(getAllShipsByType(shipType).get(0));
        }

        return ship;
    }

    @Override
    public Optional<Ship> getNextNonPlacedShip() {
        Optional<Ship> ship = Optional.empty();

        if (!getAllNonPlacedShips().isEmpty()) {
            ship = Optional.of(getAllNonPlacedShips().get(0));
        }

        return ship;
    }

    @Override
    public void addShip(Ship s) {
        this.ships.add(s);
    }

    @Override
    public void resetFleetPlacement() {
        this.getAllShips().forEach(Ship::resetPlacement);
    }

    @Override
    public boolean isSunk() {
        for (Ship ship : this.ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isReady() {
        for (final Ship s : this.ships ) {
            if (!s.isPlaced()) {
                return false;
            }
        }
        return true;
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
