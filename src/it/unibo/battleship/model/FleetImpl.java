package it.unibo.battleship.model;

import java.util.ArrayList;
import java.util.List;

import it.unibo.battleship.common.Point2d;

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

    public boolean tryHit(Point2d point) {
        boolean retVal = false;
        if (point == null) {
            return false; // THROW EXCEPTION
            // SARA' DA CAMBIARE NELL'INTERFACCIA -> THROWS EXCEPTION
        }
        for (Ship s : this.ships) {
            if (s.tryHit(point)) {
                retVal = true;
                break;
            }
        }
        return retVal;
    }

    public boolean isPlaced() {
        return this.placed;
    }

    public boolean isSunk() {
        return this.sunk;
    }

    public boolean addShip(Ship ship) {
        this.ships.add(ship);
        return true;
    }

    public List<Ship> getShips() {
        //  WARNING : RESTITUIRE COPIA DELLE NAVI
        return ships;
    }

}
