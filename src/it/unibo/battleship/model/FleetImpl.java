package it.unibo.battleship.model;

import java.util.ArrayList;
import java.util.List;

import it.unibo.battleship.common.Point2d;

public class FleetImpl implements Fleet {

    private boolean isPlaced;
    private boolean isSank;
    private List<Ship> ships;
    
    public FleetImpl() {
        this.isPlaced = false;
        this.isSank = false;
        this.ships = new ArrayList<>(); // La creazione potrebbe esser delegata ad un metodo astratto
        // Flotta astratta -> metodo protetto. Implementazione riguardo all'interfaccia List
    }
    
    public boolean tryHit(Point2d point) {
        return false;
    }

    public boolean isPlaced() {
        return this.isPlaced;
    }

    public boolean isSank() {
        return this.isSank;
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
