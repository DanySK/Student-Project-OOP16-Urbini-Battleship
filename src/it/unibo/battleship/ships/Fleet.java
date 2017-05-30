package it.unibo.battleship.ships;

import it.unibo.battleship.common.GlobalProperties;
import it.unibo.battleship.common.GlobalProperties.ShipRules;
import it.unibo.battleship.common.Ruleset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents a fleet in the battlefield.
 * A fleet is composed of a number of ships.
 * @author fabio
 *
 */
public class Fleet {
    private final List<AbstractShip> ships;

    // COSTRUTTORE PROTETTO PER IL MOMENTO? -> LA FLOTTA VIENE CREATA SOLO CON RULESET
    public Fleet() {
        this.ships = new ArrayList<>();
    }

    public List<AbstractShip> getAllShips() {
        // MODIFICARE
        // Usare la copia non modificabile, andare a rivedere
        //return this.ships;
    	return Collections.unmodifiableList(this.ships);
    }

    public List<AbstractShip> getAllNonPlacedShips() {
        // Metodo da rivedere per il momento
        List<AbstractShip> nonPlacedShips = new ArrayList<>();

        // ogni nave non piazzata viene aggiunta alla lista
        for (final AbstractShip ship : this.getAllShips()) {
            if (!ship.isPlaced()) {
                nonPlacedShips.add(ship);
            }
        }

        return Collections.unmodifiableList(nonPlacedShips);
    }

    // Ricevere una CLASSE potrebbe NON essere il modo corretto, RIGUARDARE
    public List<AbstractShip> getAllShipsByType(ShipRules tipoNave) {
        List<AbstractShip> ships = new ArrayList<AbstractShip>();

        for (AbstractShip ship : this.ships) {
            if ( ship.toString().equals(tipoNave.toString()) ) {
                if ( !ship.isPlaced()) {
                    ships.add(ship);
                }
            }
        }

        return Collections.unmodifiableList(ships);
    }

    // Ricevere una CLASSE potrebbe NON essere il modo corretto, RIGUARDARE
    // TODO : ricontrollare
    public Optional<AbstractShip> getNextShipByType(ShipRules tipoNave) {
        Optional<AbstractShip> ship = Optional.empty();

        if (!getAllShipsByType(tipoNave).isEmpty()) {
            ship = Optional.of(getAllShipsByType(tipoNave).get(0)); // PRESO IL PRIMO ELEMENTO
        }

        return ship;
    }

    // RESTITUISCE la prossima nave NON PIAZZATA. Non la toglie dalla collezione
    public Optional<AbstractShip> getNextNonPlacedShip() {
        Optional<AbstractShip> ship = Optional.empty();

        if (!getAllNonPlacedShips().isEmpty()) {
            ship = Optional.of(getAllNonPlacedShips().get(0)); // PRESO IL PRIMO ELEMENTO
        }

        return ship;
    }

    public void addShip(AbstractShip s) {
        this.ships.add(s);
    }

    public void resetFleetPlacement () {
        for (AbstractShip ship : this.getAllShips()) {
            ship.resetPlacement();
        }
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
    
    public static Fleet getNewFleet(){
        Fleet fleet = new Fleet();
        
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
    
    private static void createShips(final Fleet fleet, final int ShipsNumber) {
    	
    }
}
