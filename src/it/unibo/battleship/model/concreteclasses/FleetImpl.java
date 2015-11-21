package it.unibo.battleship.model.concreteclasses;

import java.util.ArrayList;
import java.util.List;

import it.unibo.battleship.model.common.Point2d;
import it.unibo.battleship.model.interfaces.Fleet;
import it.unibo.battleship.model.interfaces.Ship;

public class FleetImpl implements Fleet {

    private boolean placed;
    private boolean sunk;
    private List<Ship> ships;

    public FleetImpl() {
        this.placed = false;
        this.sunk = false;
        this.ships = new ArrayList<>(); // La creazione potrebbe esser delegata ad un metodo astratto
        // Flotta astratta -> metodo protetto. Implementazione riguardo all'interfaccia List
    }

    public boolean tryHit(final Point2d point) {
        if (point == null) {
            return false; // THROW EXCEPTION
            // SARA' DA CAMBIARE NELL'INTERFACCIA -> THROWS EXCEPTION
        }
        for (final Ship s : this.ships) {
            if (s.tryHit(point)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPlaced() {
        return this.placed;
    }

    public boolean isSunk() {
        //return this.sunk;
        
        for (final Ship s : ships) {
            if (!s.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public boolean addShip(final Ship ship) {
        this.ships.add(ship);
        return true;
    }

    public List<Ship> getShips() {
        //  WARNING : RESTITUIRE COPIA DELLE NAVI
        return ships;
    }

}
